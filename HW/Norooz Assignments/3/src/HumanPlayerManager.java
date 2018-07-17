import java.util.ArrayList;

public class HumanPlayerManager extends PlayerManager
{
    //fields
    ArrayList<Animal> secondPerson;

    //constructor
    public HumanPlayerManager(ArrayList<Animal> firstPerson, ArrayList<Animal> secondPerson)
    {
        super(firstPerson, 0);
        this.secondPerson = secondPerson;
    }

    //methods
    @Override
    public void run()
    {
        while(true)
        {
            UI(firstPerson, secondPerson);
            humanTurn(firstPerson, secondPerson);
            finishTheGame(secondPerson);

            System.out.println("\n\n\n");

            UI(secondPerson, firstPerson);
            humanTurn(secondPerson, firstPerson);
            finishTheGame(firstPerson);
        }
    }

}
