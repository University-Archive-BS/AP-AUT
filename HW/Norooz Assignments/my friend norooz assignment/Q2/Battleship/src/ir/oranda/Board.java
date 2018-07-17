package ir.oranda;

/**
 * Board class - Stores a square shaped game board with 100 cells.
 *
 * Each cell has a property , which it's value is 0 , 1 or 2 :
 * 0 means that the cell is empty.
 * 1 means that the cell is not damaged.
 * 2 means that the cell is damaged.
 *
 * @author MMKH
 * @version 1.0.0
 */
public class Board {

    // Cells of board
    private int[][] cells;

    /**
     * Creates a new 10*10 Board.
     */
    public Board() {
        cells = new int[10][10];
    }

    /**
     * Checks if given ship intersects with any ship that exists on this board.
     * @param x X startPoint of ship
     * @param y Y startPoint of ship
     * @param shipDirection Direction of ship
     * @param shipLength Length of ship
     * @return {@code true} if intersects
     */
    public boolean intersectsWithAnotherShip(int x,int y,Direction shipDirection,int shipLength){
        for(int i = 0 ; i < shipLength ; i++){
            if(getCellProperty(x+(shipDirection == Direction.HORIZONTAL ? i : 0),y+(shipDirection == Direction.HORIZONTAL ? 0 : i)) != 0)
                return true;
        }
        return false;
    }

    /**
     * Gets cell property.
     * @param x
     * @param y
     * @return 0 , 1 or 2
     */
    public int getCellProperty(int x,int y){
        return cells[x][y];
    }

    /**
     * Sets cell property.
     * @param x
     * @param y
     * @param property 0 , 1 or 2
     */
    public void setCellProperty(int x,int y,int property){
        cells[x][y]=property;
    }
}
