package game.map;

import game.Utils.SharedData;
import game.elements.ObjectId;
import game.elements.Tank;

/**
 * a class for update the camera of the game the should follow Player tank movements.
 */
public class Camera
{
    private double x, y;

    /**
     * @param x
     * @param y
     */
    public Camera(double x, double y)
    {
        this.x = x;
        this.y = y;
    }

    /**
     * update the condition of the camera.
     *
     * @param player
     */
    public void tick(Tank player)
    {
        //first map
        if (SharedData.getData().whichMap.equals(ObjectId.FirstMap))
        {
            x += ((player.getX() - x) - 1820 / 2) * 0.05f;
            y += ((player.getY() - y) - 1024 / 2) * 0.05f;

            if (x <= 0)
            {
                x = 0;
            }
            if (x >= 1670)
            {
                x = 1670;
            }

            if (y <= 0)
            {
                y = 0;
            }
            if (y >= 5450)
            {
                y = 5450;
            }
        }

        //second map
        else if (SharedData.getData().whichMap.equals(ObjectId.SecondMap))
        {
            x += ((player.getX() - x) - 1820 / 2) * 0.05f;
            y += ((player.getY() - y) - 1024 / 2) * 0.05f;

            if (x <= 0)
            {
                x = 0;
            }
            if (x >= 600)
            {
                x = 600;
            }

            if (y <= 0)
            {
                y = 0;
            }
            if (y >= 5450)
            {
                y = 5450;
            }
        }

        //third map
        else if (SharedData.getData().whichMap.equals(ObjectId.ThirdMap))
        {
            x = 0;
            y += ((player.getY() - y) - 1024 / 2) * 0.05f;

            if (y <= 0)
            {
                y = 0;
            }
            if (y >= 1870)
            {
                y = 1870;
            }
        }

        //fourth map
        else if (SharedData.getData().whichMap.equals(ObjectId.FourthMap))
        {
            x += ((player.getX() - x) - 1820 / 2);
            y += ((player.getY() - y) - 1024 / 2);

            if (x <= 0)
            {
                x = 0;
            }
            if (x >= 1670)
            {
                x = 1670;
            }

            if (y <= 0)
            {
                y = 0;
            }
            if (y >= 5450)
            {
                y = 5450;
            }
        }

    }

    /**
     * @return x of the camera
     */
    public double getX()
    {
        return x;
    }

    /**
     * set the x of the camera
     *
     * @param x
     */
    public void setX(double x)
    {
        this.x = x;
    }

    /**
     * @return y of the camera
     */
    public double getY()
    {
        return y;
    }

    /**
     * set the y of the camera
     *
     * @param y
     */
    public void setY(double y)
    {
        this.y = y;
    }
}
