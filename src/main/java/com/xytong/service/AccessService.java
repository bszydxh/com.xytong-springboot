package com.xytong.service;

import com.xytong.model.bo.TokenBO;

public interface AccessService {
    String tokenMaker(String username, String password, Long timestamp);
    boolean tokenChecker(String token);
    String tokenRenewer(String token);
    TokenBO tokenParser(String token);
}
