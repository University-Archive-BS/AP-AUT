import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;
import static java.lang.Character.getNumericValue;

public class User
{
    Scanner inputs = new Scanner(System.in);

    //constructor
    public User()
    {
    }
    //methods

    /**
     * This method receive the String of integers from the client.
     * @return String ArrayList of matrix.
     */
    public ArrayList<String> getTheMatrix(char nameOfMatrix)
    {
        ArrayList<String> strings = new ArrayList<String>();

        String temp;
        System.out.println("Define the matrix (" + nameOfMatrix + "):");
        while(true)
        {
            temp = inputs.nextLine();
            temp = temp.replaceAll("[, ]", "");
            if (temp.isEmpty())
            {
                break;
            }
            else
            {
                strings.add(temp);
            }
        }
        if (strings.size() == 0)
        {
            System.out.println("Invalid Matrix!");
            strings = getTheMatrix(nameOfMatrix);
        }
        return strings;
    }

    /**
     * This method gets the Expression string from the client.
     * @return Expression string.
     */
    public String getTheExpression()
    {
        System.out.println("Enter your polynomial expression: ");
        String expression = inputs.nextLine();
        return expression;
    }

    /**
     * This method checks whether this string has extra Space and etc...
     * And then remove extra characters.
     * @param input
     * @return New String
     */
    public String correctTheInput(String input)
    {
        String x = input;
        x = x.replace('(',' ');
        x = x.replace(')',' ');
        x = x.replaceAll(" ", "");
        x = x.replaceAll("\\+-", "-");
        x= x.replaceAll("--", "+");
        x = x.replace('x', 'X');
        x = x.replace('y', 'Y');
        if (x.charAt(0) == 'X')
        {
            x = x.replaceAll("X", "1X");
        }

        int t = x.indexOf('Y');
        if ( (x.charAt(t - 1) == '*') || (x.charAt(t - 1) == '-') || (x.charAt(t - 1) == '+') )
        {
            x = x.replaceAll("Y", "1Y");
        }

        return x;
    }

    /**
     * This method checks whether we have char X or char Y in our expression or not.
     * @param expression
     * @param XOY
     */
    public int checkXOY(String expression, char XOY)
    {
        if (expression.indexOf(XOY) == -1)
        {
            return (-666);
        }
        else
        {
            if (XOY == 'X')
            {
                return findRatioOfX(expression);
            }
            else
            {
                return findRatioOfY(expression);
            }
        }
    }

    /**
     * This methdod find the ratio of the X.
     * @param input
     * @return Ratio of the X.
     */
    public int findRatioOfX(String input)
    {
        String[] x = input.split("X");
        int length = Integer.parseInt(x[0]);
        return length;
    }

    /**
     * This method finds the operation that client wanted to do.
     * @param input
     * @return char operation.
     */
    public char findTheOperation(String input)
    {

        String[] x = input.split("X");
        char operation = x[1].charAt(0);
        return operation;
    }

    /**
     * This methdod find the ratio of the Y.
     * @param input
     * @return Ratio of the Y.
     */
    public int findRatioOfY(String input)
    {
        String[] x = input.split("X");
        int ratioY;
        if (x[1].charAt(1) == '-')
        {
            ratioY = Integer.parseInt(String.valueOf(x[1].charAt(2)));
            return -1 * ratioY;
        }
        else
        {
            ratioY = Integer.parseInt(String.valueOf(x[1].charAt(1)));
            return ratioY;
        }
    }

    /**
     * This method returns number of the Rows
     * @param inputs
     * @return int Row
     */
    public int findRow(ArrayList<String> inputs)
    {
        return inputs.size();
    }

    /**
     * This method returns number of the Columns
     * @param inputs
     * @return
     */
    public int findColumn(ArrayList<String> inputs)
    {
        String x = inputs.get(0).replaceAll("[, ]", "");
        return x.length();
    }

    /**
     * This method switch the numbers from ArrayList<String> to int[][]
     * @param inputs
     */
    public int[][] putNumbers(ArrayList<String> inputs, int row, int column, int ratio)
    {
        int[][] output = new int[row][column];
        String x;
        for (int j = 0; j < row; j++)
        {
            x = inputs.get(j);
            for (int i = 0; i < column; i++)
            {
                output[j][i] = ratio * ( ( (int) (x.charAt(i)) ) - 48);
            }
        }
        return output;
    }


    public void run()
    {
        ArrayList<String> firstMatrix = getTheMatrix('X');
        ArrayList<String> secondMatrix = getTheMatrix('Y');

        String expression = getTheExpression();
        expression = correctTheInput(expression);

        int ratioX = checkXOY(expression, 'X');

        int ratioY = checkXOY(expression, 'Y');

        char operator = findTheOperation(expression);

        int[][] iMatrix = new int[100][100];
        for (int i = 0; i < 100; i++)
        {
            for (int j = 0; j < 100; j++)
            {
                iMatrix[i][j] = 1;
            }
        }

        //First Matrix
        if (expression.indexOf('X') < 0)
        {
            if (operator != '*')
            {
                ratioX = 0;
            }
            else
            {
                System.out.println("Invalid Matrix/Operator!");
                System.exit(1);
            }
        }
        int row1 = findRow(firstMatrix);
        int column1 = findColumn(firstMatrix);
        int[][] matrix1 = putNumbers(firstMatrix, row1, column1, ratioX);
        Matrix matrixX = new Matrix(matrix1, row1, column1);

        //Second Matrix
        if (expression.indexOf('Y') < 0)
        {
            if (operator != '*')
            {
                ratioY = 0;
            }
            else
            {
                System.out.println("Invalid Matrix/Operator!");
                System.exit(1);
            }
        }
        int row2 = findRow(secondMatrix);
        int column2 = findColumn(secondMatrix);
        int[][] matrix2 = putNumbers(secondMatrix, row2, column2, ratioY);
        Matrix matrixY = new Matrix(matrix2, row2, column2);

        Matrix tmp = new Matrix();
        switch (operator)
        {
            case '+':
            {
                tmp = matrixX.addition(matrixX, matrixY);
                break;
            }
            case '-':
            {
                tmp = matrixX.subtraction(matrixX, matrixY);
                break;
            }
            case '*':
            {
                tmp = matrixX.multiplication(matrixX, matrixY);
                break;
            }
            default:
            {
                System.out.println("Invalid Operator!");
            }
        }
        tmp.printMatrix();
        System.out.println("\n\n\n");
    }
}
