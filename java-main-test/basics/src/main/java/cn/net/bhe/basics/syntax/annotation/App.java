package cn.net.bhe.basics.syntax.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * Java注解是一种将元数据信息添加到源代码中的机制，注解可以替换传统的XML配置文件。自JDK5之后可用。
 * 自定义注解包括三个部分：
 * 1、创建自定义的注解，包括注解的生命周期、作用目标类型、字段
 * 2、在目标上声明注解
 * 3、注解解析，注解的元数据信息是这一步释放出来的
 */
public class App {

    @SuppressWarnings("deprecation")
    public static void main(String[] args) throws Exception {
        int passed = 0, failed = 0, count = 0, ignore = 0;
        Class<TestExample> obj = TestExample.class;

        // @TesterInfo注解解析
        if (obj.isAnnotationPresent(TesterInfo.class)) {
            Annotation annotation = obj.getAnnotation(TesterInfo.class);
            TesterInfo testerInfo = (TesterInfo) annotation;

            System.out.printf("%nPriority :%s", testerInfo.priority());
            System.out.printf("%nCreatedBy :%s", testerInfo.createdBy());
            System.out.printf("%nTags :");

            String tagStr = "";
            for (String tag : testerInfo.tags()) {
                tagStr += ", " + tag;
            }
            System.out.println(tagStr.substring(2));
            System.out.printf("%nLastModified :%s%n%n", testerInfo.lastModified());
        }

        // @Test注解解析
        for (Method method : obj.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Test.class)) {
                Annotation annotation = method.getAnnotation(Test.class);
                Test test = (Test) annotation;
                if (test.enabled()) { 
                    try {
                        method.invoke(obj.newInstance());
                        System.out.printf("%s - Test '%s' - passed %n", ++count, method.getName());
                        passed++;
                    } catch (Throwable ex) {
                        System.out.printf("%s - Test '%s' - failed: %s %n", ++count, method.getName(), ex.getCause());
                        failed++;
                    }
                } else {
                    System.out.printf("%s - Test '%s' - ignored%n", ++count, method.getName());
                    ignore++;
                }
            }
        }
        System.out.printf("%nResult : Total : %d, Passed: %d, Failed %d, Ignore %d%n", count, passed, failed, ignore);
    }
}