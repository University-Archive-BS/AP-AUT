public class TwoPlayer extends GameManager
{
    //fields
    private Ship secondPlayer;

    //constructor
    public TwoPlayer(Ship firstPlayer, Ship secondPlayer)
    {
        super(firstPlayer);
        this.secondPlayer = secondPlayer;
    }

    //methods
    @Override
    public void run()
    {
        while (true)
        {
            userInterface(firstPlayer);
            getCoordinates(firstPlayer, secondPlayer);
            userInterface(secondPlayer);
            getCoordinates(secondPlayer, firstPlayer);
        }
    }
}
