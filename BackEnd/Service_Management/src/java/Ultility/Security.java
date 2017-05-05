package Ultility;

import Config.ApplicationConfig;
import java.io.*;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.*;
import java.util.Arrays;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;


public class Security {
    private static final String KEY_CODE = "&$@&$^OEWTMO$IFR&$OIFO#$JFK4MRFI*$UT";
    
    public static String Encrypt(String str) throws UnsupportedEncodingException{
        byte[] a = KEY_CODE.getBytes("UTF-8");
        byte[] b = str.getBytes("UTF-8");
        for (int i = 0; i < a.length; i++) {
            if (i < b.length)
                a[i] += b[i];
        }
        return new String(a, Charset.forName("UTF-8"));
    }
    
    public static String Decrypt(String hash) throws UnsupportedEncodingException{
        byte[] a = hash.getBytes("UTF-8");
        byte[] b = KEY_CODE.getBytes("UTF-8");
        for (int i = 0; i < a.length; i++) {
            if (i < b.length)
                a[i] -= b[i];
        }
        return new String(a, Charset.forName("UTF-8"));
    }
}
