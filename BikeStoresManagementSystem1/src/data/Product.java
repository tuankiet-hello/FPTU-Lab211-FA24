/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.util.List;


public class Product {
    private String id;
    private String name;
    private Brand brand;
    private Category cate;
    private int modelyear;
    private double price;

    public Product() {
    }

    public Product(String id, String name, Brand brand, Category cate, int modelyear, double price) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.cate = cate;
        this.modelyear = modelyear;
        this.price = price;
    }

   

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Category getCate() {
        return cate;
    }

    public void setCate(Category cate) {
        this.cate = cate;
    }

    public int getModelyear() {
        return modelyear;
    }

    public void setModelyear(int modelyear) {
        this.modelyear = modelyear;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }  
    @Override
    public String toString() {
        String s=String.format("%s, %s, %s, %s, %d, %.2f\n",getId(),getName(),getBrand().getId().toUpperCase(),getCate().getId().toUpperCase(),getModelyear(),getPrice());
        return s;    }


 
  
}