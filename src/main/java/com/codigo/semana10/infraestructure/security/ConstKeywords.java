package com.codigo.semana10.infraestructure.security;

import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;

public class ConstKeywords {
    public static final SecretKey SECRET_TOKEN = Keys.secretKeyFor(io.jsonwebtoken.SignatureAlgorithm.HS256);

}
