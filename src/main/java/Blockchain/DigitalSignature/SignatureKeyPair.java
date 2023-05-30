/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Blockchain.DigitalSignature;

/**
 *
 * @author JUN WEI
 */
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.security.*;

public class SignatureKeyPair {
    private static final String ALGORITHM = "RSA";


    private KeyPairGenerator keygen;
    private KeyPair keyPair;
    private static PublicKey publicKey;
    private static PrivateKey privateKey;

    //get method for publicKey and privateKey
    public static PublicKey getPublicKey(){
        return publicKey;
    }

    public static PrivateKey getPrivateKey(){
        return privateKey;
    }

    public SignatureKeyPair(){
        try{
            keygen = KeyPairGenerator.getInstance(ALGORITHM);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void create(){
        SignatureKeyPair keyMaker = new SignatureKeyPair();
        //generate keypair
        keyMaker.keyPair = keyMaker.keygen.generateKeyPair();
        //get public key
        publicKey = keyMaker.keyPair.getPublic();
        //get private key
        privateKey = keyMaker.keyPair.getPrivate();
    }

    public static void put(byte[] keyBytes, String path){
        File f = new File(path);
        f.getParentFile().mkdirs();
        try{
            Files.write(Paths.get(path), keyBytes, StandardOpenOption.CREATE);
        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
