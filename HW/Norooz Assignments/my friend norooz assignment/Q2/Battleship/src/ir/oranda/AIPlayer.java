package ir.oranda;

import java.util.Random;

/**
 * AIPlayer class - An intelligent , computer-controlled player.
 *
 * @author MMKH
 * @version 1.0.0
 */
public class AIPlayer extends Player {

    // An AISearch object that is used to control AI
    private AISearch aiSearch;

    /**
     * Creates an AIPlayer.
     * @param normalBullet Bullet type ( normal or approximate )
     * @param playerIndex Player index from 0
     * @param uim Ui manager
     */
    public AIPlayer(boolean normalBullet, int playerIndex, UIManager uim) {
        super(normalBullet, playerIndex, uim);
        aiSearch = new AISearch(this,!normalBullet);
    }

    /**
     * Take turn of this player ( Uses AISearch for AI )
     * @param opponent opponent player
     */
    public void takeTurn(Player opponent){
        boolean bonusTurn=false;
        do {

            aiSearch.nextMove();

            if(isNormalBullet())
                bonusTurn = fireNormalBullet(opponent,aiSearch.getNextX(),aiSearch.getNextY());
            else
                bonusTurn = fireApproximateBullet(opponent,aiSearch.getNextX(),aiSearch.getNextY());

            aiSearch.reportLastMove(bonusTurn);

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
            return true;
        }
        else{
            // Oops...!
            getOpponentBoard().setCellProperty(x,y,1);
            return false;
        }
    }


    /**
     * Arranges ship of this player (randomly).
     */
    public void arrangeShips(){
        Random r = new Random();
        for(int i=0;i<5;i++) {
            char[] shipCommand = {'0','0','0','0'};
            shipCommand[0] = (r.nextBoolean() ? 'H' : 'V');
            shipCommand[1] = (char)(r.nextInt(10)+48);
            shipCommand[2] = (char)(r.nextInt(10)+48);
            shipCommand[3] = (char)(r.nextInt(4)+2+48);
            while ((shipCommand[0] == 'H' ? shipCommand[2]-48 + shipCommand[3]-48 > 10 : shipCommand[1]-48 + shipCommand[3]-48 > 10) || getPlayerBoard().intersectsWithAnotherShip(shipCommand[2]-48,shipCommand[1]-48,(shipCommand[0] == 'H' ? Direction.HORIZONTAL : Direction.VERTICAL),shipCommand[3]-48)) {
                shipCommand[0] = (r.nextBoolean() ? 'H' : 'V');
                shipCommand[1] = (char)(r.nextInt(10)+48);
                shipCommand[2] = (char)(r.nextInt(10)+48);
                shipCommand[3] = (char)(r.nextInt(4)+2+48);
            }
            addShip(shipCommand[2]-48,shipCommand[1]-48,(shipCommand[0] == 'H' ? Direction.HORIZONTAL : Direction.VERTICAL),shipCommand[3]-48);
        }


        // 5 Ships are arranged !
    }
}
