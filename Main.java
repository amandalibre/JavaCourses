import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("DS9_episode_names.txt");
        Scanner scanner = new Scanner(file);

        List<String> episodeTitles = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().toLowerCase();
            episodeTitles.add(line);
        }

        int numberEpisodes = episodeTitles.size();

        System.out.println("I have randomly chosen one of the " + numberEpisodes + " glorious DS9 episodes.");

        int randomNumber = (int) (Math.random() * numberEpisodes) + 1;
        String randomEpisodeTitle = episodeTitles.get(randomNumber);

        System.out.println("Try to guess which one this is:");

        List<Character> correctGuesses = new ArrayList<>();
        correctGuesses.add(' ');

        List<Character> titleCharCorrectGuesses = new ArrayList<>();
        for (char letter : randomEpisodeTitle.toCharArray()) {
            if (correctGuesses.contains(letter)) {
                titleCharCorrectGuesses.add(letter);
            } else {
                titleCharCorrectGuesses.add('*');
            }
        }

        for (char letter : titleCharCorrectGuesses) {
            System.out.print(letter);
        }


        List<Character> titleCharAnswer = new ArrayList<>();
        for (char letter : randomEpisodeTitle.toCharArray()) {
            titleCharAnswer.add(letter);
        }

        System.out.println();

        Scanner scanner2 = new Scanner(System.in);
        int numGuessesRemaining = 10;
        while (numGuessesRemaining > 0) {

            titleCharCorrectGuesses.clear();

            char guess = scanner2.next().toCharArray()[0];
            System.out.println("You chose: " + guess);

            if (titleCharAnswer.contains(guess)) {
                System.out.println("The letter " + guess + " is in the title.");
                correctGuesses.add(guess);
                for (char letter : randomEpisodeTitle.toCharArray()) {
                    if (correctGuesses.contains(letter)) {
                        titleCharCorrectGuesses.add(letter);
                    } else {
                        titleCharCorrectGuesses.add('*');
                    }
                }
                if (!titleCharCorrectGuesses.contains('*')) {
                    System.out.println("YOU WIN!");
                    System.out.println("The title was: ");
                    for (char c : titleCharCorrectGuesses) {
                        System.out.print(c);
                    }
                    System.exit(0);
                }
                for (char c : titleCharCorrectGuesses) {
                    System.out.print(c);
                }
                System.out.println();
            } else {
                System.out.println("The letter " + guess + " is not in the title.");
                numGuessesRemaining--;
                System.out.println("You have " + numGuessesRemaining + " guesses remaining.");
                for (char letter : randomEpisodeTitle.toCharArray()) {
                    if (correctGuesses.contains(letter)) {
                        titleCharCorrectGuesses.add(letter);
                    } else {
                        titleCharCorrectGuesses.add('*');
                    }
                }
                for (char c : titleCharCorrectGuesses) {
                    System.out.print(c);
                }
                System.out.println();
            }
            if (numGuessesRemaining == 0) {
                System.out.println("YOU LOSE!");
                System.out.println("The episode was: " + randomEpisodeTitle);
            }
        }

    }
}