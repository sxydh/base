package cn.net.bhe.basics.jvm.classload;

import java.io.IOException;
import java.io.InputStream;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 类加载器虽然只用于实现类的加载动作，但它在Java程序中起到的作用却远远不限于类加载阶段。
 * 
 * 对于任意一个类，都需要由加载它的类加载器和这个类本身一同确立其在Java虚拟机中的唯一性，每一个类加载器，都拥有一个独立的类名称空间。
 * 这句话可以表达得更通俗一些：比较两个类是否“相等”，只有在这两个类是由同一个类加载器加载的前提下才有意义，
 * 否则，即使这两个类来源于同一个Class文件，被同一个虚拟机加载，只要加载它们的类加载器不同，那这两个类就必定不相等。
 * 这里所指的“相等”，包括代表类的Class对象的equals()方法、isAssignableFrom()方法、isInstance()方法的返回结果，
 * 也包括使用instanceof关键字做对象所属关系判定等情况。
 */
public class CustomLoader {
    
    static Logger log = LoggerFactory.getLogger(CustomLoader.class);

    @Test
    public void customLoader() throws Exception {
        ClassLoader myLoader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                try {
                    String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
                    InputStream is = getClass().getResourceAsStream(fileName);
                    if (is == null) {
                        return super.loadClass(name);
                    }
                    byte[] b = new byte[is.available()];
                    is.read(b);
                    return defineClass(name, b, 0, b.length);
                } catch (IOException e) {
                    throw new ClassNotFoundException(name);
                }
            }
        };
        @SuppressWarnings("deprecation")
        Object obj = myLoader.loadClass(CustomLoader.class.getCanonicalName()).newInstance();
        log.info("自定义类加载器生成的Class对象：\r\n{}", obj.getClass());
        log.info("自定义类加载器生成的Class对象是否和JVM生成的相等：\r\n{}", obj instanceof CustomLoader);
    }
    
}
