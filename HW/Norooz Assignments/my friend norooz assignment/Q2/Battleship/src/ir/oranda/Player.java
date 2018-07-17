package ir.oranda;

import java.util.Random;

/**
 * Player abstract class - All players that are playing this game.
 *
 * @author MMKH
 * @version 1.0.0
 */
public abstract class Player {

    // Bullet type ( normal or approximate )
    private boolean normalBullet;

    // Player index from 0
    private int playerIndex;


    // Ui manager
    private UIManager uim;

    // Player Board of this player
    private Board playerBoard;

    // Opponent Board of this player
    private Board opponentBoard;

    // Remaining cells of player board of this player ( if equals to zero , this player is lost )
    private int remainingCells;

    /**
     * Creates a player (called from a child class) and initializes boards.
     * @param normalBullet Bullet type ( normal or approximate )
     * @param playerIndex Player index from 0
     * @param uim Ui manager
     */
    public Player(boolean normalBullet, int playerIndex, UIManager uim) {
        this.normalBullet = normalBullet;
        this.playerIndex = playerIndex;
        this.uim = uim;
        playerBoard = new Board();
        opponentBoard = new Board();
    }


    /**
     * Gets remaining cells of PlayerBoard.
     * @return remainingCells
     */
    public int getRemainingCells() {
        return remainingCells;
    }

    /**
     * Gets bullet type
     * @return {@code true} if it's normal , {@code false} if it's approximate.
     */
    public boolean isNormalBullet() {
        return normalBullet;
    }

    /**
     * Gets player index
     * @return playerIndex from 0
     */
    public int getPlayerIndex() {
        return playerIndex;
    }

    /**
     * Gets current UI manager
     * @return UIManager
     */
    public UIManager getUim() {
        return uim;
    }

    /**
     * Gets Player Board of this player
     * @return playerBoard
     */
    public Board getPlayerBoard() {
        return playerBoard;
    }

    /**
     * Gets Opponent Board of this player
     * @return opponentBoard
     */
    public Board getOpponentBoard() {
        return opponentBoard;
    }

    /**
     * Adds a ship to this player.
     * @param x X Coordinate of upper-left cell of ship
     * @param y Y Coordinate of upper-left cell of ship
     * @param shipDirection Direction of ship
     * @param shipLength Length of ship
     */
    void addShip(int x,int y,Direction shipDirection,int shipLength){
        for(int i = 0 ; i < shipLength ; i++){
            playerBoard.setCellProperty(x+(shipDirection == Direction.HORIZONTAL ? i : 0),y+(shipDirection == Direction.HORIZONTAL ? 0 : i),1);
            remainingCells++;
        }
    }

    /**
     * Checks that targetted cell can be hitted or not.
     * @param x X Coordinate of cell
     * @param y Y Coordinate of cell
     * @return {@code true} if it can be hitted , {@code false} if it can't.
     */
    public boolean tryHitShips(int x,int y){
        if(playerBoard.getCellProperty(x,y)==1){
            playerBoard.setCellProperty(x,y,2);
            remainingCells--;
            return true;
        }

        return false;
    }

    /**
     * Arranges ship of this player (Depends on player type)
     */
    public abstract void arrangeShips();

    /**
     * Take turn of this player (Depends on player type)
     * @param opponent opponent player
     */
    public abstract void takeTurn(Player opponent);

    /**
     * Fires an approximate bullet to opponent on given cell
     * @param opponent opponent player
     * @param x X Coordinate of cell
     * @param y Y Coordinate of cell
     * @return {@code true} if hitted , {@code false} if not.
     */
    protected boolean fireApproximateBullet(Player opponent,int x,int y){
        Random r = new Random();
        int approxX = x - 1 + r.nextInt(3);
        int approxY = y - 1 + r.nextInt(3);
        while(approxX < 0 || approxX > 9 || approxY < 0 || approxY > 9 || (getOpponentBoard().getCellProperty(approxX,approxY)!=0)){
            approxX = x - 1 + r.nextInt(3);
            approxY = y - 1 + r.nextInt(3);
        }
        return fireNormalBullet(opponent, approxX,approxY);
    }


    /**
     * Fires a normal bullet to opponent on given cell
     * @param opponent opponent player
     * @param x X Coordinate of cell
     * @param y Y Coordinate of cell
     * @return {@code true} if hitted , {@code false} if not.
     */
    protected abstract boolean fireNormalBullet(Player opponent,int x,int y);

}
