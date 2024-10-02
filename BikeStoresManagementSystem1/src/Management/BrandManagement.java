/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Management;

import data.Brand;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class BrandManagement implements IItem<Brand>{
    private List<Brand> list = new ArrayList<Brand>();
    Scanner sc = new Scanner(System.in);
     
    @Override
    public Brand getItem(String id) {
        if (list.isEmpty()) {
            return null;  // No need to iterate if the list is empty
        }

        for (Brand b : list) {
            if (b.getId().equalsIgnoreCase(id)) {
                return b;
            }
        }
        return null;
    }


    @Override
    public boolean addItem(Brand b) {
         try {
            list.add(b);
            return true;
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean updateItem(Brand b) {
        for (Brand brand : list) {
            if (brand.getId() == b.getId()) {
                list.set(IndexById(brand.getId()), b);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteItem(String id) {
        if (list.remove(getItem(id))) {
            System.out.println("Deleted!");
            return true;
        }
        System.out.println("Product does not exist!");
        return false;
    }

    @Override
    public void save() {
        String filepath="01_Brand.txt";
        try (FileWriter fw = new FileWriter(filepath); BufferedWriter writer = new BufferedWriter(fw)) {
            for (Brand b : list) {
                writer.write(b.toString());
                writer.newLine();
            }
             writer.flush();
            System.out.println("Products saved successfully.");
        } catch (IOException ex) {
            System.out.println("Error saving file: " + ex.getMessage());
        }
    }

 @Override
    public void load() {
                String filepath="01_Brand.txt";
        File f = new File(filepath);
        if (!f.exists()) {
            System.out.println("File does not exist.");
            return;
        }
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
                String country=stk.nextToken().trim();
                Brand a = new Brand(id, name,country);
                this.list.add(a);
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }
    

    @Override
    public List<Brand> getAll() {
         return list;
    }

    @Override
    public boolean checkExist(String id) {
         for (Brand a : list) {
            if (a.getId().equalsIgnoreCase(id)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int IndexById(String id) {
         for (int i = 0; i <= list.size() - 1; i++) {
            if (list.get(i).getId().equals(id)) {
                return i;
            }
        }
        return -1;
    }

   
}
