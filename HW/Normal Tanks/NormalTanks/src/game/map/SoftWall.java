package game.map;

import game.elements.*;

import java.awt.*;

/**
 * a class for softWall elements of the map.
 */
public class SoftWall extends GameObject
{
    int health = 400;

    public SoftWall(double x, double y)
    {
        super(x, y, ObjectId.SoftWall);
    }

    /**
     * set the health of the softWall.
     *
     * @param health
     */
    public void setHealth(int health)
    {
        this.health = health;
    }

    /**
     * @return health of the softWall.
     */
    public int getHealth()
    {
        return health;
    }
}
