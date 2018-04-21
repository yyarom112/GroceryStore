package Presentation_Layer;

import java.util.Scanner;
import BusinessLayer.*;
/*import BusinessLayer.buisinessManager;
import DataAccessLayer.Worker;*/

public class Command_Line_gui {

	private Scanner in;
	private product_menu pm;
	private category_menu cm;
	private InventoryManager BL;
	private checker check;

	public Command_Line_gui() {
		BL=InventoryManager.GetInvManager();
		Scanner in = new Scanner(System.in);
		pm = new product_menu(this,BL, in);
		cm = new category_menu(this,BL, in);
		pm.set_category_menu(cm);
		cm.set_product_menu(pm);
		check = new checker();
	}

	// the main program of the presentation layer.
	void run() {
		Scanner in = new Scanner(System.in);
		System.out.println("Grocery inventory menu:\n"
							+ "1. Category menu.\n"
							+ "2. Product menu.\n"
							+ "3. exit.");
		String s = in.nextLine();
		while (!check.check_number_range(s, 1, 3)) {
			System.out.println("Illegal input, please try again.");
			s = in.nextLine();
		}
		switch (Integer.parseInt(s)) {
			case (1): {
				cm.run();
				break;
			}
			case (2): {
				pm.run();
				break;
			}
			case (3): {
				System.exit(0);
			}
			default:
				System.out.println("sdadsadsa");
		}
	}

}
