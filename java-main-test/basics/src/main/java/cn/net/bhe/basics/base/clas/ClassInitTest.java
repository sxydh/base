package cn.net.bhe.basics.base.clas;

public class ClassInitTest {

    public static void main(String[] args) {
        /*field();*/
        /*array();*/
        /*constants();*/
    }

    /**
     * Referencing a static field of a super class through a subclass does not
     * cause subclass initialization.
     * <p/>
     * output:<br/>
     * SuperClass init!<br/>
     * 123
     */
    public static void field() {
        System.out.println(SubClass.value);
    }

    /**
     * Referencing a class through an array definition does not trigger
     * initialization of this class.
     * <p/>
     * output:<br/>
     * empty
     */
    public static void array() {
        @SuppressWarnings("unused")
        SuperClass[] sca = new SuperClass[10];
    }

    /**
     * Constants are stored in the constant pool of the calling class during the
     * compilation phase. Essentially, they are not directly referenced to the
     * class that defines the constant, so the initialization of the class that
     * defines the constant is not triggered.
     * <p/>
     * output:<br/>
     * hello world
     */
    public static void constants() {
        System.out.println(SubClass.HELLO_WORLD);
    }
}

class SuperClass {

    static {
        System.out.println("SuperClass init!");
    }
    public static int value = 123;
    public static final String HELLO_WORLD = "hello world";
}

class SubClass extends SuperClass {
    static {
        System.out.println("SubClass init!");
    }

}
