
package data;

import MenuFuncs.subMenu;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;
import utils.Inputter;

public class WarehouseManager  {
    ArrayList<Bill> bills = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);
    
    public void createImportBill(ProductManager pm) {
        Bill bill = new Bill();
        boolean check = true;
        while (check) {
            String code = Inputter.getString("Enter Code Of Product : ", "Wrong Format!!", "^[K]\\d{4}");
            Product product = pm.findProductByCode(code);
            if (product == null) {
                System.out.println("Product not found.");
                check = true;
                continue; //phát hiện kh tìm thấy bỏ qua phần còn lại
            }
            int quantity = Inputter.getAnIteger("Enter quantity to import: ", "Only Number");
            product.setQuantity(product.getQuantity()+quantity);
            product.setImported("imported");
            bill.addItem(new Item(product, quantity));
            bill.setType("Import");
            check = useAgain("Press enter to continue\n", "Press any key to import");
        }
        bills.add(bill);
        System.out.println("[Import] : " + bill);
    }
    
    public void createExportBill(ProductManager pm) {
        Bill bill = new Bill();
        boolean check = true;
        while (check) {
            System.out.print("Enter product code: ");
            String code = scanner.nextLine();
            Product product = pm.findProductByCode(code);
            if (product == null) {
                System.out.println("Product not found.");
                check = true;
                continue;
            }
            int choice = 0;
            int quantity;
            do{
                choice = 0;
                quantity = Inputter.getAnIteger("Enter quantity to export:", "Only number");
                if(product.getQuantity() - quantity < 0){
                    System.out.println("Quantity of product "+product.getCode() + " : " +product.getQuantity());
                    choice = Inputter.getAnIteger("Do you want input again ?  1(Yes) 2(No) ", "1(Yes) 2(No)", 1, 2);   
                }else {
                    //set lại quantity
                    product.setQuantity(product.getQuantity() - quantity);
                    product.setExported("exported");
                    bill.addItem(new Item(product, quantity));
                    //type bill
                    bill.setType("Export");
                }
                if(choice != 1) break;
            }while(choice == 1);
            check = useAgain("Press enter to continue\n", "Press any key to export");
        }
        bills.add(bill);
        System.out.println("[Export] : " + bill);
    }
    
     //check useagain
    public boolean useAgain (String msg1, String mgs2){
        Scanner sc = new Scanner(System.in);
        System.out.printf("Add more items? ");
        System.out.printf(msg1);
        System.out.printf(mgs2);
        String checkESC = sc.nextLine();
        if(checkESC.isEmpty()) return true;
        else return false;
    }
    
    public void printBillByCode(String code){
        for (Bill bill : bills) {
            if(bill.checkItemInBill(code)){
                System.out.println(bill.getType()+ " " + bill);
            }
        }
    }
    
    
    public boolean writeToFile(String fileName){
        boolean resutl = false;
        FileOutputStream fos ;
        ObjectOutputStream os = null;
        File file = new File(fileName);
        try {
            fos = new FileOutputStream(file);
            os = new ObjectOutputStream(fos);
            for (Bill b : bills) {
                os.writeObject(b);
            }
            resutl = true;
            os.flush();
            os.close();
        } catch (Exception e) {
           e.printStackTrace();
        }   
        return resutl;
    }
    
    
       public boolean readFromFile(String fileName) {
        bills.clear();
        File file = new File(fileName);
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis= new FileInputStream(file);
            ois= new ObjectInputStream(fis);
            Bill Bills;
            while(fis.available()>0){
                Bills = (Bill) ois.readObject();
                bills.add(Bills);
            } 
            ois.close();
            fis.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}

