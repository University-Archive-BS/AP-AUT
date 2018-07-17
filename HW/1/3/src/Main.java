import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);

        System.out.println("Please enter your input:");
        String inputs = input.next();

        char[] array = inputs.toCharArray();

        int a = java.lang.Character.getNumericValue(array[0]);
        int b = java.lang.Character.getNumericValue(array[1]);
        int adad = (10 * a) + b;

        int[] arr = new int[adad];

        for(int i = 0; i < adad; i++)
        {
            arr[i] = (i * (i + 1)) / 2;
        }
        for(int i = 0; i < (adad - 1); i++)
        {
            System.out.print(arr[i] + ", ");
        }
        System.out.print(arr[adad - 1]);
    }
}
