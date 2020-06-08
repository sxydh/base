package cn.net.bhe.basics.syntax.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME) // RetentionPolicy.RUNTIME：表示注解不仅被保存到class文件中，jvm加载class文件之后，仍然存在
@Target(ElementType.METHOD) // 只能用在方法级别上
public @interface Test {

    public boolean enabled() default true; // 注解字段，用来保存信息

}