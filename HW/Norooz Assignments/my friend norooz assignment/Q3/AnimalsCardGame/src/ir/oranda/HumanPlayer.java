package ir.oranda;

import ir.oranda.animals.Animal;

import java.io.IOException;
import java.util.ArrayList;

/**
 * HumanPlayer class - Indicates a player which is human-controlled
 *
 * @author MMKH
 * @version 1.0.0
 */
public class HumanPlayer extends Player {

    /**
     * Creates a human-controlled player
     * @param playerIndex Player Index
     * @param uim UIManager
     */
    public HumanPlayer(int playerIndex, UIManager uim) {
        super(playerIndex, uim);
    }

    /**
     * Selects 10 cards from given animal cards and adds them to this player
     * @param animals
     */
    @Override
    public void addCards(ArrayList<Animal> animals) {
        setPlayerAnimals(getUim().cardSelector(getPlayerIndex(),animals));
    }

    /**
     * Controls player's turn
     * @param opponent Opponent Player
     * @throws IOException
     */
    @Override
    public void takeTurn(Player opponent) throws IOException{
        getUim().passToPlayer(getPlayerIndex());
        ArrayList<Animal> animals = getUim().cardExpression(this,opponent);
        // If animals is null , a repair was happened
        if(animals!=null)
            opponent.defend(animals.get(0),attack(animals));

    }


}
