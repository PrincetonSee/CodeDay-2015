package BensCryptoPackage;

import java.util.Scanner;

public class Main {
	public static String UserInputString;
	private static Scanner scan = new Scanner(System.in);
	public static void main(String[] args) {
		System.out.println("Console Initialized");
		choose();		
	}
	public static void choose() {
		System.out.println("\nEncrypt or Decrypt?");
		Scanner enOrdescan = new Scanner(System.in);
		String enOrde;
		enOrde = enOrdescan.nextLine();
		if(enOrde.equalsIgnoreCase("Encrypt")||enOrde.equalsIgnoreCase("E")){
			System.out.println("Input your Decrypted message:");
			UserInputString = scan.nextLine();
			Encryption.Encrypt();
		}else if(enOrde.equalsIgnoreCase("Decrypt")||enOrde.equalsIgnoreCase("D")){
			System.out.println("Input your Encrypted message:");
			UserInputString = scan.nextLine();
			Decryption.Decrypt();
		}else{
			scan.close();
			enOrdescan.close();
		    System.exit(0);
		}
	}
}