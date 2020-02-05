package caseStudy.dto;

import java.io.Serializable;

public class UpdatedBookDto implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private double price;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
