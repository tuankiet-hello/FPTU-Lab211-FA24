package utils;

import java.util.Scanner;

/*
inputter la mot cai khuon (class) nhung khong dung de duc ra object
ma anh dung class nay de luu nhung ham nhung method ham ho tro cho viec nhap
*/
public class Inputter {
   public static Scanner sc = new Scanner(System.in);
   //nhung medthod ho tro cho viec nhap
   
   //method ep nguoi dung nhap so nguyen chuan
   public static int getAnIteger(String inpMsg, String errMsg){
       System.out.println(inpMsg);//hien thi cau moi nhap
       while(true){
           try{
               int number = Integer.parseInt(sc.nextLine());
               return number;
           }catch(Exception e){
               System.out.println(errMsg);//hien thi cau chui
           }
       }
   } 
   //nhap so nguyen trong khoang a to b
   public static int getAnIteger(String inpMsg, String errMsg, int lowerBound,
                                   int upperBound){
       if(lowerBound > upperBound){
           int tmp = lowerBound;
           lowerBound = upperBound;
           upperBound = tmp;
       }
       System.out.println(inpMsg);//hien thi cau moi nhap
       while(true){
           try{
               int number = Integer.parseInt(sc.nextLine());
               if(number < lowerBound || number > upperBound){
                   throw new Exception();
               }
               return number;
           }catch(Exception e){
               System.out.println(errMsg);//hien thi cau chui
           }
       }
   } 
    //method ep nguoi dung nhap so thuc chuan
   public static double getADouble(String inpMsg, String errMsg){
       System.out.println(inpMsg);//hien thi cau moi nhap
       while(true){
           try{
               double number = Double.parseDouble(sc.nextLine());
               return number;
           }catch(Exception e){
               System.out.println(errMsg);//hien thi cau chui
           }
       }
   } 
   //nhap so nguyen trong khoang a to b
   public static double getADouble(String inpMsg, String errMsg, double lowerBound,
                                   double upperBound){
       if(lowerBound > upperBound){
           double tmp = lowerBound;
           lowerBound = upperBound;
           upperBound = tmp;
       }
       System.out.println(inpMsg);//hien thi cau moi nhap
       while(true){
           try{
               double number = Double.parseDouble(sc.nextLine());
               if(number < lowerBound || number > upperBound){
                   throw new Exception();
               }
               return number;
           }catch(Exception e){
               System.out.println(errMsg);//hien thi cau chui
           }
       }
   } 
   //ham nhap chuoi kh dc de trong
   public static String getString(String inpMsg, String errMsg){
       System.out.println(inpMsg);
       while(true){
           try{
               String str = sc.nextLine();
               if(str.isEmpty()){
                   throw new Exception();
               }
               return str;
           }catch(Exception e){
               System.out.println(errMsg);
           }
       }
   }
   //ham nhap chuoi kh dc de trong va dung format
   public static String getString(String inpMsg, String errMsg, String regex){
       System.out.println(inpMsg);
       while(true){
           try{
               String str = sc.nextLine();
               if(str.isEmpty() || !str.matches(regex)){
                   throw new Exception();
               }
               return str;
           }catch(Exception e){
               System.out.println(errMsg);
           }
       }
   }  
}
