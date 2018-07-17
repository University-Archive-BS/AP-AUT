import java.util.ArrayList;

public class Animal
{
    //fields
    private String name;
    private int energy;
    private int health;
    private ArrayList<AttackType> attackTypes = new ArrayList<AttackType>();

    //constructors
    public Animal(String name)
    {
        this.name = name;

        switch (name)
        {

            case "Lion":
            {
                this.energy = 1000;
                this.health = 900;

                AttackType a = new AttackType("Wound", 150);
                attackTypes.add(a);
                AttackType b = new AttackType("Kill", 500);
                attackTypes.add(b);
                break;
            }

            case "Bear":
            {
                this.energy = 900;
                this.health = 850;

                AttackType a = new AttackType("Wound", 130);
                attackTypes.add(a);
                AttackType b = new AttackType("Kill", 600);
                attackTypes.add(b);
                break;
            }

            case "Tiger":
            {
                this.energy = 850;
                this.health = 850;

                AttackType a = new AttackType("Wound", 120);
                attackTypes.add(a);
                AttackType b = new AttackType("Kill", 650);
                attackTypes.add(b);
                break;
            }

            case "Vulture":
            {
                this.energy = 600;
                this.health = 350;

                AttackType a = new AttackType("Wound", 100);
                attackTypes.add(a);
                break;
            }

            case "Fox":
            {
                this.energy = 600;
                this.health = 400;

                AttackType a = new AttackType("Wound", 90);
                attackTypes.add(a);
                break;
            }

            case "Elephant":
            {
                this.energy = 500;
                this.health = 1200;

                AttackType a = new AttackType("Injure", 70);
                attackTypes.add(a);
                AttackType b = new AttackType("Attack", 50);
                attackTypes.add(b);
                break;
            }

            case "Wolf":
            {
                this.energy = 700;
                this.health = 450;

                AttackType a = new AttackType("kill", 700);
                attackTypes.add(a);
                break;
            }

            case "Pig":
            {
                this.energy = 500;
                this.health = 1100;

                AttackType a = new AttackType("Injure", 80);
                attackTypes.add(a);
                break;
            }

            case "Hippopotamus":
            {
                this.energy = 360;
                this.health = 1000;

                AttackType b = new AttackType("Attack", 110);
                attackTypes.add(b);
                break;
            }

            case "Cow":
            {
                this.energy = 400;
                this.health = 750;

                AttackType a = new AttackType("Wound", 100);
                attackTypes.add(a);
                AttackType b = new AttackType("Attack", 90);
                attackTypes.add(b);
                break;
            }

            case "Rabbit":
            {
                this.energy = 350;
                this.health = 200;

                AttackType b = new AttackType("Bite", 80);
                attackTypes.add(b);
                break;
            }

            case "Turtle":
            {
                this.energy = 230;
                this.health = 350;

                AttackType b = new AttackType("Bite", 200);
                attackTypes.add(b);
                break;
            }

        }
    }

    public String getName()
    {
        return name;
    }

    public int getHealth()
    {
        return health;
    }
    public void setHealth(int health)
    {
        this.health = health;
    }

    public int getEnergy()
    {
        return energy;
    }
    public void setEnergy(int energy)
    {
        this.energy = energy;
    }

    public ArrayList<AttackType> getAttackTypes()
    {
        return attackTypes;
    }

    public void repair()
    {
        switch (name)
        {
            case "Lion":
            {
                setEnergy(1000);
                break;
            }
            case "Bear":
            {
                setEnergy(900);
                break;
            }
            case "Tiger":
            {
                setEnergy(850);
                break;
            }
            case "Vulture":
            {
                setEnergy(600);
                break;
            }
            case "Fox":
            {
                setEnergy(600);
                break;
            }
            case "Elephant":
            {
                setEnergy(500);
                break;
            }
            case "Wolf":
            {
                setEnergy(700);
                break;
            }
            case "Pig":
            {
                setEnergy(500);
                break;
            }
            case "Hippopotamus":
            {
                setEnergy(360);
                break;
            }
            case "Cow":
            {
                setEnergy(400);
                break;
            }
            case "Rabbit":
            {
                setEnergy(350);
                break;
            }
            case "Turtle":
            {
                setEnergy(230);
                break;
            }
        }
    }
}
