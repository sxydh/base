package cn.net.bhe.basics.security;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.junit.jupiter.api.Test;

public class RSA {
    
    public static final String SIGN_ALGORITHMS = "SHA1WithRSA";

    @Test
    public void test() {
        String content = "app_id=10000&phone=18561739342&req_no=7217572252fb7d4bdf91a66ece4ff8b3&sign_nonce=05d2d24b163b1c641f7f2c23184de7fe&timestamp=1595818225310";
        String sign = sign(
                content,
                "MIIBVAIBADANBgkqhkiG9w0BAQEFAASCAT4wggE6AgEAAkEA0ppFRkVGorknOoJjXgPsndqfmnvZyZh1fw4u+2d+pNeA0sFYJEScR77tz1XWD+4EjZacQT5brCRNkVUlK6jmOQIDAQABAkAT89u2UZ/kkMgfqfTCFnsXZXwThcJXcCNJm01mMiL0eO5DBmdl3ki82r8tItij9kVjYtSCrAe1jS4eRyfsijiBAiEA9QNdpYl6snvSnnHxjQIojbquX6AjucP3hD5snVaQLmkCIQDcC+NpiHRLN+1MXBzXmG7doCS56HOiIV29mHPsKJ+fUQIge44j9mt61XD9yGsbbYluG/LNJmniOjappiW4ritN8CkCICLVwvenAXoS7X8kKfC7kfmF66p+sSeejp+ALgHZ4IMxAiEA6wESXvpF+NHQt/D1WpTaXZhMMC+g/dPHpdu3en+UXgQ=",
                StandardCharsets.UTF_8);
        boolean result = verify(
                content, 
                sign, 
                "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBANKaRUZFRqK5JzqCY14D7J3an5p72cmYdX8OLvtnfqTXgNLBWCREnEe+7c9V1g/uBI2WnEE+W6wkTZFVJSuo5jkCAwEAAQ==",
                StandardCharsets.UTF_8);
        System.out.println(result);
    }

    public static String sign(String content, String pvtk, Charset charset) {
        try {
            PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(Base64.decodeBase64(pvtk));
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);

            Signature signature = Signature.getInstance(SIGN_ALGORITHMS);

            signature.initSign(privateKey);
            signature.update(content.getBytes(charset));

            byte[] signatureBytes = signature.sign();

            return new String(Base64.encodeBase64(signatureBytes));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static boolean verify(String content, String sign, String pubk, Charset charset) {
        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            byte[] decodedBytes = Base64.decodeBase64(pubk);
            PublicKey publicKey = keyFactory.generatePublic(new X509EncodedKeySpec(decodedBytes));

            Signature signature = Signature.getInstance(SIGN_ALGORITHMS);

            signature.initVerify(publicKey);
            signature.update(content.getBytes(charset));

            boolean result = signature.verify(Base64.decodeBase64(sign));
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
