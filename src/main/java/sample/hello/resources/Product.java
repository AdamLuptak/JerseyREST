package sample.hello.resources;

import java.io.Serializable;

public class Product implements Serializable {

	private Long id;

	private String product;

	private double price;

	public Product() {

	}

	public Product(String product, double price) {
		super();
		this.product = product;
		this.price = price;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
