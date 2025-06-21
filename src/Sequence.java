import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Sequence {
    Random random = new Random();
    Scanner scan = new Scanner(System.in);
    ArrayList<Character> sequenceList = new ArrayList<>(5);
    ArrayList<String> guessStorage = new ArrayList<>();
    ArrayList<Integer> attemptsStorage = new ArrayList<>();
    StringBuilder sb = new StringBuilder(); // creates empty StringBuilder


    private String correctSequence;
    private String guess = "";

    public void generateSequence() { // create ArrayList with random sequence from a-f
        sequenceList.clear();
        sb.setLength(0);
        for(int i = 0; i <= 3; i++) {
            char c = (char) (random.nextInt('a','g'));
            sequenceList.add(c);
            sb.append(c);
        }
        correctSequence = sb.toString();
    }



    public String getCorrectSequence() {
        return correctSequence;
    }

    public void presetSequence(String customSequence) {
        correctSequence = customSequence;
        sequenceList.clear();

        while(correctSequence.length() != 4) {
            System.out.print("Pls enter sequence according tu Rules: ");
            correctSequence = scan.next();
        }

        for(int i = 0; i<=3; i++) {
            sequenceList.add(correctSequence.charAt(i));
        }
    }

    public void checkGuess(String userInput) {
        for(int i = 0; i < 4; i++) {
            if(sequenceList.get(i).equals(userInput.charAt(i))) {
                guess += userInput.charAt(i);
            } else if(sequenceList.get(i) != userInput.charAt(i)) {
                guess += "-";
            }
        }

    }

    public void storeGuess(String guess) {
        guess = this.guess;
        guessStorage.add(guess);
    }


    public String getUserGuess() {
        return guess;
    }

    public void storeAttempt(int attempt) {
        attemptsStorage.add(attempt);

    }


    public void resetUserGuess() {
        guess = "";
    }

}
