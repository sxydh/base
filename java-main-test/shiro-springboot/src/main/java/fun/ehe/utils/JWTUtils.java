package fun.ehe.utils;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;

public class JWTUtils {

    static final Logger LOGGER = LogManager.getLogger(JWTUtils.class);
    private static final long EXPIRE_TIME = 5 * 60 * 1000;

    /**
     * Verify the token is valid or not.
     *
     * @param token
     * @param username
     * @param password
     * @return
     */
    public static boolean verify(String token, String username, String password) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(password);
            JWTVerifier verifier = JWT.require(algorithm).withClaim("username", username).build();
            @SuppressWarnings("unused")
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (Exception e) {
            LOGGER.error(e.getLocalizedMessage());
            return false;
        }
    }

    /**
     * The information in the token can be obtained without decryption.
     *
     * @param token
     * @return
     */
    public static String getUsername(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("username").asString();
        } catch (JWTDecodeException e) {
            LOGGER.error(e.getLocalizedMessage());
            return null;
        }
    }

    /**
     * Generate signature
     *
     * @param username
     * @param password
     * @return
     */
    public static String sign(String username, String password) {
        try {
            Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            Algorithm algorithm = Algorithm.HMAC256(password);
            // With username information
            return JWT.create().withClaim("username", username).withExpiresAt(date).sign(algorithm);
        } catch (UnsupportedEncodingException e) {
            LOGGER.error(e.getLocalizedMessage());
            return null;
        }
    }
}
