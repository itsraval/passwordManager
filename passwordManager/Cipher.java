package src.passwordManager;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * SHA256 one-way hash.
 */
public class Cipher {

    /**
     * Convert an array of byte  to String.
     * @param hash 
     * @return the string converted from the input
     */
    private static String toHexString(byte[] hash) {
        BigInteger number = new BigInteger(1, hash);
        StringBuilder hexString = new StringBuilder(number.toString(16));
        while (hexString.length() < 32) {
            hexString.insert(0, '0');
        }
        return hexString.toString();
    }

    /**
     * SHA256 one-way hash.
     * @param originalString string you want to hash.
     * @return hashed input string.
     */
    public static String SHA256(String originalString) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] encodedhash = md.digest(originalString.getBytes(StandardCharsets.UTF_8));
            return toHexString(encodedhash);
        } catch (NoSuchAlgorithmException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return null;
    }
}
