package DataAccessLayer;

public class Category {
	
	private int categoryID;
	private Integer topCategoryID;
	private String categoryName;
	
	public Category(int categoryID) {
		this.categoryID = categoryID;
		this.topCategoryID = 0;
		this.categoryName = null;

	}
	
	public Category(int categoryID, int topCategoryID, String categoryName) {
		this.categoryID = categoryID;
		this.topCategoryID = topCategoryID;
		this.categoryName = categoryName;
	}
	
	public Integer getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(int categoryID) {
		this.categoryID = categoryID;
	}

	public int getTopCategoryID() {
		return topCategoryID;
	}

	public void setTopCategoryID(int topCategoryID) {
		this.topCategoryID = topCategoryID;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
}