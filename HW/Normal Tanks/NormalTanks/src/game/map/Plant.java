package game.map;

import game.elements.*;

import java.awt.*;

/**
 * a class for plant elements of the map.
 */
public class Plant extends GameObject
{
    public Plant(double x, double y)
    {
        super(x, y, ObjectId.Plant);
    }
}
