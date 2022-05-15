package logic;

import enumType.UserType;

public class User {
	String userName;
	String password;
	UserType userType;
	Integer isLoggedIn;
	public User(String userName, String password, String userType, Integer isLoggedIn) {
		super();
		this.userName = userName;
		this.password = password;
		this.isLoggedIn = isLoggedIn;
		switch (userType) {
		case "Customer":
			this.userType=UserType.CUSTOMER;
			break;
		case "Delivery Person":
			this.userType=UserType.DELIVERY_PERSON;
			break;
		case "Stor Mannager":
			this.userType=UserType.STORE_MANNAGER;
			break;
		default://need to continue with more users
			break;
		};
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public UserType getUserType() {
		return userType;
	}
	public void setUserType(UserType userType) {
		this.userType = userType;
	}
	public Integer getIsLoggedIn() {
		return isLoggedIn;
	}
	public void setIsLoggedIn(Integer isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return userName+" "+password+" "+userType.toString()+ " IsLoggedIn: "+isLoggedIn;
	}

}
