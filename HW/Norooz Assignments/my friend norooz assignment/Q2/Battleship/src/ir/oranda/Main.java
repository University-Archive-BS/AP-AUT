package ir.oranda;

/**
 * Main class - contains start point.
 *
 * @author MMKH
 * @version 1.0.0
 */
public class Main {

    /**
     * Start point of program.
     * @param args Not used.
     */
    public static void main(String[] args) {

        // Everything happens inside GameManager
        GameManager gm = new GameManager();
        // gm.start() do all operations for a game and returns true if Player 1 is winner and false if Player 2 is winner .
        boolean isP1Winner = gm.start();
        if(isP1Winner){
            // P1 is winner !
            gm.getUim().breakAndContinue("Player 1 is winner !");
        }
        else{
            // P2 is winner !
            gm.getUim().breakAndContinue("Player 2 is winner !",12);
        }

    }
}
