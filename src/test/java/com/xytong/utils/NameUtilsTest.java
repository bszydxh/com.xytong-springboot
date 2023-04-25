package com.xytong.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NameUtilsTest {
    @Test
    void getFileExtensionTest() {
        assertEquals(".qwee", NameUtils.getFileExtension("qwe.qwee"));
    }

}
