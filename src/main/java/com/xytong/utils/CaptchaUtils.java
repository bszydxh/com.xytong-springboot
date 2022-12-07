package com.xytong.utils;

import java.util.Random;
/**
 * @author bszydxh
 */

public class CaptchaUtils {
    private static char randomChar() {
        //A-Z,a-z,0-9,可剔除一些难辨认的字母与数字
        String str = "0123456789ABCdefghiDEFGHIJopPQRVWXYZabcjklSTUmnqrstKLMNOvuwxyz";
        Random random = new Random();
        return str.charAt(random.nextInt(str.length()));
    }

    public static String getCaptchaCode(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(randomChar());
        }
        return sb.toString();
    }
}
