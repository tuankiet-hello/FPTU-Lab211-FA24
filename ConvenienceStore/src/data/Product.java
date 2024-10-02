package data;

import java.io.Serializable;
import java.time.LocalDate;

public class Product implements Serializable{
    private String code;
    private String name;
    private LocalDate manufactureDate;
    private LocalDate expirationDate;
    private int quantity;
    private Double price;
    private String imported = null;
    private String exported = null;

    public Product(String code, String name, LocalDate manufactureDate, LocalDate expirationDate, int quantity, Double price) {
        this.code = code;
        this.name = name;
        this.manufactureDate = manufactureDate;
        this.expirationDate = expirationDate;
        this.quantity = quantity;
        this.price = price;
    }

    // Getters and Setters

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getManufactureDate() {
        return manufactureDate;
    }

    public void setManufactureDate(LocalDate manufactureDate) {
        this.manufactureDate = manufactureDate;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isExpired() {
        return LocalDate.now().isAfter(this.expirationDate);
    }

    public void setImported(String imported) {
        this.imported = imported;
    }

    public void setExported(String exported) {
        this.exported = exported;
    }

    public String getImported() {
        return imported;
    }

    public String getExported() {
        return exported;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getPrice() {
        return price;
    }
    
    
    public boolean isImportExport(String imExport){
        return imExport != null;
    }
    @Override
    public String toString() {
        return String.format("Product [code=%s, name=%s, price=%.3f, manufactureDate=%s, expirationDate=%s, quantity=%d]",
                code, name,price, manufactureDate, expirationDate, quantity);
    }
}
