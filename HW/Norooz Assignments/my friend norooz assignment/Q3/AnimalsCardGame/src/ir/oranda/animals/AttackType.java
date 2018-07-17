package ir.oranda.animals;

/**
 * AttackType class - Holds information about an attack type
 *
 * @author MMKH
 * @version 1.0.0
 */
public class AttackType {

    // Attack's display name
    private String attackName;

    // Attack's destructive energy
    private int attackEnergy;

    /**
     * Creates an attack type with given display name and destructive energy
     * @param attackName Attack's display name
     * @param attackEnergy Attack's destructive energy
     */
    public AttackType(String attackName ,int attackEnergy) {
        this.attackEnergy = attackEnergy;
        this.attackName = attackName;
    }

    /**
     * Gets attack's display name
     * @return attackName
     */
    public String getAttackName() {
        return attackName;
    }

    /**
     * Gets attack's destructive energy
     * @return attackEnergy
     */
    public int getAttackEnergy() {
        return attackEnergy;
    }

    /**
     * Gets attack code
     * @return Uppercase , first character of attack's display name
     */
    public char getAttackCode(){
        return attackName.toUpperCase().charAt(0);
    }
}
