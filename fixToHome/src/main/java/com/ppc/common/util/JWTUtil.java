package com.ppc.common.util;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
@Component
public class JWTUtil {
    private static final String CLAIM_KEY_USERNAME = "username";
    private static final String CLAIM_KEY_EXPIRE = "expireTime";

    @Value("${jwt.config.secret}")
    private String SECRET;//创建加密盐
    @Value("${jwt.config.access-token-expire}")
    private Long ACCESS_TOKEN_EXPIRE;
    @Value("${jwt.config.refresh-token-expire}")
    private Long REFRESH_TOKEN_EXPIRE;

    public String getAccessToken(String username) {
        HashMap<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME, username);
        claims.put(CLAIM_KEY_EXPIRE, new Date(System.currentTimeMillis()+ACCESS_TOKEN_EXPIRE));
        return generateToken(claims);
    }

    public String getRefreshToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME, username);
        claims.put(CLAIM_KEY_EXPIRE, new Date(System.currentTimeMillis()+REFRESH_TOKEN_EXPIRE));
        return generateToken(claims);
    }

    private String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
    }

    public String getUsernameFromToken(String token) {
        String username = "";
        try {
            Claims claims = getClaims(token);
            username = claims.get(CLAIM_KEY_USERNAME,String.class);
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    private Claims getClaims(String token){
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody();
    }

    public Boolean isValidAccessToken(String token){
        if (token!=null){
            return token.equals(getAccessToken(getUsernameFromToken(token)));
        }return false;
    }

    public Boolean isValidRefreshToken(String token){
        if (token!=null){
            return token.equals(getRefreshToken(getUsernameFromToken(token)));
        }return false;
    }

    public Boolean isExpired(String token){
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

    public String refreshRefreshToken(String token){
        Claims claims = getClaims(token);
        //修改为当前时间
        claims.put(CLAIM_KEY_EXPIRE,new Date(System.currentTimeMillis()+REFRESH_TOKEN_EXPIRE));
        return generateToken(claims);
    }

    public String refreshAccessToken(String token){
        Claims claims = getClaims(token);
        //修改为当前时间
        claims.put(CLAIM_KEY_EXPIRE,new Date(System.currentTimeMillis()+ACCESS_TOKEN_EXPIRE));
        return generateToken(claims);
    }

}
