package PTecnica;

import java.util.ArrayList;

public class Order {
	
	private String id, customer;
	private ArrayList<String> productList;

	public Order(String id, String customer, ArrayList<String> productList) {
		super();
		this.id = id;
		this.customer = customer;
		this.productList = productList;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public ArrayList<String> getProductList() {
		return productList;
	}

	public void setProductList(ArrayList<String> productList) {
		this.productList = productList;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", customer=" + customer + ", products=" + productList + "]";
	}
	
	
}
