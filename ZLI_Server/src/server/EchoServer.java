// This file contains material supporting section 3.7 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 
package server;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import DBConnector.CustomerDBConnector;
import DBConnector.CustomerServiceWorkerDBConnector;
import DBConnector.DeliveryDBConnector;
import DBConnector.GeneralConnector;
import DBConnector.SmDBConnector;
import DBConnector.WorkerDBConnector;
import common.Message;
import enumType.ServerMessageType;
import logic.Customer;
import logic.CustomerDetails;
import logic.Item;
import logic.Order;
import logic.OrderDelivery;
import logic.Product;
import logic.Survey;
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
		User user = null;
		Message message = (Message) msg;
		System.out.println("Message received: " + msg + " from " + client);
		String splitString[] = message.toString().split(" ");
		switch (message.getClientMessageType()) {
		case LOGIN:
			try {// ---------------------we need the row below****
				user = GeneralConnector.getUserInfo(splitString[0]);
				message = new Message((user == null) ? ServerMessageType.FAILED : ServerMessageType.SUCCEED, user);
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
			break;
		case GetItems:
			try {
				ArrayList<Item> rs = GeneralConnector.getItems();
				message = new Message((rs == null) ? ServerMessageType.FAILED : ServerMessageType.SUCCEED, rs);
				client.sendToClient(message);
			} catch (Exception e) {
				// TODO: handle exception
			}
			break;
		case GetCustomer:
			try {
				Customer rs = CustomerDBConnector.getCustomer((User) message.getObj());
				message = new Message((rs == null) ? ServerMessageType.FAILED : ServerMessageType.SUCCEED, rs);
				client.sendToClient(message);
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		case GetCustomerOrders:
			try {
				ArrayList<Order> rs = CustomerDBConnector.getCustomerOrders((User) message.getObj());
				message = new Message((rs == null) ? ServerMessageType.FAILED : ServerMessageType.SUCCEED, (Object) rs);
				client.sendToClient(message);
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;

		case UpadteOrderStatus:
			try {
				boolean succeeded = CustomerDBConnector.updateOrderStatus((Order) message.getObj());
				client.sendToClient(
						new Message(succeeded ? ServerMessageType.SUCCEED : ServerMessageType.FAILED, succeeded));

			} catch (IOException e) {
				e.printStackTrace();
			}
			break;

		case GetClientsOrders:
			try {
				ArrayList<Order> rs = GeneralConnector.getOrders();
				message = new Message((rs == null) ? ServerMessageType.FAILED : ServerMessageType.SUCCEED, rs);
				client.sendToClient(message);
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		case GetClientsOrdersDelivery:
			try {
				ArrayList<OrderDelivery> rs = DeliveryDBConnector.GetClientsOrdersDelivery();
				message = new Message((rs == null) ? ServerMessageType.FAILED : ServerMessageType.SUCCEED, rs);
				client.sendToClient(message);
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
			
		case GetSurveys: //added by Gal
			try {
				ArrayList<Survey> rs = CustomerServiceWorkerDBConnector.LoadSurveys();
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
				
				boolean succeeded = GeneralConnector.UpdateLoggedIn(splitString[0],Integer.parseInt(splitString[1]));
				System.out.println("client " + client + " logout " + (succeeded ? "succeed" : "faild"));
				client.sendToClient(new Message(ServerMessageType.SUCCEED, succeeded));
			} catch (IOException e) {
			}
			break;
		case AddComplaint: // added by gal
			try {
				@SuppressWarnings("unchecked")
				ArrayList<Object> data = (ArrayList<Object>) message.getObj();
				Integer CustomerID = (Integer) data.get(0);

		    	String Date = (String) data.get(1);
		    	String endDate = (String) data.get(2);
		    	String Text = (String) data.get(3);
				boolean succeeded = CustomerServiceWorkerDBConnector.UpdateComplaint(CustomerID, Date,endDate, Text);
				client.sendToClient(new Message(succeeded ? ServerMessageType.SUCCEED : ServerMessageType.FAILED, succeeded));// changed
																													// from
																													// sendToAllClient
			} catch (IOException e) {
			}
			break;
			
		case UpdateSurveyComment: // added by gal
			try {
				@SuppressWarnings("unchecked")
				ArrayList<Object> data = (ArrayList<Object>) message.getObj();
				Integer surveyID = (Integer) data.get(0);
		    	Integer customerID= (Integer) data.get(1);
		    	String surveyType = (String) data.get(2);
		    	String userName= (String) data.get(3);
		    	Integer ans1 = (Integer) data.get(4);
		    	Integer ans2 = (Integer) data.get(5);
		    	Integer ans3 = (Integer) data.get(6);
		    	Integer ans4 = (Integer) data.get(7);
		    	Integer ans5 = (Integer) data.get(8);
		    	Integer ans6 = (Integer) data.get(9);
				boolean succeeded = WorkerDBConnector.UpdateSurveyComment(surveyID, surveyType, userName, customerID, ans1, ans2, ans3, ans4, ans5, ans6);
				client.sendToClient(new Message(succeeded ? ServerMessageType.SUCCEED : ServerMessageType.FAILED, succeeded));// changed
																													// from
																													// sendToAllClient
			} catch (IOException e) {
			}
			break;
		case AddConclusion: // added by gal
			try {
				// System.out.println(splitString[0] + " test");
				@SuppressWarnings("unchecked")
				ArrayList<Object> temp = (ArrayList<Object>) message.getObj();
				Survey survey = (Survey) temp.get(0);
				File conclusionFile = (File) temp.get(1);
				boolean succeeded = CustomerServiceWorkerDBConnector.AddConclusions(survey, conclusionFile);
				client.sendToClient(
						new Message(succeeded ? ServerMessageType.SUCCEED : ServerMessageType.FAILED, succeeded));// changed
																													// from
																													// sendToAllClient
			} catch (Exception e) {
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
			
		case CheckOrCreateSurveyResults: // added by gal
			try {
				Integer surveyID = Integer.parseInt((String)message.getObj());
				System.out.println(splitString[0] + " test");
				boolean succeeded = WorkerDBConnector.CheckOrCreateSurveyResults(surveyID);
				client.sendToClient(
						new Message(succeeded ? ServerMessageType.SUCCEED : ServerMessageType.FAILED, succeeded));// changed
																													// from
																													// sendToAllClient
			} catch (IOException e) {
			}
			break;
		case EXIT:
			try {
				ServerUI.aFrame.delClient(client, getNumberOfClients());
				boolean succeeded = GeneralConnector.UpdateLoggedIn(splitString[0], 0);
				System.out.println("client " + client + " Exit operation " + (succeeded ? "succeed" : "faild"));

				client.sendToClient(new Message(ServerMessageType.SUCCEED, succeeded));
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
