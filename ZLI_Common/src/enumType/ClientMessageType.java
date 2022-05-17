package enumType;

public enum ClientMessageType {
	
	/*General operations*/
	LOGIN,LOGOUT,UpdateLoggedIn,EXIT,
	
	/*customer operations*/
	GetItems, GetProducts,
	
	/*Store Manager operations*/
	GetCustomerDetails,
	
	/*Delivery person operations*/
	UpdateOrderDelivered,GetClientsOrders 
	
	
	
	;
}
