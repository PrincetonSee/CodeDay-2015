package IMSBuild;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Scanner;

public class MessageListener implements Runnable{
	private Server server;
	private Socket connection;//this is the actual connection
	
	public MessageListener(Server s, Socket c)
	{
		server = s;
		connection = c;
	}
	
	@Override
	public void run() {
		ObjectInputStream in;//this is how the server receives info
		try {
			in = new ObjectInputStream(connection.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			server.showMessage("\nThe connection ended from ML" + e.toString());
			return;
		}//this sets up your end of that "pipeline".
		server.showMessage("\nStream setup is now made! \n");
		server.showMessage("Chat Rules:\n\t+Your username MUST BE your real name\n\t+Do Not Troll, Spam, swear exessively or basically be an asshat");
		String message = "Your now connected!";
		String clientName ="CLIENT";
		do{//this uses a rare do-while (no one really uses them) but because we need to find out when to end it. This occurs via input.
			try{
				message = (String) in.readObject();//this will also sit here and not do anything else until an input comes through
				Scanner temp = new Scanner(message);//I had to include a scanner to differentiate the user-name and the message.
				temp.useDelimiter(" - ");
				clientName=temp.next();
				temp.close();
				server.showMessage("\n"+message);
				server.sendToOthers(message, connection);
			}catch(ClassNotFoundException classNotFoundException){
				server.showMessage("\nError receiving message from ML! :(");
			} catch (IOException e) {
				server.showMessage("\nThe connection ended from ML, ERROR COMES FROM HERE" + e.toString());
				return;
			}
		}while(!message.equals(clientName+" - END"));
	}
}
