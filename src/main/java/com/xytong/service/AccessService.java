package com.xytong.service;

import com.xytong.model.controllerData.json.AccessRequestJson;

public interface AccessService {
    String tokenMaker(String username, String password, Long timestamp);
    boolean tokenChecker(String token);
    String tokenRenewer(String token);

    AccessRequestJson tokenParser(String token);
}
