package game.elements;

import game.Utils.SharedData;
import game.Utils.Utility;
import GameEngine.Physics;

import java.awt.*;

/**
 * robot that is offline in default but gets activated when user gets close to them.
 * it follow player and hits player.
 */
public class BuriedRobot extends GameObject
{
    //fields
    private int health;

    private double minDistance;
    private boolean activated;
    private double angle;

    private Tank target;

    //constructor
    public BuriedRobot()
    {

    }

    public BuriedRobot(double x, double y)
    {
        super(x, y, ObjectId.BuriedRobot);

        health = 80;
        minDistance = 400;
        activated = false;
        angle = 0;

        setVelX(5);
        setVelY(5);
    }

    //methods

    /**
     * if robot is activated it moves and get closer to target
     * and if its off , it checks area to see whether player tank
     * is in range or not
     *
     * @param objects
     */
    public void tick(Objects objects)
    {
        determineTarget(objects);
        setAngle(calculateAngle(this, target));
        if (activated)
        { // pass player tank as target
            move(target, objects);
        }
        else
        {
            checkArea();
        }
    }

    /**
     * determine which player tank is the target. if game is in coop mode
     * it will set closer tank to robot as its target.
     *
     * @param objects objects of the game
     */
    private void determineTarget(Objects objects)
    {
        if (SharedData.getData().gameType.equals(ObjectId.TwoPlayer) && SharedData.getData().playerType.equals(ObjectId.ServerPlayer))
        {
            target = objects.getPlayers().get(0);
            for (int i = 0; i < objects.getPlayers().size(); i++)
            {
                double distance = Utility.calculateDistance(this, objects.getPlayers().get(i));
                if (distance <= Utility.calculateDistance(this, target))
                {
                    target = objects.getPlayers().get(i);
                }
            }
        }
        else
        { //in single player
            target = objects.getPlayers().get(0);
        }
    }

    /**
     * calculate angle between 2 objects.
     *
     * @param object1 starting point
     * @param object2 second point
     * @return
     */
    private static double calculateAngle(GameObject object1, GameObject object2)
    {
        double angle = Math.atan((object2.getY() - object1.getY()) / (object2.getX() - object1.getX()));
//        angle += Math.PI / 2;
        if (object2.getX() < object1.getX())
        {
            angle += Math.PI;
        }
        return angle;
    }

    /**
     * check area and if player tank in located in area, robot gets activated
     */
    private void checkArea()
    {
        double distance = Utility.calculateDistance(this, target);
        if (distance < minDistance)
        {
            activated = true;
        }
    }

    /**
     * moves robot to get closer to player tank.
     *
     * @param target player tank
     */
    public void move(Tank target, Objects objects)
    {
        int vib = 6; // removes robot vibration when x or y is same as target x , y
        if (target.x > this.x + vib)
        {
            if (!Physics.BuriedRobotCollisionRight(objects, this))
            {
                this.setX(this.getX() + this.getVelX());
            }
        }
        else if (target.x < this.x - vib)
        {
            if (!Physics.BuriedRobotCollisionLeft(objects, this))
            {
                this.setX(this.getX() - this.getVelX());
            }
        }

        if (target.y > this.y + vib)
        {
            if (!Physics.BuriedRobotCollisionDown(objects, this))
            {
                this.setY(this.getY() + this.getVelY());
            }
        }
        else if (target.y < this.y - vib)
        {
            if (!Physics.BuriedRobotCollisionUp(objects, this))
            {
                this.setY(this.getY() - this.getVelY());
            }
        }
    }

    /**
     * state of robot( on or off )
     *
     * @return true if activated and false if deactivated
     */
    public boolean isActivated()
    {
        return activated;
    }

    /**
     * a buried robot get activated when player tank get
     * closer to robot than robot's minDistance.
     *
     * @param state true for activating and false for deactivating
     */
    public void setActivated(boolean state)
    {
        activated = state;
    }

    /**
     * set angle for robot to rotate
     *
     * @param angle the angle of robot for rotating
     */
    public void setAngle(double angle)
    {
        this.angle = angle;
    }

    /**
     * get the robot angle
     *
     * @return the angle of robot for rotating
     */
    public double getAngle()
    {

        return angle;
    }

    /**
     * set health for robot
     *
     * @param health amount of health for robot to set
     */
    public void setHealth(int health)
    {
        this.health = health;
    }

    /**
     * how much health robot has
     *
     * @return health of the robot
     */
    public int getHealth()
    {
        return health;
    }

    /**
     * returns the target of the robot
     *
     * @return a tank a target
     */
    public Tank getTarget()
    {
        return target;
    }

    /**
     * returns bounds of robot
     *
     * @return a rectangle that has robot bounds
     */
    public Rectangle getBounds()
    {
        return new Rectangle((int) x + 30, (int) y + 20, 50, 60);
    }
}
