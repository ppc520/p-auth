package ppc.security_framework.util;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ppc.security_framework.auto_import_params.JwtConfig;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
@Component
public class JWTUtil {
    @Autowired
    private JwtConfig jwtConfig;
    private static final String CLAIM_KEY_USERNAME = "username";
    private static final String CLAIM_KEY_EXPIRE = "expireTime";
    private String SECRET;
    private Long ACCESS_TOKEN_EXPIRE;
    private Long REFRESH_TOKEN_EXPIRE;
    private String accessTokenName;
    private String refreshTokenName;
    @PostConstruct
    private void init(){
        this.SECRET= jwtConfig.getSecret();
        this.ACCESS_TOKEN_EXPIRE= jwtConfig.getAccessTokenExpire();
        this.REFRESH_TOKEN_EXPIRE= jwtConfig.getRefreshTokenExpire();
        this.accessTokenName=jwtConfig.getAccessTokenName();
        this.refreshTokenName= jwtConfig.getRefreshTokenName();
    }

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

    public Boolean isUnexpired(String token){
        try {
            Claims claims = getClaims(token);
            Date expireTime = claims.get(CLAIM_KEY_EXPIRE, Date.class);
            if (expireTime.after(new Date(System.currentTimeMillis()))){
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public String refreshAccessToken(String oldAccessToken){
        Claims claims = getClaims(oldAccessToken);
        //修改为当前时间
        claims.put(CLAIM_KEY_EXPIRE,new Date(System.currentTimeMillis()+ACCESS_TOKEN_EXPIRE));
        return generateToken(claims);
    }

    public void invalidToken(String token){
        Claims claims = getClaims(token);
        claims.put(CLAIM_KEY_EXPIRE,new Date(System.currentTimeMillis()));
        generateToken(claims);
    }

    public String getAccessTokenName() {
        return accessTokenName;
    }

    public String getRefreshTokenName() {
        return refreshTokenName;
    }
}
