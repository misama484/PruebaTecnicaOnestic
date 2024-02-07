package PTecnica;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class main {
	
	private static ArrayList <Customer> customers;
	private static ArrayList <Product> products;
	private static ArrayList<Order> orders;
	
	
	//CONSOLA
	private static void consola(String mensaje) {
		System.out.println(mensaje);
	}
	
    public static void main(String[] args) throws IOException {
        
    	
    	loadOrders();
    	loadProducts();
    	loadCustomers();
    	ArrayList<String> orderCost = calculateOrderCosts(orders, products);
    	String ficheroCSVCost = "order_prices.csv";
    	String product_customers = "product_customers.csv";
    	String customer_ranking = "customer_ranking.csv";
    	writeOrderPrices(orderCost, ficheroCSVCost);
    	getProductCustomers(orders, customers, products, product_customers);
    	Customer_Ranking(customers, orders, orderCost, customer_ranking);
    	
    }

    static List<Customer> loadCustomers() throws IOException {
        // Implementa la carga de clientes desde el archivo CSV
    	
    	File customersFile = new File("D:\\Proyectos\\Java\\PTecnicaOnestic\\customers.csv");  //CAMBIAR RUTA POR PARAMETRO
    	FileReader fr;
    	BufferedReader br;
    	try {
    		fr = new FileReader(customersFile);
    		br = new BufferedReader(fr);
    		String linea = br.readLine();
    		//ARRAYlIST CUSTOMERS
    		customers = new ArrayList<Customer>();
    		
    		while(linea != null) {    			
    			//Array donde almacenamos las lineas
    			String[] customersCsv = linea.split("\n");    			
    			//por cada linea, separamos campos
    			for(int i = 0; i < customersCsv.length;  i++) {
    				String[] customersLine = customersCsv[i].split(",");    				
    				Customer customer = new Customer(customersLine[0], customersLine[1], customersLine[2]);    				
    				customers.add(customer);
    			}
    			linea=br.readLine();
    		}
    		customers.remove(0);
    		
    		
    		br.close();
    		fr.close();
    		
    	}catch(IOException e){
    		
    	}
    	
    	return null;
    }

    @SuppressWarnings("resource")
	static List<Product> loadProducts() throws IOException {
        // Implementar la carga de productos desde el archivo CSV
    	File productsFile = new File("D:\\Proyectos\\Java\\PTecnicaOnestic\\products.csv");
    	FileReader fr;
    	BufferedReader br;
    	
    	fr = new FileReader(productsFile);
    	br = new BufferedReader(fr);
    	String linea = br.readLine();
    	products = new ArrayList<Product>();
    	
    	while (linea != null) {
    		//consola(linea.toString());
    		String[] productsCsv = linea.split("\n");
    		
    		for(int i = 0; i<productsCsv.length; i++) {
    			String[] productsLine = productsCsv[i].split(",");
    			Product product = new Product(productsLine[0], productsLine[1],productsLine[2]);
    			//consola(product.toString());
    			products.add(product);
    		}
    		linea = br.readLine();
    	}
    	
    	products.remove(0);
    	
    	for (Product product : products) {
    		//consola(product.toString());
    	}
    	br.close();
    	fr.close();
    	
    	return null;
    }

    static List<Order> loadOrders() throws IOException {
        // Implementar la carga de pedidos desde el archivo CSV
    	File ordersFile = new File("D:\\Proyectos\\Java\\PTecnicaOnestic\\orders.csv");
    	FileReader fr = new FileReader(ordersFile);
    	BufferedReader br = new BufferedReader(fr);
    	String linea = br.readLine();
    	orders = new ArrayList<Order>();
    	String[] orderItem = null;   	
    	
    	while(linea!= null) {
    		//consola(linea.toString());
    		String[] ordersCsv = linea.split("\n");
    		
    		for(int i = 0; i< ordersCsv.length; i++) {
    			orderItem = ordersCsv[i].split(",");    			
    			//coge la tercera columna del csv y crea un arrayList con la lista de productos, que ira como parametro de la clase Order    			
    			String[] productList = orderItem[2].split(" ");
	    		ArrayList<String> products = new ArrayList<String>();
	    		for(String product : productList) {
	    			products.add(product);	    				
	    		}	    		
	    		
    			Order order = new Order(orderItem[0], orderItem[1], products);
    			orders.add(order);
    		}   		
    		
    		linea = br.readLine();
    	}
    	
    	orders.remove(0);
    	for(Order order : orders) {
    		//consola(order.toString());
    	}
    	     	
    	return null;
    }

    static ArrayList<String> calculateOrderCosts(ArrayList<Order> orders, ArrayList<Product> products) {
        // Implementar el cálculo del costo total del pedido
    	ArrayList<Order> orderList = orders; 
    	ArrayList<Product> productList = products;
    	ArrayList<String> orderPrices = new ArrayList<String>();
    	ArrayList<String> productsOrder = new ArrayList<String>();
    	Integer orderId = 0;
    	Float priceProduct = 0f;
    	Float orderPrice = 0f;
    	//por cada pedido extraemos el id y el array de productos
    	for(Order order : orderList) {
    		//consola(order.getId());
    		orderId = Integer.parseInt(order.getId());
    		
    		
    		//obtener lista de productos de cada pedido
    		productsOrder = order.getProductList();
    		//por cada producto de la orden
    		for(String product : productsOrder) {
    			//pasamos a int el id del producto
    			int productInt = Integer.parseInt(product);
    			//por cada producto de la lista de productos, comparamos si coincide con el id del pedido
    			for(Product p : productList) {
    				if(product.equals(p.getId())) {
    					//parseamos a float el precio del producto coincidiente
    					 priceProduct = Float.parseFloat(p.getCost());    					
    				}    				
    			}
    			//sumamos el precio al pedido
    				orderPrice = orderPrice + priceProduct; 
    				//consola(orderPrice.toString());
    		}
    		//anyadimos a la lista de pedidos-costo
    		orderPrices.add(orderId + "," + orderPrice);    		
    		
    		}

    		//igualamos a cero el coste del pedido para la siguiente orden
    		orderPrice = 0f;

    	return orderPrices;
    	
    }

    static void writeOrderPrices(ArrayList<String> ordersList, String filename) throws IOException {
        // Implementar la escritura en un nuevo archivo CSV con la información requerida
    	ArrayList<String> ordersPrices = ordersList;
    	File ficheroEscritura = new File(filename);
    	//declaramos FW-BR
    	FileWriter fw;
    	BufferedWriter bw;
    	
    	fw = new FileWriter(ficheroEscritura);
    	bw = new BufferedWriter(fw);
    	//introducimos header en primera linea
    	String linea = "id, euros";
    	bw.write(linea);
    	bw.newLine();
    	for(String item : ordersPrices) {
    		bw.write(item);
    		bw.newLine();
    	}
    	bw.close();
    	fw.close();
    	
    }
    
    //-----TAREA 2-----
    //por cada producto, lista de clientes que compraron
    static void getProductCustomers(ArrayList<Order> orders, ArrayList<Customer> customers, ArrayList<Product> products, String filename) throws IOException {
    	
    	String productId = null;
    	String customerId = null;
    	//Lista de productos de cada pedido
    	ArrayList<String> productsOrder;    	
    	ProductsCustomers productC;    	
    	ArrayList<ProductsCustomers>ListaProductos = new ArrayList<ProductsCustomers>();
    
    	//por cada producto extraemos id y lo agregamos al objeto productC
    	for(Product product : products) {
    		productId = product.getId();
    		productC = new ProductsCustomers(productId);
    		
    		//por cada pedido
    		for(Order order : orders) {
    			//extraemos la lista de productos y el id del cliente
    			productsOrder = order.getProductList();
    			customerId = order.getCustomer();
    			//por cada producto de la lista,si contiene el id del producto en el bucle, anyadimos el id del cliente al objeto productC
    			for(String productItem : productsOrder) {
    				if(productsOrder.contains(productId)) {
    					productC.addCustomer(customerId);
    				}
    			}
    		}
    		
    		//traemos la lista de compradores del producto en el bucle
    		List<String>listaCustomers = productC.getCustomerList(); 
    		//eliminamos repetidos
    		listaCustomers = listaCustomers.stream().distinct().collect(Collectors.toList());
    		//pasamos a arrayList para enviar al objeto
    		ArrayList<String>listaCustomersArray = new ArrayList<String>();
    		listaCustomersArray.addAll(listaCustomers);
    		productC.setCustomerList(listaCustomersArray);
    		//anyadimos objeto a lista
    		ListaProductos.add(productC);
    	}
    	
    	//obtenemos los ids de los productos
    	for(ProductsCustomers item : ListaProductos) { 
    		String idCustomers = "";
    		ArrayList<String>idCustomer = item.getCustomerList();
	    	for(String item2 : idCustomer) {
	    		consola(item2);
	    	}   		
    	}
    	
    	//ESCRIBIMOS EN FICHERO .csv
    	// Implementar la escritura en un nuevo archivo CSV con la información requerida
    	
    	File ficheroEscritura = new File(filename);
    	//declaramos FW-BR
    	FileWriter fw;
    	BufferedWriter bw;
    	
    	fw = new FileWriter(ficheroEscritura);
    	bw = new BufferedWriter(fw);
    	//introducimos header en primera linea
    	String linea = "id, customer_ids";
    	bw.write(linea);
    	bw.newLine();
    	for(ProductsCustomers item : ListaProductos) {
    		String idProducto = item.getIdProduct();
    		String listaCustomers = "";
    		ArrayList<String> ListaProductosC = item.getCustomerList();
    		for(String p : ListaProductosC) {
    			listaCustomers = listaCustomers +" " + p;
    		}
	    	
    		bw.write(idProducto + ", " + listaCustomers);
    		bw.newLine();
    	}
    	bw.close();
    	fw.close();
    
    }
    
    //---TAREA3---
    /*
     * Para evaluar a nuestros clientes, necesitamos un archivo customer_ranking.csv que contenga las siguientes columnas, 
     * clasificadas en orden descendente por total_euros:

		id - identificación numérica del cliente
		firstname - nombre del cliente
		lastname - apellido del cliente
		total_euros - Total de euros que este cliente ha gastado en productos.
     * */
    public static void Customer_Ranking(ArrayList<Customer> customers, ArrayList<Order> orders, ArrayList<String> orderPrices, String filename) throws IOException {
    	//obtenemos el id, nombre y apellido de clientes
    	String customerId = "";
    	String orderId = "";
    	List<String> customerRanking = new ArrayList<String>();
    	List<String> customerOrder = new ArrayList<String>();
    	List<String> customerOrderPrices = new ArrayList<String>();
    	Map<Integer, Double> totalCostCustomerOrders = new HashMap<>();
    	List<CustomerRanking> ranking = new ArrayList<CustomerRanking>();
    	
    	FileWriter fw;
    	BufferedWriter bw;
    	
    	
    	for(Customer item : customers) {
    		customerId = item.getId();
    		for(Order order : orders) {
    			//si por cada pedido, el id del cliente es igual al cliente del for, almacenamos id pedido
    			if(order.getCustomer().equals(customerId)) {
    				orderId = order.getId();
    				customerOrder.add(customerId + "," + orderId); 
    			}
    		}
    	}
    	
    	
    	//por cada pedido de orderPrices extraemos el Id y el total
    	for(String opId : orderPrices) {
    			String[] param = opId.split(",");
    			String orderPricesId = param[0];
    			String orderPricesCost = param[1];
    			//por cada pedido recorremos la lista de clientes-pedidos, separamos los elementos y si el id del pedido coincide con el id de la lista de 
    			//clientes-pedidos, anyadimos el idcliente, idPedido y coste a la lista, para despues eliminar duplicados
    			for(String customerOrderItem : customerOrder) {
    				String[] customerOrderArray = customerOrderItem.split(",");
    				String customerOrderCustomerId = customerOrderArray[0];
    				String customerOrderOrderId = customerOrderArray[1];
    				if(customerOrderOrderId.equals(orderPricesId)) {
    					customerOrderPrices.add(customerOrderCustomerId + "," + customerOrderOrderId + "," + orderPricesCost);
    				}    				
    			}
    			
    		}
    	
    	//recorremos la lista, anyadimos idcliente y totalPedido a un mapa y mergeamos para que sume los totales de cada pedido del mismo cliente   	
    	for(String cop : customerOrderPrices) {
    		//consola(cop);
    		String[] params =  cop.split(",");
    		int idCustomer = Integer.parseInt(params[0]);
    		double OrderCost = Double.parseDouble(params[2]);
    		
    		totalCostCustomerOrders.merge(idCustomer, OrderCost, Double::sum);    		
    	}
    	
    	//mostramos resultados
    	for(Map.Entry<Integer, Double> item : totalCostCustomerOrders.entrySet()) {
    		//por cada entrada del mapa, consultamos en la lista de clientes y cuando el id coincida, anyadimos el nombre y apellidos
    		for(Customer customer : customers) {
    			Integer customerIdInt = Integer.parseInt(customer.getId());
    			if(customerIdInt.equals(item.getKey())) {
    				CustomerRanking custR = new CustomerRanking(item.getKey(), customer.getFirstname(), customer.getLastname(), item.getValue());
    				ranking.add(custR);
    			}
    		}    		
    	}
    	
    	
    	//ordenamos la lista de mayor a menor costo
    	ranking.sort(Comparator.comparingDouble(CustomerRanking::getTotalcost).reversed());
    	
    	//recorremos la lista de clientes-pedido-coste
    	for(CustomerRanking custR : ranking) {
    		consola(custR.toCSV());
    	}
    	
    	//escribimos en fichero
    	fw = new FileWriter(filename);
    	bw = new BufferedWriter(fw);
    	String linea = "id, name, lastname, total_euros";
    	bw.write(linea);
    	bw.newLine();
    	for(CustomerRanking custR : ranking) {
    		String id = custR.getId().toString();
    		String name = custR.getName();
    		String lastname = custR.getLastname();
    		String total_euros = custR.getTotalcost().toString();
    		
    		bw.write(id + "," + name + "," + lastname + "," + total_euros);
    		bw.newLine();
    	}
    	
    	bw.close();
    	fw.close();
    	
    }
}




