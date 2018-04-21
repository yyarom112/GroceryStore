package Presentation_Layer;

import java.util.ArrayList;
import java.util.Scanner;

import BusinessLayer.InventoryManager;
import DataAccessLayer.Product;

public class product_menu {

	private Command_Line_gui main_menu;
	private category_menu cm;
	private checker check;
	private InventoryManager bl;
	Scanner in;

	public product_menu(Command_Line_gui main_menu, InventoryManager bl, Scanner Scanner) {
		in = Scanner;
		this.main_menu = main_menu;
		check = new checker();
		this.bl = bl;

	}

	public void set_category_menu(category_menu category_menu) {
		cm = category_menu;
	}

	// the main program of the presentation layer.
	void run() {
		in = new Scanner(System.in);
		System.out.println("Grocery inventory menu:\n" + "1.Insert new product. \n" + "2.Update Product.\n"
				+ "3.Remove product.\n" + "4.Get products that are running out.\n" + "5.Get defective products.\n"
				+ "6.Get products in inventory.\n" + "7.Get product details.\n" + "8.Category manager.\n"
				+ "9.Main menu.");
		String s = in.nextLine();
		while (!check.check_number_range(s, 1, 9)) {
			System.out.println("Illegal input, please try again.");
			s = in.nextLine();
		}
		switch (Integer.parseInt(s)) {
		case (1): {
			InsertNewProduct();
			break;
		}
		case (2): {
			UpdateProduct();
			break;
		}
		case (3): {
			Removeproduct();
			break;
		}
		case (4): {
			getAllRunningOutProduct();
			break;
		}
		case (5): {
			GetDefectiveProducts();
			break;
		}
		case (6): {
			getProductsInInventory();
			break;
		}
		case (7): {
			getProductDetails();
			break;
		}
		case (8): {
			cm.run();
			;
			break;
		}
		case (9): {
			this.main_menu.run();
			;
			break;
		}
		}
	}

	public void InsertNewProduct() {
		// barcodeProduct
		System.out.println(
				"Insert barcode Product  \nThe barbode include only number without letters.\n If you wish to return to the product menu press $");
		String s = in.nextLine();
		while (!s.equals("$") && !this.check.checkOnlyNumbers(s)) {
			System.out.println("Illegal input, please try again.");
			s = in.nextLine();
		}
		if (s.trim().equals("$")) {
			run();
		} else {
			int barcodeProduct = Integer.parseInt(s);
			if (bl.isProductExistById(barcodeProduct)) {
				System.out.println("The product allready exists.\n");
				run();
			} else {
				// productName
				System.out.println(
						"Insert product Name  \nThe name must include only letters.\n If you wish to return to the product menu press $");
				s = in.nextLine();
				if (s.trim().equals("$")) {
					run();
				} else {
					String productName = s;
					// deliveryTime
					System.out.println(
							"Insert delivery  Time in days.\n Note that delivery Time must be greater than zero.\n If you wish to return to the product menu press $");
					s = in.nextLine();
					while (!s.equals("$") && !this.check.checkOnlyNumbers(s)) {
						System.out.println("Illegal input, please try again.\n");
						s = in.nextLine();
					}
					if (s.trim().equals("$")) {
						run();
					} else {
						int deliveryTime = Integer.parseInt(s);
						if (deliveryTime <= 0) {
							System.out.println("Illegal input, please try again.");
							run();
						} else {
							// storeQuantity
							System.out.println(
									"Insert store quantity.\n Note that store quantity must be greater or equal to zero.\n If you wish to return to the product menu press $");
							s = in.nextLine();
							while (!s.equals("$") && !this.check.checkOnlyNumbers(s)) {
								System.out.println("Illegal input, please try again.\n");
								s = in.nextLine();
							}
							if (s.trim().equals("$")) {
								run();
							} else {
								int storeQuantity = Integer.parseInt(s);
								if (storeQuantity < 0) {
									System.out.println("Illegal input, please try again.");
									run();
								} else {
									// storeroomQuantity
									System.out.println(
											"Insert store room Quantity.\n Note that store room quantity must be greater or equal to zero.\n If you wish to return to the product menu press $");
									s = in.nextLine();
									while (!s.equals("$") && !this.check.checkOnlyNumbers(s)) {
										System.out.println("Illegal input, please try again.\n");
										s = in.nextLine();
									}
									if (s.trim().equals("$")) {
										run();
									} else {
										int storeroomQuantity = Integer.parseInt(s);
										if (storeroomQuantity < 0) {
											System.out.println("Illegal input, please try again.\n");
											run();
										} else {
											// supplier
											System.out.println(
													"Insert supplier name.\n If you wish to return to the product menu press $");
											s = in.nextLine();
											if (s.trim().equals("$")) {
												run();
											} else {
												String supplier = s;
												// averageSalesPerDay
												System.out.println(
														"Insert average Sales Per Day.\n Note that average Sales Per Day must be greater to zero, and can be double number.\n If you wish to return to the product menu press $");
												s = in.nextLine();
												while (!s.equals("$") && !this.check.checkOnlyDouble(s)) {
													System.out.println("Illegal input, please try again.\n");
													s = in.nextLine();
												}
												if (s.trim().equals("$")) {
													run();
												} else {
													double averageSalesPerDay = Double.parseDouble(s);
													if (averageSalesPerDay <= 0) {
														System.out.println("Illegal input, please try again.\n");
														run();
													} else {
														// storeLocation
														System.out.println(
																"Insert store Location.\n If you wish to return to the product menu press $");
														s = in.nextLine();
														if (s.trim().equals("$")) {
															run();
														} else {
															String storeLocation = s;
															// storeroomLocation
															System.out.println(
																	"Insert store room Location.\n If you wish to return to the product menu press $");
															s = in.nextLine();
															if (s.trim().equals("$")) {
																run();
															} else {
																String storeroomLocation = s;
																// faultyProductInStore
																System.out.println(
																		"Insert amount of  faulty Product In Store.\n Note that faulty Product In Store must be greater or equal to zero and smaller than amount of product.\n If you wish to return to the product menu press $");
																s = in.nextLine();
																while (!s.equals("$")
																		&& !this.check.checkOnlyNumbers(s)) {
																	System.out.println(
																			"Illegal input, please try again.\n");
																	s = in.nextLine();
																}
																if (s.trim().equals("$")) {
																	run();
																} else {
																	int faultyProductInStore = Integer.parseInt(s);
																	if (faultyProductInStore < 0) {
																		System.out.println(
																				"Illegal input, faulty Product In Store must be greater to zero");
																		run();
																	} else {
																		// faultyProductInStoreroom
																		System.out.println(
																				"Insert amount of  faulty Product In Store room.\n Note that faulty Product In Store room must be greater or equal to zero and smaller than amount of product in room.\n If you wish to return to the product menu press $");
																		s = in.nextLine();
																		while (!s.equals("$")
																				&& !this.check.checkOnlyNumbers(s)) {
																			System.out.println(
																					"Illegal input, please try again.\n");
																			s = in.nextLine();
																		}
																		if (s.trim().equals("$")) {
																			run();
																		} else {
																			int faultyProductInStoreroom = Integer
																					.parseInt(s);
																			if (faultyProductInStoreroom < 0) {
																				System.out.println(
																						"Illegal input, faulty Product In Store must be greater to zero");
																				run();
																			} else {
																				if (faultyProductInStoreroom > storeroomQuantity) {
																					System.out.println(
																							"Illegal input, faulty Product In Store must be smaller than store quantity.");
																					run();
																				} else {
																					// buyPrice
																					System.out.println(
																							"Insert buy price.\n If you wish to return to the product menu press $");
																					s = in.nextLine();
																					while (!s.equals("$") && !this.check
																							.checkOnlyNumbers(s)) {
																						System.out.println(
																								"Illegal input, please try again.\n");
																						s = in.nextLine();
																					}
																					if (s.trim().equals("$")) {
																						run();
																					} else {
																						int buyPrice = Integer
																								.parseInt(s);
																						if (buyPrice < 0) {
																							System.out.println(
																									"Illegal input, faulty Product In Store must be greater to zero");
																							run();
																						} else {
																							// discountInBuyPrice
																							System.out.println(
																									"Insert discount In Buy Price.\n If you wish to return to the product menu press $");
																							s = in.nextLine();
																							while (!s.equals("$")
																									&& !this.check
																											.checkOnlyDouble(
																													s)) {
																								System.out.println(
																										"Illegal input, please try again.\n");
																								s = in.nextLine();
																							}
																							if (s.trim().equals("$")) {
																								run();
																							} else {
																								double discountInBuyPrice = Double
																										.parseDouble(s);
																								if (discountInBuyPrice < 0) {
																									System.out.println(
																											"Illegal input, faulty Product In Store must be greater to zero");
																									run();
																								} else {
																									// sellPrice
																									System.out.println(
																											"Insert sell price.\n If you wish to return to the product menu press $");
																									s = in.nextLine();
																									while (!s
																											.equals("$")
																											&& !this.check
																													.checkOnlyNumbers(
																															s)) {
																										System.out
																												.println(
																														"Illegal input, please try again.\n");
																										s = in.nextLine();
																									}
																									if (s.trim().equals(
																											"$")) {
																										run();
																									} else {
																										int sellPrice = Integer
																												.parseInt(
																														s);
																										if (sellPrice < 0) {
																											System.out
																													.println(
																															"Illegal input, faulty Product In Store must be greater to zero");
																											run();
																										} else {
																											// discpountSellPrice
																											System.out
																													.println(
																															"Insert discount in Sell Price.\n If you wish to return to the product menu press $");
																											s = in.nextLine();
																											while (!s
																													.equals("$")
																													&& !this.check
																															.checkOnlyDouble(
																																	s)) {
																												System.out
																														.println(
																																"Illegal input, please try again.\n");
																												s = in.nextLine();
																											}
																											if (s.trim()
																													.equals("$")) {
																												run();
																											} else {
																												double discpountSellPrice = Double
																														.parseDouble(
																																s);
																												if (discpountSellPrice < 0) {
																													System.out
																															.println(
																																	"Illegal input, faulty Product In Store must be greater to zero");
																													run();
																												} else {
																													// startDateDiscountSellPrice
																													System.out
																															.println(
																																	"Insert start Date Discount Sell Price.\n the format must be dd.mm.yyyy\n If you wish to return to the product menu press $\n");
																													s = in.nextLine();
																													while (!s
																															.equals("$")
																															&& !this.check
																																	.checkDate(
																																			s)
																															&& !s.equals(
																																	"")) {
																														System.out
																																.println(
																																		"Illegal input, please try again.\n");
																														s = in.nextLine();
																													}
																													if (s.trim()
																															.equals("$")) {
																														run();
																													} else {
																														String startDateDiscountSellPrice = s;
																														// endDateDiscountSellPrice
																														System.out
																																.println(
																																		"Insert end Date Discount Sell Price.\n the format must be dd.mm.yyyy\n If you wish to return to the product menu press $\n");
																														s = in.nextLine();
																														while (!s
																																.equals("$")
																																&& !this.check
																																		.checkDate(
																																				s)
																																&& !s.equals(
																																		"")) {
																															System.out
																																	.println(
																																			"Illegal input, please try again.\n");
																															s = in.nextLine();
																														}
																														if (s.trim()
																																.equals("$")) {
																															run();
																														} else {
																															String endDateDiscountSellPrice = s;
																															// categoryID
																															System.out
																																	.println(
																																			"Insert category ID.\nif the it's new category please insert the category first.\nIf you wish to return to the product menu press $");
																															s = in.nextLine();
																															while (!s
																																	.equals("$")
																																	&& !this.check
																																			.checkOnlyNumbers(
																																					s)) {
																																System.out
																																		.println(
																																				"Illegal input, please try again.\n");
																																s = in.nextLine();
																															}
																															if (s.trim()
																																	.equals("$")) {
																																run();
																															} else {
																																int categoryID = Integer
																																		.parseInt(
																																				s);
																																if (categoryID <= 0) {
																																	System.out
																																			.println(
																																					"Illegal input, category ID must be greater than zero.\n");
																																	run();
																																} else {
																																	if (!bl.isCategoryExistById(
																																			categoryID)) {
																																		System.out
																																				.println(
																																						"Illegal input, The category is not exists.\n");
																																		run();
																																	} else {
																																		Product toInsert = new Product(
																																				barcodeProduct,
																																				productName,
																																				deliveryTime,
																																				storeQuantity,
																																				storeroomQuantity,
																																				supplier,
																																				averageSalesPerDay,
																																				storeLocation,
																																				storeroomLocation,
																																				faultyProductInStore,
																																				faultyProductInStoreroom,
																																				buyPrice,
																																				discountInBuyPrice,
																																				sellPrice,
																																				discpountSellPrice,
																																				startDateDiscountSellPrice,
																																				endDateDiscountSellPrice,
																																				categoryID);
																																		if (bl.addNewProduct(
																																				toInsert)) {
																																			System.out
																																					.println(
																																							"the product add succeed.\n");
																																			run();
																																		} else {
																																			System.out
																																					.println(
																																							"ohh...Somsthing wrong occur. please try again.\n");
																																			run();
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}

																		}
																	}

																}

															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}

	}

	public void UpdateProduct() {
		System.out.println("Insert id of product to update  \n If you wish to return to the product menu press $");
		String s = in.nextLine();
		while (!s.equals("$") && !this.check.checkOnlyNumbers(s)) {
			System.out.println("Illegal input, please try again.\n");
			s = in.nextLine();
		}
		if (s.trim().equals("$")) {
			run();
		} else {
			if (!bl.isProductExistById(Integer.parseInt(s))) {
				System.out.println("The prodect is not exists  \n If you wish to return to the product menu press $");
				run();
			} else {
				Product toUpdate = bl.getProductById(Integer.parseInt(s));
				System.out.println("Category update menu:\n" + "1. Update Name.\n" + "2. Update delivery Time.\n"
						+ "3. Update store Quantity.\n" + "4. Update store room Quantity.\n" + "5. Update supplier.\n"
						+ "6. Update average Sales Per Day.\n" + "7. Update store Location.\n"
						+ "8. Update store room Location.\n" + "9. Update amout of faulty Product In Store\n"
						+ "10.Update amount of faulty Product In Store room\n" + "11.Update buy Price.\n"
						+ "12.Update sell Price\n" + "13.Update category ID" + "14.Back to Product menu.\n");
				s = in.nextLine();
				while (!check.check_number_range(s, 1, 16)) {
					System.out.println("Illegal input, please try again.\n");
					s = in.nextLine();
				}
				switch (Integer.parseInt(s)) {
				case 1: {
					System.out.println("Insert name to update  \n If you wish to return to the product menu press $");
					s = in.nextLine();
					if (s.trim().equals("$")) {
						run();
					} else {
						toUpdate.setProductName(s);
						bl.updateProductTable(toUpdate);
						run();
					}
					break;
				}
				case 2: {
					System.out.println(
							"Insert delivery Time to update  \n If you wish to return to the product menu press $\n");
					s = in.nextLine();
					while (!s.equals("$") && !this.check.checkOnlyNumbers(s)) {
						System.out.println("Illegal input, please try again.\n");
						s = in.nextLine();
					}
					if (s.trim().equals("$")) {
						run();
					} else {
						toUpdate.setDeliveryTime(Integer.parseInt(s));
						bl.updateProductTable(toUpdate);
						run();
					}
					break;
				}
				case 3: {
					System.out.println(
							"Insert store quantity to update  \n If you wish to return to the product menu press $\n");
					s = in.nextLine();
					while (!s.equals("$") && !this.check.checkOnlyNumbers(s)) {
						System.out.println("Illegal input, please try again.\n");
						s = in.nextLine();
					}
					if (s.trim().equals("$")) {
						run();
					} else {
						toUpdate.setStoreQuantity(Integer.parseInt(s));
						bl.updateProductTable(toUpdate);
						run();
					}
					break;
				}
				case 4: {
					System.out.println(
							"Insert store room quantity to update  \n If you wish to return to the product menu press $\n");
					s = in.nextLine();
					while (!s.equals("$") && !this.check.checkOnlyNumbers(s)) {
						System.out.println("Illegal input, please try again.\n");
						s = in.nextLine();
					}
					if (s.trim().equals("$")) {
						run();
					} else {
						toUpdate.setStoreroomQuantity(Integer.parseInt(s));
						bl.updateProductTable(toUpdate);
						run();
					}
					break;
				}
				case 5: {
					System.out.println(
							"Insert supplier to update  \n If you wish to return to the product menu press $\n");
					s = in.nextLine();
					if (s.trim().equals("$")) {
						run();
					} else {
						toUpdate.setSupplier(s);
						bl.updateProductTable(toUpdate);
						run();
					}
					break;
				}
				case 6: {
					System.out.println(
							"Insert average Sales Per Day to update  \n If you wish to return to the product menu press $\n");
					s = in.nextLine();
					while (!s.equals("$") && !this.check.checkOnlyNumbers(s)) {
						System.out.println("Illegal input, please try again.\n");
						s = in.nextLine();
					}
					if (s.trim().equals("$")) {
						run();
					} else {
						toUpdate.setAverageSalesPerDay(Integer.parseInt(s));
						bl.updateProductTable(toUpdate);
						run();
					}
					break;
				}
				case 7: {
					System.out.println(
							"Insert store Location to update  \n If you wish to return to the product menu press $\n");
					s = in.nextLine();
					if (s.trim().equals("$")) {
						run();
					} else {
						toUpdate.setStoreLocation(s);
						bl.updateProductTable(toUpdate);
						run();
					}
					break;
				}
				case 8: {
					System.out.println(
							"Insert store room Location to update  \n If you wish to return to the product menu press $\n");
					s = in.nextLine();
					if (s.trim().equals("$")) {
						run();
					} else {
						toUpdate.setStoreroomLocation(s);
						bl.updateProductTable(toUpdate);
						run();
					}
					break;
				}
				case 9: {
					System.out.println(
							"Insert amout of faulty Product to update  \n If you wish to return to the product menu press $\n");
					s = in.nextLine();
					while (!s.equals("$") && !this.check.checkOnlyNumbers(s)) {
						System.out.println("Illegal input, please try again.\n");
						s = in.nextLine();
					}
					if (s.trim().equals("$")) {
						run();
					} else {
						if (Integer.parseInt(s) < 0) {
							System.out.println("Illegal input, please try again.\n");
							run();
						} else {
							if (Integer.parseInt(s) > toUpdate.getStoreQuantity()) {
								System.out.println(
										"Impossible the store have more faulty product then all the store quatity:/  . please try again.\n");
								run();
							}
							toUpdate.setAverageSalesPerDay(Integer.parseInt(s));
							bl.updateProductTable(toUpdate);
							run();
						}
					}
					break;
				}
				case 10: {
					System.out.println(
							"Insert amount of faulty Product In Store room to update  \n If you wish to return to the product menu press $\n");
					s = in.nextLine();
					while (!s.equals("$") && !this.check.checkOnlyNumbers(s)) {
						System.out.println("Illegal input, please try again.\n");
						s = in.nextLine();
					}
					if (s.trim().equals("$")) {
						run();
					} else {
						if (Integer.parseInt(s) < 0) {
							System.out.println("Illegal input, please try again.\n");
							run();
						} else {
							if (Integer.parseInt(s) > toUpdate.getFaultyProductInStore()) {
								System.out.println(
										"Impossible the store have more faulty product only in room store then all the store :/  . please try again.\n");
								run();
							} else {
								toUpdate.setAverageSalesPerDay(Integer.parseInt(s));
								bl.updateProductTable(toUpdate);
								run();
							}
						}
					}
					break;
				}
				case 11: {
					System.out.println(
							"Insert buy Price to update  \n If you wish to return to the product menu press $\n");
					s = in.nextLine();
					while (!s.equals("$") && !this.check.checkOnlyNumbers(s)) {
						System.out.println("Illegal input, please try again.\n");
						s = in.nextLine();
					}
					if (s.trim().equals("$")) {
						run();
					} else {
						toUpdate.setBuyPrice(Integer.parseInt(s));
						System.out.println(
								"Insert discount In Buy Price to update  \n If you wish to return to the product menu press $\n");
						s = in.nextLine();
						while (!s.equals("$") && !this.check.checkOnlyNumbers(s)) {
							System.out.println("Illegal input, please try again.\n");
							s = in.nextLine();
						}
						if (s.trim().equals("$")) {
							run();
						} else {
							toUpdate.setDiscountInBuyPrice(Double.parseDouble(s));
							bl.updateProductTable(toUpdate);
							run();
						}
					}
					break;
				}
				case 12: {
					System.out.println(
							"Insert sell Price to update  \n If you wish to return to the product menu press $\n");
					s = in.nextLine();
					while (!s.equals("$") && !this.check.checkOnlyNumbers(s)) {
						System.out.println("Illegal input, please try again.\n");
						s = in.nextLine();
					}
					if (s.trim().equals("$")) {
						run();
					} else {
						toUpdate.setSellPrice(Integer.parseInt(s));
						System.out.println(
								"Insert discount In sell Price to update  \n If you wish to return to the product menu press $\n");
						s = in.nextLine();
						while (!s.equals("$") && !this.check.checkOnlyNumbers(s)) {
							System.out.println("Illegal input, please try again.\n");
							s = in.nextLine();
						}
						if (s.trim().equals("$")) {
							run();
						} else {
							toUpdate.setDiscpountSellPrice(Double.parseDouble(s));
							System.out.println(
									"Insert start day of discount In sell Price to update  \nInsert format dd.mm.yyyy If you wish to return to the product menu press $\n");
							s = in.nextLine();
							while (!s.equals("$") && !this.check.checkDate(s)) {
								System.out.println("Illegal input, please try again.\n");
								s = in.nextLine();
							}
							if (s.trim().equals("$")) {
								run();
							} else {
								toUpdate.setStartDateDiscountSellPrice(s);
								System.out.println(
										"Insert end day of discount In sell Price to update  \nInsert format dd.mm.yyyy If you wish to return to the product menu press $\n");
								s = in.nextLine();
								while (!s.equals("$") && !this.check.checkDate(s)) {
									System.out.println("Illegal input, please try again.\n");
									s = in.nextLine();
								}
								if (s.trim().equals("$")) {
									run();
								} else {
									toUpdate.setEndDateDiscountSellPrice(s);
									bl.updateProductTable(toUpdate);
									run();
								}
							}
						}

					}

					break;
				}
				case 13: {
					System.out.println(
							"Insert category ID to update  \n If you wish to return to the product menu press $\n");
					s = in.nextLine();
					while (!s.equals("$") && !this.check.checkOnlyNumbers(s)) {
						System.out.println("Illegal input, please try again.\n");
						s = in.nextLine();
					}
					if (s.trim().equals("$")) {
						run();
					} else {
						if (!bl.isCategoryExistById(Integer.parseInt(s))) {
							System.out.println("Illegal input, Category is not exist.\n");
							run();
						} else {
							toUpdate.setCategoryID(Integer.parseInt(s));
							bl.updateProductTable(toUpdate);
							run();
						}
					}
					break;
				}
				case 14: {
					run();
					break;
				}
				}

			}
		}
	}

	public void Removeproduct() {
		System.out.println("Insert id of product to remove  \n If you wish to return to the product menu press $\n");
		String s = in.nextLine();
		while (!s.equals("$") && !this.check.checkOnlyNumbers(s)) {
			System.out.println("Illegal input, please try again.\n");
			s = in.nextLine();
		}
		if (s.trim().equals("$")) {
			run();
		} else {
			if (!bl.isProductExistById(Integer.parseInt(s))) {
				System.out.println("Illegal input, the product is not exist .\n");
				run();
			} else {
				if (bl.removeProduct(bl.getProductById(Integer.parseInt(s)))) {
					System.out.println("The deletion success.\n");
					run();
				} else {
					System.out.println("Ohhh...The deletion failed.\n");
					run();
				}
			}
		}
	}

	public void getAllRunningOutProduct() {
		ArrayList<Product> missingList = bl.getListOfMissingProductsInInv();
		if (missingList == null) {
			System.out.println("The store is full with all the products");
			run();
		} else {
			for (Product p : missingList) {
				System.out.println(("Product barcode: " + p.getBarcodeProduct() + "\nProduct name: "
						+ p.getProductName() + "\nTotal amount in inventory: "
						+ (p.getStoreQuantity() + p.getStoreroomQuantity()) + "\nTotal missing in inventory: "
						+ (p.checkWarningQuntityFlag() - (p.getStoreQuantity() + p.getStoreroomQuantity()))
						+ "\nSupply time: " + p.getDeliveryTime() + "\nAverage sell per day: "
						+ p.getAverageSalesPerDay() + "\n" + "----------------------------------------"));
			}
			run();
		}
	}

	public void GetDefectiveProducts() {
		ArrayList<Product> defectiveList = bl.GetDefectiveProducts();
		if (defectiveList == null) {
			System.out.println("All the products in store are proper. ");
			run();
		} else {
			for (Product p : defectiveList) {
				System.out.println("Prodect barcode: " + p.getBarcodeProduct() + "prodect name: " + p.getProductName()
						+ "Amount of defective product: "
						+ (p.getFaultyProductInStore() + p.getFaultyProductInStoreroom())
						+ "proportion of defective product: "
						+ ((p.getFaultyProductInStore() + p.getFaultyProductInStoreroom())
								/ (p.getStoreQuantity() + p.getStoreroomQuantity())));
			}
			run();
		}
	}

	public void getProductsInInventory() {
		int[] proinvlist = bl.getListOfProductsInInv();
		if (proinvlist.length == 0) {
			System.out.println("The store is empty.");
			run();
		} else {
			for (int i = 0; i < proinvlist.length; i++) {
				Product tmp = bl.getProductById(proinvlist[i]);
				System.out.println("Product name: " + tmp.getProductName() + "\n" + "Product barcode: "
						+ tmp.getBarcodeProduct() + "\n" + "Product amount in store: "
						+ (tmp.getStoreQuantity() + tmp.getStoreroomQuantity()) + "\n");
			}
			run();
		}
	}

	public void getProductDetails() {
		System.out.println(
				"Insert barcode Product you wish to see his details  \n If you wish to return to the product menu press $\n");
		String s = in.nextLine();
		while (!s.equals("$") && !this.check.checkOnlyNumbers(s)) {
			System.out.println("Illegal input, please try again.\n");
			s = in.nextLine();
		}
		if (s.trim().equals("$")) {
			run();
		} else {
			int barcodeProduct = Integer.parseInt(s);
			if (!bl.isProductExistById(barcodeProduct)) {
				System.out.println("The product not exists.\n");
				run();
			} else {
				Product tmp = bl.getProductById(Integer.parseInt(s));
				System.out.println("Product name: " + tmp.getProductName() + "\n" + "Product barcode: "
						+ tmp.getBarcodeProduct() + "\n" + "Product amount in store: "
						+ (tmp.getStoreQuantity() + tmp.getStoreroomQuantity()) + "\n" + "supplier: "
						+ tmp.getSupplier() + "\n" + "average Sales Per Day: " + tmp.getAverageSalesPerDay() + "\n"
						+ "store Location: " + tmp.getStoreLocation() + "\n" + "store room Location: "
						+ tmp.getStoreroomLocation() + "\n" + "faulty Product In Store: "
						+ (tmp.getFaultyProductInStore() + tmp.getFaultyProductInStoreroom()) + "\n" + "buyPrice: "
						+ (tmp.getBuyPrice()) + "\n" + "discount In Buy Price: " + tmp.getDiscountInBuyPrice() + "\n"
						+ "sellPrice: " + (tmp.getSellPrice()) + "\n" + "discount In Sell Price: "
						+ (tmp.getDiscpountSellPrice()) + "\n" + "start Date Discount Sell Price: "
						+ (tmp.getStartDateDiscountSellPrice()) + "\n" + "end Date Discount Sell Price: "
						+ (tmp.getEndDateDiscountSellPrice()) + "\n" + "categoryID: " + (tmp.getCategoryID()) + "\n");
				run();
			}
		}
	}
}
