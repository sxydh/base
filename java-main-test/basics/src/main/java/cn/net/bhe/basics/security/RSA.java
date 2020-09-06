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
                "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDAtqKhoJxa94sQMLT4AGBS+zXbrdMwbA/khsCbCAz1pWNsceCE5/+YeDntlW2FJh6yVGPqRhj5KgBkxQhWFlIJY+lC0JNv+yLCjVNe7cYznZv8xvOkNayikgfg9dYSLOTZi8DY0ej33IP1g0BgCVUX23M+CGeei0Kvcvzf5/xxJktK2jsTh7U7o3cR9NcjFs3KLGG0bIXlCI2TP73VgT6ZJyt+Aa/+Gv8cyopWU8f7FRbJJXxcG/1xn1XdOOtD1ZnDuE4ZFm5Qg14zHGUG2H883PZ2AUAB3caVXQjJrM36Fk3CSG7C/awpC73fkrTz0vAaut3JRZdgIYkqz2RdeSgVAgMBAAECggEAC2K++Q2kLzuw26QFDSH4ju6IoBIFCDdaOQY9Guu+2ESNOzr/KNsMHtX7li3d6p/18FNME+tQTmhM3adGds+ayTM99NSd4V/VDemLo3Ao5aPKM4UNoAYLzQ8TqBbeLvS4EKJRIn5EZWCB6191zja+8Enm5SNZ++P+bJ8+kXwDTiv6oU8Ft2YHV7B0FF7C/11IbDO0QpRpUisdEfEUo9nRbNRgJO0dYr5y5w+cG1q/MACZIZ5I4Kk7PBqAlBO6n0AnDG9Bf/pb+WSFCWuB4OFAYqUXsovMgXf+WvzhGTuxBHnOZtSHOSoLNimtVRPZBhfUk5JW508Xex/R4RrresY0AQKBgQD3OCl2+IuUYDT/KENxzeQv5ieN6ZCDyoH8D0NtAOzWcNH7VasYql1HujpZaPeibgHbilrB+oGe5Dc6FgzlBWxbU7yITDTay3O8OUrbeaHr7/WVDGue8asFHdbsTG7O1e18mKPa9cOs3bgixpJ3Q2eRFBlPTbweupDDkzIkAs93mQKBgQDHjuD2dS79CoT2iT2aWWkcNKvv8F5lG0pSPTtaBmaSCDa148JWzrorYmWq6bu20uhQQXmjLaD84Jj9MbrrmbtW/mBns8Q5WrbghnoL5/tAh8gE39UptOzy/SDQ+kflvePd0Jb0N/xaQXuSt5nBQ2d+AfFCvLo2YnAtM1R8RnPR3QKBgFuEge0V424BE31lpVoK0y9cL0ZcsW/oVqg96kL+9a4Iw+Mb+qO95Zw494LD1fBecveXxzNKDhQI/dzV7t4VXOCOJhv6whJBAM4sE2ceJvAVu776gxiq9BmHy1lcjiSpkyntw7K6Cj8DMThGdFAMNtnBLAFskHh7rj2ITyZ/l9f5AoGAF9hbyzdPaQ8Tuj3F7aaXZ7ZngSEwtlYfeFo2uFghc3ulxfA0LZlAJ6T3Twfk1UC4X7ajKTtvjCfuP/rsQfC5sPhuXpXdcZkEVynadYe/vwM6hFKWrwgedB6JCE+PvcUF8GBXWVFDRymh8XMc3ToHqMfkozvX6F2018HVa5AjkMUCgYEAhiHSgYpgyLmtRh4badllFspIwqCrDac7pq/62DRUns95O9p67iYoLu/bOYz+9EBm9CRMZ4DpjWblU4oz+laJIf5/mxUgiHfwe9EIs9izjTGxMIMF07154TmSvednjqGm+opZERSyrbXYW4QEkmLVhIYje+Wip7VE6LRFDrDdbE4=",
                StandardCharsets.UTF_8);
        boolean result = verify(
                content, 
                sign, 
                "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAwLaioaCcWveLEDC0+ABgUvs1263TMGwP5IbAmwgM9aVjbHHghOf/mHg57ZVthSYeslRj6kYY+SoAZMUIVhZSCWPpQtCTb/siwo1TXu3GM52b/MbzpDWsopIH4PXWEizk2YvA2NHo99yD9YNAYAlVF9tzPghnnotCr3L83+f8cSZLSto7E4e1O6N3EfTXIxbNyixhtGyF5QiNkz+91YE+mScrfgGv/hr/HMqKVlPH+xUWySV8XBv9cZ9V3TjrQ9WZw7hOGRZuUINeMxxlBth/PNz2dgFAAd3GlV0IyazN+hZNwkhuwv2sKQu935K089LwGrrdyUWXYCGJKs9kXXkoFQIDAQAB",
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
