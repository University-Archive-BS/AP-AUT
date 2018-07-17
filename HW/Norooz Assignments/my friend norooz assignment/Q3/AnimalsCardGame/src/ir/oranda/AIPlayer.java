package ir.oranda;

import ir.oranda.animals.Animal;

import java.util.ArrayList;
import java.util.Random;

/**
 * AIPlayer class - Indicates a player which is ai-controlled
 *
 * @author MMKH
 * @version 1.0.0
 */
public class AIPlayer extends Player {

    /**
     * Creates a ai-controlled player
     * @param playerIndex Player Index
     * @param uim UIManager
     */
    public AIPlayer(int playerIndex, UIManager uim) {
        super(playerIndex, uim);
    }

    /**
     * Randomly selects 10 cards from given animal cards and adds them to this player
     * @param animals
     */
    @Override
    public void addCards(ArrayList<Animal> animals) {
        Random r = new Random();
        while (animals.size()>10)
            animals.remove(r.nextInt(animals.size()));
        setPlayerAnimals(animals);

    }

    /**
     * Controls player's turn
     * @param opponent Opponent Player
     */
    @Override
    public void takeTurn(Player opponent) {
        getUim().passToPlayer(getPlayerIndex());
        ArrayList<Animal> animals = cardExpressionAI(opponent);
        if(animals!=null)
            opponent.defend(animals.get(0),attack(animals));
    }

    /**
     * Performs whole of AI operations during the game
     * @param opponent Opponent Player
     * @return An ArrayList of animals , which it's first element is defender card and others are attacker cards
     */
    public ArrayList<Animal> cardExpressionAI(Player opponent){
        Random r = new Random();
        ArrayList<Integer> animalIndexes = new ArrayList<>();
        int currentAnimalIndex;
        boolean currentAnimalType2Selected;
        char selectedAttackTypeCode = '0';
        boolean finished = false;

        if(energyShortage()){
            if(getRemainingRepairs()>0){
                // Repair
                int max = 0;
                int selectedRepairIndex = r.nextInt(getRemainingCards());
                for (int i=0 ; i<getPlayerAnimals().size() ; i++) {
                    if (getPlayerAnimals().get(i).getFullEnergy() - getPlayerAnimals().get(i).getEnergy()>max){
                        max = getPlayerAnimals().get(i).getFullEnergy() - getPlayerAnimals().get(i).getEnergy();
                        selectedRepairIndex = i;
                    }
                }
                repair(selectedRepairIndex);
            }
            return null;
        }



        do {
            currentAnimalIndex = r.nextInt(getRemainingCards());
            while (animalIndexes.contains(currentAnimalIndex))
                currentAnimalIndex = r.nextInt(getRemainingCards());
            animalIndexes.add(currentAnimalIndex);

            if (getPlayerAnimals().get(currentAnimalIndex).getAttackType2() != null) {
                currentAnimalType2Selected = r.nextBoolean();
                getPlayerAnimals().get(currentAnimalIndex).setAttackType2Selected(currentAnimalType2Selected);
            } else {
                currentAnimalType2Selected = false;
            }
            if (animalIndexes.size() == 1) {
                // First animal !
                selectedAttackTypeCode = (currentAnimalType2Selected ? getPlayerAnimals().get(currentAnimalIndex).getAttackType2().getAttackCode() : getPlayerAnimals().get(currentAnimalIndex).getAttackType1().getAttackCode());
            } else {
                if (selectedAttackTypeCode != (currentAnimalType2Selected ? getPlayerAnimals().get(currentAnimalIndex).getAttackType2().getAttackCode() : getPlayerAnimals().get(currentAnimalIndex).getAttackType1().getAttackCode())) {
                    if(getPlayerAnimals().get(currentAnimalIndex).getAttackType2() != null){
                        currentAnimalType2Selected=!currentAnimalType2Selected;
                        if (selectedAttackTypeCode != (currentAnimalType2Selected ? getPlayerAnimals().get(currentAnimalIndex).getAttackType2().getAttackCode() : getPlayerAnimals().get(currentAnimalIndex).getAttackType1().getAttackCode())) {
                            animalIndexes.remove(animalIndexes.size() - 1);
                        }
                    }
                    else{
                        animalIndexes.remove(animalIndexes.size() - 1);
                    }
                }
            }

            if (r.nextInt(6) == 2) {
                ArrayList<Integer> erroredAnimalIndexes = checkEnergyAvailablity(animalIndexes);
                if(erroredAnimalIndexes.size()==0){
                    finished = true;
                }
                else{
                    animalIndexes.clear();
                }
            }

        } while (animalIndexes.size()==0 || !finished);

        // Checks for sufficient energy ...
        int totalEnergy = calculateTotalEnergy(animalIndexes);

        // Find best target ...
        int minPositive = 1000;
        int selectedOpponentIndex = r.nextInt(opponent.getRemainingCards());
        for (int i=0 ; i<opponent.getPlayerAnimals().size() ; i++) {
            int deltaEnergy=totalEnergy - opponent.getPlayerAnimals().get(i).getHealth();
            if (deltaEnergy > 0 && deltaEnergy<minPositive){
                minPositive = deltaEnergy;
                selectedOpponentIndex = i;
            }
        }

        animalIndexes.add(0,selectedOpponentIndex);
        return cardExpressionIndexToAnimal(opponent, animalIndexes);


    }

}
