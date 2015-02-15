//Edited by Princeton See
package IMSBuild;

import java.io.*;//an import with a "*" means "all of it". 
import java.net.*;
import java.util.HashMap;
import java.util.LinkedList;
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
	private ObjectInputStream in;//this is how the server receives info
	private ServerSocket server;//this is the socket or port it will use
	private LinkedList<Thread> threadList;
	private LinkedList<Socket> connectionList;
	private HashMap<Socket, ObjectOutputStream> outputMap;

	public Server(int PORT){//since this is the server version, the constructors parameter is only a port, and not an IP.
		super("ClassChat : Host");
		threadList = new LinkedList<Thread>();
		connectionList = new LinkedList<Socket>();
		outputMap = new HashMap<Socket, ObjectOutputStream>();
		port=PORT;
		userName = new JTextField("User name",10);
		userName.setEditable(true);
		text = new JTextField();
		text.setEditable(false);
		text.addActionListener(new ActionListener(){//if you don't know actionListners, itemListeners, focusListners, mouseListeners... look them up, they're useful
			public void actionPerformed(ActionEvent event){
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
		while(1 != 2)
		{
			Socket connection = server.accept();//this will sit here until it gets a connection made.
			connectionList.add(connection);
			outputMap.put(connection, new ObjectOutputStream(connection.getOutputStream()));
			send(connection, "You Are Now Connected!");
			showMessage("\nConnected to "+connection.getInetAddress().getHostName());
			Thread t = new Thread(new MessageListener(this, connection));
			t.start();
			threadList.add(t);
		}
	}
	private void close(){//a simple method to close things up
		showMessage("\nClosing connections...\n");
		type(false);
		try{
			in.close();
			for(Socket s : connectionList)
			{
				s.close();
			}
		}catch(IOException ioException){
			ioException.printStackTrace();
		}
	}
	public synchronized void showMessage(final String text){
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

	public void sendToOthers(final String text, Socket soc)
	{
		for(Socket s : connectionList)
		{
			if(s != soc)
			{
				boolean disconnect = send(s, text, false);
				if(disconnect)
				{
					connectionList.remove(s);
					outputMap.remove(s);
				}
			}
		}

	}

	public void send(Socket soc, String text)
	{
		try {
			outputMap.get(soc).writeObject(text);
		} catch (IOException e) {
			sendToOthers("One or more connections have been disconnected.", soc);
			return;
		}
	}
		
	public boolean send(Socket soc, String text, Boolean disconnected)
	{
		disconnected = false;
		try {
			outputMap.get(soc).writeObject(text);
		} catch (IOException e) {
			sendToOthers("One or more connections have been disconnected.", soc);
			return true;
		}
		return disconnected;
	}
}
