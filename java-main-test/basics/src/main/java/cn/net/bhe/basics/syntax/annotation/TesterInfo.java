package cn.net.bhe.basics.syntax.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE) // 只能用在类上
public @interface TesterInfo {

    public enum Priority { LOW, MEDIUM, HIGH }

    Priority priority() default Priority.MEDIUM;
    String[] tags() default "";
    String createdBy() default "Mkyong";
    String lastModified() default "03/01/2014";

}
