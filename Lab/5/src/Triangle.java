public class Triangle extends PolyGon
{
    //constructors
    public Triangle(double[] sides)
    {
        super(sides);
    }

    //methods

    /**
     * This method checks that
     * this shape is Equilateral or not.
     *
     */
    public boolean isEquilateral()
    {
        if (sides[0] == sides[1] && sides[0] == sides[2])
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    public double calculatePerimeter()
    {
        return (sides[0] + sides[1] + sides[2]);
    }

    @Override
    public double calculateArea()
    {
        double p = (calculatePerimeter() / 2);
        return Math.sqrt(p * (p - sides[0]) * (p - sides[1]) * (p - sides[2]));
    }

    @Override
    public void draw()
    {
        if (!((sides[0] + sides[1] > sides[2]) && (sides[0] + sides[2] > sides[1]) && (sides[1] + sides[1] > sides[0])))
        {
            System.out.println("\nTriangle" + toString());
            System.out.println("Wrong inputs!!!");
            return;
        }
        System.out.println("\nTriangle::\t Perimeter: " + calculatePerimeter()
                + "\tArea: " + calculateArea());
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
