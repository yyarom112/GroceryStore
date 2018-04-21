package DataAccessLayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;

public class CategoryManager {

	private static CategoryManager instance;
	private String dataBase;

	public static CategoryManager getCategoryManager() {
		if (instance == null)
			instance = new CategoryManager();
		return instance;
	}

	private CategoryManager() {
		dataBase = "jdbc:sqlite:" + System.getProperty("user.dir") + "/DataBase.db";
	}

	private Connection connect() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(dataBase);
		} catch (SQLException e) {
			System.out.println("CategoryManager connect : "+e.getMessage());
		}
		return conn;
	}

	/**
	 * initial new category table if it does not exist
	 * @return void
	 */
	public void initialCategoryTable() {
		String sqlCommand = "CREATE TABLE IF NOT EXISTS CategoryTable (\n" + "CategoryID integer PRIMARY KEY,\n"
				+ "topCategoryID integer DEFAULT Null,\n" + "categoryName VARCHAR(30) NOT NULL,\n"
				+ "discount integer DEFAULT 0 NOT NULL,\n" + "startDiscountDate VARCHAR(30) DEFAULT NULL,\n"
				+ "endDiscountDate  VARCHAR(30) DEFAULT NULL\n" + ");";// create the fields of the table
		try (Connection conn = DriverManager.getConnection(dataBase); Statement stmt = conn.createStatement()) {
			stmt.execute(sqlCommand);// create the worker's table
		} catch (SQLException e) {
			System.out.println("initialCategoryTable: "+e.getMessage());
		}
	}

	/**
	 * insert new category  
	 * @return void
	 */
	public void addCategoryToTable(Category category) {
		String sqlCommand = "Insert Into CategoryTable \n"
				+ "(CategoryID, topCategoryID, categoryName, discount, startDiscountDate, endDiscountDate)\n"
				+ "values(" + "?" + "," + "?" + "," + "?" + "," + "?" + "," + "?" + "," + "?" + ")";
		try (Connection conn = DriverManager.getConnection(dataBase);
				PreparedStatement pstmt = conn.prepareStatement(sqlCommand)) {
			pstmt.setInt(1, category.getCategoryID());
			pstmt.setInt(2, category.getTopCategoryID());
			pstmt.setString(3, category.getCategoryName());
			pstmt.setInt(4, category.getDiscount());
			pstmt.setString(5, category.getStartDiscountDate());
			pstmt.setString(6, category.getEndDiscountDate());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("addCategoryToTable: "+ e.getMessage());
		}
	}

	/**
	 * delete the required category  
	 * @return void
	 */
	public boolean deleteCategory(Category categoryToDelete) {
		String sqlCommand = "Delete FROM CategoryTable \n" + "WHERE CategoryID=" + categoryToDelete.getCategoryID();
		try (Connection conn = DriverManager.getConnection(dataBase); Statement stmt = conn.createStatement()) {
			stmt.execute(sqlCommand);
			return true;
		} catch (SQLException e) {
			System.out.println("deleteCategory: "+e.getMessage());
			return false;
		}
	}

	public boolean updateOneCategoryInCategoryTable(Category category) {
		String sqlCommand = "UPDATE CategoryTable \n" 
				+ "SET categoryName='" + category.getCategoryName() + "',\n"
				+ "topCategoryID='" + category.getTopCategoryID() + "',\n" 
				+ "Discount=" + category.getDiscount()
				+ ",\n" + "startDiscountDate='" + category.getStartDiscountDate() + "'\n" 
				+ "endDiscountDate='"+ category.getEndDiscountDate() + "'\n" 
				+ "WHERE CategoryID=" + category.getCategoryID();
		try (Connection conn = DriverManager.getConnection(dataBase); Statement stmt = conn.createStatement()) {
			stmt.execute(sqlCommand);
			return true;
		} catch (SQLException e) {
			System.out.println("updateOneCategoryInCategoryTable: "+e.getMessage());
			return false;
		}
	}
	
	public boolean updateAllCategoryTable(Category category) {
		String sqlCommand = "UPDATE CategoryTable \n" 
				+ "SET CategoryName='" + category.getCategoryName() + "',\n"
				+ "Discount=" + category.getDiscount()+ ",\n" 
				+ "startDiscountDate='" + category.getStartDiscountDate() + "'\n" 
				+ "endDiscountDate='"+ category.getEndDiscountDate() + "'\n" 
				+ "WHERE CategoryID=" + category.getCategoryID();
		try (Connection conn = DriverManager.getConnection(dataBase); Statement stmt = conn.createStatement()) {
			stmt.execute(sqlCommand);
			return true;
		} catch (SQLException e) {
			System.out.println("updateAllCategoryTable: "+e.getMessage());
			return false;
		}
	}

	/**
	 * return the required category by Id 
	 * @return required category
	 */
	public Category getCategoryByID(int categoryID) {
		String sqlCommand = "SELECT CategoryID, TopCategoryID, CategoryName, Discount, StartDiscountDate, EndDiscountDate\n"
				+ "From CategoryTable Where CategoryID = " + categoryID;
		Category returnCategory = null;
		try (Connection conn = this.connect();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sqlCommand)) {
			int Id = rs.getInt("CategoryID");
			String categoryName = rs.getString("CategoryName");
			int discount = rs.getInt("Discount");
			String startDiscountDate = rs.getString("StartDiscountDate");
			String endDiscountDate = rs.getString("EndDiscountDate");
			if (rs.getInt("TopCategoryID") == 0) {
				returnCategory = new Category(categoryID, 0, categoryName, discount, startDiscountDate,
						endDiscountDate);
			} else {
				int topCategoryID = rs.getInt("TopCategoryID");
				returnCategory = new Category(categoryID, topCategoryID, categoryName, discount, startDiscountDate,
						endDiscountDate);
			}

		} catch (SQLException e) {
			System.out.println("getCategoryByID: "+e.getMessage());
			return null;
		}
		return returnCategory;
	}

	/**
	 * return an array with all the categories by their categoryID
	 * @return array of ID's categories 
	 */
	public int[] getListOfId() {
		String sqlCommand = "Select CategoryID From CategoryTable";
		int[] idList = null;
		ArrayList<Integer> tmpList = new ArrayList<Integer>();
		int counter = 0;
		try (Connection conn = this.connect();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sqlCommand)) {
			while (rs.next()) {
				tmpList.add(rs.getInt("CategoryID"));
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
	 * return an array with all the categories in the grocery store
	 * @return array of categories
	 */
	public Category[] getAllCategories() {
		String sql = "SELECT CategoryID, TopCategoryID, CategoryName ,Discount, StartDiscountDate, EndDiscountDate FROM CategoryTable";
		Category[] categoryList = null;
		ArrayList<Category> tmpList = new ArrayList<Category>();
		int counter = 0;
		try (Connection conn = this.connect();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {
			while (rs.next()) {
				int Id = rs.getInt("CategoryID");
				int topCategoryID = rs.getInt("TopCategoryID");
				String categoryName = rs.getString("CategoryName");
				int discount = rs.getInt("Discount");
				String startDiscountDate = rs.getString("StartDiscountDate");
				String endDiscountDate = rs.getString("EndDiscountDate");
				tmpList.add(
						new Category(Id, topCategoryID, categoryName, discount, startDiscountDate, endDiscountDate));
				counter++;
			}
			categoryList = new Category[counter];
			for (int i = 0; i < counter; i++) {
				categoryList[i] = tmpList.get(i);
			}
		} catch (SQLException e) {
			System.out.println("getAllCategories : "+e.getMessage());
			return new Category[0];
		}
		return categoryList;
	}

}
