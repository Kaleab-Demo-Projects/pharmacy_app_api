package com.pharmacy.controller;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

  @Value("${jwt.secret}") private String secret;
  @Value("${jwt.ttl-minutes:60}") private long ttlMinutes;

  record LoginRequest(String username, String password) {}
  record LoginResponse(String token) {}

  @PostMapping("/login")
  public LoginResponse login(@RequestBody LoginRequest req) {
    // POC check (replace with real lookup later)
    if (!("admin".equals(req.username()) && "password".equals(req.password()))) {
      throw new RuntimeException("Invalid credentials");
    }

    var key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    var now = Instant.now();
    var exp = Date.from(now.plusSeconds(ttlMinutes * 60));

    String jwt = Jwts.builder()
        .setSubject(req.username())
        .claim("roles", "ROLE_USER")
        .setIssuedAt(Date.from(now))
        .setExpiration(exp)
        .signWith(key, SignatureAlgorithm.HS256)
        .compact();

    return new LoginResponse(jwt);
  }
}