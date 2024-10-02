package data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Bill implements Serializable{
    private static final AtomicInteger codeCounter = new AtomicInteger(1000000); // bắt đầu từ 1000000

    private int code;
    private LocalDateTime timestamp;
    private List<Item> items;
    private String type;

    public Bill() {
        this.code = codeCounter.getAndIncrement();
        this.timestamp = LocalDateTime.now();
        this.items = new ArrayList<>();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    public int getCode() {
        return code;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public List<Item> getItems() {
        return items;
    }

    public boolean checkItemInBill(String prdCode){
        for(int i = 0; i < items.size(); i++){
            if(items.get(i).getProduct().getCode().equals(prdCode)){
                return true;
            }
        }
        return false;
    }
    
    @Override
    public String toString() {
        return String.format(" BillCode=%d, TimeStamp=%s \n        Items=%s", code, timestamp, items);
    }
 
}
