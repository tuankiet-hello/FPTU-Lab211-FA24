
package data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import utils.Inputter;

public class ReportManager {
    
    
    public void ExpiredPrd(ProductManager pm) {
        System.out.println("List Expired Product:");
        pm.showExpiredPrd();
    }
    
    public void PrdSelling(ProductManager pm) {
        System.out.println("List Expired Product:");
        pm.showPrdSelling();
    }
    
    public void PrdOutOfStock(ProductManager pm){
        System.out.println("List Product Running Out Of Stock : ");
        ArrayList<Product> prd = pm.stockPrd();
        if(prd.isEmpty()){
            System.out.println("Don't have product!");
            return;
        }
        Collections.sort(prd, new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                if (Integer.compare(o1.getQuantity(), o2.getQuantity()) > 0){
                    return 1;
                }
                return -1;
            }
        });
        for (Product product : prd) {
            System.out.println(product);
        }
    }
    
    public void listImportExport(WarehouseManager wh, ProductManager pm){
        String code = Inputter.getString("Enter Code Of Product [Kxxxx] : ", "Wrong Format!!", "^[K]\\d{4}");
        boolean flag = false;
        Product product = pm.findProductByCode(code);
        if( product != null){
            flag = true;
            System.out.println("List Import/Export of Product Code " + code);
            if(product.isImportExport(product.getImported())){
                wh.printBillByCode(code);
            }else if (product.isImportExport(product.getExported())){
                wh.printBillByCode(code);
            }else {
                System.out.println("Don't have Import/Export of Product Code "+ code);
            }
            
        }
        if(flag == false){
            System.out.println("Does Not Exist Product");
        }
    }
}
