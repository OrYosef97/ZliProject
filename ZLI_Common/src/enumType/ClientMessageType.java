package enumType;

public enum ClientMessageType {
	
	/*General operations*/
	LOGIN,LOGOUT,UpdateLoggedIn,EXIT,
	
	/*customer operations*/
	GetItems, GetProducts, GetCustomer,  GetCustomerOrders,UpadteOrderStatus,
	
	/*Store Manager operations*/
	GetCustomerDetails,
	
	/*Delivery person operations*/
	UpdateOrderDelivered,GetClientsOrders, 
	
	/*Customer Service Worker operations*/
	AddComplaint, GetSurveys, AddConclusion, 
	
	/*Worker operations*/
	UpdateSurveyComment,
	
	
	
	;
}
