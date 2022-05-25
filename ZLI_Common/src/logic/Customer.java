package logic;

import java.io.Serializable;

import enumType.AccountStatus;

@SuppressWarnings("serial")
public class Customer implements Serializable {
	private Integer id;
	private String firstName;
	private String lastName;
	private String phonNumber;
	private String email;
	private String branch;
	private String creditCardNumber;
	private AccountStatus accountStatus;

	public Customer(Integer id, String firstName, String lastName, String phonNumber, String email, String branch,
			String creditCardNumber, String accountStatus) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phonNumber = phonNumber;
		this.email = email;
		this.branch = branch;
		this.creditCardNumber = creditCardNumber;
		this.accountStatus = setEnum(accountStatus);
	}

	public Customer(Integer id, String firstName, String lastName, String phonNumber, String email, String branch,
			String accountStatus) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phonNumber = phonNumber;
		this.email = email;
		this.branch = branch;
		this.accountStatus = setEnum(accountStatus);
		this.creditCardNumber="Not Available";
	}
	
	public AccountStatus setEnum(String str) {
		switch(str) {
		case "UNCONFIRMED":
			return AccountStatus.UNCONFIRMED;
		case "CONFIRMED":
			return AccountStatus.CONFIRMED;
		case "FROZEN":
			return AccountStatus.FROZEN;
		default:
			System.out.println("Account Status not valid");
			return null;
		}
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhonNumber() {
		return phonNumber;
	}

	public void setPhonNumber(String phonNumber) {
		this.phonNumber = phonNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getCreditCardNumber() {
		return creditCardNumber;
	}

	public void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}

	public AccountStatus getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(String accountStatus) {
		this.accountStatus = setEnum(accountStatus);
	}

}
