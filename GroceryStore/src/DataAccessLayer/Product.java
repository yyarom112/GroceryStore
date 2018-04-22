package DataAccessLayer;

public class Product {

	private int barcodeProduct;
	private String productName;
	private int deliveryTime;
	private int storeQuantity;
	private int storeroomQuantity;
	private String supplier;
	private double averageSalesPerDay;
	private String storeLocation;
	private String storeroomLocation;
	private int faultyProductInStore;
	private int faultyProductInStoreroom;
	private double buyPrice;
	private double sellPrice;
	private int categoryID;

	public Product(int barcodeProduct) {
		this.barcodeProduct = barcodeProduct;
		this.productName = null;
		this.deliveryTime = 0;
		this.storeQuantity = 0;
		this.storeroomQuantity = 0;
		this.supplier = null;
		this.averageSalesPerDay = 0;
		this.storeLocation = null;
		this.storeroomLocation = null;
		this.faultyProductInStore = 0;
		this.faultyProductInStoreroom = 0;
		this.buyPrice = 0;
		this.sellPrice = 0;
	}

	public Product(int barcodeProduct, String productName, int deliveryTime, int storeQuantity, int storeroomQuantity,
			String supplier, double averageSalesPerDay, String storeLocation, String storeroomLocation,
			int faultyProductInStore, int faultyProductInStoreroom, double buyPrice, double sellPrice, int categoryID) {
		this.barcodeProduct = barcodeProduct;
		this.productName = productName;
		this.deliveryTime = deliveryTime;
		this.storeQuantity = storeQuantity;
		this.storeroomQuantity = storeroomQuantity;
		this.supplier = supplier;
		this.averageSalesPerDay = averageSalesPerDay;
		this.storeLocation = storeLocation;
		this.storeroomLocation = storeroomLocation;
		this.faultyProductInStore = faultyProductInStore;
		this.faultyProductInStoreroom = faultyProductInStoreroom;
		this.buyPrice = buyPrice;
		this.sellPrice = sellPrice;
		this.categoryID = categoryID;
	}

	public Product(int barcodeProduct, String productName, int deliveryTime, int storeQuantity, int storeroomQuantity,
			String supplier, double averageSalesPerDay, String storeLocation, String storeroomLocation,
			int faultyProductInStore, int faultyProductInStoreroom) {
		this.barcodeProduct = barcodeProduct;
		this.productName = productName;
		this.deliveryTime = deliveryTime;
		this.storeQuantity = storeQuantity;
		this.storeroomQuantity = storeroomQuantity;
		this.supplier = supplier;
		this.averageSalesPerDay = averageSalesPerDay;
		this.storeLocation = storeLocation;
		this.storeroomLocation = storeroomLocation;
		this.faultyProductInStore = faultyProductInStore;
		this.faultyProductInStoreroom = faultyProductInStoreroom;

	}

	public int getBarcodeProduct() {
		return barcodeProduct;
	}

	public void setBarcodeProduct(int barcodeProduct) {
		this.barcodeProduct = barcodeProduct;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(int deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public int getStoreQuantity() {
		return storeQuantity;
	}

	public void setStoreQuantity(int storeQuantity) {
		this.storeQuantity = storeQuantity;
	}

	public int getStoreroomQuantity() {
		return storeroomQuantity;
	}

	public void setStoreroomQuantity(int storeroomQuantity) {
		this.storeroomQuantity = storeroomQuantity;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public double getAverageSalesPerDay() {
		return averageSalesPerDay;
	}

	public void setAverageSalesPerDay(double averageSalesPerDay) {
		this.averageSalesPerDay = averageSalesPerDay;
	}

	public String getStoreLocation() {
		return storeLocation;
	}

	public void setStoreLocation(String storeLocation) {
		this.storeLocation = storeLocation;
	}

	public String getStoreroomLocation() {
		return storeroomLocation;
	}

	public void setStoreroomLocation(String storeroomLocation) {
		this.storeroomLocation = storeroomLocation;
	}

	public int getFaultyProductInStore() {
		return faultyProductInStore;
	}

	public void setFaultyProductInStore(int faultyProductInStore) {
		this.faultyProductInStore = faultyProductInStore;
	}

	public int getFaultyProductInStoreroom() {
		return faultyProductInStoreroom;
	}

	public void setFaultyProductInStoreroom(int faultyProductInStoreroom) {
		this.faultyProductInStoreroom = faultyProductInStoreroom;
	}

	public double getBuyPrice() {
		return buyPrice;
	}

	public void setBuyPrice(int buyPrice) {
		this.buyPrice = buyPrice;
	}



	public double getSellPrice() {
		return sellPrice;
	}

	public void setSellPrice(int sellPrice) {
		this.sellPrice = sellPrice;
	}

	public int getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(int categoryID) {
		this.categoryID = categoryID;
	}
	
	public int checkWarningQuntityFlag(){
		return (int) (2*(this.averageSalesPerDay*deliveryTime));
	}
}