package com.ma.comics.utils;
import java.io.*;
import javax.crypto.*;
import javax.crypto.spec.*;
import java.security.*;

public class GenerateSignature
{
    public static final String TAG;
    protected static final char[] hexArray;
    String signature;
    String url;

    static {
        TAG = GenerateSignature.class.getSimpleName();
        hexArray = "0123456789abcdef".toCharArray();
    }

    public static String bytesToHex(final byte[] array) {
        final char[] array2 = new char[array.length * 2];
        for (int i = 0; i < array.length; ++i) {
            final int n = array[i] & 0xFF;
            array2[i * 2] = GenerateSignature.hexArray[n >>> 4];
            array2[i * 2 + 1] = GenerateSignature.hexArray[n & 0xF];
        }
        return new String(array2);
    }

    public String getSignature(String s, final String s2) {
        synchronized (this) {
            try {
                final byte[] bytes = s2.getBytes("UTF-8");
                s = s.toLowerCase();
                final String tag = GenerateSignature.TAG;
//                StringBuffer builder = new StringBuffer();
//                builder.append("RAW SIGNATURE = ").append(s);

                this.signature = this.hashToString(s, bytes);
                s = this.signature;
                return s;
            }
            catch (UnsupportedEncodingException ex) {
                ex.printStackTrace();

            }
        }
        return  null;
    }

    protected String hashToString(String bytesToHex, final byte[] array) {
        final String s = null;
        try {
            final Mac instance = Mac.getInstance("HmacSHA256");
            instance.init(new SecretKeySpec(array, "HmacSHA256"));
            bytesToHex = bytesToHex(instance.doFinal(bytesToHex.getBytes("UTF-8")));
            return bytesToHex;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            bytesToHex = s;
            return bytesToHex;
        }
    }
}