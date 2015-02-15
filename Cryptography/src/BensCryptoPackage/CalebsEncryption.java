package BensCryptoPackage;

import java.util.Scanner;

public class CalebsEncryption {
    private static int key;
    private static int sum=0;
    public static void main(String[] args) {
        // TODO code application logic here
    	Scanner scan = new Scanner(System.in);
    	System.out.print("Code? ");
        setCode(scan.nextLine());
        while(true){
            String currentLine=scan.nextLine();
     //       String encrypted=encrypt(currentLine);
     //       System.out.println("Encrypted: "+encrypted);
            System.out.println("Decrypted: "+decrypt(currentLine));
        }
        
    }
    public static String encrypt(String a){
        String newString="";
        for(int i=0;i<a.length();i++){
            int shanku=a.charAt(i);
            shanku+=key;
            while(shanku>126){
                shanku-=94;//originally 94
            }
            newString+=(char)shanku;
        }
        return newString;
    }
    public static String decrypt(String a){
        String newString="";//Crass zelda #lskh
        for(int i=0;i<a.length();i++){
            int shanku=a.charAt(i);
            shanku-=key;
            while(shanku<32){//Here, he states the minimum ascii character (33=!) Meaning if it goes 
                shanku+=94;//Make it 94, so it loops
            }
            newString+=(char)shanku;//Build up chars to make string
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