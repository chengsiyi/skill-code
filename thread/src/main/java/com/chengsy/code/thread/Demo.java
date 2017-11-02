package com.chengsy.code.thread;

public class Demo implements Runnable {

    public void run() {
        System.out.println("hi,I'm a thread, and I implement the Runnable!");
    }

    public static void main(String[] args) {
        Thread t = new Thread(new Demo());
        t.start();
    }

}
