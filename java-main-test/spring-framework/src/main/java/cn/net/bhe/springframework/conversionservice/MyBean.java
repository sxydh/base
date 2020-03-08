package cn.net.bhe.springframework.conversionservice;

import java.util.Date;

public class MyBean {
    
    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
        System.out.println(this.date);
    }

}
