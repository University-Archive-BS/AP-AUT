package ir.oranda;


import java.util.ArrayList;
import java.util.Random;

/**
 * GameManager class - handles whole of the game process.
 *
 * @author MMKH
 * @version 1.0.0
 */
public class GameManager {

    // If playing with computer , aiMode = true
    private boolean aiMode;

    // a UIManager for managing game's ui
    private UIManager uim;

    /**
     * Starts this game and manages it.
     * @return {@code true} if Player1 is winner .
     */
    public boolean start(){
        uim = new UIManager();
        ArrayList<String> gameModes = new ArrayList<>();
        gameModes.add("Play with a friend.");
        gameModes.add("Play with computer.");
        aiMode = (uim.modeSelectMessage("Select game mode :", gameModes,"Mode Select")!=1);
        if(aiMode){
            return startAIMode();
        }
        else{
            return startTwoPlayerMode();
        }
    }

    /**
     * Starts Player vs Computer(AI)
     * @return {@code true} if Player1 (Human player) is winner .
     */
    private boolean startAIMode(){

        ArrayList<String> bulletModes = new ArrayList<>();
        bulletModes.add("Play with normal bullet.");
        bulletModes.add("Play with approximate bullet.");
        HumanPlayer player1 = new HumanPlayer((uim.modeSelectMessage("Select bullet type :",bulletModes,"Bullet type P#1")==1),0,uim);

        // Uncomment following lines , and comment next if approximate bullet is legal for AIPlayer :
        //Random r = new Random();
        //AIPlayer player2 = new AIPlayer(r.nextBoolean(),1,uim);
        AIPlayer player2 = new AIPlayer(true,1,uim);

        uim.passToPlayer(player1.getPlayerIndex());
        player1.arrangeShips();

        player2.arrangeShips();

        return startMatch(player1,player2);

    }

    /**
     * JUST FOR TEST - Starts Computer(AI) vs Computer(AI)
     * @return {@code true} if Player1 is winner .
     */
    private boolean startDoubleAIMode(){

        Random r = new Random();
        AIPlayer player1 = new AIPlayer(r.nextBoolean(),0,uim);
        AIPlayer player2 = new AIPlayer(r.nextBoolean(),1,uim);

        player1.arrangeShips();

        player2.arrangeShips();

        return startMatch(player1,player2);

    }

    /**
     * Starts Player vs Player (Both human players)
     * @return {@code true} if Player1 is winner .
     */
    private boolean startTwoPlayerMode(){

        ArrayList<String> bulletModes = new ArrayList<>();
        bulletModes.add("Play with normal bullet.");
        bulletModes.add("Play with approximate bullet.");
        HumanPlayer player1 = new HumanPlayer((uim.modeSelectMessage("Select bullet type :",bulletModes,"Bullet type P#1")==1),0,uim);
        HumanPlayer player2 = new HumanPlayer((uim.modeSelectMessage("Select bullet type :",bulletModes,"Bullet type P#2")==1),1,uim);

        uim.passToPlayer(player1.getPlayerIndex());
        player1.arrangeShips();

        uim.passToPlayer(player2.getPlayerIndex());
        player2.arrangeShips();

        return startMatch(player1,player2);



    }

    /**
     * Manages turns until game is over .
     * @param p1 Player1
     * @param p2 Player2
     * @return {@code true} if Player1 is winner .
     */
    private boolean startMatch(Player p1,Player p2){
        boolean firstPlayer = true;
        boolean gameOver = false;
        while (!gameOver){
            if(firstPlayer){
                p1.takeTurn(p2);
                if(p2.getRemainingCells()==0){
                    gameOver=true;
                }
            }
            else{
                p2.takeTurn(p1);
                if(p1.getRemainingCells()==0){
                    gameOver=true;
                }
            }
            firstPlayer=!firstPlayer;

        }

        // Game is over ! Printing player boards ...
        uim.clearConsole();
        uim.createWindow("    Game Results    ",6);
        uim.showTwoBoards("PlayerBoard P#1","PlayerBoard P#2",p1.getPlayerBoard(),p2.getPlayerBoard(),UIManager.ANSI_BLUE,UIManager.ANSI_RED,UIManager.ANSI_BLUE,UIManager.ANSI_RED,'@','#','@','#');

        // Who is winner ?!
        if(p1.getRemainingCells()==0){
            // P2 is winner !
            return false;
        }
        else{
            // P1 is winner !
            return true;
        }


        //END !

    }

    /**
     * Gets aiMode
     * @return aiMode
     */
    public boolean isAiMode() {
        return aiMode;
    }

    /**
     * Gets current user interface manager .
     * @return UIManager
     */
    public UIManager getUim() {
        return uim;
    }
}
