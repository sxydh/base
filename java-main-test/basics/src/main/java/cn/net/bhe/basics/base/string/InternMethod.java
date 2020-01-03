package cn.net.bhe.basics.base.string;

public class InternMethod {

    public static void main(String[] args) {
        a();
        b();
    }

    static void a() {
        String s1 = new StringBuilder("ja").append("vw").toString();//new "javw" object in heap
        String s2 = new StringBuilder("ja").append("va").toString();
        System.out.println(s1.intern() == s1);//true, if object did not exist in constant pool, but there was the one in the heap, intern() will reuse it
        System.out.println(s2.intern() == s2);//false, because "java" object have existed in constant pool as a system's keyword
    }

    static void b() {
        String s1 = "nihao";//new "nihao" object in constant pool
        String s2 = new StringBuilder(s1).toString();//new "nihao" object in heap
        System.out.println(s2 == s2.intern());//false
    }
}
