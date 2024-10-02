
package data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import utils.Inputter;
import static utils.Inputter.sc;

public class ProductManager {
     ArrayList<Product> pd = new ArrayList<>();
    Scanner sc = new Scanner(System.in);
    
    public void addRating() {
        boolean check = true;
        do{
            String code = Inputter.getString("Enter Code Of Product [Kxxxx] : ", "Wrong Format!!", "^[K]\\d{4}");
            for(int i = 0; i < pd.size(); i++){
                if(pd.get(i).getCode().equals(code)){
                    System.out.println("Duplicate!!");
                    return;
                }
            }
            //add nè
            String name = Inputter.getString("Enter Name", "Do not empty!");
            int newDate,newMonth,newYear;
            int expDate,expMonth,expYear;
            LocalDate manufactureDate;
            LocalDate expirationDate;
            do{
               //ngày sản xuất
               newDate = Inputter.getAnIteger("Enter  date manufacture", "Date between 1-31", 1, 31);
               newMonth = Inputter.getAnIteger("Enter  month manufacture", "Month between 12", 1, 12);
               newYear = Inputter.getAnIteger("Enter  year manufacture", "Year can not over 2024", 1, 2024);
               manufactureDate =  LocalDate.of(newYear,newMonth,newDate);
               //ngày hết hạn
               expDate = Inputter.getAnIteger("Enter  date exp", "Date between 1-31", 1, 31);
               expMonth = Inputter.getAnIteger("Enter  month exp", "Month between 12", 1, 12);
               expYear = Inputter.getAnIteger("Enter  year exp", "Year can not under 1900", 1900, 100000);
               expirationDate =  LocalDate.of(expYear,expMonth,expDate);
               if(manufactureDate.isAfter(expirationDate)){
                   System.out.println("Your expirationDate is not valid!");
               }
            }while(manufactureDate.isAfter(expirationDate));
            Double price = Inputter.getADouble("Enter price : ", "Do not empty!");
//            //quantity
//            int quantity = Inputter.getAnIteger("Enter quantity", "Do not empty!!");
            //tạo product
            Product nProduct = new Product(code, name, manufactureDate, expirationDate, 0, price);
            //lưu lên list
            pd.add(nProduct);
            check = useAgain();
        }while(check);
    }
    
    //hàm updating
    public void updating(){
        if(pd.isEmpty()){
            System.out.println("Don't have product!");
            return;
        }
        String code = Inputter.getString("Enter Code Of Product [Kxxxx] : ", "Wrong Format!!", "^[K]\\d{4}");
        boolean flag = false;
        for(int i = 0; i < pd.size(); i++){
            if(pd.get(i).getCode().equals(code)){
                //updating
                System.out.println("Enter Name");
                String name = sc.nextLine();
                if (!name.isEmpty()) {
                    pd.get(i).setName(name);
                }
                int newDate,newMonth,newYear;
                int expDate,expMonth,expYear;
                LocalDate manufactureDate;
                LocalDate expirationDate;
                do{
                   //ngày sản xuất
                   System.out.println("Enter date manufacture");
                   newDate = CheckEmptyAndFormatInt(sc.nextLine(), pd.get(i).getManufactureDate().getDayOfMonth(), 1, 31, "Date Between 1-31");
                   System.out.println("Enter month manufacture");
                   newMonth = CheckEmptyAndFormatInt(sc.nextLine(), pd.get(i).getManufactureDate().getMonthValue(), 1, 12, "Month Between 1-12");
                   System.out.println("Enter year manufacture");
                   newYear = CheckEmptyAndFormatInt(sc.nextLine(), pd.get(i).getManufactureDate().getYear(), 1, 2024, "Year not over 2024");
                   manufactureDate =  LocalDate.of(newYear,newMonth,newDate);
                   //ngày hết hạn
                   System.out.println("Enter date expirationDate");
                   expDate = CheckEmptyAndFormatInt(sc.nextLine(), pd.get(i).getExpirationDate().getDayOfMonth(), 1, 31, "Date Between 1-31");
                   System.out.println("Enter month expirationDate");
                   expMonth= CheckEmptyAndFormatInt(sc.nextLine(), pd.get(i).getExpirationDate().getMonthValue(), 1, 12, "Month Between 1-12");
                   System.out.println("Enter year expirationDate");
                   expYear = CheckEmptyAndFormatInt(sc.nextLine(), pd.get(i).getExpirationDate().getYear(), 1, 1000000, "Year Too Far!");
                   expirationDate =  LocalDate.of(expYear,expMonth,expDate);
                   if(manufactureDate.isAfter(expirationDate)){
                       System.out.println("Your expirationDate is not valid!");
                   }
                }while(manufactureDate.isAfter(expirationDate));
//                //quantity
//                int quantity = Inputter.getAnIteger("Enter quantity", "Do not empty!!");
                //set lại thông tin
                System.out.println("Enter price");
                Double price = CheckEmptyAndFormatDouble(sc.nextLine(), pd.get(i).getPrice(), "Only number");
                pd.get(i).setManufactureDate(manufactureDate);
                pd.get(i).setExpirationDate(expirationDate);
                pd.get(i).setPrice(price);
//                pd.get(i).setQuantity(quantity);
                System.out.println("After Updating : ");
                System.out.println(pd.get(i));
                flag = true;
            }
        }
        if(flag == false){
            System.out.println("Product Does Not Exist");
        }
    }
    
    //hàm xóa sản phẩm
    public void removeProduct() {
        if(pd.isEmpty()){
            System.out.println("Don't have product!");
            return;
        }
        String code = Inputter.getString("Enter Code Of Product [Kxxxx] : ", "Wrong Format!!", "^[K]\\d{4}");
        for(int i = 0; i < pd.size(); i++){
            if(pd.get(i).getCode().equals(code)){
                int choice = Inputter.getAnIteger("Are u sure to remove ?  1(Yes) 2(No) ", "1(Yes) 2(No)", 1, 2);
                if(choice == 1) pd.remove(i);
            }
        }
    }
    
    //check useagain
    public boolean useAgain (){
        Scanner sc = new Scanner(System.in);
        System.out.printf("\nPress ENTER to continue the program\n");
        System.out.printf("\nPress any key to exit the program\n");
        String checkESC = sc.nextLine();
        if(checkESC.isEmpty()) return true;
        else return false;
}
    
    //hàm tìm product
    public Product findProductByCode(String code) {
        for(int i = 0 ; i < pd.size(); i++){
            if(pd.get(i).getCode().equals(code)){
                return pd.get(i);
            }
        }
        return null;
    }
    
    //hàm in list
    public void printList() {
        if(pd.isEmpty()){
            System.out.println("Don't have product!");
            return;
        }
        for (Product product : pd) {
            System.out.println(product);
        }
    }
    
    //hàm tìm sản phẩm hết hạn
    public void showExpiredPrd() {
        boolean flag = false;
        for (Product product : pd) {
            if(product.isExpired()){
                flag = true;
                System.out.println(product);
            }
        }
        if(flag == false){
            System.out.println("Don't have product is expired!");
        }
    }
    
    //hàm tìm sản phẩm đang bán (chưa hết hạn kể cả trùng ngày hết hạn)
    public void showPrdSelling() {
        boolean flag = false;
        for (Product product : pd) {
            if(!product.isExpired()){
                flag = true;
                System.out.println(product);
            }
        }
        if(flag == false){
            System.out.println("Don't have product!");
        }
    }
    
    //hàm trả ra số lượng sản phẩm theo thứ tự
    public ArrayList<Product> stockPrd() {
        ArrayList<Product> subPrd = new ArrayList<>();
        for (Product product : pd) {
            if(!product.isExpired()){
                subPrd.add(product);
            }
        }
        return subPrd;
    }  
    
    public boolean writeToFile(String fileName){
        boolean resutl = false;
        FileOutputStream fos ;
        ObjectOutputStream os = null;
        File file = new File(fileName);
        try {
            fos = new FileOutputStream(file);
            os = new ObjectOutputStream(fos);
            for (Product p : pd) {
                os.writeObject(p);
            }
            resutl = true;
            os.flush();
            os.close();
        } catch (Exception e) {
           e.printStackTrace();
        }   
        return resutl;
    }
    
    //hàm ddocjj từ file
    public boolean readFromFile(String fileName) {
        pd.clear();
        File file = new File(fileName);
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis= new FileInputStream(file);
            ois= new ObjectInputStream(fis);
            Product product; 
            while(fis.available()>0){
                product = (Product) ois.readObject();
                pd.add(product);
            } 
            ois.close();
            fis.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    //hàm check xem người dùng nhập số hay để rỗng và nếu nhập thì có phải số kh
    public int CheckEmptyAndFormatInt(String str, int oldInt, int lowerBound, int upperBound,String errMsg ){
        if(str.isEmpty()){
            return oldInt;
        }else{
            while(true){
                try{
                    int number = Integer.parseInt(str);
                    if(number < lowerBound || number > upperBound){
                        throw new Exception();
                    }
                    return number;
                }catch(Exception e){
                    System.out.println(errMsg);//hien thi cau chui
                    str = sc.nextLine();
                    if(str.isEmpty()) return oldInt;
                }
            }
        }
    }
    
     public Double CheckEmptyAndFormatDouble(String str, Double oldDouble,String errMsg ){
        if(str.isEmpty()){
            return oldDouble;
        }else{
            while(true){
                try{
                    Double number = Double.parseDouble(str);
                    return number;
                }catch(Exception e){
                    System.out.println(errMsg);//hien thi cau chui
                    str = sc.nextLine();
                    if(str.isEmpty()) return oldDouble;
                }
            }
        }
    }
}
