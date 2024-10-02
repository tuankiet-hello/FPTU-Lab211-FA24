/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Management;

import data.Brand;
import data.Category;
import data.Product;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ProductManagement implements IItem<Product> {

    private List<Product> list = new ArrayList<Product>();
    

    @Override
    public Product getItem(String id) {
        for (Product product : list) {
            if(product.getId().equalsIgnoreCase(id)){
                return product;
            }
        }
        return null;
    }

    @Override
    public boolean checkExist(String id) {
        for (Product a : list) {
            if (a.getId().equalsIgnoreCase(id)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean addItem(Product e) {
        try {
            list.add(e);
            return true;
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean updateItem(Product item) {
        try {
            list.set(IndexById(item.getId()), item);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteItem(String id) {
        if (list.remove(getItem(id))) {
            return true;
        }
        return false;
    }

    @Override
    public List<Product> getAll() {
        return this.list;
    }

    @Override
    public int IndexById(String keyId) {
        for (int i = 0; i <= list.size() - 1; i++) {
            if (list.get(i).getId().equals(keyId)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void save() {
        try {
            String PRODUCT_FILE = "01_Product.txt";
            OutputStreamWriter writter = new OutputStreamWriter(new FileOutputStream(PRODUCT_FILE));
            for (Product pro : list) {
                writter.write(pro.toString());
                writter.write("\n");
            }
            writter.flush();//save xong nhớ dừng
            System.out.println("Products have been successfully saved to " + PRODUCT_FILE);
        } catch (Exception e) {
            System.err.println("Your file is corrupted: " + e);
        }
    }

    @Override
public void load() {
    String filepath="01_Product.txt";
        File f = new File(filepath);
        if (!f.exists()) {
            System.out.println("File does not exist.");
            return;
        }
        BrandManagement b=new BrandManagement();
                b.load();
        CategoryManagement c=new CategoryManagement();
                c.load();
        try {
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
            if (line.isEmpty()) {
                continue; 
            }
                StringTokenizer stk = new StringTokenizer(line, ",");
                
                String id = stk.nextToken().trim();
                String name = stk.nextToken().trim();
                //Brand
                String brandid = stk.nextToken().trim();        
                Brand brand=b.getItem(brandid);
                //Cate
                String cateid = stk.nextToken().trim();
                Category cate=c.getItem(cateid);
                //
                int modelyear = Integer.parseInt(stk.nextToken().trim());
                double price = Double.parseDouble(stk.nextToken().trim());
                Product a = new Product(id, name, brand, cate, modelyear, price);
                list.add(a);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } 
    }
}
