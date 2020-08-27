package fun.ehe.designpatterns.creational.singleton.stati;

public class SubClass extends Singleton {
    protected static Singleton uniqueInstance;

    private SubClass() {
        super();
    }

}
