package cn.net.bhe.springcloudalicommon.bean;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "t_order")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GenericGenerator(name = "bhe_pkg_uuid", // 生成器名称，和@GeneratedValue的generator对应
            strategy = "uuid") // 使用hibernate内部实现的uuid策略
    @GeneratedValue(generator = "bhe_pkg_uuid")
    private String id;
    private String usrid; // 用户id
    private String usrname; // 用户名
    private String pid; // 产品id
    private String pname; // 产品名称
    private Integer pprice; // 产品价格
    private Integer num; // 数量

    public Order() {
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public String getId() {
        return id;
    }

    public String getUsrid() {
        return usrid;
    }

    public String getUsrname() {
        return usrname;
    }

    public String getPid() {
        return pid;
    }

    public String getPname() {
        return pname;
    }

    public Integer getPprice() {
        return pprice;
    }

    public Integer getNum() {
        return num;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUsrid(String usrid) {
        this.usrid = usrid;
    }

    public void setUsrname(String usrname) {
        this.usrname = usrname;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public void setPprice(Integer pprice) {
        this.pprice = pprice;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

}
