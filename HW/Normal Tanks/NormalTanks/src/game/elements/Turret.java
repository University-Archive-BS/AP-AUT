package game.elements;

import game.Utils.SharedData;
import game.Utils.Utility;
import game.map.Camera;

import java.io.Serializable;
import java.util.ArrayList;

import java.util.ArrayList;

/**
 * a Turret is a unmovable kind of weapon stuck to the ground.
 * turrets have infinite amount of ammo.
 * turrets also has health, when it gets down to zero means it is destroyed.
 */
public class Turret extends GameObject implements Serializable
{

    //fields
    private Gun gun;

    private int health;
    private ArrayList<Tank> targets;
    private Tank target;

    private double gunAngle;

    private double rangeOfView;

    //constructor
    public Turret()
    {

    }

    /**
     * @param x
     * @param y
     * @param targets
     * @param type
     */
    public Turret(double x, double y, ArrayList<Tank> targets, ObjectId type)
    {
        super(x, y, ObjectId.Turret);
        if (type.equals(ObjectId.MachineGun))
        {
            gun = new MachineGun(this.x, this.y);
        }
        else if (type.equals(ObjectId.MissileGun))
        {
            gun = new MissileGun(this.x, this.y);
        }
        gun.setAmmo(1000);

        target = targets.get(0);
        this.targets = targets;

        gunAngle = 0;

        health = 120;
        rangeOfView = 800;
    }

    //methods

    /**
     * if more than 1 player is playing then turret will shoot the player which is closer to turret
     *
     * @param tanks
     */
    public void determineTarget(ArrayList<Tank> tanks)
    {
        for (int i = 0; i < tanks.size(); i++)
        {
            double distance = calculateDistance(tanks.get(i));
            if (distance < calculateDistance(target))
            {
                target = tanks.get(i);
            }
        }
    }


    /**
     * process of a turret during each loop including checking area,
     * shooting target, ... .
     *
     * @param objects objects of the game
     */
    public void tick(Objects objects)
    {
        if (SharedData.getData().gameType.equals(ObjectId.TwoPlayer))
        {
            determineTarget(targets);
        }
        if (checkArea() == true)
        {
            setGunAngle(calculateAngle(this, target)); // angle between turret and tank
            if (gun.readyForShoot())
            {
                objects.addBullet(gun.shoot(this.x, this.y, target.x + target.TANK_WIDTH / 2, target.y + target.TANK_HEIGHT / 2, ObjectId.AIShooter));
                target = objects.getPlayers().get(0);
            }
        }
    }


    /**
     * a method for check the area and calculate the distance
     *
     * @return
     */
    public boolean checkArea()
    {
        double distance = calculateDistance(target);
        if (distance - rangeOfView <= 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * calculate the distance between turret and target(player tank).
     *
     * @param tank target
     * @return distance between turret and player tank
     */
    private double calculateDistance(Tank tank)
    {
        double distance = Math.sqrt(Math.pow(Math.abs(this.x - tank.x), 2) + Math.pow(Math.abs(this.y - tank.y), 2));
        return distance;
    }

    /**
     * @return the target(Tank)
     */
    public Tank getTarget()
    {
        return target;
    }

    /**
     * set the target of the turret
     *
     * @param target
     */
    public void setTarget(Tank target)
    {
        this.target = target;
    }

    /**
     * set the gun angle of the turret to shoot
     *
     * @param gunAngle
     */
    public void setGunAngle(double gunAngle)
    {
        this.gunAngle = gunAngle;
    }

    /**
     * @return the gun angle
     */
    public double getGunAngle()
    {

        return gunAngle;
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
        if (object2.getX() < object1.getX())
        {
            angle += Math.PI;
        }
        return angle;
    }

    /**
     * @return the gun of the turret
     */
    public Gun getGun()
    {
        return gun;
    }

    /**
     * @return the health of the turret
     */
    public int getHealth()
    {
        return health;
    }

    /**
     * set the health of the turret
     *
     * @param health
     */
    public void setHealth(int health)
    {
        this.health = health;
    }

    /**
     * set the gun of the turret
     *
     * @param gun
     */
    public void setGun(Gun gun)
    {
        this.gun = gun;
    }
}
