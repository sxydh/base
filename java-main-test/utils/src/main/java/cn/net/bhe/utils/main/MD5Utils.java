package cn.net.bhe.utils.main;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public enum MD5Utils {
    ;
    static Logger LOGGER = LoggerFactory.getLogger(MD5Utils.class);

    public static String toLowerStr(String data) {
        byte[] bytes = null;
        try {
            bytes = toBytes(data);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        char[] hexArray = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
        char[] hexChars = new char[bytes.length * 2];
        int v;
        for (int j = 0; j < bytes.length; j++) {
            v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v / 16];
            hexChars[j * 2 + 1] = hexArray[v % 16];
        }
        return new String(hexChars);
    }

    public static String toUpperStr(String data) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        return toLowerStr(data).toUpperCase();
    }

    public static byte[] toBytes(String data) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        byte[] src = data.getBytes(StandardCharsets.UTF_8);
        md5.update(src);
        return md5.digest();
    }
    
    public static byte[] to16Bytes(String str) {
        return DigestUtils.md5(str);
    }
    
    public static byte[] to16Bytes(byte[] bytes) {
        return DigestUtils.md5(bytes);
    }
    
    public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String data = "123456";
        System.out.println(toLowerStr(data));
        System.out.println(DigestUtils.md5Hex(data));
    }
    
    /**
     * MD5 bytes to string
     * @param bytes
     * @return
     */
    public static String toHexString(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(0xFF & bytes[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
