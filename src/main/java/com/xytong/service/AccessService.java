package com.xytong.service;

import com.xytong.model.DTO.AccessRequestDTO;

public interface AccessService {
    String tokenMaker(String username, String password, Long timestamp);
    boolean tokenChecker(String token);
    String tokenRenewer(String token);
    AccessRequestDTO tokenParser(String token);
}
