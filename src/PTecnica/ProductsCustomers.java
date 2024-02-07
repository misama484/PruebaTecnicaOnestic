package PTecnica;

import java.util.ArrayList;

public class ProductsCustomers {
	

	private String idProduct;
	ArrayList<String>CustomerList = new ArrayList<String>();	
	
	
	//CONSTRUCTOR
	public ProductsCustomers(String idProduct) {
		super();
		//this.IdCustomer = idCustomer;
		this.idProduct = idProduct;
		//this.CustomerList = customerList;
	}



	public String getIdProduct() {
		return idProduct;
	}


	public void setIdProduct(String idProduct) {
		this.idProduct = idProduct;
	}


	public ArrayList<String> getCustomerList() {
		return CustomerList;
	}


	public void setCustomerList(ArrayList<String> customerList) {
		CustomerList = customerList;
	}
	
	public void addCustomer(String idCustomer) {
		CustomerList.add(idCustomer);
	}


	@Override
	public String toString() {
		return "ProductsCustomers [idProduct=" + idProduct + ", CustomerList="
				+ CustomerList + "]";
	}
	
	

	
}
