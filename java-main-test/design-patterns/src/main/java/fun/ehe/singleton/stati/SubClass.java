package fun.ehe.singleton.stati;

public class SubClass extends Singleton {
    protected static Singleton uniqueInstance;

    private SubClass() {
        super();
    }

}
