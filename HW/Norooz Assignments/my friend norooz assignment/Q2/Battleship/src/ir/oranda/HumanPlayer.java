package ir.oranda;

/**
 * AIPlayer class - A human-controlled player.
 *
 * @author MMKH
 * @version 1.0.0
 */
public class HumanPlayer extends Player {

    /**
     * Creates an AIPlayer.
     * @param normalBullet Bullet type ( normal or approximate )
     * @param playerIndex Player index from 0
     * @param uim Ui manager
     */
    public HumanPlayer(boolean normalBullet, int playerIndex, UIManager uim) {
        super(normalBullet, playerIndex, uim);
    }

    /**
     * Take turn of this player .
     * @param opponent opponent player
     */
    public void takeTurn(Player opponent){
        if(!(opponent instanceof AIPlayer)){
            getUim().passToPlayer(getPlayerIndex());
        }
        boolean bonusTurn=false;
        do {
            getUim().clearConsole();
            getUim().showTwoBoards("PlayerBoard P#" + (getPlayerIndex()+1),"OpponentBoard P#" + (getPlayerIndex()+1),getPlayerBoard(),getOpponentBoard(),UIManager.ANSI_BLUE,UIManager.ANSI_RED,UIManager.ANSI_PURPLE,UIManager.ANSI_GREEN,'@','#','X','&');
            char[] shipCommand = getUim().stringQuestion("Input a cell : 12", "(Row)(Column)").toUpperCase().toCharArray();
            while (shipCommand.length != 2 || (shipCommand[0]-48) < 0 || (shipCommand[0]-48) > 9 || (shipCommand[1]-48) < 0 || (shipCommand[1]-48) > 9 || (getOpponentBoard().getCellProperty(shipCommand[1]-48,shipCommand[0]-48)!=0)) {
                getUim().clearConsole();
                getUim().showTwoBoards("PlayerBoard P#" + (getPlayerIndex()+1),"OpponentBoard P#" + (getPlayerIndex()+1),getPlayerBoard(),getOpponentBoard(),UIManager.ANSI_BLUE,UIManager.ANSI_RED,UIManager.ANSI_PURPLE,UIManager.ANSI_GREEN,'@','#','X','&');
                shipCommand = getUim().stringQuestion("Input a cell : 12", "(Row)(Column)").toUpperCase().toCharArray();
            }
            if(isNormalBullet())
                bonusTurn = fireNormalBullet(opponent,shipCommand[1]-48,shipCommand[0]-48);
            else
                bonusTurn = fireApproximateBullet(opponent,shipCommand[1]-48,shipCommand[0]-48);

        } while (bonusTurn && opponent.getRemainingCells()>0);




    }

    /**
     * Fires a normal bullet to opponent on given cell
     * @param opponent opponent player
     * @param x X Coordinate of cell
     * @param y Y Coordinate of cell
     * @return {@code true} if hitted , {@code false} if not.
     */
    protected boolean fireNormalBullet(Player opponent,int x,int y){
        if(opponent.tryHitShips(x,y)){
            // Got it !
            getOpponentBoard().setCellProperty(x,y,2);
            opponent.getPlayerBoard().setCellProperty(x,y,2);
            getUim().clearConsole();
            getUim().showTwoBoards("PlayerBoard P#" + (getPlayerIndex()+1),"OpponentBoard P#" + (getPlayerIndex()+1),getPlayerBoard(),getOpponentBoard(),UIManager.ANSI_BLUE,UIManager.ANSI_RED,UIManager.ANSI_PURPLE,UIManager.ANSI_GREEN,'@','#','X','&');
            getUim().breakAndContinue("Got it !!");
            return true;
        }
        else{
            // Oops...!
            getOpponentBoard().setCellProperty(x,y,1);
            getUim().clearConsole();
            getUim().showTwoBoards("PlayerBoard P#" + (getPlayerIndex()+1),"OpponentBoard P#" + (getPlayerIndex()+1),getPlayerBoard(),getOpponentBoard(),UIManager.ANSI_BLUE,UIManager.ANSI_RED,UIManager.ANSI_PURPLE,UIManager.ANSI_GREEN,'@','#','X','&');
            getUim().breakAndContinue("Oops ... NO !");
            return false;
        }
    }


    /**
     * Arranges ship of this player.
     */
    public void arrangeShips(){

        for(int i=0;i<5;i++) {
            getUim().clearConsole();
            getUim().createWindow("Arrangement P#" + (getPlayerIndex()+1),0);
            getUim().showBoard(getPlayerBoard(),UIManager.ANSI_BLUE,UIManager.ANSI_RED,'@','#');
            char[] shipCommand = getUim().stringQuestion("Input a new ship(" + (i+1) + " of 5) in this format : H122", "(H/V)(Row)(Column)(Length)").toUpperCase().toCharArray();
            while (shipCommand.length != 4 || (shipCommand[0] != 'H' && shipCommand[0] != 'V') || (shipCommand[1]-48) < 0 || (shipCommand[1]-48) > 9 || (shipCommand[2]-48) < 0 || (shipCommand[2]-48) > 9 || (shipCommand[3]-48) > 5 || (shipCommand[3]-48) < 2 || (shipCommand[0] == 'H' ? shipCommand[2]-48 + shipCommand[3]-48 > 10 : shipCommand[1]-48 + shipCommand[3]-48 > 10) || getPlayerBoard().intersectsWithAnotherShip(shipCommand[2]-48,shipCommand[1]-48,(shipCommand[0] == 'H' ? Direction.HORIZONTAL : Direction.VERTICAL),shipCommand[3]-48)) {
                getUim().clearConsole();
                getUim().createWindow("Arrangement P#" + (getPlayerIndex()+1), 0);
                getUim().showBoard(getPlayerBoard(),UIManager.ANSI_BLUE,UIManager.ANSI_RED,'@','#');
                shipCommand = getUim().stringQuestion("Input a new ship(" + (i+1) + " of 5) in this format : H122", "(H/V)(Row)(Column)(Length)").toUpperCase().toCharArray();
            }
            addShip(shipCommand[2]-48,shipCommand[1]-48,(shipCommand[0] == 'H' ? Direction.HORIZONTAL : Direction.VERTICAL),shipCommand[3]-48);
        }


        // 5 Ships are arranged !



    }
}
