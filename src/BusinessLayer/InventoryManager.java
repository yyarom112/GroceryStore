package BusinessLayer;

import java.util.ArrayList;

import DataAccessLayer.Category;
import DataAccessLayer.CategoryManager;
import DataAccessLayer.Product;
import DataAccessLayer.ProductManager;

public class InventoryManager {
	
	private static InventoryManager instance;
	private CategoryManager categoryManager;
	private ProductManager productManager;
	
	public static InventoryManager GetInvManager(){
		if(instance==null)
			instance= new InventoryManager();
		return instance;
	}
	
	private InventoryManager() {
		categoryManager = CategoryManager.getCategoryManager();
		categoryManager.initialCategoryTable();
		productManager = ProductManager.getProductManager();
		productManager.initialProductTable();
	}
	
	//Category manager options
	public void addNewCategory(Category category) {
		categoryManager.initialCategoryTable();
		categoryManager.addCategoryToTable(category);		
	}
	
	public boolean updateCategoryInTable(Category category) {
		categoryManager.initialCategoryTable();
		if(category.getCategoryID()==0)
			return categoryManager.updateAllCategoryTable(category);
		else
			return categoryManager.updateOneCategoryInCategoryTable(category);
	}
	
	public boolean deleteCategory(Category category) {
		categoryManager.initialCategoryTable();
		return categoryManager.deleteCategory(category);
	}
	
	public Category getCategoryByCategoryID(int categoryId) {
		categoryManager.initialCategoryTable();
		Category returnCategory = categoryManager.getCategoryByID(categoryId);
		return returnCategory;
	}
	
	public int[] getListOfCategoryId() {
		categoryManager.initialCategoryTable();
		int[] returnList = categoryManager.getListOfId();
		return returnList;
	}
	
	public Category[] getListOfCategories() {
		categoryManager.initialCategoryTable();
		Category[] returnList = categoryManager.getAllCategories();
		return returnList;
	}
	
	public boolean isCategoryExistById(int categoryId) {
		int[] idList = categoryManager.getListOfId();
		for(int i = 0; i<idList.length; i++) {
			if(categoryId == idList[i])
				return true;
		}
		return false;
	}
	
	//Product manager functions
	public boolean addNewProduct(Product product) {
		productManager.initialProductTable();
		return productManager.addProductToTable(product);
	}
	
	public void updateProductTable(Product product) {
		productManager.initialProductTable();
		productManager.updateProductTable(product);
	}
	
	public boolean removeProduct(Product product) {
		productManager.initialProductTable();
		return productManager.deleteProduct(product);
	}
	
	public Product getProductById(int productId) {
		productManager.initialProductTable();
		Product returnProduct = productManager.getProductByID(productId);
		return returnProduct;
	}
	
	public int[] getListOfProductId() {
		productManager.initialProductTable();
		int[] returnList = productManager.getListOfId();
		return returnList;
	}
	
	public int[] getListOfProductsInInv() {
		productManager.initialProductTable();
		int[] returnList = productManager.currentProductsInInventory();
		return returnList;
	}
	
	public ArrayList<Product> getListOfProducts(){
		productManager.initialProductTable();
		int[] tmpList = getListOfProductId();
		ArrayList<Product> returnList = new ArrayList<Product>();
		for(int i = 0; i<tmpList.length;i++) {
			returnList.add(productManager.getProductByID(tmpList[i]));
		}
		return returnList;
	}
	
	public ArrayList<Product> getListOfMissingProductsInInv(){
		productManager.initialProductTable();
		ArrayList<Product> returnList = productManager.getMissingProduct();
		return returnList;
	}
	
	public boolean isProductExistById(int productId) {
		int[] idList = productManager.getListOfId();
		for(int i = 0; i<idList.length; i++) {
			if(productId == idList[i])
				return true;
		}
		return false;
	}
	
	public ArrayList<Product> GetDefectiveProducts(){
		return productManager.GetDefectiveProducts();
	}

}
