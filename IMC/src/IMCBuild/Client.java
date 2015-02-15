package IMCBuild;

import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.awt.event.*;
import java.awt.*;

import javax.swing.*;

@SuppressWarnings("serial")
public class Client extends JFrame{ //extends means inherits it's methods

	private final int port; //a lot of the same stuff here, the only difference is we're finding a connection
	private JTextField text;
	private JTextField userName;
	private JTextArea chatBox;
	private JTextArea decryptBox;
//	private Rectangle chatBoxGUI;
//	private Rectangle decryptBoxGUI;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	private String message = "";
	private String serverIP;
	private Socket connection;
	public static int key;
	
	
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
    }
	
	public Client(String host, int PORT) {//so here we have the constructor with an IP and and a port number 
		super("Chat : Client");
		port=PORT;
		serverIP=host;
		setSize(500,500);
		setVisible(true);
		userName = new JTextField("User name",10);
		userName.setEditable(true);
		text = new JTextField();
		text.setEditable(false);
		text.addActionListener(new ActionListener() {//this is actually a tiny class written in another class O.o
			public void actionPerformed(ActionEvent event) {
				sendMessage(event.getActionCommand()); //this is every time you hit "enter"
				text.setText("");
			}
		});
		add(userName, BorderLayout.NORTH); //this adds to the JFrame and the second parameter moves things around
		add(text, BorderLayout.SOUTH);
//		chatBoxGUI = new Rectangle(0, 0, getWidth()/2, getHeight());
//		decryptBoxGUI = new Rectangle(getWidth()/2 + 1, 0, getWidth()/2 + 1, getHeight());
		chatBox = new JTextArea();
		decryptBox = new JTextArea(20, 20);
		chatBox.add(new JScrollPane());
		decryptBox.add(new JScrollPane());
		
		chatBox.setEditable(false);
		decryptBox.setEditable(false);
//		chatBox.setBounds(chatBoxGUI);
		add(chatBox, BorderLayout.WEST);
//		decryptBox.setBounds(decryptBoxGUI);
		add(decryptBox, BorderLayout.EAST);
	}
	public void starting() {
		showMessage("To close type \"END\" then exit out.");
		boolean connected = false;
		try {
			do {
				try {
				connect();
				connected=true;
				}
				catch(EOFException eofException) {
					showMessage("\nSorry, the server ended connection");
				}
				catch(IOException ioException) {
				}
			}
			while(!connected);
			setStreams();
			whileChat();
		}
		catch(EOFException eofException) {
			showMessage("\nThe connection ended");
		}
		catch(IOException ioException) {
			ioException.printStackTrace();
		}
		finally {
			close();
		}
	}
	public void connect() throws IOException {
		if(chatBox.getText().contains("Attempting connection")) {
			if(chatBox.getText().contains(" . . .")) {
				chatBox.setText("To close type \"END\" then exit out.\nAttempting connection");
			}
			else {
				showMessage(" .");
			}
		}
		else {
			showMessage("\nAttempting connection");
		}
		connection = new Socket(InetAddress.getByName(serverIP), port);//this is actually what connects the client to the server
		showMessage("\nConnected to: "+connection.getInetAddress().getHostName());
	}
	
	public void setStreams() throws IOException {
		out = new ObjectOutputStream(connection.getOutputStream());
		out.flush();
		in = new ObjectInputStream(connection.getInputStream());
		showMessage("\nStream setup is now made! \n");
	}
	
	//mostly everything else here is repetitive of the server class.
	public void whileChat() throws IOException {
		type(true);
		showMessage("Chat Rules:\n\t+Your username MUST BE your real name\n\t+Do Not Troll, Spam, swear exessively or basically be a jerk");
		showMessageDecrypted(encrypt("This is the Decrypted Chat Box\n"));
		String serverName ="SERVER";
		do {
			try {
				message = (String) in.readObject();
				Scanner temp = new Scanner(message);
				temp.useDelimiter(" - ");
				//serverName=temp.next();
				message = temp.next();
				temp.close();
				showMessageDecrypted("\r\n" + message);
				showMessage("\r\n"+message);
			}
			catch(ClassNotFoundException classNotFoundException) {
				showMessage("\nError receiving message! :(");
			}
		}
		//while(!message.equals(serverName+" - END")||!message.equals(serverName+encrypt("END")));
		while(true);
	}
	public void close() {
		showMessage("\nClosing connections...");
		type(false);
		try {
			out.close();
			in.close();
			connection.close();
		}
		catch(IOException ioException) {
			ioException.printStackTrace();
		}
	}
	public void sendMessage(String message) {
		String newString = "";
		try {
			for(int i=0;i<message.length();i++){
		          int charAtInt=message.charAt(i);
		          charAtInt+=key;
		          while(charAtInt>126){
		        	  charAtInt-=94;
		         }
		         newString+=(char)charAtInt;
		    }
			out.writeObject(userName.getText()+" - "+newString);//the message is sent out
			out.flush();//the message is "flushed" which is really just pushing all bits through like a pump. It wraps it up.
			showMessage("\n"+userName.getText()+" - "+newString);
			showMessageDecrypted(encrypt("\r\n)" + userName.getText() + " - ") + newString);

		}catch(IOException ioException){
			chatBox.append("\nError sending message! :(");
		}
	}
	public void showMessage(final String message){
		SwingUtilities.invokeLater(
			new Runnable() {
				public void run() {
					chatBox.append(message);//this adds text to the text area
					//decryptBox.append(decrypt(message));
				}
			}
		);
	}
	public void showMessageDecrypted(final String message){
		SwingUtilities.invokeLater(
			new Runnable() {
				public void run() {
					decryptBox.append("\r\n" + decrypt(message));//this adds text to the text area
				}
			}
		);
	}

	public void type(final boolean ToF){
	SwingUtilities.invokeLater(
			new Runnable(){
				public void run(){
					text.setEditable(ToF);
				}
			}
		);
	}
}
