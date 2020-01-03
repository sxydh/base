package cn.net.bhe.basics.base.clas;

public class ObjectInitTest {

    private int in=1;

    public ObjectInitTest(){
        System.out.println(in);
    }

    public static void main(String[] args){
        new ObjectInitTest();
    }

}
