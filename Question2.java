import java.util.Scanner;

public class Question2 {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);

    double balance;

    do{
      System.out.println("Enter outstanding balance (RM): ");
      balance = input.nextDouble();
    }while(balance < 500 || balance > 50000){
      System.out.println("Balance: RM " + balance);
        
    
