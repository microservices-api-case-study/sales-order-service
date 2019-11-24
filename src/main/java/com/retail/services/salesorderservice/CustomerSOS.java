package com.retail.services.salesorderservice;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Customer_SOS")
public class CustomerSOS {

	@Id
	@Column(name = "cust_id")
	private Long cust_id;

	@Column(name = "cust_first_name")
	private String custFirstName;

	@Column(name = "cust_last_name")
	private String custLastName;

	@Column(name = "cust_email")
	private String cust_email;

	public CustomerSOS() {
	}

	public CustomerSOS(Long cust_id, String custFirstName, String custLastName, String cust_email) {
		super();
		this.cust_id = cust_id;
		this.custFirstName = custFirstName;
		this.custLastName = custLastName;
		this.cust_email = cust_email;
	}

	public Long getCust_id() {
		return cust_id;
	}

	public void setCust_id(Long cust_id) {
		this.cust_id = cust_id;
	}

	public String getCustFirstName() {
		return custFirstName;
	}

	public void setCustFirstName(String custFirstName) {
		this.custFirstName = custFirstName;
	}

	public String getCustLastName() {
		return custLastName;
	}

	public void setCustLastName(String custLastName) {
		this.custLastName = custLastName;
	}

	public String getCust_email() {
		return cust_email;
	}

	public void setCust_email(String cust_email) {
		this.cust_email = cust_email;
	}

}
