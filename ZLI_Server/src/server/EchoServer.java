// This file contains material supporting section 3.7 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 
package server;
import logic.Order;
import java.io.IOException;
import java.util.ArrayList;
import DBConnector.*;

import ocsf.server.AbstractServer;
import ocsf.server.ConnectionToClient;

/**
 * This class overrides some of the methods in the abstract superclass in order
 * to give more functionality to the server.
 *
 * @author Dr Timothy C. Lethbridge
 * @author Dr Robert Lagani&egrave;re
 * @author Fran&ccedil;ois B&eacute;langer
 * @author Paul Holden
 * @version July 2000
 */

public class EchoServer extends AbstractServer {
	// Class variables *************************************************
	//private ServerScreenController serverSController;

	/**
	 * The default port to listen on.
	 */
	 final public static int DEFAULT_PORT = 5555;

	// Constructors ****************************************************

	/**
	 * Constructs an instance of the echo server.
	 *
	 * @param port The port number to connect on.
	 * 
	 */

	public EchoServer(int port) {
		super(port);
		//this.serverSController=serverSController;
	}

	// Instance methods ************************************************

	/**
	 * This method handles any messages received from the client.
	 *
	 * @param msg    The message received from the client.
	 * @param client The connection from which the message originated.
	 * @param
	 */
	public void handleMessageFromClient(Object msg, ConnectionToClient client)

	{

		System.out.println("Message received: " + msg + " from " + client);
		String splitString[] = msg.toString().split(" ");
		switch (splitString[0]) {
		case "getUserInfo":
			try {//---------------------we need the row below****
				String rs=mysqlConnection.getUserInfo(splitString[1]);
				client.sendToClient((Object)rs);
				} catch (IOException e) {
				}
			break;
			
			
		case "getOrder":
			try {//---------------------we need the row below****
			Order rs=mysqlConnection.getOrder(splitString[1]);
			client.sendToClient((Object)rs);
			} catch (IOException e) {
			}
		break;
		
		case "updateColor":
			try {
			boolean succeeded = mysqlConnection.updateColor(splitString[1], splitString[2]);
			client.sendToClient(succeeded ? (Object)"success" : (Object)"failed");//changed from sendToAllClient
			}catch(IOException e) {
			}
			break;
			
		case "updateDate":
			try {
				boolean succeeded = mysqlConnection.updateDate(splitString[1], splitString[2]);
				client.sendToClient(succeeded ? (Object)"success" : (Object)"failed");//changed from sendToAllClient
			} catch (IOException e) {
			}
		case "getOrders":
			try {
				ArrayList<Order> rs=mysqlConnection.getOrders();
				client.sendToClient(rs);
			}catch (IOException e) {
				
			}
		case "disconnect":
			try {
				client.sendToClient("");
				client.close();
				ServerUI.aFrame.delClient(client,getNumberOfClients());
			} catch (IOException e) {
			}
		default:
			break;
		}
	}

	/**
	 * This method overrides the one in the superclass. Called when the server
	 * starts listening for connections.
	 */
	protected void serverStarted() {
		System.out.println("Server listening for connections on port " + getPort());
//		ServerUI.aFrame.setTextToConsole("Server listening for connections on port " + getPort());

	}

	/**
	 * This method overrides the one in the superclass. Called when the server stops
	 * listening for connections.
	 */
	protected void serverStopped() {
		System.out.println("Server has stopped listening for connections.");
//		ServerUI.aFrame.setTextToConsole("Server has stopped listening for connections.");
	}
	
	@Override
	protected void clientConnected(ConnectionToClient client) {
		super.clientConnected(client);
		ServerUI.aFrame.addClient(client);
	}

	@Override
	synchronized protected void clientDisconnected(ConnectionToClient client) {
		ServerUI.aFrame.delClient(client, getNumberOfClients());
	}
}
//End of EchoServer class
