/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import Blockchain.Blockchain.Block;
import Blockchain.Blockchain.Blockchain;
import Blockchain.Blockchain.MerkleTree;
import Blockchain.Blockchain.Transaction;
import Blockchain.Hashing.*;
import Blockchain.Crypto.SymmetricEncryption;
import Blockchain.Crypto.PredefinedCharsSecretKey;
import Blockchain.Hashing.Salt;
import Class.Parcel;
import Class.User;
import UserInterface.MakePaymentGUI;
import UserInterface.ManagePackageGUI;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.Key;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JTextField;

/**
 *
 * @author JUN WEI
 */
public class Utils {
    private static String databaseFolder = "database/";
    private static String escrowAgreementFile = databaseFolder+"EscrowAgreement.txt";
    
    private static String secretFolder = "secret/";
    static List<String> result = new ArrayList();
    public static boolean inputFieldsFilled(String... inputs) {
        for (String input : inputs) {
            if (input.isBlank()) {
                return false;
            }
        }
        return true;
    }

    public static boolean textFieldsFilled(JTextField... textFields) {
        for (JTextField textField : textFields) {
            if (textField == null || textField.getText().trim().isEmpty()) {
                return false;
            }
        }
        return true;
    }
    
    public static User login(User user) throws Exception {
        String username="";
        String firstName="";
        String lastName="";
        String passport="";
        String role = "";
        String line = "";
        try {
            File myObj = new File("database/User.txt");
            Scanner readFile = new Scanner(myObj);
            while (readFile.hasNextLine()) {
                line = readFile.nextLine().toString();
                if(line.contains(user.getUsername())){
                    result.add(line);
                    break;
                }
            }
            readFile.close();
        }catch (FileNotFoundException e) {
                e.printStackTrace();
       }
       if(result.size()==0){
           throw new Exception("User not found!");
       }else{
            String[] column;
            for(int i=0; i<result.size(); i++){
                column = result.get(i).split("!");
                String password = column[4];
                String salt = retrieveSaltValue();
                String enteredPassword = Hasher.sha256_salted(user.getPassword(),Base64.getDecoder().decode(salt));
                if (!enteredPassword.equals(password)) {
                    throw new Exception("Incorrect password!");
                }
                username = column[2];
                firstName = column[0];
                lastName = column[1];
                role = column[5];
                user.setRole(role);
                break;
            }
        }
        result.clear();
        return new User(username, firstName, lastName, role);
    }
   
    public static void register(User newUser) throws Exception {
        String line="";
        List<String> passportNumber = new ArrayList();
        try {
                File myObj = new File("database/User.txt");
                Scanner readFile = new Scanner(myObj);
                while (readFile.hasNextLine()) {
                    line = readFile.nextLine().toString();
                    if(line.contains(newUser.getUsername())){
                        result.add(line);
                        break;
                    }
                    if(line.contains(newUser.getPassportNumber())){
                        passportNumber.add(line);
                        break;
                    }
                }
        }catch (FileNotFoundException e) {
                e.printStackTrace();
       }
        if(result.size()!=0){
            throw new Exception("Username "+newUser.getUsername()+" already exist! Please try again.");
        }
        else if(result.size()!=0){
            throw new Exception("User with "+newUser.getPassportNumber()+" already exist! Please try again.");
        }
        else{
            String salt = retrieveSaltValue();
            try {
                FileWriter myWriter = new FileWriter("database/User.txt",true);
                myWriter.write("\n"+newUser.getFirstName()+"!"+newUser.getLastName()+"!"+newUser.getPassportNumber()+"!"+newUser.getUsername()+"!"+Hasher.sha256_salted(newUser.getPassword(),Base64.getDecoder().decode(salt))+"!"+"User");
                myWriter.close();
                System.out.println("Successfully added user to 'user' file.");
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        
        }
        result.clear();
        passportNumber.clear();
    }
    
    public static String createParcel(Parcel parcel,User currentUser){
        SymmetricEncryption symm = new SymmetricEncryption();
        Key secretKey = PredefinedCharsSecretKey.create();
        byte[] secret = secretKey.getEncoded();
        String generatedKey = Base64.getEncoder().encodeToString(secret);
        String senderName="";
        String senderContact="";
        String senderAddress="";
        String receipientName="";
        String receipientContact="";
        String receipientAddress="";
        String parcelWeight="";
        String deliveryType="";
        String packagePayment="";
        String packagePaymentStatus="";
        String serviceFee="";
        String servicePaymentStatus="";
        String approvalStatus="";
        String packageDeliveryStatus="";
        
        try {
            senderName = symm.encrypt(parcel.getSenderName(), secretKey);
            senderContact = symm.encrypt(parcel.getSenderContact(),secretKey);
            senderAddress = symm.encrypt(parcel.getSenderAddress(), secretKey);
            receipientName = symm.encrypt(parcel.getRecipientName(), secretKey);
            receipientContact = symm.encrypt(parcel.getRecipientContact(), secretKey);
            receipientAddress = symm.encrypt(parcel.getRecipientAddress(), secretKey);
            parcelWeight = symm.encrypt(parcel.getParcelWeight(),secretKey);
            deliveryType = symm.encrypt(parcel.getDeliveryType(), secretKey);
            packagePayment = symm.encrypt(Double.toString(parcel.getEscrowAmount()),secretKey);
            packagePaymentStatus = symm.encrypt("Not Paid",secretKey);
            serviceFee = symm.encrypt(Double.toString(parcel.getServiceFee()),secretKey);
            servicePaymentStatus = symm.encrypt("Not Paid", secretKey);
            approvalStatus = symm.encrypt("Processing",secretKey);
            packageDeliveryStatus = "Escrow Agreement Process";
        } catch (Exception ex) {
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String line = "";
        String packageNo="";
        int lastPackageNo=0;
        String fileLastLine = "";
        BufferedReader br;
        String sCurrentLine;
        try {
            File myObj = new File("database/Parcel.txt");
            if(myObj.exists()==false||myObj.length()==0){
                packageNo = "0000000001";
            }
            else{
                br = new BufferedReader(new FileReader("database/Parcel.txt"));
                while ((sCurrentLine = br.readLine()) != null) 
                {
                    fileLastLine = sCurrentLine;
                }
                String[] rowData = fileLastLine.split("!");
                lastPackageNo=Integer.parseInt(rowData[0]);
                int newPackageNo = lastPackageNo+1;
                packageNo = Integer.toString(newPackageNo);
                for(int i=0; i<9; i++){
                    packageNo = "0"+packageNo;
                }
            }
        }catch (Exception e) {
                e.printStackTrace();
       }
        try {
                FileWriter myWriter = new FileWriter("database/Parcel.txt",true);
                BufferedWriter out = new BufferedWriter(myWriter);
                myWriter.write(packageNo+"!"+senderName+"!"+senderContact+"!"+senderAddress+"!"+receipientName+"!"+receipientContact+"!"+receipientAddress+"!"+parcelWeight+"!"+deliveryType+"!"+packagePayment+"!"+packagePaymentStatus+"!"+serviceFee+"!"+servicePaymentStatus+"!"+approvalStatus+"!"+packageDeliveryStatus+"!"+generatedKey+"\n");
                out.newLine();
                myWriter.close();
                System.out.println("Successfully wrote to package file.");
                new MakePaymentGUI(currentUser,String.valueOf(parcel.getServiceFee()),"Escrow Company",packageNo,"Service Fee").setVisible(true);
                createEscrowAgreement(parcel,packageNo);
//            String parcelInformation = packageNo+"!"+senderName+"!"+senderContact+"!"+senderAddress+"!"+receipientName+"!"+receipientContact+"!"+receipientAddress; 
            
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        
        try{
            FileWriter secretWriter = new FileWriter("secret/SecretKey.txt",true);
            BufferedWriter out = new BufferedWriter(secretWriter);
            secretWriter.write(packageNo+"!"+generatedKey+"\n");
            out.newLine();
            secretWriter.close();
        }catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return generatedKey;
    }
    
    public static void createEscrowAgreement(Parcel parcel, String packageNo){
        try {     
                FileWriter escrowWriter = new FileWriter(escrowAgreementFile,true);
                BufferedWriter escrowOut = new BufferedWriter(escrowWriter);
                escrowWriter.write(packageNo+"!"+parcel.getSenderName()+"!"+"Not Agreed"+"!"+parcel.getRecipientName()+"!"+"Not Agreed"+"!"+"Escrow Company"+"!"+"Not Witnessed"+"!"+"All Parties Have Not Agreed"+"!"+parcel.getEscrowAmount()+"!"+java.time.LocalDate.now().toString());
                escrowWriter.close();
//            String parcelInformation = packageNo+"!"+senderName+"!"+senderContact+"!"+senderAddress+"!"+receipientName+"!"+receipientContact+"!"+receipientAddress; 
            
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    
    }
    
    public static boolean updatePackageStatus(Parcel parcel, String status) throws IOException{
        boolean updateStatus = false;
        String filePath = "package.txt";
        String line = "";
        try {
                File myObj = new File("database/Parcel.txt");
                Scanner readFile = new Scanner(myObj);
                while (readFile.hasNextLine()) {
                    line = readFile.nextLine().toString();
                    if(line.contains(parcel.getPackageNo())){
                        result.add(line);
                        break;
                    };
                }
                readFile.close();
                if(result.size()!=0){
                    String[] column;
                    for(int i=0; i<result.size(); i++){
                        column = result.get(i).split("!");
                        column[11] = status;
                        String concatenatedString = "";
                        String delimiter = "!";
                        for (String word : column) {
                            System.out.println("concat");
                            concatenatedString += concatenatedString.equals("") ? word : delimiter + word;
                        }
                        Scanner updateFile = new Scanner(myObj);
                        StringBuffer buffer = new StringBuffer();
                        while (updateFile.hasNextLine()) {
                            buffer.append(updateFile.nextLine()+System.lineSeparator());
                        }
                        String fileContents = buffer.toString();
                        
                        System.out.println(concatenatedString);
                        String replaceLine = fileContents.replaceAll(line, concatenatedString);
                        
                        FileWriter writer = new FileWriter(filePath);
                        writer.append(replaceLine);
                                
                        updateStatus = true;
                        updateFile.close();
                        writer.close();
                        break;    
                    }
                    }
                 result.clear();   
                
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        return updateStatus;
    }
    
    public static boolean updateDeliveryStatus(Parcel parcel, String deliveryStatus) throws IOException{
        boolean updateStatus = false;
        String filePath = "package.txt";
        String line = "";
        try {
                File myObj = new File("database/Parcel.txt");
                Scanner readFile = new Scanner(myObj);
                while (readFile.hasNextLine()) {
                    line = readFile.nextLine().toString();
                    if(line.contains(parcel.getPackageNo())){
                        result.add(line);
                        break;
                    };
                }
                readFile.close();
                if(result.size()!=0){
                    String[] column;
                    for(int i=0; i<result.size(); i++){
                        column = result.get(i).split("!");
                        column[14] = deliveryStatus;
                        String concatenatedString = "";
                        String delimiter = "!";
                        for (String word : column) {
                            concatenatedString += concatenatedString.equals("") ? word : delimiter + word;
                        }
                        Scanner updateFile = new Scanner(myObj);
                        StringBuffer buffer = new StringBuffer();
                        while (updateFile.hasNextLine()) {
                            buffer.append(updateFile.nextLine()+System.lineSeparator());
                        }
                        String fileContents = buffer.toString();
                        
                        System.out.println(concatenatedString);
                        
                        String replaceLine = fileContents.replace(line, concatenatedString);
                        System.out.println(replaceLine);
                        FileWriter writer = new FileWriter("database/Parcel.txt");
                        writer.append(replaceLine);
                                
                        updateStatus = true;
                        updateFile.close();
                        writer.close();
                        break;    
                    }
                    }
                 result.clear();   
                
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        return updateStatus;
    }
    
    public static String retrieveSaltValue(){
        String salt="";
        String line = "";
        try{
            File file = new File("salt.txt");
            Scanner readFile;
            readFile = new Scanner(file);
            while (readFile.hasNextLine()) {
                line = readFile.nextLine().toString();
                    result.add(line);
                    break;
            }
            for(int i=0; i<result.size(); i++){
                String[] column;
                column = result.get(i).split("!");
                salt = column[0];
            }
        }catch (FileNotFoundException ex) {
            Logger.getLogger(Salt.class.getName()).log(Level.SEVERE, null, ex);
        }
        return salt;
    }
    
    public static void updateEscrowAgreementStatus(String parcelNumber){
        String databaseFolder = "database/";
        String escrowAgreementFile = databaseFolder+"EscrowAgreement.txt";
        String parcelNo = parcelNumber;
        String line = "";
        String replaceLine = "";
        boolean senderStatus = false; 
        boolean recipientStatus = false; 
        boolean witnessStatus = false;
        List<String> checkResult = new ArrayList();
        List<String> packageResult = new ArrayList();
            try {
                
                File myObj = new File("database/EscrowAgreement.txt");
                Scanner readFile = new Scanner(myObj);
                while (readFile.hasNextLine()) {
                    line = readFile.nextLine().toString();
                    checkResult.add(line);
                    
                }
                if(checkResult.size()>0){
                for(int i=0; i<checkResult.size();i++){
                        String getRow = checkResult.get(i);
                        String[] row = getRow.split("!");
                        String packageNo = row[0];
                        if(packageNo.equals(parcelNo)){
                            
                            result.add(getRow);
                            replaceLine = checkResult.get(i);
                        };
                    }
                readFile.close();
                for(int i=0; i<result.size();i++){
                    String getColumn = result.get(i);
                    String[] column = getColumn.split("!");
                    if(column.length>1){
                    String packageNo = column[0];
                    String sender = column[2];
                    String receiver = column[4];
                    String witness = column[6];
                    if(sender.equals("Agreed")){
                        senderStatus = true;
                    }
                    if(receiver.equals("Agreed")){
                        recipientStatus = true;
                    }
                    if(witness.equals("Agreed")){
                        witnessStatus = true;
                    }
                    }
            }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        if(senderStatus==true&&recipientStatus==true&&witnessStatus==true){
            if(result.size()!=0){
                String[] column;
                for(int i=0; i<result.size(); i++){
                    column = result.get(i).split("!");
                    column[7] = "All parties have agreed"; 
                    String concatenatedString = "";
                    String delimiter = "!";
                    for (String word : column) {
                        concatenatedString += concatenatedString.equals("") ? word : delimiter + word;
                    }

                    try {
                        File escrowFile = new File("database/EscrowAgreement.txt");
                        Scanner readEscrowFile = new Scanner(escrowFile);
                        StringBuffer buffer = new StringBuffer();
                        while (readEscrowFile.hasNextLine()) {
                            buffer.append(readEscrowFile.nextLine()+System.lineSeparator());
                        }   
                            String fileContents = buffer.toString();
                            String updateLine = fileContents.replaceAll(replaceLine, concatenatedString);
                            FileWriter writer;

                        FileWriter myWriter = new FileWriter("database/EscrowAgreement.txt");
                        myWriter.write(updateLine);
                        myWriter.close();
                        System.out.println("Successfully updated escrow agreement status");


                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                
                try {
                    String replacePackageLine = "";
                    File myObj = new File("database/Parcel.txt");
                    Scanner readFile = new Scanner(myObj);
                    while (readFile.hasNextLine()) {
                        line = readFile.nextLine().toString();
                        if(line.contains(parcelNumber)){
                            packageResult.add(line);
                            replacePackageLine = line;
                        }
                    }
                    if(packageResult.size()!=0){
                        String[] packageRow;
                        for(int i=0; i<packageResult.size(); i++){
                            column = packageResult.get(i).split("!");
                            System.out.println(column[14]);
                            column[14] = "Escrow Agreement Process Complete"; 
                            String concatenatedString = "";
                            String delimiter = "!";
                            for (String word : column) {
                                concatenatedString += concatenatedString.equals("") ? word : delimiter + word;
                            }
                 
                                File escrowFile = new File("database/Parcel.txt");
                                Scanner readEscrowFile = new Scanner(escrowFile);
                                StringBuffer buffer = new StringBuffer();
                                while (readEscrowFile.hasNextLine()) {
                                    buffer.append(readEscrowFile.nextLine()+System.lineSeparator());
                                }   
                                    String fileContents = buffer.toString();
                                    String updateParcelFile = fileContents.replace(replacePackageLine, concatenatedString);
                                    FileWriter writer;
                                System.out.println(concatenatedString);
                                System.out.println("---------------------------------------------");
                                System.out.println(replacePackageLine);
                                System.out.println(updateParcelFile);
                                FileWriter myWriter = new FileWriter("database/Parcel.txt");
                                myWriter.write(updateParcelFile);
                                myWriter.close();
                                System.out.println("Successfully updated escrow agreement status");
                        }
                    }
                }catch(IOException e) {
                    e.printStackTrace();
                }
            }
        }
        
        result.clear();
    
    }
    
    public static void insertPaymentDetails(String packageNo, String paymentPurpose, String payeeName, String selectedPaymentMethod, String paymentAmount){
        try {
            // TODO add your handling code here:
            FileWriter myWriter = new FileWriter("database/Payment.txt",true);
            myWriter.write(packageNo+"!"+paymentPurpose+"!"+payeeName+"!"+selectedPaymentMethod+"!"+paymentAmount+"!"+java.time.LocalDateTime.now().toString()+"\n");
            myWriter.close();
        } catch (IOException ex) {
            Logger.getLogger(MakePaymentGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void createDatabaseDirectory(){
        if(!new File(databaseFolder).exists()){
            new File(databaseFolder).mkdir();
        }
    }
    
    public static void createSecretDirectory(){
        if(!new File(secretFolder).exists()){
            new File(secretFolder).mkdir();
        }
    }
    
    public static void createBlockchainFile(){
        String masterFolder = "master/";
        String fileName = masterFolder+"Chain.bin";
        String merkleeTreeFile = masterFolder+"Ledger.txt";
    
        Blockchain bc = Blockchain.getInstance(fileName, merkleeTreeFile);
        if(!new File(masterFolder).exists()){
            System.err.println(">creating Blockchain binary!");
            new File(masterFolder).mkdir();
            bc.genesis();
        }
        bc.distribute();
    }
    
    public static void addNewBlock(String parcelNo){
        List<String> gatherParcelTransactions = new ArrayList();
        String line;
        String masterFolder = "master/";
        String fileName = masterFolder+"chain.bin";
        String ledgerFile = masterFolder+"Ledger.txt";
        
        try {
            String replacePackageLine = "";
            File parcelFile = new File("database/Parcel.txt");
            Scanner readFile;
        
            readFile = new Scanner(parcelFile);
            while (readFile.hasNextLine()) {
            line = readFile.nextLine().toString();
            if(line.contains(parcelNo)){
                System.out.println(line);
                gatherParcelTransactions.add(decryptNFormatParcelData(line));
                            
                }
            }
            readFile.close();
            
            
            File escrowAgreementFile = new File("database/EscrowAgreement.txt");
            Scanner readEscrowFile;
        
            readEscrowFile = new Scanner(escrowAgreementFile);
            while ( readEscrowFile.hasNextLine()) {
            line =  readEscrowFile.nextLine().toString();
            if(line.contains(parcelNo)){
                gatherParcelTransactions.add(line);
                            
                        }
            }
            readEscrowFile.close();
            
            File paymentFile = new File("database/Payment.txt");
            Scanner readPaymentFile;
        
            readPaymentFile = new Scanner(paymentFile);
            while ( readPaymentFile.hasNextLine()) {
            line =  readPaymentFile.nextLine().toString();
            if(line.contains(parcelNo)){
                gatherParcelTransactions.add(line);
                            
                        }
            }
            readPaymentFile.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        Blockchain bc = Blockchain.getInstance(fileName, ledgerFile);

            //String transactionBlockInformation = "dasdasdas"+"!"+"dasdasdasds"+"!";
            
            LinkedList<Block> db=bc.get();
            String previousHash = db.getLast().getBlockHeader().getCurrentHash();
            int previousIndex = db.getLast().getBlockHeader().getIndex();
            
            Block b = new Block(previousHash,"0",previousIndex+1);
            Transaction newTransaction = new Transaction();
            for(int i=0; i<gatherParcelTransactions.size();i++){
                
                newTransaction.add(gatherParcelTransactions.get(i));
            }
            b.setTranxLst(newTransaction);
            MerkleTree merkle= MerkleTree.getInstance(gatherParcelTransactions);
            merkle.build();
            String root = merkle.getRoot();
            b.getBlockHeader().setMerkleRoot(root);
            b.getTranxLst().setMerkleRoot(root);
            bc.nextBlock(b);
        
        bc.distribute();
    }
    
    public static String decryptNFormatParcelData(String line){
        SymmetricEncryption symm = new SymmetricEncryption();
        String formattedParcel = "";
        String[] parcelDataAttributes;
        parcelDataAttributes = line.split("!");
        String secretKey = parcelDataAttributes[15];
        byte[] decodeKey = Base64.getDecoder().decode(secretKey);
        Key decryptKey = new SecretKeySpec(decodeKey,0,decodeKey.length,"AES");
        try {
            String packageNo = parcelDataAttributes[0];
                
            String senderName = symm.decrypt(parcelDataAttributes[1],decryptKey);
            String senderContact = symm.decrypt(parcelDataAttributes[2],decryptKey);
            String senderAddress = symm.decrypt(parcelDataAttributes[3],decryptKey);
            String recipientName = symm.decrypt(parcelDataAttributes[4],decryptKey);
            String recipientContact = symm.decrypt(parcelDataAttributes[5],decryptKey);
            String recipientAddress = symm.decrypt(parcelDataAttributes[6],decryptKey);
            String parcelWeight = symm.decrypt(parcelDataAttributes[7],decryptKey);
            String deliveryType = symm.decrypt(parcelDataAttributes[8],decryptKey);
            String packagePayment = symm.decrypt(parcelDataAttributes[9],decryptKey);
            String serviceFee = symm.decrypt(parcelDataAttributes[11],decryptKey);
            String packageDeliveryStatus = parcelDataAttributes[14];
                    
            formattedParcel="\n--Parcel Details--"+
                            "\nSender Name: "+senderName+
                            "\nSender Contact: "+senderContact+
                            "\nSender Address: "+senderAddress+
                            "\nRecipient Name: "+recipientName+
                            "\nRecipient Name: "+recipientContact+
                            "\nRecipient Address: "+recipientAddress+
                            "\nParcel Weight: "+parcelWeight+
                            "\nDelivery Type: "+deliveryType+
                            "\nEscrow Fee: "+packagePayment+
                            "\nService Fee: "+serviceFee+
                            "\nStatus: "+packageDeliveryStatus;
                    
                } catch (Exception ex) {
                     ex.printStackTrace();
                }
        return formattedParcel;
    }
}
