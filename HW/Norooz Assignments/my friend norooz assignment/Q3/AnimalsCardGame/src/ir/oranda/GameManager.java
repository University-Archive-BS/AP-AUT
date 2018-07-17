package ir.oranda;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * GameManager - Handles playing modes and player turns.
 *
 * @author MMKH
 * @version 1.0.0
 */
public class GameManager {

    // Is playing with computer ?
    private boolean aiMode;

    // UIManager for this game
    private UIManager uim;

    /**
     * Game starting point - determines game mode
     * @throws IOException
     */
    public void start() throws IOException{
        uim = new UIManager();
        ArrayList<String> gameModes = new ArrayList<>();
        gameModes.add("Play with a friend.");
        gameModes.add("Play with computer.");
        aiMode = (uim.modeSelectMessage("Select game mode :", gameModes,"Mode Select")!=1);
        if(aiMode){
            startAIMode();
        }
        else{
            startTwoPlayerMode();
        }
    }

    /**
     * Starts AI mode game (Human vs AI)
     * @throws IOException
     */
    public void startAIMode() throws IOException{
        HumanPlayer player1 = new HumanPlayer(0,uim);
        AIPlayer player2 = new AIPlayer(1,uim);

        // Card selection for player 1
        uim.passToPlayer(player1.getPlayerIndex());
        player1.randomizeCards();

        // Card selection for player 2 (Computer)
        uim.passToPlayer(player2.getPlayerIndex());
        player2.randomizeCards();

        // Starts the game
        startMatch(player1,player2);

    }

    /**
     * EXPERIMENTAL - Just for testing (AI vs AI)
     * Start full AI mode game (Both players are computer)
     * @throws IOException
     */
    public void startDoubleAIMode() throws IOException{
        AIPlayer player1 = new AIPlayer(0,uim);
        AIPlayer player2 = new AIPlayer(1,uim);

        // Card selection for player 1 (Computer)
        uim.passToPlayer(player1.getPlayerIndex());
        player1.randomizeCards();

        // Card selection for player 2 (Computer)
        uim.passToPlayer(player2.getPlayerIndex());
        player2.randomizeCards();

        // Starts the game
        startMatch(player1,player2);


    }

    /**
     * Starts two player game mode (Human vs Human)
     * @throws IOException
     */
    public void startTwoPlayerMode() throws IOException{

        HumanPlayer player1 = new HumanPlayer(0,uim);
        HumanPlayer player2 = new HumanPlayer(1,uim);

        // Card selection for player 1
        uim.passToPlayer(player1.getPlayerIndex());
        player1.randomizeCards();

        // Card selection for player 2
        uim.passToPlayer(player2.getPlayerIndex());
        player2.randomizeCards();

        // Starts the game
        startMatch(player1,player2);


    }

    /**
     * Starts a match
     * @param p1 Player One
     * @param p2 Player Two
     * @throws IOException
     */
    private void startMatch(Player p1,Player p2) throws IOException{
        boolean firstPlayer = true;
        boolean gameOver = false;

        // Continues the game until game is over
        while (!gameOver){
            if(firstPlayer){
                p1.takeTurn(p2);
                if(p2.getRemainingCards()==0 || (p2.getRemainingRepairs()==0 && p2.energyShortage())){
                    gameOver=true;
                }
            }
            else{
                p2.takeTurn(p1);
                if(p1.getRemainingCards()==0 || (p1.getRemainingRepairs()==0 && p1.energyShortage())){
                    gameOver=true;
                }
            }
            // Swap turn
            firstPlayer=!firstPlayer;

        }

        // Print last cards state and remaining repairs ...
        uim.clearConsole();
        uim.createWindow("Player 1 Cards", 0);
        uim.showCards(p1.getPlayerAnimals(), new HashSet<>(),UIManager.ANSI_GREEN);
        System.out.println(" ---> Remaining repairs : " + UIManager.ANSI_WARNING + p1.getRemainingRepairs() + UIManager.ANSI_RESET);
        System.out.println();
        uim.createWindow("Player 2 Cards", 0);
        uim.showCards(p2.getPlayerAnimals(), new HashSet<>(),UIManager.ANSI_GREEN);
        System.out.println(" ---> Remaining repairs : " + UIManager.ANSI_WARNING + p2.getRemainingRepairs() + UIManager.ANSI_RESET);
        System.out.println();
        uim.createWindow("    Game Results    ",0);

        // Check conditions ...
        if((p1.getRemainingRepairs()==0 && p1.energyShortage()) && (p2.getRemainingRepairs()==0 && p2.energyShortage())){
            // Draw !
            uim.breakAndContinue("Game is draw !");
        }
        else if(p1.getRemainingCards()==0 || (p1.getRemainingRepairs()==0 && p1.energyShortage())){
            // P2 is winner !
            uim.breakAndContinue("Player 2 is winner !");
        }
        else{
            // P1 is winner !
            uim.breakAndContinue("Player 1 is winner !");
        }
        //END !

    }

    /**
     * Is on AI mode ?
     * @return aiMode
     */
    public boolean isAiMode() {
        return aiMode;
    }

    /**
     * Gets current UI manager
     * @return uim
     */
    public UIManager getUim() {
        return uim;
    }
}
