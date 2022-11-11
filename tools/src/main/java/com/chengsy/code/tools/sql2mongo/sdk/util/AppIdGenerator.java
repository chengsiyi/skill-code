package com.chengsy.code.tools.sql2mongo.sdk.util;

import cn.hutool.crypto.digest.DigestUtil;

import java.util.UUID;

/**
 * @author chengsiyi
 * @date 2022/6/2 15:30
 */
public class AppIdGenerator {

    private AppIdGenerator() {
        // constructor
    }

    public static String generate(){
        byte[] bs = DigestUtil.sha1(UUID.randomUUID().toString());
        return Base58Codec.doEncode(bs);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println(generate());
        }
    }
}
