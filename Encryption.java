/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package encryption;

import java.util.Scanner;

/**
 *
 * @author caleb
 */
public class Encryption {
    private static int key;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner Shashank=new Scanner(System.in);
        System.out.print("Code? ");
        setCode(Shashank.nextLine());
        while(1!=2){
            String currentLine=Shashank.nextLine();
            System.out.println(encrypt(currentLine));
            System.out.println(decrypt(currentLine));
        }
    }
    public static String encrypt(String a){
        String newString="";
        for(int i=0;i<a.length();i++){
            int shanku=a.charAt(i);
            shanku+=key;
            while(shanku>126){
                shanku-=94;
            }
            newString+=(char)shanku;
        }
        return newString;
    }
    public static String decrypt(String a){
        String newString="";
        for(int i=0;i<a.length();i++){
            int shanku=a.charAt(i);
            shanku-=key;
            while(shanku<32){
                shanku+=94;
            }
            newString+=(char)shanku;
        }
        return newString;
    }
    public static void setCode(String a){
        int sum=0;
        for(int i=0;i<a.length();i++){
            sum+=a.charAt(i);
        }
        key= sum/a.length();
        if(key==94){
            key=42;
        }
        
        System.out.println((char)key);
    }
}
