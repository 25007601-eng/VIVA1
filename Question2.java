import java.util.Scanner;

public class Question2 {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);

    double balance;

    do{
      System.out.println("Enter outstanding balance (RM): ");
      balance = input.nextDouble();
      
      if (balance < 500 || balance > 50000) {
        System.out.println("Error: Balance must be between RM 500 and RM 50000.");
      }
      
    }while(balance < 500 || balance > 50000){
      System.out.println("Balance: RM " + balance);
        
    
