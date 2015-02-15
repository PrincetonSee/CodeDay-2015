package BensCryptoPackage;

public class Decryption extends Main {
	public static void Decrypt(){
		UserInputString.split("(?!^)");	
		char[] characterArray = new char[1];
		characterArray = UserInputString.toCharArray();

		for(int i=0; i<= UserInputString.length()-1;i++){
			if(characterArray[i] ==  '!'){
				characterArray[i] = 'a';
			}else if(characterArray[i] == '$'){
				characterArray[i] = 'b';
			}else if(characterArray[i] == '%'){
				characterArray[i] = 'c';
			}else if(characterArray[i] == '@'){
				characterArray[i] = 'd';
			}else if(characterArray[i] == '#'){
				characterArray[i] = 'e';
			}else if(characterArray[i] == '&'){
				characterArray[i] = 'f';
			}else if(characterArray[i] == '^'){
				characterArray[i] = 'g';
			}else if(characterArray[i] == '*'){
				characterArray[i] = 'h';
			}else if(characterArray[i] == ')'){
				characterArray[i] = 'i';
			}else if(characterArray[i] == '='){
				characterArray[i] = 'j';
			}else if(characterArray[i] == '-'){
				characterArray[i] = 'k';
			}else if(characterArray[i] == '_'){
				characterArray[i] = 'l';
			}else if(characterArray[i] == '+'){
				characterArray[i] = 'm';
			}else if(characterArray[i] == '('){
				characterArray[i] = 'n';
			}else if(characterArray[i] == '{'){
				characterArray[i] = 'o';
			}else if(characterArray[i] == '}'){
				characterArray[i] = 'p';
			}else if(characterArray[i] == '['){
				characterArray[i] = 'q';
			}else if(characterArray[i] == '\\'){
				characterArray[i] = 'r';
			}else if(characterArray[i] == '|'){
				characterArray[i] = 's';
			}else if(characterArray[i] == ']'){
				characterArray[i] = 't';
			}else if(characterArray[i] == '?'){
				characterArray[i] = 'u';
			}else if(characterArray[i] == '}'){
				characterArray[i] = 'v';
			}else if(characterArray[i] == ','){
				characterArray[i] = 'w';
			}else if(characterArray[i] == '/'){
				characterArray[i] = 'x';
			}else if(characterArray[i] == '.'){
				characterArray[i] = 'y';
			}else if(characterArray[i] == '>'){
				characterArray[i] = 'z';
			}else if(characterArray[i] == '<'){
				characterArray[i] = ' ';
			}else if(characterArray[i] == '3'){
				characterArray[i] = '.';
			}
			System.out.print(characterArray[i]);
		}
		choose();
	}
}
