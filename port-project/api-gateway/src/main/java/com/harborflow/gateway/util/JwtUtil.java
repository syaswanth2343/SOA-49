package com.harborflow.gateway.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;

/**
 * Shared JWT secret/logic. In production this key would come from a
 * centralized Config Server / vault, not be hard-coded in each service.
 */
public class JwtUtil {

    // NOTE: same secret must be used by auth-service to sign tokens.
    private static final String SECRET = "HarborFlowSuperSecretKeyForJWTSigning_ChangeMe_32B!";
    private static final SecretKey KEY = Keys.hmacShaKeyFor(SECRET.getBytes());

    public static Claims validateAndGetClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
