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
            if (!sc.hasNextInt()) {
                System.out.println("input was of bad format");
            } else {
                int guess = sc.nextInt();
                res = guess;
                if (isValidGuessFormat(guess, answer)) {
                    count++;
                }
            }
            sc.nextLine();
        }
        sc.close();
        System.out.println("Total guesses: " + count);
    }
    
    private static boolean isValidGuessFormat(int n, int answer) {
        if (n == answer) {
            return true;
        } else if (n > 1000 || n < 1) {
            System.out.println("out of range");
            return false;
        } else if (n > answer) {
            System.out.println("too high");
            return true;
        } else if (n < answer) {
            System.out.println("too low");
            return true;
        }
        return false;
    }

    private static int getRandom() {
        Random r = new Random();
        return r.nextInt(1001) + 0;
    }
}