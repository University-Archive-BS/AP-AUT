public class Rectangle extends PolyGon
{
    //constructors
    public Rectangle(double[] sides)
    {
        super(sides);
    }

    //methods
    /**
     * This method checks that
     * this shape is square or not.
     *
     */
    public boolean isSquare(Shape x)
    {
        if (sides[0] == sides[1] && sides[0] == sides[2] && sides[0] == sides[3])
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean isRectangle()
    {
        if (sides[0] == sides[1])
        {
            if (sides[2] != sides[3])
            {
                System.out.println("\nRectangle" + toString());
                System.out.println("Wrong inputs!!!");
                return false;
            }
        }
        if (sides[0] == sides[2])
        {
            if (sides[1] != sides[3])
            {
                System.out.println("\nRectangle" + toString());
                System.out.println("Wrong inputs!!!");
                return false;
            }
        }
        if (sides[0] == sides[3])
        {
            if (sides[1] != sides[3])
            {
                System.out.println("\nRectangle" + toString());
                System.out.println("Wrong inputs!!!");
                return false;
            }
        }
        if (sides[2] == sides[1])
        {
            if (sides[3] != sides[0])
            {
                System.out.println("\nRectangle" + toString());
                System.out.println("Wrong inputs!!!");
                return false;
            }
        }
        if (sides[3] == sides[1])
        {
            if (sides[0] != sides[2])
            {
                System.out.println("\nRectangle" + toString());
                System.out.println("Wrong inputs!!!");
                return false;
            }
        }
        if (sides[2] == sides[3])
        {
            if (sides[0] != sides[1])
            {
                System.out.println("\nRectangle" + toString());
                System.out.println("Wrong inputs!!!");
                return false;
            }
        }

        return true;
    }

    @Override
    public double calculatePerimeter()
    {
        return (sides[0] + sides[1] + sides[2] + sides[3]);
    }

    @Override
    public double calculateArea()
    {
        if (sides[0] != sides[1])
        {
            return (sides[0] * sides[1]);
        }
        else
        {
            return (sides[0] * sides[2]);
        }
    }

    @Override
    public void draw()
    {
        if (isRectangle() == false)
        {
            return;
        }

        System.out.println("\nRectangle::  Perimeter: " + calculatePerimeter()
                + "  Area: " + calculateArea());
        System.out.println(toString());
    }

    /*@Override
    public boolean equals(Shape a)
    {

    }*/

    @Override
    public String toString()
    {
        return ( "\t  " + super.toString() );
    }


}
