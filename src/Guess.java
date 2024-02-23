import java.sql.SQLOutput;
import java.util.Scanner;

Import java.util.Scanner;

public class Guess {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
String targetWord = "gnome"; //TODO
        System.out.println("Welcome to our Wordle clone!" );
        System.out.println("Enter a 5 letter word. ");

        while (true) {
            System.out.println("Eneter your guess: ");
            String userGuess = scanner.nextLine();

        if (userGuess.length() !=5) {
            System.out.println("Invalid word length! Please enter a 5-letter word. ");

        } else {

            //check
            if (userGuess.equalsIgnoreCase((targetWord))) {
                System.out.println("Correct Guess");
                break;
            } {
                System.out.println("Incorrect gess. Try again|");
            }
        }

        }
        System.out.println("Thanks for playing!" );

    }

            }

