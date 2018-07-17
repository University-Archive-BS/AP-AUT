package ir.oranda;

import ir.oranda.animals.Animal;
import ir.oranda.animals.AnimalType;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * Player class - Indicates a player which could be human-controlled or ai-controlled
 *
 * @author MMKH
 * @version 1.0.0
 */
public abstract class Player {

    // Player index from 0
    private int playerIndex;

    // UIManager
    private UIManager uim;

    // Animal cards of this player
    private ArrayList<Animal> playerAnimals;

    // Remaining repair times (From 3 to 0)
    private int remainingRepairs;

    /**
     * Creates a new player
     * @param playerIndex Player index from 0
     * @param uim UIManager
     */
    public Player(int playerIndex, UIManager uim) {
        this.playerIndex = playerIndex;
        this.uim = uim;
        remainingRepairs=3;
    }

    /**
     * Gets player index
     * @return playerIndex
     */
    public int getPlayerIndex() {
        return playerIndex;
    }

    /**
     * Gets UIManager
     * @return uim
     */
    public UIManager getUim() {
        return uim;
    }

    /**
     * Gets player animal cards
     * @return playerAnimals
     */
    public ArrayList<Animal> getPlayerAnimals() {
        return playerAnimals;
    }

    /**
     * Sets player animal cards
     * @param playerAnimals
     */
    public void setPlayerAnimals(ArrayList<Animal> playerAnimals) {
        this.playerAnimals = playerAnimals;
    }

    /**
     * Gets remaining cards
     * @return
     */
    public int getRemainingCards() {
        return playerAnimals.size();
    }

    /**
     * Gets remaining repairs
     * @return remainingRepairs
     */
    public int getRemainingRepairs() {
        return remainingRepairs;
    }

    /**
     * Repairs given animal card (by index)
     * @param animalIndex Card index
     * @return {@code true} If repair was successful , otherwise returns {@code false}
     */
    public boolean repair(int animalIndex){
        if(remainingRepairs>0) {
            remainingRepairs--;
            playerAnimals.get(animalIndex).repair();
            return true;
        }
        return false;
    }

    /**
     * Generates 30 random animal cards and then passes them to {@code addCards(ArrayList<Animal>)}
     */
    public void randomizeCards() {
        Random r = new Random();
        ArrayList<Animal> animals = new ArrayList<>();
        ArrayList<Integer> selectedAnimalTypes = new ArrayList<>();
        int[] selectedCountFromType = new int[12];
        while(animals.size() < 30) {
            if(selectedAnimalTypes.size() == 12){
                selectedAnimalTypes.remove(r.nextInt(12));
            }
            int selectedType = r.nextInt(12);
            while (selectedAnimalTypes.contains(selectedType)) {
                selectedType = r.nextInt(12);
            }
            selectedAnimalTypes.add(selectedType);
            int count = r.nextInt(6);
            while(selectedCountFromType[selectedType] + count > 5 || count+animals.size() > 30){
                count = r.nextInt(6);
            }
            selectedCountFromType[selectedType] += count;
            for(int i=0 ; i<count ; i++){
                animals.add(new Animal(AnimalType.values()[selectedType]));
            }
        }

        // animals array has 30 animals.

        addCards(animals);

    }

    /**
     * Selects 10 cards from given animal cards and adds them to this player
     * @param animals
     */
    public abstract void addCards(ArrayList<Animal> animals);

    /**
     * Controls player's turn
     * @param opponent Opponent Player
     * @throws IOException
     */
    public abstract void takeTurn(Player opponent) throws IOException;

    /**
     * Checks if given animals have sufficient energy for attack
     * @param animalIndexes
     * @return An ArrayList of index of animals that have insufficient energy for an attack
     */
    public ArrayList<Integer> checkEnergyAvailablity(ArrayList<Integer> animalIndexes){

        ArrayList<Integer> erroredAnimalIndexes = new ArrayList<>();

        int minimumNeededEnergy = calculateTotalEnergy(animalIndexes)/animalIndexes.size();

        for (Integer index:animalIndexes) {
            if(getPlayerAnimals().get(index).getEnergy() < minimumNeededEnergy){
                erroredAnimalIndexes.add(index);
            }
        }

        return erroredAnimalIndexes;

    }

    /**
     * Calculates total attack energy of given animal cards
     * @param animalIndexes
     * @return Total attack energy
     */
    public int calculateTotalEnergy(ArrayList<Integer> animalIndexes){
        int totalAttackEnergy = 0;

        for (Integer index:animalIndexes) {
            totalAttackEnergy += (getPlayerAnimals().get(index).isAttackType2Selected() ? getPlayerAnimals().get(index).getAttackType2().getAttackEnergy() : getPlayerAnimals().get(index).getAttackType1().getAttackEnergy());
        }

        return totalAttackEnergy;
    }

    /**
     * Substracts average attack energy from any attacker card
     * @param animals An ArrayList of animals , which it's first element is defender card and others are attacker cards
     * @return Total attack energy
     */
    public int attack(ArrayList<Animal> animals) {
        int totalAttackEnergy=0;
        for (Animal animal : animals) {
            if(animals.indexOf(animal)!=0) {
                if (animal.isAttackType2Selected()) {
                    totalAttackEnergy += animal.getAttackType2().getAttackEnergy();
                } else {
                    totalAttackEnergy += animal.getAttackType1().getAttackEnergy();
                }
            }
        }
        int effectiveEnergy = totalAttackEnergy/(animals.size()-1);

        for (Animal animal : animals)
            if(animals.indexOf(animal)!=0)
                animal.setEnergy(animal.getEnergy() - effectiveEnergy);

        return totalAttackEnergy;
    }


    /**
     * Substracts total attack energy from defender's health
     * @param animal Defender animal
     * @param totalAttackEnergy Total attack energy
     */
    public void defend(Animal animal,int totalAttackEnergy) {
        animal.setHealth(animal.getHealth() - totalAttackEnergy);
        if(animal.getHealth()<=0){
            getPlayerAnimals().remove(animal);
        }
    }

    /**
     * Checks if none of animals can make an attack as a result of insufficient energy
     * @return {@code true} On energy shortage
     */
    public boolean energyShortage(){
        for (Animal animal : getPlayerAnimals()) {
            if(animal.getEnergy() > animal.getAttackType1().getAttackEnergy())
                return false;
            if(animal.getAttackType2()!=null)
                if(animal.getEnergy() > animal.getAttackType2().getAttackEnergy())
                    return false;
        }
        return true;
    }

    /**
     * Converts an ArrayList of card indexes to an ArrayList of Animals
     * @param opponent Opponent player
     * @param animalIndexes An ArrayList of card indexes , which it's first element is defender card and others are attacker cards
     * @return An ArrayList of Animals , which it's first element is defender card and others are attacker cards
     */
    public ArrayList<Animal> cardExpressionIndexToAnimal(Player opponent,ArrayList<Integer> animalIndexes){
        ArrayList<Animal> animals = new ArrayList<>();
        animals.add(opponent.getPlayerAnimals().get(animalIndexes.get(0)));
        for (int i = 1; i < animalIndexes.size(); i++) {
            animals.add(getPlayerAnimals().get(animalIndexes.get(i)));
        }
        return animals;
    }

}
