import java.util.Random;
import java.util.Scanner;

public class Guess
{
    public static void main(String[] args)
    {
        int answer = getRandom(),
            count = 0,
            res = -1;
        System.out.println("random gen num: "+ answer);
        Scanner sc = new Scanner(System.in);

        // Get user input. Test if it equal, higher or lower than answer.
        // Malformated guesses (eg letters or numbers outside guess range)
        // will not incrememt count. 
        while (res != answer) {
            boolean isValid = sc.hasNextInt();
            if (!isValid) {
                System.out.println("input was of bad format");
            } else {
                int guess = sc.nextInt();
                res = testGuess(guess, answer);
                count++;
            }
            sc.nextLine();
        }
        System.out.println("Total guesses: " + count);
    }
    
    // First check if input can be interpreted as an int.
    // Then check if int is outside range. Return -1 if failure.
    private static int getUserInput(Scanner sc){
        System.out.println("input a number between 1 and 1000: ");
        if (!sc.hasNextInt()) {
            sc.reset();
            return -1;
        }
        int guess = sc.nextInt();
        if (guess < 1 || guess > 1000) {
            return -1;
        }
        return guess;
    }

    private static int testGuess(int n, int answer) {
        if (n > answer) {
            System.out.println("too high");
        } else if (n < answer) {
            System.out.println("too low");
        }
        return n;
    }

    private static int getRandom() {
        Random r = new Random();
        return r.nextInt(1001) + 0;
    }
}