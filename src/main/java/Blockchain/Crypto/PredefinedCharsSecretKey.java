/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Blockchain.Crypto;

import java.nio.charset.Charset;
import java.security.Key;
import java.util.Arrays;
import java.util.Random;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author JUN WEI
 */
public class PredefinedCharsSecretKey {
    /*handle the secret chars with care! */
    private static final String SECRET_CHARS = "escrowCompany123"; 
    
    public static void generateSecretChars(){
        byte[] array = new byte[10]; 
        new Random().nextBytes(array);
        String generatedString = new String(array, Charset.forName("UTF-8"));

        System.out.println(generatedString);
    }
    
    public static Key create(){
        int keySize = 16;
        return new SecretKeySpec(Arrays.copyOf(SECRET_CHARS.getBytes(),keySize),"AES");
    }
}
