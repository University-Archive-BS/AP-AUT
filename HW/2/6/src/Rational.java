import java.lang.Math;

public class Rational
{
    private int numerator;
    private int denominator;
    public int sameD;

    //constructors

    public Rational(int numerator1,int denominator1)
    {
        if ( (numerator1 * denominator1) > 0 )
        {
            numerator1 = Math.abs(numerator1);
            denominator1 = Math.abs(denominator1);
        }
        else
        {
            numerator1 = (-1) * Math.abs(numerator1);
            denominator1 = Math.abs(denominator1);
        }
        sameD = GCD(Math.abs(numerator1),Math.abs(denominator1));
        numerator = numerator1 / sameD;
        denominator = denominator1 / sameD;
    }

    public Rational()
    {
        sameD = 1;
        numerator = 1;
        denominator = 1;
    }



    //methods

    public int GCD (int numerator, int denominator)
    {
        int a, b;
        if (numerator > denominator)
        {
            a = numerator;
            b = denominator;
        }
        else
        {
            a = denominator;
            b = numerator;
        }
        if (b == 0)
        {
            return a;
        }
        return GCD (b, a%b);
    }


    //getters
    public int getNumerator()
    {
        return numerator;
    }
    public int getDenominator()
    {
        return denominator;
    }

    //setters
    public void setDenominator(int newDenominator)
    {
        denominator = newDenominator;
    }
    public void setNumerator(int newNumerator)
    {
        numerator = newNumerator;
    }

    //the result of addition operation is stored in fields (numerator, denominator)
    public  void add(Rational secondNum)
    {
        int numerator1 = getNumerator();
        int denominator1 = getDenominator();

        int numerator2 = secondNum.numerator;
        int denominator2 = secondNum.denominator;

        int finalN = (numerator1 * denominator2) + (numerator2 * denominator1);
        int finalD = (denominator1 * denominator2);
        setNumerator(finalN / GCD(finalN, finalD));
        setDenominator(finalD / GCD(finalN, finalD));
    }
    //the result of subtraction operation is stored in fields (numerator, denominator)
    public void sub(Rational secondNum)
    {
        int numerator1 = getNumerator();
        int denominator1 = getDenominator();

        int numerator2 = secondNum.numerator;
        int denominator2 = secondNum.denominator;

        int finalN = (numerator1 * denominator2) - (numerator2 * denominator1);
        int finalD = (denominator1 * denominator2);
        setNumerator(finalN / GCD(finalN, finalD));
        setDenominator(finalD / GCD(finalN, finalD));
    }
    //the result of multiplication operation is stored in fields (numerator, denominator)
    public void mult(Rational secondNum)
    {
        int numerator1 = getNumerator();
        int denominator1 = getDenominator();

        int numerator2 = secondNum.numerator;
        int denominator2 = secondNum.denominator;

        int finalN = (numerator1 * numerator2);
        int finalD = (denominator1 * denominator2);
        setNumerator(finalN / GCD(finalN, finalD));
        setDenominator(finalD / GCD(finalN, finalD));
    }
    //the result of division operation is stored in fields (numerator, denominator)
    public void div(Rational secondNum)
    {
        int numerator1 = getNumerator();
        int denominator1 = getDenominator();

        int numerator2 = secondNum.numerator;
        int denominator2 = secondNum.denominator;

        int finalN = (numerator1 * denominator2);
        int finalD = (denominator1 * numerator2);
        setNumerator(finalN / GCD(finalN, finalD));
        setDenominator(finalD / GCD(finalN, finalD));
    }

    //prints
    public void printFraction()
    {
        if (getDenominator() == 0)
        {
            System.out.println("Whaaat...!");
            return;
        }
        if (getNumerator() == 0)
        {
            System.out.println("0");
            return;
        }
        System.out.println(getNumerator() + "/" + getDenominator());
    }
    public void printFloat()
    {
        if (getDenominator() == 0)
        {
            System.out.println("Whaaat...!");
            return;
        }
        if (getNumerator() == 0)
        {
            System.out.println("0");
            return;
        }
        System.out.println(getNumerator() + "." + getDenominator());
    }
}