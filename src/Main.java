import java.util.Random;
import java.util.Scanner;

public class Main {


    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);


        //Java slot machine
        //Declare variables
        int balance=100;
        int bet;
        int payout;
        String[] row;
        String playAgain;
        //Display welcome message
        System.out.println("*************************");
        System.out.println("  Welcome to Java Slots  ");
        System.out.println("Symbols: 🍒 🍉 🍋 🔔 ⭐");
        System.out.println("*************************");


        //Play if balance>0

        while(balance>0){
            System.out.println("Your current balance: $"+ balance);
            System.out.print("Place your bet amount: ");
            bet=scanner.nextInt();
            scanner.nextLine();
            if (bet> balance){
                System.out.println("Insufficient funds");
                continue;
            }
            else if(bet<=0){
                System.out.println("Bet must be greater than 0");
                continue;
            }
            else{
                balance=balance-bet;

            }
            System.out.println("Spinning.....");
            row=spinRow();
            printRow(row);
            payout=getPayout(row,bet);
            if (payout>0){
                System.out.println("You Won $"+payout);
                balance=balance+payout+bet;
            }
            else {
                System.out.println("Sorry you lost this round");
            }
            System.out.println("Do you want to play Again? (Y/N)");
            playAgain=scanner.nextLine().toUpperCase();
            if(!playAgain.equals("Y")){
                break;
            }



        }
        System.out.println("Game Over..Your final balance is $"+balance);
        //Enter Bet Amount
        // Verify if bet > balance
        //Verify if bet > 0
        //Subtract bet from balance
        //Spin row
        //Print row
        //Get Payout
        //Ask to play again
        //Display exit message

        scanner.close();

    }

    //Spinrow
    static String[] spinRow(){
        String[] symbols ={"🍒","🍉","🍋","🔔","⭐"};
        String[] row = new String[3];
        Random random = new Random();

        for(int i=0; i<3;i++){

            row[i]=symbols[random.nextInt(symbols.length)];

        }
        return row;
    }
    static void printRow(String[] row){
        System.out.println("*************************");
        System.out.println(" "+ String.join(" | ",row));
        System.out.println("*************************");


    }
    static int getPayout(String[]row,int bet){
        if(row[0].equals(row[1])&& row[1].equals(row[2])){
            return switch (row[0]){
                case "🍒"->bet*3;
                case "🍉"->bet*4;
                case "🍋"->bet*5;
                case "🔔"->bet*10;
                case "⭐"->bet*20;
                default -> 0;

            };


        }
        else if(row[0].equals(row[1])) {
            return switch (row[0]) {
                case "🍒" -> bet * 2;
                case "🍉" -> bet * 3;
                case "🍋" -> bet * 4;
                case "🔔" -> bet * 5;
                case "⭐" -> bet * 10;
                default -> 0;

            };
        }
        else if(row[1].equals(row[2])) {
            return switch (row[1]) {
                case "🍒" -> bet * 2;
                case "🍉" -> bet * 3;
                case "🍋" -> bet * 4;
                case "🔔" -> bet * 5;
                case "⭐" -> bet * 10;
                default -> 0;

            };
        }
        return 0;
    }
}
