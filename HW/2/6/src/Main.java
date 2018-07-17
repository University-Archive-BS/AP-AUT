import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        Scanner inputs = new Scanner(System.in);

        Rational n1;
        Rational n2;

        System.out.println("Enter the numerator and the denominator of the first number:");
        int numerator1 = inputs.nextInt();
        int denominator1 = inputs.nextInt();
        n1 = new Rational(numerator1, denominator1);

        System.out.println("Enter the numerator and the denominator of the second number:");
        int numerator2 = inputs.nextInt();
        int denominator2 = inputs.nextInt();
        n2 = new Rational(numerator2, denominator2);


        System.out.println();
        System.out.println();

        System.out.println("Div:");     //put your operator name here
        n1.div(n2);     //put your operator name here

        n1.printFloat();
    }
}
