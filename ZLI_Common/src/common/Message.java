package common;

import java.io.Serializable;
import java.util.ArrayList;

import enumType.ClientMessageType;
import enumType.ServerMessageType;

public class Message implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1997977260794511165L;

	// Type of the operation we want from the server to make.
    private ClientMessageType ClientMessageType;

    //Which controller on the client should do the operation
//    private DBControllerType DBcontrollerType;
    
    // Which controller on the server should make the operation.
    private ServerMessageType serverMessageType;
    
    private Object obj;
    
    //private ArrayList<Object> returnedArray;
    
//    public Message(ServerMessageType serverMessageType, ArrayList<Object> returnedArray) {
//		super();
//		this.serverMessageType = serverMessageType;
//		this.returnedArray = returnedArray;
//	}
	//Constructor for client message
	public Message(ClientMessageType clientMessageType, Object obj) {
		super();
		ClientMessageType = clientMessageType;

		this.obj = obj;
	}
	//Constructor for server message
	public Message(ServerMessageType serverMessageType, Object obj) {
		super();
		this.serverMessageType = serverMessageType;
		this.obj = obj;
	}
	 public ClientMessageType getClientMessageType() {
			return ClientMessageType;
		}
	public void setClientMessageType(ClientMessageType clientMessageType) {
		ClientMessageType = clientMessageType;
	}

	public ServerMessageType getServerMessageType() {
		return serverMessageType;
	}

	public void setServerMessageType(ServerMessageType serverMessageType) {
		this.serverMessageType = serverMessageType;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}
	
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return obj.toString();
	}

}
