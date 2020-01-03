package fun.ehe.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Pojo implements Serializable {

    private static final long serialVersionUID = -3701507881663064956L;
    @Id
    private Integer id;
    private String name;
    private Date ctime;
    private Date utime;

    public Pojo() {

    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getCtime() {
        return ctime;
    }

    public Date getUtime() {
        return utime;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public void setUtime(Date utime) {
        this.utime = utime;
    }

}
