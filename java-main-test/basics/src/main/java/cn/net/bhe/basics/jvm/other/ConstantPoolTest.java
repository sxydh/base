package cn.net.bhe.basics.jvm.other;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 运行时常量池(Runtime Constant Pool)是方法区的一部分。
 * Class文件中除了有类的版本、字段、方法、接口等描述信息外，还有一项信息是常量池(Constant Pool Table)，用于存放编译期生成的各种字面量和符号引用，
 * 这部分内容将在类加载后进入方法区的运行时常量池中存放。
 * 
 * Java虚拟机对Class文件每一部分(自然也包括常量池)的格式都有严格规定，每一个字节用于存储哪种数据都必须符合规范上的要求才会被虚拟机认可、装载和执行，但对于运行时常量池，
 * Java虚拟机规范没有做任何细节的要求，不同的提供商实现的虚拟机可以按照自己的需要来实现这个内存区域。不过，一般来说，除了保存Class文件中描述的符号引用外，还会把翻译出来
 * 的直接引用也存储在运行时常量池中。
 * 
 * 运行时常量池相对于Class文件常量池的另外一个重要特征是具备动态性，Java语言并不要求常量一定只有编译期才能产生，也就是并非预置入Class文件中常量池的内容才能进入方法区
 * 运行时常量池，运行期间也可能将新的常量放入池中，这种特性被开发人员利用得比较多的便是String类的intern()方法。
 * 
 * 既然运行时常量池是方法区的一部分，自然受到方法区内存的限制，当常量池无法再申请到内存时会抛出OutOfMemoryError异常。
 * 
 * -- P42 深入理解Java虚拟机：JVM高级特性与最佳实践(第2版)
 */
public class ConstantPoolTest {
    
    static Logger log = LoggerFactory.getLogger(ConstantPoolTest.class);
    
    /**
     * intern优先返回常量池中的字符串对象，如果不存在的话返回堆上的字符串对象，并将该对象加到常量池。
     * 注意jvm会将字面量存入常量池。
     */
    @Test
    public void internTest() {
        String java = new String("java"); // 堆上产生java对象, 常量池产生java对象(jvm会将字面量存入常量池)
        log.info("{}", java.intern() == java); // false
        
        String javw = new StringBuilder("ja").append("vw").toString();
        log.info("{}", javw.intern() == javw);
    }
}
