package cn.net.bhe.basics.concurrent;

import java.util.concurrent.ThreadLocalRandom;

public class ThreadLocalRandomTest {

    public static void main(String[] args) {
        int in = ThreadLocalRandom.current().nextInt();
        System.out.println(in);
    }

}
