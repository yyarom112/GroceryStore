package DataAccessLayer;

public class Category {
	
	private int categoryID;
	private int topCategoryID;
	private String categoryName;
	private int discount;
	private String startDiscountDate;
	private String endDiscountDate;
	
	public Category(int categoryID) {
		this.categoryID = categoryID;
		this.topCategoryID = 0;
		this.categoryName = null;
		this.discount = 0;
		this.startDiscountDate = null;
		this.endDiscountDate = null;	
	}
	
	public Category(int categoryID, int topCategoryID, String categoryName, int discount, String startDiscountDate, String endDiscountDate) {
		this.categoryID = categoryID;
		this.topCategoryID = topCategoryID;
		this.categoryName = categoryName;
		this.discount = discount;
		this.startDiscountDate = startDiscountDate;
		this.endDiscountDate = endDiscountDate;
	}
	
	public int getCategoryID() {
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

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public String getStartDiscountDate() {
		return startDiscountDate;
	}

	public void setStartDiscountDate(String startDiscountDate) {
		this.startDiscountDate = startDiscountDate;
	}

	public String getEndDiscountDate() {
		return endDiscountDate;
	}

	public void setEndDiscountDate(String endDiscountDate) {
		this.endDiscountDate = endDiscountDate;
	}
	
	

}
