package cn.net.bhe.basics.base.clas;

import java.util.Date;

public class ExtendTest {
    public static void main(String[] args) {
        SubA subA = new SubA();
        SubB subB = new SubB();
        System.out.println(subA.getData()); // Super data
        System.out.println(subB.getData()); // SubB data
        /*
        System.out.println(new SubA().toString()); // Exception in thread "main" java.lang.StackOverflowError
         */
        /*
        SubA subA = new SubA(); // null
        subA.overrideMe(); // Mon Apr 08 08:58:50 CST 2019
         */
    }

}

class Super {

    private String data = "Super data";

    Super() {
        overrideMe();
    }

    public void overrideMe() {
        System.out.println("Call Super.overrideMe()");
    }

    public String getData() {
        return this.data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return this.toString();
    }

}

class SubA extends Super {
    @SuppressWarnings("unused")
    private String data = "SubA data";
    private final Date date;

    SubA() {
        date = new Date();
    }

    @Override
    public void overrideMe() {
        System.out.println(date);
    }

    @Override
    public String toString() {
        return super.toString();
    }

}

class SubB extends Super {
    private String data = "SubB data";

    @Override
    public String getData() {
        return data;
    }

    @Override
    public void setData(String data) {
        this.data = data;
    }

}
