package fun.ehe.bean;

import java.io.Serializable;

public class User implements Serializable {

    private static final long serialVersionUID = -8917462514316329381L;

    private String name;
    private Integer age;
    private String sex;

    public User() {

    }

    public String getName() {
        return name;
    }

    public String getSex() {
        return sex;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "name: " + name + ", age: " + age + ", sex: " + sex;
    }

}
