import java.util.Random;
import java.util.Scanner;

public class PreShip
{
    Scanner inputs = new Scanner(System.in);

    public PreShip()
    {

    }

    /**
     * In this method you choose whether you want to play with a human or not.
     * @return 1 for human, 2 for computer
     */
    public int pcOrPerson()
    {
        System.out.println("[1]. 1 Player\n[2]. 2 Player");
        String oneOrTwoPlayer = inputs.nextLine();
        switch (oneOrTwoPlayer)
        {
            case "1":
            {
                return 1;
            }
            case "2":
            {
                return 2;
            }
            default:
            {
                return pcOrPerson();
            }
        }
    }

    /**
     * This method gets the username from client.
     * @return String username
     */
    public String whatIsYourName()
    {
        System.out.println("Enter your username:");
        String username = inputs.nextLine();
        return username;
    }

    /**
     * In this method, client decide to use exact shot or mistake shot.
     * @return 1 for exact, 2 for mistake
     */
    public int whichShot()
    {
        System.out.println("[1]. Exact Shot\n[2]. with Mistake Shot");
        String shot = inputs.nextLine();
        switch (shot)
        {
            case "1":
            {
                return 1;
            }
            case "2":
            {
                return 2;
            }
            default:
            {
                return whichShot();
            }
        }
    }

    /**
     * This method will call the calculateTheInputString to fill the ships in the table.
     * @return an int array[][]
     */
    public int[][] getTheStrings()
    {
        int[][] temp = new int[10][10];
        //fill the Array with Space
        for (int i = 0; i < 10; i++)
        {
            for (int j = 0; j < 10; j++)
            {
                temp[i][j] = 0;
            }
        }

        System.out.println("<Notice>: your input should be like this: H234, W578, etc.\n" +
                "That H stands for Vertical ship, W stands for Horizontal ship\n" +
                "first integer(e.x: 2 in H234) stands for Length of the ship that should be 2, 3, 4 or 5.\n" +
                "second integer(e.x: 3 in H234) stands for the first element of the coordinates(X) that can be 0, 1, 2, ..., 8, 9.\n" +
                "third integer(e.x: 4 in H234) stands for the second element of the coordinates(Y) that can be 0, 1, 2, ..., 8, 9.\n");

        for (int i = 0; i < 5; i++)
        {
            calculateTheInputString(temp);
        }
        return temp;
    }

    /**
     * This method will be called by getTheStrings to fill a ship in the table.
     * @param cTemp that is an int array[][]
     */
    public void calculateTheInputString(int[][] cTemp)
    {
        System.out.println("Enter the info of your ship: ");
        String address = inputs.nextLine();
        address = address.replaceAll("[, ]", "");
        address = address.replace('h', 'H');
        address = address.replace('w', 'W');
        //System.out.println(address);

        if (address.length() != 4)
        {
            System.out.println("Wrong input!");
            calculateTheInputString(cTemp);
            return;
        }

        char hOrW = address.charAt(0);
        //System.out.println(hOrW);
        int lengthOfShip = Integer.parseInt(String.valueOf(address.charAt(1)));
        //System.out.println(lengthOfShip);
        int XOfShip = Integer.parseInt(String.valueOf(address.charAt(2)));
        //System.out.println(XOfShip);
        int YOfShip = Integer.parseInt(String.valueOf(address.charAt(3)));
        //System.out.println(YOfShip);

        if ( !( (hOrW == 'W') || (hOrW == 'H')) )
        {
            System.out.println("Wrong input!");
            calculateTheInputString(cTemp);
            return;
        }
        if (!(lengthOfShip <= 5))
        {
            System.out.println("Wrong input!");
            calculateTheInputString(cTemp);
            return;
        }
        if (!(lengthOfShip >= 2))
        {
            System.out.println("Wrong input!");
            calculateTheInputString(cTemp);
            return;
        }
        if (!(XOfShip >= 0))
        {
            System.out.println("Wrong input!");
            calculateTheInputString(cTemp);
            return;
        }
        if (!(XOfShip <= 9))
        {
            System.out.println("Wrong input!");
            calculateTheInputString(cTemp);
            return;
        }
        if (!(YOfShip >= 0))
        {
            System.out.println("Wrong input!");
            calculateTheInputString(cTemp);
            return;
        }
        if (!(YOfShip <= 9))
        {
            System.out.println("Wrong input!");
            calculateTheInputString(cTemp);
            return;
        }
        if (cTemp[XOfShip][YOfShip] == 1)
        {
            System.out.println("Repeated input!");
            calculateTheInputString(cTemp);
            return;
        }
        if (hOrW == 'H')
        {
            if ((lengthOfShip + YOfShip) > 9)
            {
                System.out.println("Invalid input!");
                calculateTheInputString(cTemp);
                return;
            }
        }
        if (hOrW == 'W')
        {
            if ((lengthOfShip + XOfShip) > 9)
            {
                System.out.println("Invalid input!");
                calculateTheInputString(cTemp);
                return;
            }
        }

        switch (hOrW)
        {
            case 'H':
            {
                for (int i = YOfShip; i < (lengthOfShip + YOfShip); i++)
                {
                    cTemp[XOfShip][i] = 1;
                }
                break;
            }
            case 'W':
            {
                for (int i = XOfShip; i < (lengthOfShip + XOfShip); i++)
                {
                    cTemp[i][YOfShip] = 1;
                }
                break;
            }
        }
        //prints the array in each step
        /*for (int i = 0; i < 10; i++)
        {
            for (int j =0; j < 10; j++)
            {
                System.out.print(cTemp[j][i]);
            }
            System.out.println();
        }*/

    }
    //Until here is about Human Ship

    //From here is about Computer ship
    Random rand = new Random();

    /**
     * This method will call the processRandomStrings to fill the table with random ships.
     * @return an int array[][]
     */
    public int[][] getRandomStrings()
    {
        int[][] temp = new int[10][10];
        //fill the Array with Space
        for (int i = 0; i < 10; i++)
        {
            for (int j = 0; j < 10; j++)
            {
                temp[i][j] = 0;
            }
        }

        for (int k = 0; k < 5; k++)
        {
            processRandomStrings(temp);
        }
        return temp;
    }

    /**
     * This method will be called by getRandomStrings to fill the table with a random ship.
     * @param temp that is an int array[][]
     */
    public void processRandomStrings(int[][] temp)
    {
        int hOrW = rand.nextInt(2) + 1;
        int lengthOfShip = rand.nextInt(4) + 2;
        int XOfShip = rand.nextInt(10);
        int YOfShip = rand.nextInt(10);

        if (temp[XOfShip][YOfShip] == 1)
        {
            processRandomStrings(temp);
            return;
        }
        if (hOrW == 1)
        {
            if ((lengthOfShip + YOfShip) > 9)
            {
                processRandomStrings(temp);
                return;
            }
        }
        if (hOrW == 2)
        {
            if ((lengthOfShip + XOfShip) > 9)
            {
                processRandomStrings(temp);
                return;
            }
        }

        switch (hOrW)
        {
            case 1:
            {
                for (int i = YOfShip; i < (lengthOfShip + YOfShip); i++)
                {
                    temp[XOfShip][i] = 1;
                }
                break;
            }
            case 2:
            {
                for (int i = XOfShip; i < (lengthOfShip + XOfShip); i++)
                {
                    temp[i][YOfShip] = 1;
                }
                break;
            }
        }
    }

    /**
     * This method will call other methods to make a RealHumanBased Ship.
     * @param name
     * @param shot
     * @param cTemp
     * @return a Person
     */
    public Ship human(String name, int shot, int[][] cTemp)
    {
        name = whatIsYourName();
        shot = whichShot();
        cTemp = getTheStrings();
        Ship person = new Ship(name, shot, cTemp, true);
        return person;
    }

    /**
     * This method will call other methods to make a ComputerBased Ship.
     * @param name
     * @param shot
     * @param cTemp
     * @return a Computer
     */
    public Ship comp(String name, int shot, int[][] cTemp)
    {
        name = "Computer";
        shot = rand.nextInt(2) + 1;
        cTemp = getRandomStrings();
        Ship computer = new Ship(name, shot, cTemp, false);
        return computer;
        /*for (int i = 0; i < 10; i++)
        {
            for (int j =0; j < 10; j++)
            {
                System.out.print(cTemp[j][i]);
            }
            System.out.println();
        }*/
    }
}
