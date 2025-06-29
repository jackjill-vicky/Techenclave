import java.util.Scanner;

public class NumberGuessingGame {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String playAgain;

        do {
            int numberToGuess = (int) (Math.random() * 100) + 1;
            int guess;
            int attempts = 0;

            System.out.println("🎯 Welcome to the Number Guessing Game!");
            System.out.println("Guess a number between 1 and 100.");

            while (true) {
                System.out.print("Enter your guess: ");
                guess = scanner.nextInt();
                attempts++;

                if (guess < numberToGuess) {
                    System.out.println("Too Low! Try again.");
                } else if (guess > numberToGuess) {
                    System.out.println("Too High! Try again.");
                } else {
                    System.out.println("🎉 Correct! You guessed the number in " + attempts + " attempts.");
                    break;
                }
            }

            System.out.print("Do you want to play again? (yes/no): ");
            playAgain = scanner.next();

        } while (playAgain.equalsIgnoreCase("yes"));

        System.out.println("Thanks for playing! 👋");
        scanner.close();
    }
}
