import java.util.Scanner;

public abstract class GameManager
{
    Scanner inputs = new Scanner(System.in);

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";


    //fields
    protected Ship firstPlayer;

    //constructor
    public GameManager(Ship firstPlayer)
    {
        this.firstPlayer = firstPlayer;
    }

    //methods
    public abstract void run();

    /**
     * This method will said whether in this cell there is any ship or not.
     * @param input
     * @param x
     * @param y
     * @return
     */
    public int getTrueOrFalse(Ship input, int x, int y)
    {
        return input.cellsTemp[x][y];
    }

    /**
     * This method will fill the true hits with &.
     * @param input
     * @param x
     * @param y
     */
    public void setTrueHit(Ship input, int x, int y)
    {
        input.enemyCells[x][y] = '&';
    }

    /**
     * This method will fill the wrong hits with X.
     * @param input
     * @param x
     * @param y
     */
    public void setWrongHit(Ship input, int x, int y)
    {
        input.enemyCells[x][y] = '$';
    }

    /**
     * This method will fill the damaged ships with #.
     * @param input
     * @param x
     * @param y
     */
    public void setDamagedShip(Ship input, int x, int y)
    {
        input.cells[x][y] = '#';
    }

    /**
     * This method checks whether the game should be finished or not.
     * @param input
     * @return number of remaining ships(int)
     */
    public int remainedShips(Ship input)
    {
        int x = 0;
        for (int i = 0; i < 10; i++)
        {
            for (int j = 0; j < 10; j++)
            {
                if (input.cellsTemp[j][i] == 1)
                {
                    x++;
                }
            }
        }
        return x;
    }

    /**
     * This method will decide to finish the game...
     * @param input
     * @return 1 for finishing the game
     */
    public int checkFinish(Ship input)
    {
        if (remainedShips(input) == 0)
        {
            return 1;
        }
        else
        {
            return 0;
        }
    }

    /**
     * This method will print a line to show that the game has finished.
     * @param winner
     */
    public void finish(Ship winner)
    {
        System.out.println("Player: " + winner.name + " Won the Game.");
    }

    /**
     * This method prints the tables and headers in each step.
     * @param one
     */
    public void userInterface(Ship one)
    {
        //[j][i]
        System.out.print("\t\t\t\t" + "Your Board" + "\t\t\t\t\t\t\t\t\t" +"Your Enemy's Board" + "\n" +
                " \t\t\t" + "##################" + "\t\t\t\t\t\t\t" +"##########################" + "\n\n");
        for (int i = -1; i < 10; i++)
        {
            for (int j = -1; j < 10; j++)
            {
                if (i < 0)
                {
                    if (j < 0)
                    {
                        System.out.print("  |");
                    }
                    else
                    {
                        System.out.print(" " + j + " |");
                    }
                }
                else
                {
                    if (j < 0)
                    {
                        System.out.print(i + " |");
                    }
                    else
                    {
                        switch (one.cells[j][i])
                        {
                            case '@':
                            {
                                System.out.print(ANSI_BLUE_BACKGROUND + "   " + ANSI_RESET);
                                break;
                            }
                            case '#':
                            {
                                System.out.print(ANSI_RED_BACKGROUND + "   " + ANSI_RESET);
                                break;
                            }
                            case ' ':
                            {
                                System.out.print("   ");
                                break;
                            }
                        }
                        System.out.print("|");
                    }
                }
            }
            System.out.print("\t");
            for (int j = -1; j < 10; j++)
            {
                if (i < 0)
                {
                    if (j < 0)
                    {
                        System.out.print("\t  |");
                    }
                    else
                    {
                        System.out.print(" " + j);
                        System.out.print(" " + "|");
                    }
                }
                else
                {
                    if (j < 0)
                    {
                        System.out.print("\t" + i + " |");
                    }
                    else
                    {
                        //System.out.print(" " + one.enemyCells[j][i] + " |");
                        //System.out.print(" ");// + one.cells[j][i] + " |");
                        switch (one.enemyCells[j][i])
                        {
                            case '&':
                            {
                                System.out.print(ANSI_GREEN_BACKGROUND + "   " + ANSI_RESET);
                                break;
                            }
                            case '$':
                            {
                                System.out.print(ANSI_YELLOW_BACKGROUND + "   " + ANSI_RESET);
                                break;
                            }
                            case ' ':
                            {
                                System.out.print("   ");
                                break;
                            }
                        }
                        System.out.print("|");
                    }
                }
            }
            System.out.println();
            for(int j = -1; j < 10; j++)
            {
                if(j < 0)
                {
                    System.out.print("--|");
                }
                else
                {
                    System.out.print("---+");
                }
            }
            System.out.print("\t\t");
            for(int j = -1; j < 10; j++)
            {
                if(j < 0)
                {
                    System.out.print("--|");
                }
                else
                {
                    System.out.print("---+");
                }
            }
            System.out.println();
        }
    }

    public void getCoordinates(Ship input, Ship temp)
    {
        System.out.print("Guess(X)<0 to 9>: ");
        int x = inputs.nextInt();
        System.out.print("Guess(Y)<0 to 9>: ");
        int y = inputs.nextInt();

        if (!(x >= 0))
        {
            System.out.println("Invalid input!");
            getCoordinates(input, temp);
            return;
        }
        if (!(x <= 9))
        {
            System.out.println("Invalid input!");
            getCoordinates(input, temp);
            return;
        }
        if (!(y >= 0))
        {
            System.out.println("Invalid input!");
            getCoordinates(input, temp);
            return;
        }
        if (!(y <= 9))
        {
            System.out.println("Invalid input!");
            getCoordinates(input, temp);
            return;
        }

        switch (getTrueOrFalse(temp, x, y))
        {
            case 1:
            {
                setTrueHit(input, x, y);
                setDamagedShip(temp, x, y);

                temp.cellsTemp[x][y] = 2;
                userInterface(input);

                switch (checkFinish(temp))
                {
                    case 1:
                    {
                        finish(input);
                        System.exit(1);
                        break;
                    }
                    case 0:
                    {
                        break;
                    }
                }

                getCoordinates(input, temp);
                break;
            }
            case 2:
            {
                System.out.println("Repeated input!...Try Again.");
                getCoordinates(input, temp);
                break;
            }
            case 0:
            {
                setWrongHit(input, x, y);

                userInterface(input);

                input.cellsTemp[x][y] = 2;
                break;
            }
        }
    }

}
