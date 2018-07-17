public class Ship
{
    //fields
    protected char[][] cells; //this array will use just for print my table
    protected char[][] enemyCells; // this array will use just for print enemy's table
    protected int[][] cellsTemp; //this array will help use to set each cell of the char[][] cells
    protected int shipCells;
    protected String name;
    protected int shot;
    protected boolean human;

    //constructor
    public Ship(String name, int shot, int[][]cellsTemp, boolean human)
    {
        this.name = name;
        this.shot = shot;
        this.cellsTemp = cellsTemp;
        this.human = human;

        cells = new char[10][10];
        for (int i = 0; i < 10; i++)
        {
            for (int j = 0; j < 10; j++)
            {
                if (cellsTemp[j][i] == 1)
                {
                    cells[j][i] = '@';
                }
                else
                {
                    cells[j][i] = ' ';
                }
            }
        }

        enemyCells = new char[10][10];
        for (int i = 0; i < 10; i++)
        {
            for (int j = 0; j < 10; j++)
            {
                enemyCells[j][i] = ' ';
            }
        }

        shipCells = 0;
        for (int i = 0; i < 10; i++)
        {
            for (int j =0; j < 10; j++)
            {
                if (cellsTemp[j][i] == 1)
                {
                    shipCells++;
                }
            }
        }
    }
    public Ship()
    {
    }
}
