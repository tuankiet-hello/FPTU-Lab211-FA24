
package runtime;

import MenuFuncs.subMenu;
import java.util.Scanner;
import utils.Inputter;

public class main {
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        subMenu sM = new subMenu();
        String proURL = "Product.dat";
        String wareURL = "WareHouse.dat";
        while (true) {
            System.out.println("---Management system menu---");
            System.out.println("1. Manage Products");
            System.out.println("2. Manage Warehouse");
            System.out.println("3. Report");
            System.out.println("4. Store Data to Files");
            System.out.println("5. Exit");
            int choice;
            do{
                choice = Inputter.getAnIteger("Select an option: ", "Choose 1-5");
                if(choice <= 0 || choice >= 6){
                    System.out.println("Choose 1-5");
                }
            }while(choice <= 0 || choice >= 6);
            switch (choice) {
                case 1:
                    sM.menuProductManagement();
                    break;
                case 2:
                    sM.menuWareHouse();
                    break;
                case 3:
                    sM.menuReport();
                    break;
                case 4:
                    sM.menuStore(proURL,wareURL);
                    break;
                case 5:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option, please try again.");
            }
        }
    }
}
