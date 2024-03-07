package Repository;

import Entity.TransactionHistory;
import Entity.User;
import Enums.AdminFeature;
import Enums.ClientFeature;

import java.util.*;
import java.util.stream.Collectors;

public class UserRepo {
    private static Set<User> users=new HashSet<>();
    private static Scanner sc=new Scanner(System.in);

    static {
        User admin1=new User("admin1","password","12345","admin",false,false);
        User user1=new User("user1","password","12346","user",false,false);

        User user2=new User("user2","password","12347","user",false,false);

        //User user3=new User("user2","password","12347","user",2500);


        users.add(admin1);
        users.add(user1);
        users.add(user2);
        //users.add(user3);

    }
    public  void printUsers()
    {
        //for(User user:users) //to display line by line
        System.out.println(users); // to display whole user set as object by overriding toString method in User class
    }
    public  void printCustomersList()
    {
        List<User> customers=users.stream().filter(obj->obj.getRole().equals("user")).collect(Collectors.toList());
        for(User user:customers)
        {
            System.out.println("Name : "+user.getUsername()+" | Contact number : "+user.getContact()+" | Account number : "+user.getAccountNumber()+" | Balance : "+user.getAccountBalance()+" | Cheque : "+user.isChequeApproved());
        }
    }
    public void createAccount()
    {
        System.out.println("Enter user name :");
        String name=sc.next();
        System.out.println("Enter password");
        String password=sc.next();
        boolean flag=true;
        while(flag)
        {
            System.out.println("Enter password again");
            String passwordAgain=sc.next();
            if(password.equals(passwordAgain))
            {
                flag=false;
            }
        }
        String number=null;
        flag=true;
        while(flag)
        {
            try
            {
                System.out.println("Enter contact number");
                 number= sc.next();
                Integer.parseInt(number);
                flag=false;

            }
            catch (NumberFormatException e)
            {
                System.out.println(" Not a valid number");
            }
        }
        boolean chequeApproved=false;
        boolean chequeRequest=false;
        System.out.println(" Do you want cheque book Y/N :");
        String chequeInput=sc.next();
        if(chequeInput.equalsIgnoreCase("Y"));
        {
            chequeApproved=true;
            chequeRequest=true;
        }
        User user=new User(name,password,number,"user",chequeApproved,chequeRequest);
        users.add(user);
        System.out.println(" Account Successfully created");
    }
    public  boolean isAccountNumberPresent(Integer accountNumber)
    {

        boolean isPresent=false;
        for(User usr:users)
        {
            if(usr.getAccountNumber().equals(accountNumber))
            {
                isPresent=true;
                break;
            }
        }
        return isPresent;
    }
    public void issueCheque()
    {
        try {
            System.out.println("Enter account number of customer to be cheque issued");
            Integer accountNumber=sc.nextInt();
            boolean isPresent=isAccountNumberPresent(accountNumber);
            if(isPresent)
            {
                for(User usr:users)
                {
                    if(usr.getAccountNumber().equals(accountNumber) && usr.isChequeRequest())
                    {
                        usr.setChequeApproved(true);
                        usr.setChequeRequest(false);
                        System.out.println(" Cheque issued successfully ");
                        break;
                    }
                }
            }
            else{
                System.out.println("Account number does not exist");
            }
        }
        catch (InputMismatchException e) {
            System.out.println("Not a valid Account number");
        }

    }
    public void chequeRequest(Integer accountNumber)
    {
        for(User usr:users)
        {
            if(usr.getAccountNumber().equals(accountNumber))
            {
                if(!usr.isChequeRequest())
                {
                    usr.setChequeRequest(true);
                    System.out.println(" Cheque book request submitted successfully ");
                    break;
                }
                System.out.println(" Cheque book request Already in queue");
                break;
            }
        }
    }

    public void transferMoney(double availableBalance, Integer senderAccountNumber)
    {
        try {
            System.out.println("Enter account number of customer to transfer money");
            Integer recieverAccountNumber=sc.nextInt();
            boolean isPresent=isAccountNumberPresent(recieverAccountNumber);
            if(isPresent)
            {
                System.out.println("Enter account amount to transfer money");
                double transferAmount=sc.nextInt();
                if(transferAmount<=availableBalance)
                {

                    String recieverName=null;
                    for(User usr:users)
                    {
                        if(usr.getAccountNumber().equals(recieverAccountNumber))
                        {
                            recieverName=usr.getUsername();

                            usr.setAccountBalance(usr.getAccountBalance()+transferAmount);
                            break;
                        }
                    }
                    for(User usr:users)
                    {
                        if(usr.getAccountNumber().equals(senderAccountNumber))
                        {
                            TransactionHistory transactionHistory=new TransactionHistory(String.valueOf(ClientFeature.TRANSFER_MONEY),transferAmount,recieverName,recieverAccountNumber);
                            usr.getTransactionHistoryList().add(transactionHistory);
                            usr.setAccountBalance(usr.getAccountBalance()-transferAmount);
                            System.out.println("Amount Successfully transfered  and available balance is "+usr.getAccountBalance());
                            break;
                        }
                    }

                }
                else {
                    System.out.println("Account does not have sufficient balance");
                }
            }
            else{
                System.out.println("Account number does not exist");
            }
        }
        catch (InputMismatchException e) {
            System.out.println("Not a valid Account number");
        }

    }
    public double checkBalance(Integer accountNumber)
    {
        double balance=0;
        for(User usr:users)
        {
            if(usr.getAccountNumber().equals(accountNumber))
            {
                balance=usr.getAccountBalance();
                break;
            }
        }
        return balance;
    }
    public void withdrawMoney(Integer accountNumber,double availableBalance)
    {
        System.out.println("Enter amount to withdraw");
        double withdrawAmount=sc.nextInt();
        if(withdrawAmount<=availableBalance)
        {
            for(User usr:users)
            {
                if(usr.getAccountNumber().equals(accountNumber))
                {
                    TransactionHistory transactionHistory=new TransactionHistory(String.valueOf(ClientFeature.WITHDRAW_MONEY),withdrawAmount,"self",accountNumber);
                    usr.getTransactionHistoryList().add(transactionHistory);
                    usr.setAccountBalance(usr.getAccountBalance()-withdrawAmount);
                    System.out.println("Amount Successfully withdrawn  and available balance is "+usr.getAccountBalance());
                    break;
                }
            }
        }
        else {
            System.out.println("Account does not have sufficient balance");
        }
    }
    public void depositMoney(Integer accountNumber)
    {
        System.out.println("Enter amount to deposit");
        double depositAmount=sc.nextInt();
            for(User usr:users)
            {
                if(usr.getAccountNumber().equals(accountNumber))
                {
                    TransactionHistory transactionHistory=new TransactionHistory(String.valueOf(ClientFeature.DEPOSIT_MONEY),depositAmount,"self",accountNumber);
                    usr.getTransactionHistoryList().add(transactionHistory);
                    usr.setAccountBalance(usr.getAccountBalance()+depositAmount);
                    System.out.println("Amount Successfully deposited  and available balance is "+usr.getAccountBalance());
                    break;
                }
            }
    }
    public void clientTransactionHistory(Integer accountNumber)
    {
        for(User usr:users)
        {
            if(usr.getAccountNumber().equals(accountNumber))
            {
                for(TransactionHistory tr:usr.getTransactionHistoryList())
                    System.out.println(tr);
            }
        }
    }
    public void allTransactionHistory()
    {
        for(User usr:users)
        {
            for(TransactionHistory tr:usr.getTransactionHistoryList())
            {
                if(tr!=null)
                {
                    System.out.println(tr);
                }
            }
        }
    }
    public User login(String username, String password)
    {
        List<User> finallist=users.stream().filter(obj->obj.getUsername().equals(username) && obj.getPassword().equals(password)).collect(Collectors.toList());

        if(!finallist.isEmpty())
        {
            return finallist.get(0);
        }
        else {
            return null;
        }
    }
}
