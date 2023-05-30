/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Blockchain.Hashing;

/**
 *
 * @author JUN WEI
 */
import java.security.MessageDigest;
import java.util.Base64;
import org.apache.commons.codec.binary.Hex;
public class Hasher {
   
    public static String sha256_salted(String input, byte[] salt)
    {
        return hash( input, "SHA-256", salt );
    }
    
    public static String sha256(String input)
    {
        return hash( input, "SHA-256" );
    }
    
    /* sha384 */    /* sha512 */    /* -hash(String, String) : String */    
    private static String hash(String input, String algorithm)
    {
        String hashCode = "";
        try {
            MessageDigest md = MessageDigest.getInstance(algorithm);
            md.update( input.getBytes() );
            //digesting...            byte[] hashBytes = md.digest();
            byte[] hashBytes = md.digest();
            //convert the byte[] to String            //1)  
            hashCode = Base64.getEncoder().encodeToString(hashBytes);            //2) hex format output - recommended!//          External libary: https://commons.apache.org/proper/commons-codec/download_codec.cgi            hashCode = Hex.encodeHexString(hashBytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hashCode;
    }

    /* -hash(String, String, byte[]) : String */    
    private static String hash(String input, String algorithm, byte[] salt)
    {
        String hashCode = "";
        try {
            MessageDigest md = MessageDigest.getInstance(algorithm);
            md.update( input.getBytes() );
            md.update(salt);
            //digesting...            byte[] hashBytes = md.digest();
            byte[] hashBytes = md.digest();
            //convert the byte[] to String            //1)  
          hashCode = Base64.getEncoder().encodeToString(hashBytes);            //2) hex format output - recommended!//          External libary: https://commons.apache.org/proper/commons-codec/download_codec.cgi            hashCode = Hex.encodeHexString(hashBytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hashCode;
    }
}
