package cn.net.bhe.springcloudalicommon.bean;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_product")
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	private String id;
	private String name;
	private Integer price;

	public Product() {
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Integer getPrice() {
		return price;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

}
