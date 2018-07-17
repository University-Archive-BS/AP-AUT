package game.map;

import game.elements.*;

import java.awt.*;

/**
 * a class for hardWall elements of the map.
 */
public class HardWall extends GameObject
{

    public HardWall(double x, double y)
    {
        super(x, y, ObjectId.HardWall);
    }
}
