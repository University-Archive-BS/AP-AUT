public class AttackType
{
    //fields
    private String type;
    private int effect;

    //constructor
    public AttackType(String type, int effect)
    {
        this.type = type;
        this.effect = effect;
    }

    public String getType()
    {
        return type;
    }

    public int getEffect()
    {
        return effect;
    }
}
