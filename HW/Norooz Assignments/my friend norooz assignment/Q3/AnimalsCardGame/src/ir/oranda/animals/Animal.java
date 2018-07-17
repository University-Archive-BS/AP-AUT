package ir.oranda.animals;

/**
 * Animal class - Holds an animal card
 *
 * @author MMKH
 * @version 1.0.0
 */
public class Animal {
    // Animal's display name
    private String name;

    // Animal's current energy
    private int energy;

    // Animal's current health
    private int health;

    // Animal's AttackType one
    private AttackType attackType1;

    // Animal's AttackType two (If there is any , otherwise it will be null)
    private AttackType attackType2;

    // Is AttackType two selected ? (If there is any)
    private boolean attackType2Selected;

    // Animal Type
    private AnimalType animalType;

    // Animal's full initial energy
    private int fullEnergy;


    /**
     * Creates an animal card with the given type
     * @param animalType
     */
    public Animal(AnimalType animalType) {
        switch (animalType){
            case Cow:
                setupAnimal("Cow", 400, 750, new AttackType("Attack",90), new AttackType("Wound",100),animalType);
                break;
            case Fox:
                setupAnimal("Fox", 600, 400, new AttackType("Wound",90),null,animalType);
                break;
            case Bear:
                setupAnimal("Bear", 900, 850, new AttackType("Wound",130), new AttackType("Kill",600),animalType);
                break;
            case Lion:
                setupAnimal("Lion", 1000, 900, new AttackType("Wound",150), new AttackType("Kill",500),animalType);
                break;
            case Wolf:
                setupAnimal("Wolf", 700, 450, new AttackType("Kill",700), null,animalType);
                break;
            case Swine:
                setupAnimal("Swine", 500, 1100, new AttackType("Injure",80), null,animalType);
                break;
            case Tiger:
                setupAnimal("Tiger", 850, 850, new AttackType("Wound",120), new AttackType("Kill",650),animalType);
                break;
            case Rabbit:
                setupAnimal("Rabbit", 350, 200, new AttackType("Bite",80), null,animalType);
                break;
            case Turtle:
                setupAnimal("Turtle", 230, 350, new AttackType("Bite",200), null,animalType);
                break;
            case Vulture:
                setupAnimal("Vulture", 600, 350, new AttackType("Wound",100), null,animalType);
                break;
            case Elephant:
                setupAnimal("Elephant", 500, 1200, new AttackType("Injure",70), new AttackType("Attack",50),animalType);
                break;
            case Hippo:
                setupAnimal("Hippo", 360, 1000, new AttackType("Attack",110), null,animalType);
                break;
        }

    }

    /**
     * Setups an animal card
     * @param name Display name
     * @param energy Current energy
     * @param health Current health
     * @param attackType1 AttackType one
     * @param attackType2 AttackType two
     * @param animalType Animal type
     */
    private void setupAnimal(String name, int energy, int health, AttackType attackType1, AttackType attackType2,AnimalType animalType) {
        this.name = name;
        this.energy = energy;
        this.fullEnergy = energy;
        this.health = health;
        this.attackType1 = attackType1;
        this.attackType2 = attackType2;
        this.attackType2Selected = false;
        this.animalType = animalType;

    }

    /**
     * Repairs this animal
     */
    public void repair(){
        energy=fullEnergy;
    }

    /**
     * Gets animal's display name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets animal's display name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets animal's current energy
     * @return energy
     */
    public int getEnergy() {
        return energy;
    }

    /**
     * Sets animal's current energy
     * @param energy
     */
    public void setEnergy(int energy) {
        this.energy = energy;
    }

    /**
     * Gets animal's current health
     * @return health
     */
    public int getHealth() {
        return health;
    }

    /**
     * Sets animal's current health
     * @param health
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * Is AttackType two selected ? (If there is any)
     * @return attackType2Selected
     */
    public boolean isAttackType2Selected() {
        return attackType2Selected;
    }

    /**
     * Sets AttackType two selection state (If there is any)
     * @param attackType2Selected
     */
    public void setAttackType2Selected(boolean attackType2Selected) {
        this.attackType2Selected = attackType2Selected;
    }

    /**
     * Gets AttackType one
     * @return attackType1
     */
    public AttackType getAttackType1() {
        return attackType1;
    }

    /**
     * Gets AttackType two
     * @return attackType2
     */
    public AttackType getAttackType2() {
        return attackType2;
    }

    /**
     * Gets AttackTypes formatted human-readable string
     * @return
     */
    public String getAttackTypesString() {
        return "[" + attackType1.getAttackCode() + "]" + attackType1.getAttackName().substring(1) + (attackType2 != null ? "/[" + attackType2.getAttackCode() + "]" + attackType2.getAttackName().substring(1) : "");
    }

    /**
     * Gets Animal Type
     * @return animalType
     */
    public AnimalType getAnimalType() {
        return animalType;
    }

    /**
     * Gets animal's full initiate energy
     * @return fullEnergy
     */
    public int getFullEnergy() {
        return fullEnergy;
    }
}
