package IMSBuild;

import java.io.*;//an import with a "*" means "all of it". 
import java.net.*;
import java.util.Scanner;
import java.awt.event.*;
import java.awt.*;

import javax.swing.*;

@SuppressWarnings("serial")
public class Server extends JFrame{

	private final int port;//this is the port number. In the system i set the max to be four digits, but you can actually use what ever num you want
	private JTextField text;//the bottom text field
	private JTextField userName;//the top text field
	private JTextArea chatBox;//the text area
	private ObjectOutputStream out;//this is how the server side sends out info
	private ObjectInputStream in;//this is how the server receives info
	private ServerSocket server;//this is the socket or port it will use
	private Socket connection;//this is the actual connection
	
	public Server(int PORT){//since this is the server version, the constructors parameter is only a port, and not an IP.
		super("ClassChat : Host");
		port=PORT;
		userName = new JTextField("User name",10);
		userName.setEditable(true);
		text = new JTextField();
		text.setEditable(false);
		text.addActionListener(new ActionListener(){//if you don't know actionListners, itemListeners, focusListners, mouseListeners... look them up, they're useful
			public void actionPerformed(ActionEvent event){
				sendMessage(event.getActionCommand());
				text.setText("");
			}
		}
		);
		add(userName, BorderLayout.NORTH);
		add(text, BorderLayout.SOUTH);
		chatBox = new JTextArea();
		chatBox.setEditable(false);
		add(new JScrollPane(chatBox));
		setSize(400,250);
		setVisible(true);
	}
	
	public void starting(){//after the constructor, the test class calls this method to actually get it running
		try{
			server = new ServerSocket(port, 1004);
			String portString=""+port;
			if(port<1000){//this is simply to print off four numbers every time
				portString="0"+portString;
			}
			if(port<100){
				portString="0"+portString;
			}
			if(port<10){
				portString="0"+portString;
			}
			while(true){
				try{
					showMessage("IP Address: "+InetAddress.getLocalHost().getHostAddress()+"\nPort: "+portString+"\n");//this is for reference
					findConnection();
					setStreams();
					whileChat();
				}catch(EOFException eofException){
					showMessage("\nThe connection ended");
				}finally{
					close();
				}
			}
		}catch(IOException ioException){
			ioException.printStackTrace();
		}
	}
	private void findConnection()throws IOException{
		showMessage("\nWaiting for connection . . .");
		connection = server.accept();//this will sit here until it gets a connection made.
		showMessage("\nConnected to "+connection.getInetAddress().getHostName());
	}
	private void setStreams()throws IOException{
		out = new ObjectOutputStream(connection.getOutputStream());//this pretty much cleans a "pipeline" and gets it ready for use
		out.flush();
		in = new ObjectInputStream(connection.getInputStream());//this sets up your end of that "pipeline".
		showMessage("\nStream setup is now made! \n");
	}
	private void whileChat()throws IOException{
		showMessage("Chat Rules:\n\t+Your username MUST BE your real name\n\t+Do Not Troll, Spam, swear exessively or basically be an asshat");
		String message = "Your now connected!";
		sendMessage(message);
		String clientName ="CLIENT";
		type(true);
		do{//this uses a rare do-while (no one really uses them) but because we need to find out when to end it. This occurs via input.
			try{
				message = (String) in.readObject();//this will also sit here and not do anything else until an input comes through
				Scanner temp = new Scanner(message);//I had to include a scanner to differentiate the user-name and the message.
				temp.useDelimiter(" - ");
				clientName=temp.next();
				temp.close();
				showMessage("\n"+message);
			}catch(ClassNotFoundException classNotFoundException){
				showMessage("\nError receiving message! :(");
			}
		}while(!message.equals(clientName+" - END"));
	}
	private void close(){//a simple method to close things up
		showMessage("\nClosing connections...\n");
		type(false);
		try{
			out.close();
			in.close();
			connection.close();
		}catch(IOException ioException){
			ioException.printStackTrace();
		}
	}
	private void sendMessage(String message){//this is how to send messages
		try{
			if(message.equals("Your now connected!")){//this is if you are first connected- i did this little phrase because there
				out.writeObject("Your now connected to the host!");//is not my user-name in there. this message will only occur once
				out.flush();
				showMessage("\nClient connected!");
			}else{
			out.writeObject(userName.getText()+" - " + message);//the message is sent out
			out.flush();//the message is "flushed" which is really just pushing all bits through like a pump. It wraps it up.
			showMessage("\n"+userName.getText()+" - "+ message);
			}
		}catch(IOException ioException){
			chatBox.append("\n Error sending message! :(");
		}
	}
	private void showMessage(final String text){
		SwingUtilities.invokeLater(
			new Runnable(){
				public void run(){
					chatBox.append(text);//this adds text to the text area
				}
			}	
		);
	}
	private void type(final boolean ToF){
		SwingUtilities.invokeLater(
			new Runnable(){
				public void run(){
					text.setEditable(ToF);//this is what permits you to type or not.
				}
			}	
		);
	}
}
