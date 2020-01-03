package cn.net.bhe.basics.base.clas;

import java.lang.reflect.Constructor;

public class ReflectTest {

    public static void main(final String[] args) throws Exception {
        Constructor<Foo> constructor = Foo.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        Foo foo = constructor.newInstance();
        System.out.println(foo);
    }
}

class Foo {
    private Foo() {

    }

    @Override
    public String toString() {
        return "I'm a Foo and I'm alright!";
    }
}
