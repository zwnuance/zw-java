package org.zw.auth.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mobile.device.Device;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.zw.auth.model.JwtUser;

/**
 * Created by zw on 3/3/2017.
 */
@Component
public class JwtTokenUtil implements Serializable {
  static final String CLAIM_KEY_USERNAME = "sub";
  static final String CLAIM_KEY_AUDIENCE = "audience";
  static final String CLAIM_KEY_CREATED = "created";

  private static final String AUDIENCE_UNKNOWN = "unknown";
  private static final String AUDIENCE_WEB = "web";
  private static final String AUDIENCE_MOBILE = "mobile";
  private static final String AUDIENCE_TABET = "tablet";

  @Value("${jwt.secret}")
  private String secret;

  @Value("${jwt.expiration}")
  private Long expiration;

  public String getUsernameFromToken(String token) {
    String username;
    final Claims claims = getClaimsFromToken(token);
    username = claims.getSubject();
    return username;
  }

  private Claims getClaimsFromToken(String token) {
    Claims claims;
    try {
      claims = Jwts.parser()
          .setSigningKey(secret)
          .parseClaimsJws(token)
          .getBody();
    } catch (Exception e) {
      claims = null;
    }
    return claims;
  }

  public boolean validateToken(String token, UserDetails userDetails) {
    JwtUser user = (JwtUser) userDetails;
    final String username = getUsernameFromToken(token);
    final Date created = getCreatedDateFromToken(token);
    return (
        username.equals(userDetails.getUsername())
          && !isTokenExpired(token)
          && !isCreatedBeforeLastPasswordReset(created, user.getLastPasswordResetDate())
        );
  }

  private boolean isTokenExpired(String token) {
    final Date expiration = getExpirationDateFromToken(token);
    return expiration.before(new Date());
  }

  private Date getExpirationDateFromToken(String token) {
    Date expiration;
    try {
      final Claims claims = getClaimsFromToken(token);
      expiration = claims.getExpiration();
    } catch (Exception e) {
      expiration = null;
    }
    return expiration;
  }

  private boolean isCreatedBeforeLastPasswordReset(Date created, Date lastPasswordResetDate) {
    return (lastPasswordResetDate != null && created.before(lastPasswordResetDate));
  }

  private Date getCreatedDateFromToken(String token) {
    Date created;
    try{
      final Claims claims = getClaimsFromToken(token);
      created = new Date((Long)claims.get(CLAIM_KEY_CREATED));
    } catch (Exception e) {
      created = null;
    }
    return created;
  }

  public String generateToken(UserDetails userDetails, Device device) {
    Map<String, Object> claims = new HashMap<>();
    claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
    claims.put(CLAIM_KEY_AUDIENCE, generateAudience(device));
    claims.put(CLAIM_KEY_CREATED, new Date());
    return generateToken(claims);
  }

  private String generateToken(Map<String, Object> claims) {
    return Jwts.builder()
              .setClaims(claims)
              .setExpiration(generateExpirationDate())
              .signWith(SignatureAlgorithm.HS512, secret)
              .compact();
  }

  private Date generateExpirationDate() {
    return new Date(System.currentTimeMillis() + expiration * 1000);
  }

  private String generateAudience(Device device) {
    String audience = AUDIENCE_UNKNOWN;
    if(device.isNormal()) {
      audience = AUDIENCE_WEB;
    } else if (device.isMobile()) {
      audience = AUDIENCE_MOBILE;
    } else if (device.isTablet()) {
      audience = AUDIENCE_TABET;
    }
    return audience;
  }

  public boolean canTokenBeRefreshed(String token, Date lastPasswordResetDate) {
    final Date created = getExpirationDateFromToken(token);
    return !isCreatedBeforeLastPasswordReset(created, lastPasswordResetDate)
            && (!isTokenExpired(token) || ignoreTokenExpiration(token));
  }

  private boolean ignoreTokenExpiration(String token) {
    String audience = getAudienceFromToken(token);
    return (AUDIENCE_TABET.equals(audience)) || (AUDIENCE_MOBILE.equals(audience));
  }

  private String getAudienceFromToken(String token) {
    String audience;
    try{
      final Claims claims = getClaimsFromToken(token);
      audience = claims.getAudience();
    } catch (Exception e) {
      audience = null;
    }
    return audience;
  }

  public String refreshToken(String token) {
    String refreshToken;
    try {
      final Claims claims = getClaimsFromToken(token);
      claims.put(CLAIM_KEY_CREATED, new Date());
      refreshToken = generateToken(claims);
    } catch (Exception e) {
      refreshToken = null;
    }
    return refreshToken;
  }
}
