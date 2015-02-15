package BensCryptoPackage;

public class Encryption extends Main {
	public static void Encrypt(){
		UserInputString.split("(?!^)");
		
		char[] characterArray = new char[1];
		characterArray = UserInputString.toCharArray();

		for(int i=0; i<= UserInputString.length()-1;i++){
			if(characterArray[i] ==  'a' || characterArray[i] == 'A'){
				characterArray[i] = '!';
			}else if(characterArray[i] == 'b' || characterArray[i] == 'B'){
				characterArray[i] = '$';
			}else if(characterArray[i] == 'c' || characterArray[i] == 'C'){
				characterArray[i] = '%';
			}else if(characterArray[i] == 'd' || characterArray[i] == 'D'){
				characterArray[i] = '@';
			}else if(characterArray[i] == 'e' || characterArray[i] == 'E'){
				characterArray[i] = '#';
			}else if(characterArray[i] == 'f' || characterArray[i] == 'F'){
				characterArray[i] = '&';
			}else if(characterArray[i] == 'g' || characterArray[i] == 'G'){
				characterArray[i] = '^';
			}else if(characterArray[i] == 'h' || characterArray[i] == 'H'){
				characterArray[i] = '*';
			}else if(characterArray[i] == 'i' || characterArray[i] == 'I'){
				characterArray[i] = ')';
			}else if(characterArray[i] == 'j' || characterArray[i] == 'J'){
				characterArray[i] = '=';
			}else if(characterArray[i] == 'k' || characterArray[i] == 'K'){
				characterArray[i] = '-';
			}else if(characterArray[i] == 'l' || characterArray[i] == 'L'){
				characterArray[i] = '_';
			}else if(characterArray[i] == 'm' || characterArray[i] == 'M'){
				characterArray[i] = '+';
			}else if(characterArray[i] == 'n' || characterArray[i] == 'N'){
				characterArray[i] = '(';
			}else if(characterArray[i] == 'o' || characterArray[i] == 'O'){
				characterArray[i] = '{';
			}else if(characterArray[i] == 'p' || characterArray[i] == 'P'){
				characterArray[i] = '}';
			}else if(characterArray[i] == 'q' || characterArray[i] == 'Q'){
				characterArray[i] = '[';
			}else if(characterArray[i] == 'r' || characterArray[i] == 'R'){
				characterArray[i] = '\\';
			}else if(characterArray[i] == 's' || characterArray[i] == 'S'){
				characterArray[i] = '|';
			}else if(characterArray[i] == 't' || characterArray[i] == 'T'){
				characterArray[i] = ']';
			}else if(characterArray[i] == 'u' || characterArray[i] == 'U'){
				characterArray[i] = '?';
			}else if(characterArray[i] == 'v' || characterArray[i] == 'V'){
				characterArray[i] = '}';
			}else if(characterArray[i] == 'w' || characterArray[i] == 'W'){
				characterArray[i] = ',';
			}else if(characterArray[i] == 'x' || characterArray[i] == 'X'){
				characterArray[i] = '/';
			}else if(characterArray[i] == 'y' || characterArray[i] == 'Y'){
				characterArray[i] = '.';
			}else if(characterArray[i] == 'z' || characterArray[i] == 'Z'){
				characterArray[i] = '>';
			}else if(characterArray[i] == ' '){
				characterArray[i] = '<';
			}else if(characterArray[i] == '.'){
				characterArray[i] = '3';
			}
			System.out.print(characterArray[i]);
		}
		choose();
	}
}
