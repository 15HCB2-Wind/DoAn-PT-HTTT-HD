package Ultility;

import java.io.*;
import java.nio.charset.Charset;

public class Security {
    private static final String KEY_CODE = "&$@&$^OEWTMO$IFR&$OIFO#$JFK4MRFI*$UT";
    
    public static String Encrypt(String str){
        try {
            byte[] a = KEY_CODE.getBytes("UTF-8");
            byte[] b = str.getBytes("UTF-8");
            for (int i = 0; i < a.length; i++) {
            if (i < b.length)
                a[i] += b[i];
            }
            return new String(a, Charset.forName("UTF-8"));
        } catch (UnsupportedEncodingException ex) { }
        return null;
    }
    
    public static String Decrypt(String hash){
        try {
            byte[] a = hash.getBytes("UTF-8");
            byte[] b = KEY_CODE.getBytes("UTF-8");
            for (int i = 0; i < a.length; i++) {
                if (i < b.length)
                    a[i] -= b[i];
            }
            return new String(a, Charset.forName("UTF-8"));
        } catch (UnsupportedEncodingException ex) { }
        return null;
    }
}
