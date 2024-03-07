package Entity;

import java.util.*;

public class User {
    private String username;
    private String password;
    private String contact;

    private Integer accountNumber;
    private String role;
    private double accountBalance;

    private boolean chequeApproved;

    private boolean chequeRequest;

    private Set<TransactionHistory> transactionHistoryList;

    private static int accountNumberGenerator=10001;
    private static double initialAmount=1000;
    public User(String username, String password,String contact, String role,boolean chequeApproved,boolean chequeRequest) {
        this.username = username;
        this.password = password;
        this.accountNumber=accountNumberGenerator++;
        this.contact = contact;
        this.role = role;
        this.accountBalance = initialAmount;
        this.chequeApproved=chequeApproved;
        this.chequeRequest = chequeRequest;
        this.transactionHistoryList = new LinkedHashSet<>();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Integer accountNumber) {
        this.accountNumber = accountNumber;
    }
    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }

    public boolean isChequeApproved() {
        return chequeApproved;
    }

    public void setChequeApproved(boolean chequeApproved) {
        this.chequeApproved = chequeApproved;
    }


    public boolean isChequeRequest() {
        return chequeRequest;
    }

    public void setChequeRequest(boolean chequeRequest) {
        this.chequeRequest = chequeRequest;
    }

    public Set<TransactionHistory> getTransactionHistoryList() {
        return transactionHistoryList;
    }

    public void setTransactionHistoryList(Set<TransactionHistory> transactionHistoryList) {
        this.transactionHistoryList = transactionHistoryList;
    }


    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", contact='" + contact + '\'' +
                ", role='" + role + '\'' +
                ", accountBalance=" + accountBalance +
                ", chaqueApproved=" + chequeApproved +
                ", chaqueRequest=" + chequeRequest +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(username, user.username) && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password);
    }


}
