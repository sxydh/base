package cn.net.bhe.basics.base.lang;

/**
 * <p>
 * In computer programming, the term hooking covers a range of techniques used
 * to alter or augment the behaviour of an operating system, of applications, or
 * of other software components by intercepting function calls or messages or
 * events passed between software components. Code that handles such intercepted
 * function calls, events or messages is called a hook.
 * <p>
 * Hooking is used for many purposes, including debugging and extending
 * functionality. Examples might include intercepting keyboard or mouse event
 * messages before they reach an application, or intercepting operating system
 * calls in order to monitor behavior or modify the function of an application
 * or other component. It is also widely used in benchmarking programs, for
 * example frame rate measuring in 3D games, where the output and input is done
 * through hooking.
 * <p>
 * Hooking can also be used by malicious code. For example, rootkits, pieces of
 * software that try to make themselves invisible by faking the output of API
 * calls that would otherwise reveal their existence, often use hooking
 * techniques.
 */
public class ShutDownHook {

    public static void main(String[] args) {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                System.err.println("Shutdown Hook is running !");
            }
        });
        System.err.println("Application Terminating ...");
    }
}
