import java.util.Random;

public class OnePlayer extends GameManager
{
    Random rand = new Random();

    //fields
    private Ship computer;

    //constructor
    public OnePlayer(Ship firstPlayer, Ship computer)
    {
        super(firstPlayer);
        this.computer = computer;
    }

    //methods
    @Override
    public void run()
    {
        while (true)
        {
            userInterface(firstPlayer);
            getCoordinates(firstPlayer, computer);
            userInterface(computer);
            playComputer();
        }
    }

    public void playComputer()
    {
        int x = rand.nextInt(10);
        int y = rand.nextInt(10);

        if (!(x >= 0))
        {
            playComputer();
            return;
        }
        if (!(x <= 9))
        {
            playComputer();
            return;
        }
        if (!(y >= 0))
        {
            playComputer();
            return;
        }
        if (!(y <= 9))
        {
            playComputer();
            return;
        }

        switch (getTrueOrFalse(firstPlayer, x, y))
        {
            case 1:
            {
                setTrueHit(computer, x, y);
                setDamagedShip(firstPlayer, x, y);

                userInterface(computer);
                firstPlayer.cellsTemp[x][y] = 2;

                switch (checkFinish(firstPlayer))
                {
                    case 1:
                    {
                        finish(computer);
                        System.exit(1);
                    }
                    case 0:
                    {
                        break;
                    }
                }

                getCoordinates(firstPlayer, computer);
                break;
            }
            case 2:
            {
                playComputer();
                break;
            }
            case 0:
            {
                System.out.println("False!");
                computer.cellsTemp[x][y] = 2;
                break;
            }
        }
    }

}
