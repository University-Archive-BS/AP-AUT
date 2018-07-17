import java.util.ArrayList;

public class Paint
{
    //fields
    private ArrayList<Shape> shapes;

    //constructors

    /**
     * This constructor is used to create the list for shapes.
     */
    public Paint()
    {
        shapes = new ArrayList<Shape>();
    }

    //methods

    /**
     * This method adds a index to the list.
     * @param a
     */
    public void addShape(Shape a)
    {
        shapes.add(a);
    }

    /**
     * This method shows all of
     * the Shapes of this class.
     */
    public void drawAll()
    {
        for (Shape x : shapes)
        {
            x.draw();
        }
    }

    /**
     * This method prints all of the
     * toString methods of the Shapes.
     */
    public void printAll()
    {
        for (Shape x : shapes)
        {
            System.out.println(x.toString());
        }
    }

}
