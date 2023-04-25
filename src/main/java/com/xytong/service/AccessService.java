package com.xytong.service;

import com.xytong.model.bo.TokenBO;

/**
 * @author bszydxh
 */

/**
 * TODO 重构计划
 * RSA成为性能瓶颈
 * 加入缓存机制
 */
public interface AccessService {
    /**
     * token生成器接口
     *
     * @param username  用户名
     * @param password  密码
     * @param timestamp 时间戳
     * @return token值
     */
    String tokenMaker(String username, String password, Long timestamp);

    /**
     * token有效性校验接口，不校验用户是否拥有token
     *
     * @param token token值
     * @return 布尔值校验结果
     */
    boolean tokenChecker(String token);

    /**
     * 校验用户是否拥有token,tokenChecker的plus版本
     *
     * @param token    token值
     * @param username 用户名
     * @return 布尔值校验结果
     */
    boolean tokenCheckerWithUsername(String token, String username);

    /**
     * token更新接口
     *
     * @param token token值
     * @return 更新后的token
     */
    String tokenRenewer(String token);

    /**
     * 解释token包含的用户信息
     *
     * @param token token值
     * @return TokenBO（用户名，密码，创建时间）
     */
    TokenBO tokenParser(String token);
}
