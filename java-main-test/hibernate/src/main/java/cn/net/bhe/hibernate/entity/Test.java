package cn.net.bhe.hibernate.entity;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "test")
public class Test implements Serializable {

    private static final long serialVersionUID = 4081887078012460579L;
    @Id
    private Integer id;
    private String email;
    private Integer value;
    private String password;
    private Date createtime;

    public Test() {

    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    @Override
    public String toString() {
        String string = "";
        for (Field field : this.getClass().getDeclaredFields()) {
            String fieldName = field.getName();
            try {
                Method m = this.getClass().getMethod("get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1));
                string += ", " + fieldName + ": " + m.invoke(this);
            } catch (Exception e) {
            }
        }
        return string.length() > 0 ? string.substring(1) : string;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

}
