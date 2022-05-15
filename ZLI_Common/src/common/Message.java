package common;

import java.io.Serializable;

import enumType.ClientMessage;
import enumType.DBControllerType;
import enumType.ServerMessage;

public class Message implements Serializable {
	// Type of the operation we want from the server to make.
    private ClientMessage ClientMessageType;

    //Which controller on the client should do the operation
    private DBControllerType DBcontrollerType;
    
    // Which controller on the server should make the operation.
    private ServerMessage serverMessageType;
    
    private Object obj;
    
    public ClientMessage getClientMessageType() {
		return ClientMessageType;
	}

	public Message(ClientMessage clientMessageType, DBControllerType dBcontrollerType, Object obj) {
		super();
		ClientMessageType = clientMessageType;
		DBcontrollerType = dBcontrollerType;
		this.obj = obj;
	}

	public Message(DBControllerType dBcontrollerType, ServerMessage serverMessageType, Object obj) {
		super();
		DBcontrollerType = dBcontrollerType;
		this.serverMessageType = serverMessageType;
		this.obj = obj;
	}

	public void setClientMessageType(ClientMessage clientMessageType) {
		ClientMessageType = clientMessageType;
	}

	public DBControllerType getDBcontrollerType() {
		return DBcontrollerType;
	}

	public void setDBcontrollerType(DBControllerType dBcontrollerType) {
		DBcontrollerType = dBcontrollerType;
	}

	public ServerMessage getServerMessageType() {
		return serverMessageType;
	}

	public void setServerMessageType(ServerMessage serverMessageType) {
		this.serverMessageType = serverMessageType;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

}
