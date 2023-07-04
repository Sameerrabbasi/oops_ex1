package com.smallworld.data;

public class Transaction {
	private Integer id;
	private double amount;
	private String sentBy;
	private String recievedBy;
	private String beneficiary;
	private boolean complianceIssue;
	private String solvedIssueMessage;
	
	public Transaction(int id,double amount){
		this.id=id;
		this.amount=amount;
	}
	
	public Integer getId(){return id;}

	public String getSentBy(){return sentBy;} 
	
	public String getRecievedBy(){return recievedBy;}
	
	public String getBeneficiary(){return beneficiary;}
	
	public double getAmount(){return amount;}
	
	public boolean getComplianceIssue(){return complianceIssue;}
	
	public String getSolvedIssueMessage(){return solvedIssueMessage;}
}