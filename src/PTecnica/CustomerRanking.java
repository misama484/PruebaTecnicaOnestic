package PTecnica;


public class CustomerRanking {

	Integer  id;
	String name, lastname;
	Double Totalcost;
	
	
	public CustomerRanking(Integer id, String name, String lastname, Double totalcost) {
		super();
		this.id = id;
		this.name = name;
		this.lastname = lastname;
		Totalcost = totalcost;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getLastname() {
		return lastname;
	}


	public void setLastname(String lastname) {
		this.lastname = lastname;
	}


	public Double getTotalcost() {
		return Totalcost;
	}


	public void setTotalcost(Double totalcost) {
		Totalcost = totalcost;
	}


	@Override
	public String toString() {
		return "CustomerRanking [id=" + id + ", name=" + name + ", lastname=" + lastname + ", Totalcost=" + Totalcost
				+ "]";
	}
	
	public String toCSV() {
		return id + "," + name + "," + lastname + "," + Totalcost; 
	}
	
	
	
}