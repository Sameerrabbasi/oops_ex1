package com.smallworld;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.*;

public class TransactionDataFetcher {
	private List<Transaction> transactions;
    /**
     * Returns the sum of the amounts of all transactions
     */
    public double getTotalTransactionAmount() {
    	double sum=0;
    	for(Transaction t: transactions){
    		sum+=t.getAmount();
    	}
    	return sum;
        //throw new UnsupportedOperationException();
    }

    /**
     * Returns the sum of the amounts of all transactions sent by the specified client
     */
    public double getTotalTransactionAmountSentBy(String senderFullName) {
        double sum=0;
    	for(Transaction t: transactions)
    		if(t.getSentBy().equals(senderFullName)) 
    			sum+=t.getAmount();
    	
    	return sum;
    }

    /**
     * Returns the highest transaction amount
     */
    public double getMaxTransactionAmount() {
        double max=0;
    	for(Transaction t: transactions)
    		if(max<t.getAmount()) 
    			max=t.getAmount();
    	
    	return max;
    }

    /**
     * Counts the number of unique clients that sent or received a transaction
     */
    //Map<String, Integer> map = new HashMap<>();
    public long countUniqueClients() {
        Map<String,String> map=new HashMap<>();
        for(Transaction t: transactions){
        	map.put(t.getSentBy(),t.getSentBy());
        	map.put(t.getRecievedBy(),t.getSentBy());
    	}
        return (long)map.size();
    }

    /**
     * Returns whether a client (sender or beneficiary) has at least one transaction with a compliance
     * issue that has not been solved
     */
    public boolean hasOpenComplianceIssues(String clientFullName) {
        boolean hasComplianceIssue=false;
    	for(Transaction t: transactions)
    		if((t.getSentBy().equals(clientFullName) || t.getBeneficiary().equals(clientFullName)) && t.getComplianceIssue()) {
	    		hasComplianceIssue=true;
    			break;
    		}

    	return hasComplianceIssue;
    }

    /**
     * Returns all transactions indexed by beneficiary name
     */
    public Map<String, Transaction> getTransactionsByBeneficiaryName() {
        Map<String,Transaction> map=new HashMap<>();
        for(Transaction t: transactions){
        	if(t.getBeneficiary()!=null)
        		map.put(t.getBeneficiary(),t);
	    }
        return map;
    }

    /**
     * Returns the identifiers of all open compliance issues
     */
    public Set<Integer> getUnsolvedIssueIds() {
        Set<Integer> ids=new HashSet<Integer>();
        for(Transaction t: transactions){
            if(t.getComplianceIssue())
                ids.add(t.getId());
        }
        return ids;
    }

    /**
     * Returns a list of all solved issue messages
     */
    public List<String> getAllSolvedIssueMessages() {
        List<String> messages=new ArrayList<String>();
        for(Transaction t: transactions){
            if(!t.getComplianceIssue())
                messages.add(t.getSolvedIssueMessage());
        }
        return messages;
    }

    /**
     * Returns the 3 transactions with the highest amount sorted by amount descending
     */
    public List<Transaction> getTop3TransactionsByAmount() {
        List<Transaction> ts=new ArrayList<Transaction>(3);
    	ts.add(new Transaction(1,0));
    	ts.add(new Transaction(2,0));
    	ts.add(new Transaction(3,0));
    	
            for(Transaction t: transactions){
    		if(ts.get(0).getAmount()<t.getAmount()){
    			ts.set(1,ts.get(0));
    			ts.set(0,t);
    		}else if(ts.get(1).getAmount()<t.getAmount()){
    			ts.set(2,ts.get(1));
    			ts.set(1,t);
    		}else
    			ts.set(2,t);
    	}
    	return ts;
    }

    /**
     * Returns the senderFullName of the sender with the most total sent amount
     */
    public Optional<String> getTopSender() {
        Map<String,Double> map=new HashMap<>();
        for(Transaction t: transactions){
        	if(map.containsKey(t.getSentBy()))
        		map.put(t.getSentBy(),map.get(t.getSentBy())+t.getAmount());
        	else map.put(t.getSentBy(),0.0);
	    }
        
        String topSender=null;
        double max=0;
        for (String key : map.keySet()) {
        	if(max<map.get(key)){
        		max=map.get(key);
                topSender=key;
            }
    	}
	    return Optional.of(topSender);
    }
    // public static void main(String[] args) {
        
    // }
}