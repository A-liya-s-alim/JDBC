package com.ilp04.entity;

public class Customer {

    private int customerCode;
    private String customerFirstname;
    private String customerLastname;
    private String address;
    private long phNumber;
    private long aadharNo;

    public Customer(int customerCode, String customerFirstname, String customerLastname, String address, long phNumber,
                    long aadharNo) {
        super();
        this.customerCode = customerCode;
        this.customerFirstname = customerFirstname;
        this.customerLastname = customerLastname;
        this.address = address;
        this.phNumber = phNumber;
        this.aadharNo = aadharNo;
    }

    public Customer() {
		// TODO Auto-generated constructor stub
	}

	public int getCustomerCode() {
        return customerCode;
    }

    public String getCustomerFirstname() {
        return customerFirstname;
    }

    public String getCustomerLastname() {
        return customerLastname;
    }

    public String getAddress() {
        return address;
    }

    public long getPhNumber() {
        return phNumber;
    }

    public void setCustomerCode(int customerCode) {
		this.customerCode = customerCode;
	}

	public void setCustomerFirstname(String customerFirstname) {
		this.customerFirstname = customerFirstname;
	}

	public void setCustomerLastname(String customerLastname) {
		this.customerLastname = customerLastname;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setPhNumber(long phNumber) {
		this.phNumber = phNumber;
	}

	public void setAadharNo(long aadharNo) {
		this.aadharNo = aadharNo;
	}

	public long getAadharNo() {
        return aadharNo;
    }
}
