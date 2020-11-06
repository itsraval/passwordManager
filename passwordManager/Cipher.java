package src.passwordManager;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class Cipher {
    private static String toHexString(byte[] hash) {
        // Convert byte array into signum representation
        BigInteger number = new BigInteger(1, hash);

        // Convert message digest into hex value
        StringBuilder hexString = new StringBuilder(number.toString(16));

        // Pad with leading zeros
        while (hexString.length() < 32) {
            hexString.insert(0, '0');
        }

        return hexString.toString();
    }

    public static String SHA256(String originalString) {
        try{
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] encodedhash = md.digest(originalString.getBytes(StandardCharsets.UTF_8));
            return toHexString(encodedhash);
        }catch (NoSuchAlgorithmException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return null;
    }
}
