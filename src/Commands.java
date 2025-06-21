public class Commands {
    Sequence sequence = new Sequence();
    Game game = new Game();

    public void help() {
        System.out.println("Commands: ");
        System.out.println("- 'start  '  Starts the Game ");
        System.out.println("- 'help   '  Display possible commands ");
        System.out.println("- 'exit   '  Terminate the Game ");
        System.out.println("- 'restart'  Restart the Game ");
        System.out.println("- 'reveal '  Reveal the sequence");
        System.out.println("- 'history'  Reveal your Guess History");
    }

    public void start() {
        System.out.println("Starting Game... Sequence successfully generated!");
        sequence.generateSequence();
    }

    public void exit() {
        System.out.println("Terminating Game... ");
        System.exit(0);
    }

    public void showIntro() {
        System.out.println("BzGuessingGame");
        System.out.println("---------------------------------------------------------------------------------------");
        System.out.println("Guess a sequence consisting of letters a,b,c,d,e,f");
        System.out.println("The sequence is 4 letters long and each letter may appear 0 to 4 times. Good Luck!");
        System.out.println("---------------------------------------------------------------------------------------");
    }

    public void restart() {
        sequence.guessStorage.clear();
        sequence.attemptsStorage.clear();
        sequence.resetUserGuess();
        game.showIntro = true;
    }
}
