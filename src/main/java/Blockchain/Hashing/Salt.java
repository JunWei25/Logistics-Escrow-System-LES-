/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Blockchain.Hashing;

/**
 *
 * @author JUN WEI
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.Base64;

import java.security.SecureRandom;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Salt {
    static List<String> result = new ArrayList();
    private static final SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd");
    static Date date = new Date();
    static Timestamp timestamp = new Timestamp(date.getTime());
    static String currentDate = sdf3.format(timestamp);
    //+generate(): byte[]
    public static byte[] generate(){
        String line = "";
        String existingSalt="";
        String existingSaltDate="";
        int size = 16;
        byte[] b = new byte[size];
        File file = new File("salt.txt");
        SecureRandom sr = new SecureRandom();
        sr.nextBytes(b);
        if(file.exists()==false||file.length()==0){
            try {
                FileWriter myWriter = new FileWriter("salt.txt",true);
                myWriter.write(Base64.getEncoder().encodeToString(b)+"!"+currentDate);
                myWriter.close();
            } catch (IOException ex) {
                Logger.getLogger(Salt.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("Successfully generated salt");
        }
        else{
        try {
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
                existingSalt=column[0];
                existingSaltDate=column[1];
                LocalDate presentDate = LocalDate.parse(currentDate);
                LocalDate exisitingDate = LocalDate.parse(existingSaltDate);
                long noOfDaysBetween = ChronoUnit.DAYS.between(exisitingDate, presentDate);
                if(noOfDaysBetween>90){
                    try {
                        FileWriter myWriter = new FileWriter("salt.txt",true);
                        myWriter.write(Base64.getEncoder().encodeToString(b)+"!"+currentDate);
                        myWriter.close();
                    } catch (IOException ex) {
                        Logger.getLogger(Salt.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    System.out.println("Successfully updated salt value");
                }
                else{
                    System.out.println("Salt value already exist");
                }
            }
        
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Salt.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        return b;
    }
    
    
    public static void main(String[] args){
        System.out.println("Salt: "+ Base64.getEncoder().encodeToString(Salt.generate()));
    }
}
