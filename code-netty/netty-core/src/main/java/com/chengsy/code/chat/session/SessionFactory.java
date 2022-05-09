package com.chengsy.code.chat.session;

public abstract class SessionFactory {

    private static Session session = new SessionMemoryImpl();

    public static Session getSession() {
        return session;
    }

    public static void main(String[] args) {
        System.out.println(16 / 4 * 4);
    }
}
