import java.util.ArrayList;
import java.util.Scanner;


public class Game {
    public static boolean gameRunning = true;
    public static boolean showIntro = true;
    public static String userInput;
    public static ArrayList<Character> allowedCharacters = new ArrayList<>();
    public static ArrayList<Character>userCharacters = new ArrayList<>();
    public static ArrayList<String> commandList = new ArrayList<>();


    public static void start() {
        Scanner scan = new Scanner(System.in);

        for (int i = 97; i <= 102; i++) {
            char character = (char) i;
            allowedCharacters.add(character);
        }

        System.out.println(allowedCharacters);

        Sequence sequence = new Sequence();
        Commands commands = new Commands();

        commandList.add("start");
        commandList.add("help");
        commandList.add("history");
        commandList.add("reveal");
        commandList.add("restart");
        commandList.add("exit");

        System.out.println("Type 'start' to play!");
        System.out.print("> ");
        userInput = scan.next();

        showIntro = true;
        int attempts;

        while (!userInput.equals("start") & !userInput.equals("exit")) {
            System.out.println("Type 'start' to continue or 'exit' to leave!");
            System.out.print("> ");
            userInput = scan.next();
        }
        if (userInput.equals("exit")) {
            System.out.println("Terminating Game...");
            System.exit(0);
        } else {
            System.out.println("Starting Game...");
            System.out.println();
        }


        while (gameRunning) {
            for (attempts = 5; attempts > 0; attempts--) {
                if (showIntro) {
                    System.out.println();
                    commands.showIntro();
                    commands.help();
                    System.out.println("---------------------------------------------------------------------------------------");
                    sequence.generateSequence();
                    sequence.attemptsStorage.clear();
                    sequence.guessStorage.clear();
                    commands.start();
                    showIntro = false;
                }

                System.out.print(attempts + " > ");
                userInput = scan.next();
                userInput = userInput.toLowerCase();

                for(char c : userInput.toCharArray()) {
                    userCharacters.add(c);
                }

                boolean valid = true;
                for(int i = 0; i < 4; i++) {
                    if(allowedCharacters.contains(userCharacters.get(i))) {

                    } else {
                        valid = false;
                        break;
                    }

                }
                userCharacters.clear();

                if(userInput.length() == 4 & !commandList.contains(userInput) & !userInput.equals(sequence.getCorrectSequence()) & valid) {
                    sequence.checkGuess(userInput);
                    sequence.storeGuess(userInput);
                    sequence.storeAttempt(attempts);
                    System.out.println(sequence.getUserGuess());
                }  else if(userInput.equals(sequence.getCorrectSequence())) {
                    System.out.println("You Won!");

                    if(userInput.equals("restart")) {
                        commands.restart();
                        break;
                    } else if(userInput.equals("exit")) {
                        System.out.println("The Sequence was " + sequence.getCorrectSequence());
                        commands.exit();
                    } else {
                        System.out.println("Please type 'restart' or 'exit' to continue");
                    }
                } else if (userInput.equals("restart")) {
                    commands.restart();
                    attempts = 6;
                } else if (userInput.equals("exit")) {
                    commands.exit();
                } else if (userInput.equals("help")) {
                    commands.help();
                    attempts++;
                } else if (userInput.equals("reveal")) {
                    break;
                } else if(userInput.equals("history")) {

                    if(sequence.attemptsStorage.isEmpty()) {
                        System.out.println("No History available!");
                    } else {
                        for(int i = 0; i < sequence.attemptsStorage.size(); i++) {
                            System.out.println("Guess #" + sequence.attemptsStorage.get(i) + ": " + sequence.guessStorage.get(i));
                        }
                    }

                    attempts++;
                    sequence.resetUserGuess();
                } else if(userInput.equals("preset")) {
                    System.out.print("Your Preset > ");
                    userInput = scan.next();
                    sequence.presetSequence(userInput);
                    attempts++;
                } else if(userInput.equals("start")) {
                    System.out.println("Game already running! User different command.");
                    attempts++;
                }
                else if(!valid) {
                    System.out.println("Invalid Input!");
                    attempts++;
                }
                sequence.resetUserGuess();
            }
            if(attempts == 0) {
                System.out.println("All Attempts used!");
                System.out.println("The Sequence was " + sequence.getCorrectSequence());
            } else if(userInput.equals("reveal")) {
                System.out.println("The Sequence was " + sequence.getCorrectSequence());
            }
            System.out.println("Type 'exit' to terminate the game or 'restart' to play again.");
            System.out.print(" > ");
            userInput = scan.next();

            boolean status = true;
            while (status) {
                if(userInput.equals("exit")) {
                    commands.exit();
                    status = false;
                } else if (userInput.equals("restart")) {
                    commands.restart();
                    status = false;
                }  else {
                    System.out.println("Type 'exit' to terminate the game or 'restart' to play again.");
                    userInput = scan.next();
                }
            }

        }
    }
}
