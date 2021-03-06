package Presentation_Layer;

import java.util.Scanner;

import BusinessLayer.InventoryManager;
import DataAccessLayer.Category;

public class category_menu {

	private Command_Line_gui main_menu;
	private product_menu pm;
	private InventoryManager bl;
	private checker check;
	private Scanner in;

	public category_menu(Command_Line_gui main_menu, InventoryManager bl, Scanner Scanner) {
		in = Scanner;
		this.bl = bl;
		this.main_menu = main_menu;
		check = new checker();
	}

	public void set_product_menu(product_menu product_menu) {
		pm = product_menu;
	}

	public void run() {
		System.out.println("\nCategory manager menu:\n" + "1. Get all categories.\n" + "2. Add new category.\n"
				+ "3. Update category.\n" + "4. Delete category.\n" + "5. Category inventory report.\n"
				+ "6. Category Details.\n" + "7. Move to product menu\n" + "8. Back to main menu");
		String s = in.nextLine();
		while (!check.check_number_range(s, 1, 8)) {
			System.out.println("Illegal input, please try again.");
			s = in.nextLine();
		}
		switch (Integer.parseInt(s)) {
		case (1): {
			Get_all_categories();
			break;
		}
		case (2): {
			AddNewCategory();
			break;
		}
		case (3): {
			updateCategory();
			break;
		}
		case (4): {
			DeleteCategory();
			break;
		}
		case (5): {
			// TODO
			CategoryInventoryReport();
			break;
		}
		case (6): {
			CategoryInventoryReport();
			break;
		}
		case (7): {
			pm.run();
			break;
		}
		case (8): {
			this.main_menu.run();
			break;
		}
		}
	}

	private void Get_all_categories() {
		Category[] categoryList = bl.getListOfCategories();
		if (categoryList.length < 0) {
			System.out.println("ohhh...error occur.please try again.");
			run();
		}
		if (categoryList.length == 0) {
			System.out.println("The category DataBase is empty.");
			run();
		}
		if (categoryList.length > 0) {
			System.out.println("Store categories list:");
			for (int i = 0; i < categoryList.length; i++) {
				print_Category(categoryList[i], categoryList);
			}
			run();
		}
	}

	public void AddNewCategory() {
		System.out.println("insert Category ID \nIf you wish to return to the update menu press $\n");
		String s = in.nextLine();
		while (!s.equals("$") && !this.check.checkOnlyNumbers(s)) {
			System.out.println("Illegal input, please try again.");
			s = in.nextLine();
		}
		if (s.trim().equals("$")) {
			run();
		} else {
			try {
				int id = Integer.parseInt(s);
				// System.out.println(bl.categoryIsExist(id));
				if (bl.isCategoryExistById(id)) {
					System.out.println("the category id is exist.\n");
					run();
				} else {
					Category cat = new Category(id);
					System.out.println("insert Category name \nIf you wish to return to the update menu press $\n");
					s = in.nextLine();
					while (!s.trim().equals("$") && !this.check.checkOnlyLetters(s)) {
						System.out.println("Illegal input, please try again.");
						s = in.nextLine();
					}
					if (s.trim().equals("$")) {
						run();
					} else {
						cat.setCategoryName(s);
						System.out.println("insert Category top category if exist \n" + "if not exist press %\n"
								+ "If you wish to return to the update menu press $\n");
						s = in.nextLine();
						while (!s.trim().equals("$") && !s.equals("%") && !this.check.checkOnlyNumbers(s)
								&& !bl.isCategoryExistById(Integer.parseInt(s))) {
							System.out.println("Illegal input, please try again.");
							s = in.nextLine();
						}
						if (s.trim().equals("$")) {
							run();
						} else {
							if (!s.equals("%")) {
								int topId = Integer.parseInt(s);
								cat.setTopCategoryID(topId);
							}
							bl.addNewCategory(cat);
							System.out.println("the category add succesfully.\n ");
							run();
						}
					}
				}
			} catch (NumberFormatException e) {
				System.out.println("ohhh...error occur.please try again.\n");
				run();
			}
		}
	}

	public void updateCategory() {
		System.out.println(
				"Please enter the category ID you wish to edit.\nIf you wish to return to category manager menu press $");
		String s = in.nextLine();
		while (!this.check.checkOnlyNumbers(s) && !(s.equals("$"))) {
			System.out.println(
					"This category does not exists, please enter category name again.\nTo go back to category manager menu press '$'.");
			s = in.nextLine();
		}
		if (s.equals("$"))
			run();
		else {
			if (!bl.isCategoryExistById(Integer.parseInt(s))) {
				System.out.println("The category is not exist.");
				run();
			}
			Category cat = bl.getCategoryByCategoryID(Integer.parseInt(s));
			Scanner in = new Scanner(System.in);
			System.out.println("Category update menu:\n" + "1. Update name.\n" + "2. Update top category.\n"
					+ "3. Back to category main menu.");
			s = in.nextLine();
			while (!this.check.check_number_range(s, 1, 3) && !(s.equals("$"))) {
				System.out.println("wrong input, please try again.\nTo go back to category manager menu press '$'.");
				s = in.nextLine();
			}
			if (s.equals("$")) {
				run();
			} else {
				switch (s) {
				case ("1"): {
					System.out.println("Enter the new name.\nTo go back to category manager menu press '$'\n");
					s = in.nextLine();
					while (!this.check.checkOnlyLetters(s) && !(s.equals("$"))) {
						System.out.println(
								"wrong input, please try again.\nTo go back to category manager menu press '$'.\n");
						s = in.nextLine();
					}
					if (s.equals("$")) {
						run();
					} else
						cat.setCategoryName(s);
					break;
				}
				case ("2"): {
					System.out
							.println("Enter the new Top ID category.\nTo go back to category manager menu press '$'\n");
					s = in.nextLine();
					while (!this.check.checkOnlyNumbers(s) && !(s.equals("$"))) {
						System.out.println(
								"wrong input, please try again.\nTo go back to category manager menu press '$'.\n");
						s = in.nextLine();
					}
					if (s.equals("$")) {
						run();
					} else if (bl.isCategoryExistById(Integer.parseInt(s)))
						cat.setTopCategoryID(Integer.parseInt(s));
					else {
						System.out.println("category not exist.\n");
						run();
						break;
					}
					break;
				}
				case ("3"):
					run();
					break;
				}
				if (bl.updateCategoryInTable(cat)) {
					System.out.println("the new update category details is: \n");
					print_Category(cat, bl.getListOfCategories());
					run();
				} else {
					System.out.println("Error occur please try again. \n");
					run();
				}
			}
		}
	}

	public void DeleteCategory() {
		System.out.println(
				"Please enter the category ID you wish to delete.\nIf you wish to return to category manager menu press $");
		String s = in.nextLine();
		while (!this.check.checkOnlyNumbers(s) && !(s.equals("$"))) {
			System.out.println(
					"This category does not exists, please enter category name again.\nTo go back to category manager menu press '$'.");
			s = in.nextLine();
		}
		if (s.equals("$"))
			run();
		else {
			if (bl.isCategoryExistById(Integer.parseInt(s))) {
				if (bl.deleteCategory(bl.getCategoryByCategoryID(Integer.parseInt(s)))) {
					System.out.println("This category deletion succeed.\n");
					run();
				} else {
					System.out.println("error occur, please try again.\n");
					run();
				}
			} else {
				System.out.println(
						"This category does not exists, please enter category name again.\nTo go back to category manager menu press '$'.\n");
				run();
			}
		}
	}

	public void CategoryInventoryReport() {
		System.out.println("Please enter the category ID you wish report about.\n"
				+ "if you want to see all categories enter 'all'."
				+ " .\nIf you wish to return to category manager menu press $");
		String s = in.nextLine();
		while (!this.check.checkOnlyNumbers(s) && !(s.equals("$")) && !(s.equals("all"))) {
			System.out.println(
					"This category does not exists, please enter category name again.\nTo go back to category manager menu press '$'.");
			s = in.nextLine();
		}
		if (s.equals("$"))
			run();
		else {
			if (s.equals("all")) {
				Get_all_categories();

			} else {
				if (!bl.isCategoryExistById(Integer.parseInt(s))) {
					System.out.println("The category is not exist.\n");
					run();
				} else {
					print_Category(bl.getCategoryByCategoryID(Integer.parseInt(s)), bl.getListOfCategories());
					run();
				}
			}
		}
	}

	public void print_Category(Category category, Category[] categoryList) {
		String output = "Category Id:" + category.getCategoryID() + "\n" + "Category name: "
				+ category.getCategoryName() + "\n" + "Hierarchy categories: ";
		int id = category.getTopCategoryID();
		boolean find = false;
		while (id != 0) {
			find = false;
			for (int i = 0; i < categoryList.length && !find; i++) {
				if (categoryList[i].getCategoryID() == id) {
					id = categoryList[i].getTopCategoryID();
					output += " -> " + categoryList[i].getCategoryName();
					find = true;
				}
			}
		}
		output += "\n";
		System.out.println(output);
	}
}
