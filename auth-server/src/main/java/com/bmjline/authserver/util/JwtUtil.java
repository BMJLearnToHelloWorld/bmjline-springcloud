package com.bmjline.authserver.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.bmjline.authserver.constant.JwtCodeEnum;
import com.bmjline.authserver.exception.TokenAuthenticationException;

import java.util.Date;

/**
 * @author bmj
 */
public class JwtUtil {

    private JwtUtil() {
        throw new IllegalStateException("JwtUtil class");
    }

    public static final long TOKEN_EXPIRE_TIME = 7200 * 1000;
    private static final String ISSUER = "bmjline";

    /**
     * 生成Token
     *
     * @param userId    用户标识（不一定是用户名，有可能是用户ID或者手机号什么的）
     * @param secretKey
     * @return
     */
    public static String generateToken(String userId, String secretKey) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        Date now = new Date();
        Date expireTime = new Date(now.getTime() + TOKEN_EXPIRE_TIME);

        return JWT.create()
                .withIssuer(ISSUER)
                .withIssuedAt(now)
                .withExpiresAt(expireTime)
                .withClaim("userId", userId)
                .sign(algorithm);
    }

    /**
     * 校验Token
     *
     * @param token
     * @param secretKey
     * @return
     */
    public static void verifyToken(String token, String secretKey) throws TokenAuthenticationException {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            JWTVerifier jwtVerifier = JWT.require(algorithm).withIssuer(ISSUER).build();
            jwtVerifier.verify(token);
        } catch (JWTDecodeException jwtDecodeException) {
            throw new TokenAuthenticationException(JwtCodeEnum.TOKEN_INVALID.getCode(), JwtCodeEnum.TOKEN_INVALID.getMessage());
        } catch (SignatureVerificationException signatureVerificationException) {
            throw new TokenAuthenticationException(JwtCodeEnum.TOKEN_SIGNATURE_INVALID.getCode(), JwtCodeEnum.TOKEN_SIGNATURE_INVALID.getMessage());
        } catch (TokenExpiredException tokenExpiredException) {
            throw new TokenAuthenticationException(JwtCodeEnum.TOKEN_EXPIRED.getCode(), JwtCodeEnum.TOKEN_INVALID.getMessage());
        } catch (Exception ex) {
            throw new TokenAuthenticationException(JwtCodeEnum.UNKNOWN_ERROR.getCode(), JwtCodeEnum.UNKNOWN_ERROR.getMessage());
        }
    }

    /**
     * 从Token中提取用户信息
     *
     * @param token
     * @return
     */
    public static String getUserId(String token) {
        DecodedJWT decodedJwt = JWT.decode(token);
        return decodedJwt.getClaim("userId").asString();
    }
}
