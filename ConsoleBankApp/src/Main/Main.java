package Main;

import Entity.User;
import Enums.AdminFeature;
import Enums.ClientFeature;
import Service.UserService;

import java.util.Scanner;

public class Main {

    static Main main=new Main();
    static UserService userService=new UserService();
    private static Scanner sc=new Scanner(System.in);
    public static void main(String[] args) {




        while(true)
        {
            System.out.println("Enter your name ");
            String username=sc.next();
            System.out.println("Enter Password ");
            String password=sc.next();

            User loginUser=userService.login(username,password);
            if(loginUser!=null && loginUser.getRole().equals("admin"))
            {
                main.adminFeature(username,password);
            }
            else if(loginUser!=null && loginUser.getRole().equals("user"))
            {
                main.clientFeature(username,password);
            }
            else {
                System.out.println("Login Failed......! ");
            }
        }

        //userService.printUsers();
    }
    private void adminFeature(String username,String password)
    {
        boolean flag=true;
        while(flag)
        {
            int i=1;
            for (AdminFeature feature : AdminFeature.values()) {
                System.out.println(i+++". "+feature);
            }

            int selectedOption=sc.nextInt();
            switch (selectedOption)
            {
                case 1: userService.createAccount();
                    break;
                case 2:userService.printCustomersList();
                        break;
                case 3:
                    userService.issueCheque();
                    break;
                case 4:
                    userService.allTransactionHistory();
                    break;
                case 5: System.out.println("Logged out...");
                    flag=false;
                    break;
                default:   System.out.println("Wrong Choice...");
            }
        }
    }
    private void clientFeature(String username,String password)
    {
        User loginUser=userService.login(username,password);
        boolean flag=true;
        while(flag)
        {
            int i=1;
            for (ClientFeature feature : ClientFeature.values()) {
                System.out.println(i+++". "+feature);
            }

            int selectedOption=sc.nextInt();
            switch (selectedOption)
            {
                case 1:
                    System.out.println("Account balance is : "+userService.checkBalance(loginUser.getAccountNumber()));
                    break;
                case 2:
                        userService.transferMoney(loginUser.getAccountBalance(),loginUser.getAccountNumber());
                    break;
                case 3:userService.withdrawMoney(loginUser.getAccountNumber(),loginUser.getAccountBalance());
                    break;
                case 4:   userService.depositMoney(loginUser.getAccountNumber());
                    break;
                case 5:   userService.chequeRequest(loginUser.getAccountNumber());
                    break;
                case 6:   userService.clientTransactionHistory(loginUser.getAccountNumber());
                    break;
                case 7: System.out.println("Logged out...");
                    flag=false;
                    break;
                default:   System.out.println("Wrong Choice...");
            }
        }
    }
}