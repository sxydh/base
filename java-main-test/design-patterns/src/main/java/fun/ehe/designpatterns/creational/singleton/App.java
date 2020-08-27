package fun.ehe.designpatterns.creational.singleton;

public class App {
    public static void main(String[] args) {
        Singleton singleton = Singleton.INSTANCE;
        singleton.setId(0);
        singleton.setName("singleton name");
        singleton.setPhone("15485632658");
        System.err.println(Singleton.INSTANCE.toString());
    }
}
