package ir.oranda;

import java.util.Random;

/**
 * AISearch - Semi-professional(or what?!) AI for an AIPlayer !
 *
 * @author MMKH
 * @version 1.0.0
 */
public class AISearch {

    // Base cell coordinates
    private int baseX;
    private int baseY;

    // Current search state
    private AISearchState searchState;

    // AIPlayer that is using this AI
    private AIPlayer aiPlayer;

    // Next cell coordinates
    private int nextX;
    private int nextY;

    // Just used if AIPlayer's bullet type is approximate , Then AI is "nearly"(not completely) unused ( if forceToRandom = true )
    private boolean forceToRandom;

    /**
     * Creates a new AISearch AI unit .
     * @param aiPlayer AIPlayer that is using this AI
     * @param forceToRandom Just used if AIPlayer's bullet type is approximate , Then AI is "nearly"(not completely) unused ( if {@code forceToRandom = true} )
     */
    public AISearch(AIPlayer aiPlayer,boolean forceToRandom) {
        this.searchState = AISearchState.RANDOM_SEARCH;
        this.aiPlayer = aiPlayer;
        this.forceToRandom = forceToRandom;
    }

    /**
     * Calculates next move and stores next move coordinates in {@code nextX} and {@code nextY}.
     */
    public void nextMove(){
        Random r = new Random();
        if(forceToRandom){
            baseX = r.nextInt(10);
            baseY = r.nextInt(10);
            nextX = baseX;
            nextY = baseY;
        }
        else {
            switch (searchState) {
                case RANDOM_SEARCH:
                    baseX = r.nextInt(10);
                    baseY = r.nextInt(10);
                    nextX = baseX;
                    nextY = baseY;
                    break;
                case SEARCH_AROUND_RIGHT:
                    nextX = baseX + 1;
                    nextY = baseY;
                    break;
                case SEARCH_AROUND_LEFT:
                    nextX = baseX - 1;
                    nextY = baseY;
                    break;
                case SEARCH_AROUND_DOWN:
                    nextY = baseY + 1;
                    nextX = baseX;
                    break;
                case SEARCH_AROUND_UP:
                    nextY = baseY - 1;
                    nextX = baseX;
                    break;
                case LINEAR_SEARCH_RIGHT:
                    nextX = nextX + 1;
                    nextY = baseY;
                    break;
                case LINEAR_SEARCH_LEFT:
                    nextX = nextX - 1;
                    nextY = baseY;
                    break;
                case LINEAR_SEARCH_DOWN:
                    nextY = nextY + 1;
                    nextX = baseX;
                    break;
                case LINEAR_SEARCH_UP:
                    nextY = nextY - 1;
                    nextX = baseX;
                    break;

            }
        }
        if((aiPlayer.getOpponentBoard().getCellProperty(nextX, nextY) != 0) || (isAlone(aiPlayer.getOpponentBoard(), nextX, nextY))){
            if(searchState==AISearchState.RANDOM_SEARCH){
                reportLastMove(false);
            }
            else{
                if(aiPlayer.getOpponentBoard().getCellProperty(nextX, nextY)==2)
                    reportLastMove(true);
                else
                    reportLastMove(false);
            }

            nextMove();

        }
    }

    /**
     * Reports that if last move was successful or not
     * @param succeed was successful ?
     */
    public void reportLastMove(boolean succeed) {
        if(succeed) {
            switch (searchState) {
                case RANDOM_SEARCH:
                    searchState = AISearchState.SEARCH_AROUND_RIGHT;
                    if(nextX==9)
                        reportLastMove(false);
                    break;
                case SEARCH_AROUND_RIGHT:
                    searchState = AISearchState.LINEAR_SEARCH_RIGHT;
                    if(nextX==9)
                        reportLastMove(false);
                    break;
                case SEARCH_AROUND_LEFT:
                    searchState = AISearchState.LINEAR_SEARCH_LEFT;
                    if(nextX==0)
                        reportLastMove(false);
                    break;
                case SEARCH_AROUND_DOWN:
                    searchState = AISearchState.LINEAR_SEARCH_DOWN;
                    if(nextY==9)
                        reportLastMove(false);
                    break;
                case SEARCH_AROUND_UP:
                    searchState = AISearchState.LINEAR_SEARCH_UP;
                    if(nextY==0)
                        reportLastMove(false);
                    break;
                case LINEAR_SEARCH_RIGHT:
                    if(nextX==9)
                        reportLastMove(false);
                    break;
                case LINEAR_SEARCH_LEFT:
                    if(nextX==0)
                        reportLastMove(false);
                    break;
                case LINEAR_SEARCH_DOWN:
                    if(nextY==9)
                        reportLastMove(false);
                    break;
                case LINEAR_SEARCH_UP:
                    if(nextY==0)
                        reportLastMove(false);
                    break;

            }
        }
        else{
            switch (searchState) {
                case RANDOM_SEARCH:
                    // Nothing , retry with random search
                    break;
                case SEARCH_AROUND_RIGHT:
                    searchState = AISearchState.SEARCH_AROUND_LEFT;
                    if(baseX==0)
                        reportLastMove(false);
                    break;
                case SEARCH_AROUND_LEFT:
                    searchState = AISearchState.SEARCH_AROUND_DOWN;
                    if(baseY==9)
                        reportLastMove(false);
                    break;
                case SEARCH_AROUND_DOWN:
                    searchState = AISearchState.SEARCH_AROUND_UP;
                    if(baseY==0)
                        reportLastMove(false);
                    break;
                case SEARCH_AROUND_UP:
                    searchState = AISearchState.RANDOM_SEARCH;
                    break;
                case LINEAR_SEARCH_RIGHT:
                    searchState = AISearchState.SEARCH_AROUND_LEFT;
                    if(baseX==0)
                        reportLastMove(false);
                    break;
                case LINEAR_SEARCH_LEFT:
                    searchState = AISearchState.SEARCH_AROUND_DOWN;
                    if(baseY==9)
                        reportLastMove(false);
                    break;
                case LINEAR_SEARCH_DOWN:
                    searchState = AISearchState.SEARCH_AROUND_UP;
                    if(baseY==0)
                        reportLastMove(false);
                    break;
                case LINEAR_SEARCH_UP:
                    searchState = AISearchState.RANDOM_SEARCH;
                    break;
            }
        }
    }

    /**
     * Checks if given cell is fully sieged by not damaged cells ( but not empty ) (In other words , it checks if given cell is alone)
     * @param board Board of given cell
     * @param x X coordinates of cell
     * @param y Y coordinates of cell
     * @return {@code true} if it's alone and {@code false} if not.
     */
    private boolean isAlone(Board board,int x,int y){
        try{
            if(board.getCellProperty(x-1,y)!=1){
                return false;
            }
        } catch (Exception e) { }

        try{
            if(board.getCellProperty(x+1,y)!=1){
                return false;
            }
        } catch (Exception e) { }

        try{
            if(board.getCellProperty(x,y-1)!=1){
                return false;
            }
        } catch (Exception e) { }

        try{
            if(board.getCellProperty(x,y+1)!=1){
                return false;
            }
        } catch (Exception e) { }


        return true;


    }

    /**
     * Gets nextX that is calculated by calling {@code nextMove()}
     * @return nextX
     */
    public int getNextX() {
        return nextX;
    }

    /**
     * Gets nextY that is calculated by calling {@code nextMove()}
     * @return nextY
     */
    public int getNextY() {
        return nextY;
    }

}
