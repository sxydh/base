package cn.net.bhe.mybatis;

import java.io.Serializable;
import java.math.BigDecimal;

public class Entity implements Serializable {

	private static final long serialVersionUID = -461687296464719185L;
	private BigDecimal id;
	private Double value;

	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
