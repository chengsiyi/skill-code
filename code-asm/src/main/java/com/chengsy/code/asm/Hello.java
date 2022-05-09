package com.chengsy.code.asm;

/**
 * @author chengsiyi
 * @date 2022/5/7 10:23
 */
public class Hello {

    public static void main(String[] args) {
        System.out.println(Hello.class.getSuperclass().getName());
    }
}
