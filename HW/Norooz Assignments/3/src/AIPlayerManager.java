import java.util.ArrayList;
import java.util.Random;

public class AIPlayerManager extends PlayerManager
{
    Random rand = new Random();

    //fields
    ArrayList<Animal> computer;
    int avoidStackOverFlow;

    //constructor
    public AIPlayerManager(ArrayList<Animal> firstPerson, ArrayList<Animal> computer)
    {
        super(firstPerson, 3);
        this.computer = computer;
        avoidStackOverFlow = 0;
    }

    //methods
    @Override
    public void run()
    {
        while (true)
        {
            UI(firstPerson, computer);
            humanTurn(firstPerson, computer);
            finishTheGame(computer);

            System.out.println("\n\n\n");

            randomTurn(computer, firstPerson);
            finishTheGame(firstPerson);
        }
    }

    public void randomTurn(ArrayList<Animal> computer, ArrayList<Animal> firstPerson)
    {
        int card = rand.nextInt(computer.size());
        int attackType = rand.nextInt(computer.get(card).getAttackTypes().size());
        int hit = rand.nextInt(firstPerson.size());

        if (computer.get(card).getAttackTypes().get(attackType).getEffect() <= computer.get(card).getEnergy())
        {
            computer.get(card).setEnergy( ( computer.get(card).getEnergy() ) - ( computer.get(card).getAttackTypes().get(attackType).getEffect() ) );
            firstPerson.get(hit).setHealth( ( firstPerson.get(hit).getHealth() ) - ( computer.get(card).getAttackTypes().get(attackType).getEffect() ) );
            deleteCard(firstPerson, hit);
        }
        else
        {
            avoidStackOverFlow++;
            if (avoidStackOverFlow > 15)
            {
                return;
            }
            else
            {
                randomTurn(computer, firstPerson);
            }
        }
        avoidStackOverFlow = 0;
    }
}
