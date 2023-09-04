package com.ppc.common.util;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JWTUtil {
    private static final String CLAIM_KEY_USERNAME = "username";
    private static final String CLAIM_KEY_EXPIRE = "expireTime";

    @Value("${jwt.config.secret}")
    private static String SECRET;//创建加密盐
    @Value("${jwt.config.access-token-expire}")
    private static Long ACCESS_TOKEN_EXPIRE;
    @Value("${jwt.config.refresh-token-expire}")
    private static Long REFRESH_TOKEN_EXPIRE;

    public static String getAccessToken(String username) {
        HashMap<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME, username);
        claims.put(CLAIM_KEY_EXPIRE, new Date(System.currentTimeMillis()+ACCESS_TOKEN_EXPIRE));
        return generateToken(claims);
    }

    public static String getRefreshToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME, username);
        claims.put(CLAIM_KEY_EXPIRE, new Date(System.currentTimeMillis()+REFRESH_TOKEN_EXPIRE));
        return generateToken(claims);
    }

    private static String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.ES512, SECRET)
                .compact();
    }

    public static String getUsernameFromToken(String token) {
        String username = "";
        try {
            Claims claims = getClaims(token);
            username = claims.get(CLAIM_KEY_USERNAME,String.class);
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    private static Claims getClaims(String token){
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody();
    }

    public static Boolean isValidAccessToken(String token){
        if (token!=null){
            return token.equals(getAccessToken(getUsernameFromToken(token)));
        }return false;
    }

    public static Boolean isValidRefreshToken(String token){
        if (token!=null){
            return token.equals(getRefreshToken(getUsernameFromToken(token)));
        }return false;
    }

    public static Boolean isExpired(String token){
        try {
            Claims claims = getClaims(token);
            Date expireTime = claims.get(CLAIM_KEY_EXPIRE, Date.class);
            if (expireTime.after(new Date(System.currentTimeMillis()))){
                return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static String refreshRefreshToken(String token){
        Claims claims = getClaims(token);
        //修改为当前时间
        claims.put(CLAIM_KEY_EXPIRE,new Date(System.currentTimeMillis()+REFRESH_TOKEN_EXPIRE));
        return generateToken(claims);
    }

    public static String refreshAccessToken(String token){
        Claims claims = getClaims(token);
        //修改为当前时间
        claims.put(CLAIM_KEY_EXPIRE,new Date(System.currentTimeMillis()+ACCESS_TOKEN_EXPIRE));
        return generateToken(claims);
    }

}
