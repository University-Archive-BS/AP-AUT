public class Circle extends Shape
{
    //fields
    private double radius;

    //constructors
    public Circle(double radius)
    {
        this.radius = radius;
    }

    //methods

    /**
     * This method give the radius to the client.
     * @return radius
     */
    public double getRadius()
    {
        return radius;
    }

    @Override
    public double calculatePerimeter()
    {
        return (2 * Math.PI * radius);
    }

    @Override
    public double calculateArea()
    {
        return (Math.PI * Math.pow(radius,2));
    }

    @Override
    public void draw()
    {
        System.out.println("\nCircle::\t Perimeter: " + calculatePerimeter()
                + "\tArea: " + calculateArea());
        System.out.println(toString());
    }

   /* @Override
    public boolean equals(Shape a)
    {
        if (radius == a.radius)
        {
            return true;
        }
        else
        {
            return false;
        }
    }*/

    @Override
    public String toString()
    {
        return ("\t\t     Radius: " + radius);
    }
}
