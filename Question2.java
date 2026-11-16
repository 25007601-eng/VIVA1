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
    }

    double balance = 8000;
    double annualRate = 18;
    double plannedPayment = 300;

    double totalInterst = 0;
    double totalPaid = 0;
    int raisedMonths = 0;
    
    double monthlyRate = annualRate / (100 * 12);
    
    double interst = balance * monthlyRate;
    
    balance += interest;
    totalInterest += interest;

    double minimumPayment = Math.max(balance * 0.05, 50);

    double actualPayment = plannedPayment;

    if(actualPayment < minimumPayment){
      actualPayment = minmumPayment;
      raisedMonths ++;
    }
    
        
    
