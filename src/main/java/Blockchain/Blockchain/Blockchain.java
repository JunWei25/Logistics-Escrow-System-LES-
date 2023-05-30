/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Blockchain.Blockchain;

/**
 *
 * @author JUN WEI
 */
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import com.google.gson.GsonBuilder;

public class Blockchain {
    private static String masterFolder = "master/";
    private static String fileName = masterFolder+"Chain.bin";
    private static String ledger = masterFolder+"Ledger.txt";
    
    // data structure    
    private static LinkedList<Block> db = new LinkedList<>();
    /**     * singleton pattern     */    
    private static Blockchain _instance;
    public static Blockchain getInstance( String chainFile, String ledgerFile ) {
        if(_instance == null)
            _instance = new Blockchain( chainFile, ledgerFile );
        return _instance;
    }
    public String chainFile;
    public String ledgerFile;
    public Blockchain(String chainFile, String ledgerFile) {
        super();
        this.chainFile = chainFile;
        this.ledgerFile = ledgerFile;
        System.out.println( "> Blockchain object is created!" );
    }
    /**     * genesis()     */    
    public void genesis()
    {
        Block genesis = new Block("0", "0",0);
        db.add(genesis);
        persist();
    }
    /**     * nextBlock()     */    
    public void nextBlock(Block newBlock)
    {
        db = get();
        db.add(newBlock);
        persist();
    }
    /**     * get()     */    
    public LinkedList<Block> get()
    {
        try( FileInputStream fin = new FileInputStream( this.chainFile ); 
             ObjectInputStream in = new ObjectInputStream( fin );
            ) {
            return (LinkedList<Block>)in.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    /**     * persist()     */    
    private void persist()
    {
        try( FileOutputStream fout = new FileOutputStream( this.chainFile );
             ObjectOutputStream out = new ObjectOutputStream( fout );
            ) {
            out.writeObject(db);
            System.out.println( ">> Master file is updated!" );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**     * distribute()     */
    public void distribute(){
        String chain = new GsonBuilder().setPrettyPrinting().create().toJson(db);
        try( FileOutputStream fout = new FileOutputStream( this.ledger );
             ObjectOutputStream out = new ObjectOutputStream( fout );
            ) {
            out.writeObject(chain);
            System.out.println( ">> Master file (Json) is updated!" );
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(chain);
    }

}