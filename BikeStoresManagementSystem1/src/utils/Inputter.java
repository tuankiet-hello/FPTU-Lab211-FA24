
package utils;

import java.util.Scanner;

/*
Inputter là 1 cái khuôn, nhưng kh dùng để đúc ra obj mà dùng để lưu những method
hỗ trợ cho việc nhập
*/
public class Inputter {
    //props
    public static Scanner sc = new Scanner(System.in);
    //những method hỗ trợ cho việc nhập
    //method ép ng dùng nhập số nguyên chuẩn
    public static int getAnInteger(String inpMsg, String errMsg){
        System.out.print(inpMsg);//hiển thị mời nhập
        while(true){
            try{
                int number = Integer.parseInt(sc.nextLine());
                return number;
            }catch(Exception e){
                System.out.print(errMsg);
            }
        }
    }
    public static int getAnInteger(String inpMsg, String errMsg,
                                   int lowerBound,int upperBound){
        if(lowerBound > upperBound){
            int tmp = lowerBound;
            lowerBound = upperBound;
            upperBound = tmp;
        }                          
        System.out.print(inpMsg);//hiển thị mời nhập
        while(true){
            try{
                int number = Integer.parseInt(sc.nextLine());
                if(number < lowerBound || number > upperBound){
                    throw new Exception();
                }
                return number;
            }catch(Exception e){
                System.out.print(errMsg);
            }
        }
    }
    //ép ng dùng nhập số thực
    public static double getADouble(String inpMsg, String errMsg){
        System.out.print(inpMsg);//hiển thị mời nhập
        while(true){
            try{
                double number = Double.parseDouble(sc.nextLine());
                return number;
            }catch(Exception e){
                System.out.print(errMsg);
            }
        }
    }
    public static double getADouble(String inpMsg, String errMsg,
                                   double lowerBound,double upperBound){
        if(lowerBound > upperBound){
            double tmp = lowerBound;
            lowerBound = upperBound;
            upperBound = tmp;
        }                          
        System.out.print(inpMsg);//hiển thị mời nhập
        while(true){
            try{
                double number = Double.parseDouble(sc.nextLine());
                if(number < lowerBound || number > upperBound){
                    throw new Exception();
                }
                return number;
            }catch(Exception e){
                System.out.print(errMsg);
            }
        }
    }
    //nhập chuỗi kh đc để trống
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
   //nhập chuỗi kh đc để trống và phải đúng format
    public static String getString(String inpMsg, String errMsg,
                                    String regex){
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
   
  //hàm nhập ngày tháng năm theo format
    
}
