public abstract class PolyGon extends Shape
{
    //fields
    /**
     * This is the length of the each side.
     */
    protected double[] sides;

    //constructor

    /**
     * This method will new the array and then
     * put the each sides in the array.
     * @param side
     */
    public PolyGon(double ...side)
    {
        int length = side.length;
        sides = new double[length];

        for (int i =0; i < length; i++)
        {
            sides[i] = side[i];
        }
    }

    //methods

    /**
     * This method returns the Array of Sides.
     */
    public double[] getSides()
    {
        return sides;
    }

    /**
     * This method overrides the toString method in Shape.
     * @return a String
     */
    @Override
    public String toString()
    {
        String str = "   ";
        int t = 1;
        for (double i : sides)
        {
            str += ("\t Side " + t + ": " + i + "\t");
            t++;
        }
        return str;
    }

}
