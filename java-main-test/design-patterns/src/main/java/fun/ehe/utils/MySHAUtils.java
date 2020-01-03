package fun.ehe.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MySHAUtils {
    public static byte[] encrypt(String data) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("SHA");
        byte[] src = data.getBytes();
        md5.update(src);
        return md5.digest();
    }

    public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String data = "data that needs to be encrypted ";
        System.out.println("data: " + data);
        byte[] result = encrypt(data);
        System.out.println("result: " + new String(result, "utf-8"));
    }
}
