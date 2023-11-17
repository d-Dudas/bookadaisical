package com.bookadaisical.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hasher {
    private Hasher(){}

    public static String hash(String toHash)
    {
        try
        {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(toHash.getBytes());

            return bytesToHex(hash);
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
            return toHash;
        }
    }

    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
