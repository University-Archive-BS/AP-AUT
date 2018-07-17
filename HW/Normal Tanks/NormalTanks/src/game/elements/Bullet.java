package game.elements;

import java.awt.*;

/**
 * each Gun makes bullets to hit others.
 * Bullets takes their damages from the gun which made them.
 */
public class Bullet extends GameObject
{
    //fields
    protected int damage;

    private double shootDirectionAngle;
    private double targetX, targetY;
    private double thrownAngle;

    private boolean isThrown;

    private ObjectId shooter;

    //constructor
    public Bullet()
    {

    }

    public Bullet(double startX, double startY, double mouseX, double mouseY, ObjectId id, ObjectId shooter)
    {
        super(startX, startY, id);

        targetX = mouseX;
        targetY = mouseY;

        startX += 50;//this must be changed if tank size changes
        startY += 50; //this must be changed if tank size changes

        shootDirectionAngle = calculateShootAngle(startX, startY, mouseX, mouseY);

        this.shooter = shooter;

        if (mouseX > startX && mouseY > startY)
        {
            velX = 14;
            velY = 14;
        }
        else if (mouseX > startX && mouseY < startY)
        {
            velX = 14;
            velY = 14;
        }
        else if (mouseX < startX && mouseY < startY)
        {
            velX = -14;
            velY = -14;
        }
        else if (mouseX < startX && mouseY > startY)
        {
            velX = -14;
            velY = -14;
        }
        //from now on if conditions are for times where angle is 0 or PI/2
        else if (mouseX == startX && mouseY > startY)
        {
            velX = 0;
            velY = 14;
        }
        else if (mouseX == startX && mouseY < startY)
        {
            velX = 0;
            velY = 14; //idk why but its true
        }
        else if (mouseX > startX && mouseY == startY)
        {
            velX = 14;
            velY = 0;
        }
        else if (mouseX < startX && mouseY == startY)
        {
            velX = -14;
            velY = 0;
        }
        this.isThrown = false;
    }

    //methods

    /**
     * calculate the angle that bullet must go.
     *
     * @param x1 x of starting point
     * @param y1 y of starting point
     * @param x2 x of destination
     * @param y2 y of destination
     * @return
     */
    private double calculateShootAngle(double x1, double y1, double x2, double y2)
    {
        return Math.atan((y2 - y1) / (x2 - x1));
    }

    /**
     * set damage for bullet
     *
     * @param damage amount of damage it must provides
     */
    public void setDamage(int damage)
    {
        this.damage = damage;
    }

    /**
     * get damage of bullet
     *
     * @return amount of damage it provides
     */
    public int getDamage()
    {
        return damage;
    }

    /**
     * the X position where bullet is going to(where mouse was clicked)
     *
     * @return X of where mouse was clicked to shoot there
     */
    public double getTargetX()
    {
        return targetX;
    }

    /**
     * the Y position where bullet is going to(where mouse was clicked)
     *
     * @return Y of where mouse was clicked to shoot there
     */
    public double getTargetY()
    {
        return targetY;
    }

    /**
     * returns the angle of the way bullet must go.
     *
     * @return the angle that way of bullet go.
     */
    public double getShootDirectionAngle()
    {
        return shootDirectionAngle;
    }

    /**
     * if a bullet is fired.
     *
     * @return true if fired
     */
    public boolean isThrown()
    {
        return isThrown;
    }

    /**
     * set if a bullet is thrown or not
     *
     * @param thrown true for thrown and vise versa
     */
    public void setThrown(boolean thrown)
    {
        isThrown = thrown;
    }

    /**
     * get the angle of bullet
     *
     * @return double as angle
     */
    public double getThrownAngle()
    {
        return thrownAngle;
    }

    /**
     * set a angle for bullet
     *
     * @param thrownAngle angle
     */
    public void setThrownAngle(double thrownAngle)
    {
        this.thrownAngle = thrownAngle;
    }

    /**
     * get bounds of the bullet.
     *
     * @return a rectangle with bounds of bullet
     */
    public Rectangle getBounds()
    {
        return new Rectangle((int) x + 50, (int) y + 50, 22, 22);
    }

    /**
     * returns the id of its shooter. Ai or player
     *
     * @return player for playerShooter and AIshooter for AiShooter
     */
    public ObjectId getShooter()
    {
        return shooter;
    }
}
