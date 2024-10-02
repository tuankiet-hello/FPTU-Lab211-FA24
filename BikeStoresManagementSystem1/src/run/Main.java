/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package run;

import Control.ProductControl;
import Management.BrandManagement;
import Management.CategoryManagement;
import Management.ProductManagement;
import data.Brand;
import data.Category;
import data.Product;
import java.util.Scanner;
import menu.Menu;

public class Main {

    public static void main(String[] args) {
        ProductControl p = new ProductControl();
        p.run();
    }
}
