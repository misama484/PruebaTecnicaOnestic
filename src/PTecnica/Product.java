package PTecnica;

public class Product {

	private String id;
	private String name;
	private String cost;
	
	public Product(String id, String name, String cost) {
		super();
		this.id = id;
		this.name = name;
		this.cost = cost;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCost() {
		return cost;
	}
	public void setCost(String cost) {
		this.cost = cost;
	}
	@Override
	public String toString() {
		return "Products [id=" + id + ", name=" + name + ", cost=" + cost + "]";
	}
	
	
	
	
}
