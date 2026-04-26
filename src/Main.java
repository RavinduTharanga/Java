import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {


    public static void main(String[] args){

        //Java Hang Man
        String filePath="words.txt";
        ArrayList<String>words=new ArrayList<>();


        try(BufferedReader reader=new BufferedReader(new FileReader(filePath))){
            String line;
            while ((line=reader.readLine())!=null){
                words.add(line);
            }

        }
        catch (FileNotFoundException e){
            System.out.println("Could not find the file");

        }
        catch (IOException e){
            System.out.println("Something Went wrong");
        }

        Random random = new Random();

        String word =words.get(random.nextInt(words.size()));
        Scanner scanner = new Scanner(System.in);
        ArrayList<Character> wordState=new ArrayList<>();

        int wrongGuesses=0;

        for(int i=0;i< word.length();i++){
            wordState.add('_');
        }
        System.out.println(wordState);
        System.out.println("************************");
        System.out.println("Welcome to Java Hang Man");
        System.out.println("************************");

        while (wrongGuesses<6) {

            System.out.println(getHangmanArt(wrongGuesses));

            System.out.println("Word: ");

            for (char c : wordState) {
                System.out.print(c + " ");
            }
            System.out.println();
            System.out.print("Guess a Letter:");
            char guess = scanner.next().toLowerCase().charAt(0);
//            System.out.println(guess);
            if (word.indexOf(guess) >= 0) {
                System.out.println("Correct Guess!");
                for (int i = 0; i < word.length(); i++) {
                    if (word.charAt(i) == guess) {
                        wordState.set(i, guess);
                    }
                }
                if(!wordState.contains('_')){
                    System.out.println(getHangmanArt(wrongGuesses));
                    System.out.println("You Win!!!");
                    System.out.println("The word was: "+word);
                    break;
                }
            }
            else {
                wrongGuesses++;
                System.out.println("Wrong Guess!");
            }
        }
        if (wrongGuesses>=6){
            System.out.println(getHangmanArt(wrongGuesses));
            System.out.println("Game Over");
            System.out.println("The word was: "+ word);
        }
        scanner.close();
    }


    static String getHangmanArt(int wrongGuesses){

        return  switch (wrongGuesses){
            case 0-> """
                     
                     
                     
                     
                     """;
            case 1-> """
                      |
                    
                    
                    
                     """;
            case 2-> """
                      |
                      0
                    
                    
                     """;
            case 3-> """
                      |
                      0
                      |
                     
                     """;
            case 4-> """
                      |
                      0
                     /|\\
                     
                     """;
            case 5-> """
                      |
                      0
                     /|\\
                     / 
                     """;
            case 6-> """
                      |
                      0
                     /|\\
                     / \\
                     """;
            default -> "";
        };
    }
}
