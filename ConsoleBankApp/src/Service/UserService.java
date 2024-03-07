package Service;

import Entity.User;
import Repository.UserRepo;

import java.util.Scanner;

public class UserService {

    private UserRepo userRepo=new UserRepo();
    private static Scanner sc=new Scanner(System.in);
    public void printUsers()
    {
        userRepo.printUsers();
    }
    public  void printCustomersList()
    {
        userRepo.printCustomersList();
    }

    public void createAccount()
    {
        userRepo.createAccount();
    }
    public double checkBalance(Integer accountNumber)
    {
        return userRepo.checkBalance(accountNumber);
    }
    public void transferMoney(double availableAmount,Integer senderAccountNumber)
    {
        userRepo.transferMoney(availableAmount,senderAccountNumber);
    }
    public void withdrawMoney(Integer accountNumber,double availableAmount)
    {
        userRepo.withdrawMoney(accountNumber,availableAmount);
    }
    public void depositMoney(Integer accountNumber)
    {
        userRepo.depositMoney(accountNumber);
    }
    public void issueCheque()
    {
        userRepo.issueCheque();
    }

    public void chequeRequest(Integer accountNumber)
    {
        userRepo.chequeRequest(accountNumber);
    }
    public void clientTransactionHistory(Integer accountNumber)
    {
        userRepo.clientTransactionHistory(accountNumber);
    }
    public void allTransactionHistory()
    {
        userRepo.allTransactionHistory();
    }
    public boolean isAccountNumberPresent(Integer accountNumber)
    {
        return userRepo.isAccountNumberPresent(accountNumber);
    }

    public User login(String username, String password)
    {
        return userRepo.login(username,password);
    }

}
