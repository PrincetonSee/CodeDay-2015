package BensCryptoPackage;

import java.util.Scanner;

public class CalebsEncryption {
    private static int key;
    private static int sum=0;
    public static void main(String[] args) {
    	Scanner scan = new Scanner(System.in);
    	System.out.print("Code? ");
        setCode(scan.nextLine());
        while(true){
            String currentLine=scan.nextLine();
            System.out.println("Decrypted: "+decrypt(currentLine));
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
        sum=0;
        for(int i=0;i<a.length();i++){
            sum+=a.charAt(i);
        }
        key= sum/a.length();
        if(key==94){
            key=42;
        }
    }
}
