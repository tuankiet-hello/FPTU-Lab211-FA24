
package MenuFuncs;

import data.Product;
import data.ProductManager;
import data.ReportManager;
import data.WarehouseManager;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;
import utils.Inputter;

public class subMenu {
     ProductManager pm = new ProductManager();
     WarehouseManager wh = new WarehouseManager();
     ReportManager rm = new ReportManager();
     Scanner sc = new Scanner(System.in);
    public void menuProductManagement(){
        while(true) {
            System.out.println("---Management Product---");
            System.out.println("1. Add Product");
            System.out.println("2. Update Product");
            System.out.println("3. Delete Product");
            System.out.println("4. Show All Products");
            System.out.println("5. Back to Main Menu");
            int choice;
            do{
                choice = Inputter.getAnIteger("Select an option: ", "Choose 1-5");
                if(choice <= 0 || choice >= 6){
                    System.out.println("Choose 1-5");
                }
            }while(choice <= 0 || choice >= 6);
            switch (choice) {
                case 1:
                    pm.addRating();
                    break;
                case 2:
                    pm.updating();
                    break;
                case 3:
                    pm.removeProduct();
                    break;
                case 4:
                    pm.printList();
                    break;
                case 5:
                    return;
            }
        }
    }
    //warehouse
     public void menuWareHouse(){
        while(true){
            System.out.println("---Management WareHouse---");
            System.out.println("1. Create Import Bill");
            System.out.println("2. Create Export Bill");
            System.out.println("3. Back to Main Menu");
            int choice;
            do{
                choice = Inputter.getAnIteger("Select an option: ", "Choose 1-3");
                if(choice <= 0 || choice >= 4){
                    System.out.println("Choose 1-3");
                }
            }while(choice <= 0 || choice >= 4);
            switch (choice) {
                case 1 : {
                    wh.createImportBill(pm);
                    break;
                }
                case 2 : {
                    wh.createExportBill(pm);
                    break;
                }
                case 3 : {
                    return;
                }
            }
        }   
    }
     
    //report
     public void menuReport(){
        while(true) {
            System.out.println("---Management Report---");
            System.out.println("1. Product Have Expired");
            System.out.println("2. Product Selling");
            System.out.println("3. Product Stock");
            System.out.println("4. Show Im/Export Product");
            System.out.println("5. Back to Main Menu");
           int choice;
            do{
                choice = Inputter.getAnIteger("Select an option: ", "Choose 1-5");
                if(choice <= 0 || choice >= 6){
                    System.out.println("Choose 1-5");
                }
            }while(choice <= 0 || choice >= 6);
            switch (choice) {
                case 1:
                    rm.ExpiredPrd(pm);
                    break;
                case 2:
                    rm.PrdSelling(pm);
                    break;
                case 3:
                    rm.PrdOutOfStock(pm);
                    break;
                case 4:
                    rm.listImportExport(wh, pm);
                    break;
                case 5:
                    return;
            }
        }
    }
     
    public void menuStore(String URLPro, String URLWare) {
         while(true){
            System.out.println("---Management Data---");
            System.out.println("1. Store List Product");
            System.out.println("2. Store List WareHouse");
            System.out.println("3. Read List Product");
            System.out.println("4. Read List WareHouse ");
            System.out.println("5. Back to Main Menu");
            int choice;
            do{
                choice = Inputter.getAnIteger("Select an option: ", "Choose 1-5");
                if(choice <= 0 || choice >= 6){
                    System.out.println("Choose 1-5");
                }
            }while(choice <= 0 || choice >= 6);
            switch (choice) {
                case 1 : {
                    if(pm.writeToFile(URLPro)){
                    System.out.println("Save to file successfully!");
                    }else{
                          System.out.println("Save to file fail!!!");  
                    }
                    break;
                }
                case 2 : {
                    if(wh.writeToFile(URLWare)){
                        System.out.println("Save to file successfully!");
                    }else{
                          System.out.println("Save to file fail!!!");  
                    }
                    break;
                }
                case 3 : {
                    if(pm.readFromFile(URLPro)){
                        System.out.println("Read file successfully!");
                    }else{
                          System.out.println("Read file fail!!!");  
                    }
                    break;
                }
                case 4 : {
                    if(wh.readFromFile(URLWare)){
                        System.out.println("Read file successfully!");
                    }else{
                          System.out.println("Read file fail!!!");  
                    }
                    break;
                }
                case 5 : {
                    return;
                }
            }
        }   
    }
}
