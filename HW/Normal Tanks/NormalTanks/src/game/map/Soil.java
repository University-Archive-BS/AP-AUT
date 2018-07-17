package game.map;

import game.elements.GameObject;
import game.elements.ObjectId;

/**
 * a class for soil elements of the map.
 */
public class Soil extends GameObject
{
    public Soil(double x, double y)
    {
        super(x, y, ObjectId.Soil);
    }
}
