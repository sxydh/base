package cn.net.bhe.basics.jvm.gc;

public class App {

    public static void main(String[] args) throws InterruptedException {
        int i = 0;
        while (true) {
            new App();
            Thread.sleep(500);
            if (i % 100 == 0) {
                System.gc();
            }
        }
    }

}
