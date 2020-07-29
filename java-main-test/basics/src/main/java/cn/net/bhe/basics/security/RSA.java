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
                "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAMCCAObD4g6fiNCakL7HQat2kth22s8Z9m/JnhrGZalZPxBbQS1Ly0Yf/ppCW0Yi70TWB2zFkSuq9W13m7DrmoKcDpWc68Yfc1tT0zwGoW0wlJpxShZPaWdtJTukMfw9j5mCeIxmEFtYC03qQ34/FIcliYw6ZzDLjEo0DRs0cvbrAgMBAAECgYB7r0ta+QH1+URTGuvi12z2+GES2RJuu7SdUEHX3B6XNvMQ1EDFFIyPCqFvhTDVmD540LbGiQ1jrByx74ZFnqqPINMVe1CHmc270Ivq+J7fU75h209/iBMH1GYHvRb7r8gs6/TIAK5BaOg/nZakZXab5ndG0pvKN2rKcVeHYKRqyQJBAONJeCr7R9LltM9ypw7sIW3o7e0+fU9D3zMraLLNwQStGSsQHYX/RiNgIjAIsTypcV/x89bEOesb4kkH8aANzxUCQQDY08PuNZ1SbKCpHNzNeqzhdEJoZgXRirrqs++61WOUvQu5l3RCVQeRX+5ErRmmboF30zSYzmxovzyfk6Se3y3/AkAPDEczeU2JDgJJlcqvtzyDXV+bTH7ZZ6H4blBRPkQgPiGjcz6xJ3SKVQwOaBTr8j/6XTeKqZU4FrGFqkv6D8/9AkEArus3lwY9om7rtUIJhuK+cSsfH6WecArg+9KpRSo8qZNQ0Co0qgZ6kna3e0PB+5gObiI+vj9ZCa+tMncjSN929QJAPyQAVQkhYt9cMXMsYaNOtz1heT79O/3hIVn52GGaUPJBsEjzFvdmri+jkvnb+snvxXhl+t5JnmzkZCbao+aWwQ==",
                StandardCharsets.UTF_8);
        boolean result = verify(
                content, 
                sign, 
                "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDAggDmw+IOn4jQmpC+x0GrdpLYdtrPGfZvyZ4axmWpWT8QW0EtS8tGH/6aQltGIu9E1gdsxZErqvVtd5uw65qCnA6VnOvGH3NbU9M8BqFtMJSacUoWT2lnbSU7pDH8PY+ZgniMZhBbWAtN6kN+PxSHJYmMOmcwy4xKNA0bNHL26wIDAQAB",
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
