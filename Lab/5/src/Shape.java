public abstract class Shape
{
    //methods
    /**
     * This method calculate the P.
     * @return double P
     */
    public abstract double calculatePerimeter();

    /**
     * This method calculate the S.
     * @return double S
     */
    public abstract double calculateArea();

    /**
     * This method prints:
     * the Name of the Shape
     * P
     * S
     */
    public abstract void draw();

    /**
     * This method checks that this shape is equal to the
     * input Shape or not.
     */
   // public abstract boolean equals(Shape a);

    /**
     * This method describes the Shape in a String.
     * @return for circle: Name of the Shape, Radius.
     * @return for others: Name of the Shape, Sides.
     */
    public abstract String toString();




}
