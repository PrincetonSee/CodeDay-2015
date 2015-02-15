package IMCBuild;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ClientTest {

	public static String encrypt(String a){
	    String newString="";
	    for(int i=0;i<a.length();i++){
	        int charAtInt=a.charAt(i);
	        charAtInt+=Client.key;
	        while(charAtInt>126){
	        	charAtInt-=94;
	        }
	        newString+=(char)charAtInt;
	    }
	    return newString;
	}
	public static String decrypt(String a){
	    String newString="";
	    for(int i=0;i<a.length();i++){
	        int charAtInt=a.charAt(i);
	        charAtInt-=Client.key;
	        while(charAtInt<32){
	        	charAtInt+=94;
	        }
	        newString+=(char)charAtInt;
	    }
	    return newString;
	}
	public static void setCode(String a){
	    int sum=0;
	    for(int i=0;i<a.length();i++){
	        sum+=a.charAt(i);
	    }
	    Client.key= sum/a.length();
	    if(Client.key==94){
	        Client.key=42;
	    }
	}
	public static void main(String[] args) {
		Client test;
		boolean confirmedIP = false;
		String IP = "127.0.0.1";
		setCode("Crapola");	
        while(!confirmedIP) {
			Object IPA = JOptionPane.showInputDialog(null, "Enter a valid IP address:", "IP ADDRESS", JOptionPane.QUESTION_MESSAGE);
			if(!((String)IPA).isEmpty()) {
				IP=(String)IPA;
				confirmedIP=true;
			}
			else {
				int response = JOptionPane.showConfirmDialog(null, "You must enter a valid IP Address. Do you wish to retry?", "CANCEL/INVLID IP Address",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if(response == JOptionPane.NO_OPTION)
					return;
			}
		}
		boolean confirmedPort = false;
		int portNum = 0;
		while(!confirmedPort) {
			Object port = JOptionPane.showInputDialog(null, "Enter a valid port number of 4 digits (MUST NOT BE \"0000\"): ", "PORT NUMBER", JOptionPane.QUESTION_MESSAGE);
			try {
				portNum = Integer.parseInt((String)port);
				if(((String)port).length() == 4 && !((String)port).equals("0000"))
					confirmedPort=true;
				else {
					int response = JOptionPane.showConfirmDialog(null, "You must enter a valid port number. Do you wish to retry?", "CANCEL/INVLID PORT",
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					if(response == JOptionPane.NO_OPTION)
						return;
				}
			}
			catch(Exception e) {
				int response = JOptionPane.showConfirmDialog(null, "You must enter a valid port number. Do you wish to retry?", "CANCEL/INVLID PORT",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if(response == JOptionPane.NO_OPTION)
					return;
			}
		}		
		test = new Client(IP,portNum);
		test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		test.starting();
	}
}