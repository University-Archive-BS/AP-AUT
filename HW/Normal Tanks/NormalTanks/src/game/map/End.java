package game.map;

import game.elements.GameObject;
import game.elements.ObjectId;

/**
 * a class for the End element of the map
 */
public class End extends GameObject
{
    public End(double x, double y)
    {
        super(x, y, ObjectId.End);
    }
}
