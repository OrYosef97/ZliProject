// This file contains material supporting section 3.7 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 
package server;

import java.io.IOException;
import java.util.ArrayList;

import DBConnector.*;
import DBConnector.GeneralConnector;
import common.Message;
import enumType.ServerMessageType;
import logic.CustomerDetails;
import logic.Order;
import logic.Product;
import logic.User;
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
	// private ServerScreenController serverSController;

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
		// this.serverSController=serverSController;
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
		Message message = (Message) msg;
		System.out.println("Message received: " + msg + " from " + client);
		String splitString[] = message.toString().split(" ");
		switch (message.getClientMessageType()) {
		case LOGIN:
			try {// ---------------------we need the row below****
				User rs = GeneralConnector.getUserInfo(splitString[0]);
				message = new Message((rs == null) ? ServerMessageType.FAILED : ServerMessageType.SUCCEED, rs);
				client.sendToClient(message);
			} catch (IOException e) {
			}
			break;
		case GetProducts:
			try {

				ArrayList<Product> rs = GeneralConnector.getProducts();
				message = new Message((rs == null) ? ServerMessageType.FAILED : ServerMessageType.SUCCEED, rs);
				client.sendToClient(message);
			} catch (Exception e) {
				e.printStackTrace();
			}
		case GetClientsOrders:
			try {

				ArrayList<Order> rs = GeneralConnector.getOrders();
				message = new Message((rs == null) ? ServerMessageType.FAILED : ServerMessageType.SUCCEED, rs);
				client.sendToClient(message);
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;

		case GetCustomerDetails: // added by yaniv
			try {
				System.out.println("got to echoserver");
				ArrayList<CustomerDetails> rs = SmDBConnector.getCustomerDetails();
				message = new Message((rs == null) ? ServerMessageType.FAILED : ServerMessageType.SUCCEED, rs);
				client.sendToClient(message);
			} catch (IOException e) {

			}
			break;

		case UpdateLoggedIn: // added by gal
			try {

				int status = Integer.parseInt(splitString[1]); // turn the status into int
				System.out.println(splitString[0] + " " + status + " test");
				if (status == 0)
					ServerUI.aFrame.delClient(client, getNumberOfClients());// update server gui
				boolean succeeded = GeneralConnector.UpdateLoggedIn(splitString[0], status);
				client.sendToClient(
						new Message(succeeded ? ServerMessageType.SUCCEED : ServerMessageType.FAILED, succeeded));// changed
																													// from
																													// sendToAllClient
			} catch (IOException e) {
			}
			break;
		case UpdateOrderDelivered: // added by gal
			try {

				System.out.println(splitString[0] + " test");
				boolean succeeded = DeliveryDBConnector.UpdateOrderDelivered(splitString[0]);
				client.sendToClient(
						new Message(succeeded ? ServerMessageType.SUCCEED : ServerMessageType.FAILED, succeeded));// changed
																													// from
																													// sendToAllClient
			} catch (IOException e) {
			}
			break;
		case EXIT:
			try {
				client.sendToClient("closed");
				ServerUI.aFrame.delClient(client, getNumberOfClients());
				boolean succeeded = GeneralConnector.UpdateLoggedIn(splitString[0], 0);
				System.out.println("client " + client + " Exit operation " + (succeeded ? "succeed" : "faild"));
				client.close();
			} catch (IOException e) {
			}
			break;
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
