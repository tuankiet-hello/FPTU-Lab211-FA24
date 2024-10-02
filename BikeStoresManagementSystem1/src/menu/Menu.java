/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menu;

import java.util.ArrayList;
import java.util.Scanner;


public class Menu {

    public ArrayList<String> menulist = new ArrayList<>();
    String titile;
    Scanner sc = new Scanner(System.in);

    public Menu(String titile) {
        this.titile = titile;
    }

    public void addNewOption(String name) {
        menulist.add(name);
    }

    public void print() {
        int count = 1;

        System.out.println("-----" + titile + "--------");
        for (String o : menulist) {
            System.out.println(count + ". " + o);
            count++;
        }
    }

    public int getChoice() {
        System.out.println("Input your choice: ");
        try {
            int c = Integer.parseInt(sc.nextLine());
            return c;
        } catch (Exception e) {
            return -1;
        }
    }

//   public  boolean confirmYesOrNo() {
//       System.out.println("Back to main menu 'Y'|'N': ");
//        try {
//            boolean flag = true;
//            String  option;
//            while (flag) {
//                option = sc.nextLine();
//                if (option.equalsIgnoreCase("y")) {
//                    flag = true;
//                    break;
//                } else if (option.equalsIgnoreCase("n")) {
//                    flag = false;
//                    break;
//                }
//            }
//            return flag;
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//            return false;
//        }
//    }
}
