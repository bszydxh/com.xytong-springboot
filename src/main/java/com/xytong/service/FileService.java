package com.xytong.service;

/**
 * @author bszydxh
 */
public interface FileService {
    /**
     * 读取文件，强转流属于是，不建议读大文件或者进行文件转发
     * @param path 文件在Spring文件夹下的路径 例子:"classpath:access/rsa_token"
     * @return 文件内容
     */
    String readFile(String path);
}
