/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Management.BrandManagement;
import Management.CategoryManagement;
import Management.ProductManagement;
import data.Brand;
import data.Category;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import data.Product;
import java.util.Collection;
import java.util.Collections;
import menu.Menu;
import utils.Inputter;

public class ProductControl {

    private ProductManagement p = new ProductManagement();
    private BrandManagement b = new BrandManagement();
    private CategoryManagement c = new CategoryManagement();
    Scanner sc = new Scanner(System.in);
    public ProductControl() {
        c.load();
        b.load();
        p.load();
         
    }
    List<Product> productList = p.getAll() ;
    public void create() {
        //id
          Collections.sort(productList, new Comparator<Product>() {
            @Override
            public int compare(Product p1, Product p2) {
                return p1.getId().compareTo(p2.getId());
            }
        }); 
        String lastId;
        int numId;
        if(productList.isEmpty()){
            lastId = "PRO000";
            numId = Integer.parseInt(lastId.substring(3)) + 1;
            String newId = String.format("PRO%03d", numId);
        }else{
            lastId = productList.get(productList.size()-1).getId();
            numId = Integer.parseInt(lastId.substring(3)) + 1;
        }
        String newId = String.format("PRO%03d", numId);
        //name
         String newName = Inputter.getString("Input name of product: ", 
                    "That fielđ is required!!!").trim();
//        //Brand id:
        String newBrandId;
        while(true){
            newBrandId = Inputter.getString("Input BrandId of product[BXXX]: ", 
                "Fill in the format[BXXX]!!!", "^[Bb]\\d{3}").trim().toUpperCase();
            Brand pos = b.getItem(newBrandId);
            if(pos != null) break;
        }
//        //Category id:
        String newCategoryId;
        while(true){
            newCategoryId = Inputter.getString("Input CategoryId of product[CXXX]: ", 
                "Fill in the format[CXXX]!!!", "^[Cc]\\d{3}$").trim().toUpperCase();
            Category pos = c.getItem(newCategoryId);
            if(pos != null) break;
        }
//        //Model year:
        int newModelYear = 0;
        do{
              newModelYear = Inputter.getAnInteger("Input model year[1,2024] of product: ", 
                "That field is required and must start from 1 to 2024!!!" );
        }while(newModelYear < 1 || newModelYear > 2024);
       
//        //List price:
        double newListPrice = 0;
        do{
            newListPrice = Inputter.getADouble("Input list price(price > 0)of product: ", 
                "That field is required and is a positive number!!!");
        }while(newListPrice < 0);
         
//        //lưu:
        Product newPr = new Product(newId, newName, b.getItem(newBrandId), 
                                       c.getItem(newCategoryId), newModelYear, newListPrice);
        productList.add(newPr);
        System.out.println("Creat successfully!");
    }

    
    //+ SearchProductInformationByName:
    public void searchProductInformationByName(){
        String inputName = Inputter.getString("Input a part of product name: ", 
                    "That field is required!!!").toLowerCase();
        ArrayList<Product> suitableProducts = new ArrayList<>();
        for (Product product : productList) {
            if(product.getName().toLowerCase().contains(inputName)){
                suitableProducts.add(product);
            }
        }
        if(suitableProducts.isEmpty()){
            System.out.println("Have no any Product");
            return;
        }
        Collections.sort(suitableProducts, new Comparator<Product>() {
            @Override
            public int compare(Product p1, Product p2) {
                return p1.getModelyear() > p2.getModelyear() ? 1 : -1;
            }
        }); 
        for (Product suitableProduct : suitableProducts) {
            System.out.println(suitableProduct);
        }
    }

    public void update() {
          String keyId = Inputter.getString
        ("Input the Id of the product you want to update: ",
            "That field is required!!!").toUpperCase();
        Product pro = null;
        for(int i = 0 ; i< productList.size(); i++){
            if(productList.get(i).getId().equals(keyId)){
               pro = productList.get(i);
               break;
            }
        }
        if(pro == null){
            System.out.println("Product does not exist!");
        }else{
            System.out.println("The Pro information before updating:");
            System.out.println(pro.toString());
            //
            System.out.println("Are you sure you want to update this product information ?");
            String choose = Inputter.getString("Yes/No[Y/N]: ", 
                    "That field is required!!!", "[YyNn]");
            if(!choose.matches("[YyNn]")) {
                System.out.println("Updating is failure!");
                return;
            }
            //
            System.out.println("Updating...");
            //Name
            System.out.println("Input Name of product:");
           String newName =  Inputter.sc.nextLine();
            if(!(newName.matches("\\s*"))){
                pro.setName(newName);
            }
            //BrandID
            System.out.println("Input BrandId of product:");
//            Nhập mã mới nếu trông có nghĩa khong cần cập nhật
            String newBrandId = Inputter.sc.nextLine().toUpperCase();
            if(!(newBrandId.matches("\\s*"))){
              Brand br = b.getItem(newBrandId);
                if(br != null) pro.setBrand(br);
               
            }
            //Cate
            System.out.println("Input CategoryId of product:");
//            Nhập mã mới nếu trông có nghĩa khog cần cập nhật
            String newCategoryId = Inputter.sc.nextLine().toUpperCase();
            if(!(newCategoryId.matches("\\s*"))){
                Category ca = c.getItem(newCategoryId);
                if(ca != null)  pro.setCate(ca);
                   
            }
            //Model
            System.out.println("Input ModelYear of product:");
            String newModelYear = Inputter.sc.nextLine();
            if(!(newModelYear.matches("\\s*"))){
                pro.setModelyear(Integer.parseInt(newModelYear));
            }
            //Price
            System.out.println("InputPrice of product:");
            String newlistPrice = Inputter.sc.nextLine();
            if(!(newlistPrice.matches("\\s*"))){
                pro.setPrice(Double.parseDouble(newlistPrice));
            }
            System.out.println("Updating is successful!");
        }
    }

    public void delete() {
             String keyId = Inputter.getString
        ("Input the Id of the product you want to remove: ",
            "That field is required!!!").toUpperCase();
        Product pro = p.getItem(keyId);
        if(pro == null){
            System.out.println("Product does not exist!");
        }else{
            System.out.println("The Product information before removing:");
            System.out.println(pro.toString());
            //
            System.out.println("Are you sure you want to remove this product ?");
            String choose = Inputter.getString("Yes/No[Y/N]: ", 
                    "That field is required!!!", "[YyNn]");
            if(!choose.matches("[YyNn]")) {
                System.out.println("Removing is failure!");
                return;
            }
            //
            System.out.println("Removing...");
            productList.remove(pro);
            System.out.println("Removing is successful!");
        }
        
//        
//        System.out.println("Input ProductID [PROXXX] need to Delete: ");
//        String id = sc.nextLine();
//        if (p.checkExist(id)) {
//            p.deleteItem(id);
//            System.out.println("Deleted Success!");
//            return;
//        } else {
//            System.out.println("Product does not exist. Deleted Fail!");
//        }
    }

    public void save() {
        p.save();
    }
    public void print() {
//        p.load();
        if(productList.isEmpty()){
            System.out.println("Nothing to print!");
            return;
        }
         Collections.sort(productList, new Comparator<Product>() {
            @Override
            public int compare(Product p1, Product p2) {
                if(p1.getPrice() == p2.getPrice()){
                    return p1.getName().compareTo(p2.getName());
                }
                return p1.getPrice()> p2.getPrice() ? -1 : 1;
            }
        });             
        for (Product c : productList) {           
            System.out.format("%s, %s, %s, %s, %d, %.2f \n", c.getId(), c.getName(),c.getBrand().getName() ,
                      c.getCate().getName() , c.getModelyear(), c.getPrice());
        }
    }
    public void run(){
        Menu menu = new Menu("Car managemnet system menu");
        menu.addNewOption("Add a product");
        menu.addNewOption("Search product by product name");
        menu.addNewOption("Update product");
        menu.addNewOption("Deleted product");
        menu.addNewOption("Save product to file");
        menu.addNewOption("Print list product from file");
        menu.addNewOption("Quit");
//------------------------------------------------------------------------------       
        while (true) {
            menu.print();
            int choice = menu.getChoice();
            switch (choice) {
                case 1: {
                create();
                  break;
                }
                case 2: {
                 searchProductInformationByName();
                  break;
                }
                case 3: {
                 update();
                  break;
                }
                case 4:{
                    delete();
                    break;
                }
                case 5:{
                    p.save();
                    break;
                }
                case 6: {
                    print();
                    break;
                }
                case 7: {
                    return;
                }
            }
        }
    }

}
