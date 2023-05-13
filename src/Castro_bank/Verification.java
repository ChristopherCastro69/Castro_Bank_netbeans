/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Castro_bank;

/**
 *
 * @author Christopher Castro
 */
public class Verification {
    String customerUsername;
    String accountNumber;
    double amount;
    String typeoftransaction;
    int status;
    
    public Verification() {
        setCustomerUsername("");
        setAccountNumber("");
        setTypeoftransaction("");
    }
    public Verification(String customerUsername, String accountNumber, double amount, String typeoftransaction) {
        setCustomerUsername(customerUsername);
        setAccountNumber(accountNumber);
        setAmount(amount);
        setTypeoftransaction(typeoftransaction);
        setStatus(0);
    }

    public Verification(String customerUsername, String accountNumber, double amount, String typeoftransaction, int status) {
        setCustomerUsername(customerUsername);
        setAccountNumber(accountNumber);
        setAmount(amount);
        setTypeoftransaction(typeoftransaction);
        setStatus(status);
    }
    
    

    public String getCustomerUsername() {
        return customerUsername;
    }

    public void setCustomerUsername(String customerUsername) {
        this.customerUsername = customerUsername;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getTypeoftransaction() {
        return typeoftransaction;
    }

    public void setTypeoftransaction(String typeoftransaction) {
        this.typeoftransaction = typeoftransaction;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
    
}
