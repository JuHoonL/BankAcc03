package com.biz.bank.vo;

public class BankVO {

	String strID;
	int intBalance;
	String strLastDate;
	
	String strIO;
	int intIOCash;

	
	public String getStrID() {
		return strID;
	}
	public void setStrID(String strID) {
		this.strID = strID;
	}
	public int getIntBalance() {
		return intBalance;
	}
	public void setIntBalance(int intBalance) {
		this.intBalance = intBalance;
	}
	public String getStrLastDate() {
		return strLastDate;
	}
	public void setStrLastDate(String strLastDate) {
		this.strLastDate = strLastDate;
	}
	public String getStrIO() {
		return strIO;
	}
	public void setStrIO(String strIO) {
		this.strIO = strIO;
	}
	public int getIntIOCash() {
		return intIOCash;
	}
	public void setIntIOCash(int intIOCash) {
		this.intIOCash = intIOCash;
	}

	public String toString() {
		return "BankVO [strID=" + strID + ", intBalance=" + intBalance + ", strLastDate=" + strLastDate + ", strIO="
				+ strIO + ", intIOCash=" + intIOCash + "]";
	}
	
	
}
