package DataAccessLayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ProductManager {

	private static ProductManager instance;
	private String dataBase;

	public static ProductManager getProductManager() {
		if (instance == null)
			instance = new ProductManager();
		return instance;
	}

	private ProductManager() {
		dataBase = "jdbc:sqlite:" + System.getProperty("user.dir") + "/DataBase.db";
	}

	private Connection connect() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(dataBase);
		} catch (SQLException e) {
			System.out.println("ProductManager "+e.getMessage());
		}
		return conn;
	}
	
	/**
	 * initial new product table if it does not exist
	 * @return void
	 */
	public void initialProductTable() {
		String sqlCommand = "CREATE TABLE IF NOT EXISTS ProductTable (\n" + "Barcode integer PRIMARY KEY,\n"
				+ "Product_Name VARCHAR(30) NOT Null,\n" + "Delivery_Time integer NOT NULL,\n"
				+ "Quantity_In_Store integer NOT NULL,\n" + "Quantity_In_storeroom integer NOT NULL,\n"
				+ "Supplier_Name VARCHAR(30) NOT NUll,\n" + "Average_Sales_Per_Day integer DEFAULT 0,\n"
				+ "Location_In_Store VARCHAR(30) NOT NULL,\n" + "Location_In_Storeroom VARCHAR(30) NOT NULL,\n"
				+ "Faulty_Product_In_Store integer DEFAULT  0,\n" + "Faulty_Product_In_Storeroom integer DEFAULT   0,\n"
				+ "Category integer DEFAULT  0,\n"
				+ " FOREIGN KEY (Category) REFERENCES CategoryTable(CategoryID) ON UPDATE CASCADE ON DELETE CASCADE"
				+ ");";// create the fields of the table
		try (Connection conn = DriverManager.getConnection(dataBase); Statement stmt = conn.createStatement()) {
			stmt.execute(sqlCommand);
		} catch (SQLException e) {
			System.out.println("initialProductTable: "+ e.getMessage());
		}
	}

	/**
	 * insert new product to the productTable 
	 * @return void
	 */
	public boolean addProductToTable(Product product) {
		String sqlCommand = "Insert Into ProductTable \n"
				+ "(Barcode, Product_Name, Delivery_Time, Quantity_In_Store, Quantity_In_storeroom, Supplier_Name, Average_Sales_Per_Day, "
				+ "Location_In_Store, Location_In_Storeroom, Faulty_Product_In_Store,Faulty_Product_In_Storeroom, Category)\n" + "values(" 
				+ "?"+ "," + "?" + "," + "?" + "," + "?" + "," + "?" + "," + "?" + "," + "?" + "," + "?" + "," + "?" + ","
				+ "?" + "," + "?" + "," + "?" + ")";
		try (Connection conn = DriverManager.getConnection(dataBase);
				PreparedStatement pstmt = conn.prepareStatement(sqlCommand)) {
			pstmt.setInt(1, product.getBarcodeProduct());
			pstmt.setString(2, product.getProductName());
			pstmt.setInt(3, product.getDeliveryTime());
			pstmt.setInt(4, product.getStoreQuantity());
			pstmt.setInt(5, product.getStoreroomQuantity());
			pstmt.setString(6, product.getSupplier());
			pstmt.setDouble(7, product.getAverageSalesPerDay());
			pstmt.setString(8, product.getStoreLocation());
			pstmt.setString(9, product.getStoreroomLocation());
			pstmt.setInt(10, product.getFaultyProductInStore());
			pstmt.setInt(11, product.getFaultyProductInStoreroom());
			pstmt.setInt(12, product.getCategoryID());
			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.out.println("addProductToTable: "+e.getMessage());
			return false;
		}
	}
	
	/**
	 * delete product from the productTable
	 * @return void
	 */
	public boolean deleteProduct(Product productToDelete){
		String sqlCommand = "Delete FROM ProductTable \n"
                + "WHERE Barcode="+productToDelete.getBarcodeProduct();     
		try (Connection conn = DriverManager.getConnection(dataBase);
                Statement stmt = conn.createStatement()) {
			conn.createStatement().execute("PRAGMA foreign_keys = ON;");
            stmt.execute(sqlCommand);
            return true;
        } catch (SQLException e) {
            System.out.println("deleteProduct: "+e.getMessage());
            return false;
        }
	}
	
	/**
	 * update the productTable 
	 * @return void
	 */
	public void updateProductTable(Product product) {
		String sqlCommand = "UPDATE ProductTable \n" 
				+ "SET Barcode="+product.getBarcodeProduct()+",\n"
                + "Product_Name='"+product.getProductName()+"',\n"
                + "Delivery_Time="+product.getDeliveryTime()+",\n"
                + "Quantity_In_Store="+product.getStoreQuantity()+",\n"
                + "Quantity_In_storeroom="+product.getStoreroomQuantity()+",\n" 
                + "Supplier_Name='"+product.getSupplier()+"',\n" 
                + "Average_Sales_Per_Day="+product.getAverageSalesPerDay()+",\n" 
                + "Location_In_Store='"+product.getStoreLocation()+"',\n" 
                + "Location_In_Storeroom='"+product.getStoreroomLocation()+"',\n"
                + "Faulty_Product_In_Store="+product.getFaultyProductInStore()+",\n"
                + "Faulty_Product_In_Storeroom="+product.getFaultyProductInStoreroom()+",\n"
                + "Category="+product.getCategoryID()+"\n"
                + "WHERE Barcode="+product.getBarcodeProduct();  
		try (Connection conn = DriverManager.getConnection(dataBase); 
				Statement stmt = conn.createStatement()) {
			stmt.execute(sqlCommand);
		} catch (SQLException e) {
			System.out.println("updateProductTable: "+e.getMessage());
		}
	}
	
	/**
	 * return the required product by Id 
	 * @return required product
	 */
	public Product getProductByID(int productID) {
		String sqlCommand = "SELECT Barcode, Product_Name, Delivery_Time, Quantity_In_Store, Quantity_In_storeroom, Supplier_Name, Average_Sales_Per_Day, "
				+ " Location_In_Store, Location_In_Storeroom, Faulty_Product_In_Store, Faulty_Product_In_Storeroom, Category\n" 
					+ "FROM ProductTable WHERE Barcode = "+ productID;
		Product returnProduct = null;
		try (Connection conn = this.connect();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sqlCommand)) {
			
			int barcode = rs.getInt("Barcode");
			String name = rs.getString("Product_Name");
			int deliveryTime = rs.getInt("Delivery_Time");
	    	int quantityInStore = rs.getInt("Quantity_In_Store");
	    	int quantityInStoreroom =  rs.getInt("Quantity_In_storeroom");
	    	String supplier = rs.getString("Supplier_Name");
	    	int averageSales = rs.getInt("Average_Sales_Per_Day");
	    	String locInStore = rs.getString("Location_In_Store");
	    	String locInStoreroom = rs.getString("Location_In_Storeroom");
	    	int faultyInStore = rs.getInt("Faulty_Product_In_Store");
	    	int faultyInStoreRoom = rs.getInt("Faulty_Product_In_Storeroom");
	    	
	    	returnProduct = new Product(barcode, name, deliveryTime, quantityInStore, quantityInStoreroom, supplier, averageSales, locInStore, locInStoreroom, faultyInStore, faultyInStoreRoom);
	    	returnProduct.setCategoryID(rs.getInt("Category"));
	    	
		} catch (SQLException e) {
			System.out.println("getProductByID: "+e.getMessage());
			return null;
		}
		return returnProduct;
	}
	
	/**
	 * return an array with all the barcode's products
	 * @return array of barcode of products
	 */
	public int[] getListOfId() {
		String sqlCommand = "SELECT Barcode FROM ProductTable";
		int[] idList = null;
		ArrayList<Integer> tmpList = new ArrayList<Integer>();
		int counter = 0;
		try (Connection conn = this.connect();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sqlCommand)) {
			while (rs.next()) {
				tmpList.add(rs.getInt("Barcode"));
				counter++;
			}
			idList = new int[counter];
			for (int i = 0; i < counter; i++) {
				idList[i] = tmpList.get(i);
			}
		} catch (SQLException e) {
			System.out.println("getListOfId: "+e.getMessage());
			return new int[0]; 
		}
		return idList;
	}
	
	/**
	 * return an array with all the products that are missing in the inventory
	 * @return array of barcodes of products
	 */
	public ArrayList<Product> getMissingProduct(){
		String sql="SELECT Barcode FROM ProductTable WHERE (Quantity_In_Store+Quantity_In_storeroom)<="
				+ "(Average_Sales_Per_Day*Delivery_Time)";
		ArrayList<Product> products = new ArrayList<>();
		 try (Connection conn = this.connect();
	             Statement stmt  = conn.createStatement();
	             ResultSet rs    = stmt.executeQuery(sql)){
			 while(rs.next()){
				 products.add(getProductByID(rs.getInt("Barcode")));
			 }
		 }
		 catch (SQLException e) {
			 System.out.println("getMissingProduct: "+e.getMessage());
			 return null;
		 }
		return products;
	}
	
	/**
	 * return an array with all the products that exist in the inventory
	 * @return array of barcodes of products
	 */
	public int[] currentProductsInInventory(){
		String sql="SELECT Barcode FROM ProductTable WHERE Quantity_In_Store>=0 OR Quantity_In_storeroom>=0 ";
		int[] products=null;
		ArrayList<Integer> tmpList = new ArrayList<Integer>();
		int counter = 0;
		 try (Connection conn = this.connect();
	             Statement stmt  = conn.createStatement();
	             ResultSet rs    = stmt.executeQuery(sql)){
			 while(rs.next()){
				 tmpList.add(rs.getInt("Barcode"));
				 counter++;
			 }
			 products= new int[counter];
			 for(int i=0;i<counter;i++){
				 products[i]=tmpList.get(i);
			 }
		 }
		 catch (SQLException e) {
			 System.out.println("currentProductsInInventory: "+e.getMessage());
			 return new int[0]; 
		 }
		return products;
	}
	public ArrayList<Product> GetDefectiveProducts(){
			String sql="SELECT Barcode, Product_Name"
					+ " FROM ProductTable "
					+ "WHERE (Faulty_Product_In_Store+Faulty_Product_In_Storeroom)>0";
			ArrayList<Product> products = new ArrayList<>();
			 try (Connection conn = this.connect();
		             Statement stmt  = conn.createStatement();
		             ResultSet rs    = stmt.executeQuery(sql)){
				 while(rs.next()){
					 products.add(getProductByID(rs.getInt("Barcode")));
				 }
			 }
			 catch (SQLException e) {
				 System.out.println("getMissingProduct: "+e.getMessage());
				 return null;
			 }
			return products;
		}
}


