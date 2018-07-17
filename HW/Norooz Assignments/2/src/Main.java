public class Main
{
    public static void main(String[] args)
    {
        Ship computer = new Ship();
        Ship firstPerson = new Ship();
        Ship secondPerson = new Ship();

        //Pre Ship------------------------------------------

        PreShip x = new PreShip();
        int numOfPlayers = x.pcOrPerson();
        String name = "";
        int shot = 0;
        int[][] cTemp = new int[10][10];

        switch (numOfPlayers)
        {
            case 1:
            {
                computer = x.comp(name, shot, cTemp);
                firstPerson = x.human(name, shot, cTemp);
                OnePlayer game = new OnePlayer(firstPerson, computer);
                for (int i = 0; i < 100; i++)
                {
                    System.out.println("\n");
                }
                game.run();
                break;
            }
            case 2:
            {
                firstPerson = x.human(name, shot, cTemp);
                secondPerson = x.human(name, shot, cTemp);
                TwoPlayer game = new TwoPlayer(firstPerson, secondPerson);
                for (int i = 0; i < 100; i++)
                {
                    System.out.println("\n");
                }
                game.run();
                break;
            }
        }
        //Finish Pre Ship------------------------------------


    }
}
