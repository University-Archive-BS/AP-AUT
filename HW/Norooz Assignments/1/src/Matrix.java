import javax.print.attribute.standard.Finishings;

/**
 * This is Matrix class
 * @author Ali_Z
 */
public class Matrix
{
    //fields
    private int[][] matrix;
    private int row;
    private int column;

    //constructor
    public Matrix(int[][] matrix, int row, int column)
    {
        this.row = row;
        this.column = column;

        this.matrix = new int[this.row][this.column];

        for (int i = 0; i < this.row; i++)
        {
            for (int j = 0; j < this.column; j++)
            {
                this.matrix[i][j] = matrix[i][j];
            }
        }
    }
    public Matrix()
    {
    }

    //methods

    /**
     * This method calculate the addition of 2 matrix.
     * @param x
     * @param y
     * @return a matrix
     */
    public Matrix addition(Matrix x, Matrix y)
    {
        if ( (x.column != y.column) || (x.row != y.row) )
        {
            System.out.println("Invalid Matrix/Operator!");
            System.exit(1);
        }

        int[][] temp = new int[x.row][x.column];
        for (int i = 0; i < x.row; i++)
        {
            for (int j = 0; j < x.column; j++)
            {
                temp[i][j] = x.matrix[i][j] + y.matrix[i][j];
            }
        }
        Matrix returnMatrix  = new Matrix(temp, x.row, x.column);
        return returnMatrix;
    }

    /**
     * This method calculate the subtraction of 2 matrix.
     * @param x
     * @param y
     * @return a matrix
     */
    public Matrix subtraction(Matrix x, Matrix y)
    {
        if ( (x.column != y.column) || (x.row != y.row) )
        {
            System.out.println("Invalid Matrix/Operator!");
            System.exit(1);
        }

        int[][] temp = new int[x.row][x.column];
        for (int i = 0; i < x.row; i++)
        {
            for (int j = 0; j < x.column; j++)
            {
                temp[i][j] = x.matrix[i][j] - y.matrix[i][j];
            }
        }
        Matrix returnMatrix  = new Matrix(temp, x.row, x.column);
        return returnMatrix;
    }

    /**
     * This method calculate the multiplication of 2 matrix.
     * @param x
     * @param y
     * @return a matrix
     */
    public Matrix multiplication(Matrix x, Matrix y)
    {
        if (x.column != y.row)
        {
            System.out.println("Invalid Matrix/Operator!");
            System.exit(1);
        }

        int[][] temp = new int[x.row][y.column];
        for (int i = 0; i < x.row; i++)
        {
            for (int j =  0; j < y.column; j++)
            {
                temp[i][j] = 0;
            }
        }

        for (int i = 0; i < x.row; i++)
        {
            for (int j = 0; j < y.column; j++)
            {
                for (int k = 0; k < x.column; k++)
                {
                    temp[i][j] += (x.matrix[i][k] * y.matrix[k][j]);
                }
            }
        }
        Matrix returnMatrix  = new Matrix(temp, x.row, y.column);
        return returnMatrix;
    }

    /**
     * This method print the int[][] (Array)
     */
    public void printMatrix()
    {
        System.out.println("----------Result----------");
        for (int i = 0; i < row; i++)
        {
            for (int j = 0; j < column; j++)
            {
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println();
        }
    }
}
