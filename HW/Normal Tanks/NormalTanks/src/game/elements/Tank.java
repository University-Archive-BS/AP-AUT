package game.elements;

import java.io.Serializable;
import java.util.Date;

/**
 * an entity which is a moving object though the mapOriginal.
 * it contains a gun to hit other objects. it also has
 * specific amount of health. if health comes down to zero
 * the tank becomes dead.
 * type determines that if the tank is AI or Player(1 for AI, 0 for Player).
 */
public class Tank extends GameObject implements Serializable
{
    //fields
    public final int TANK_WIDTH = 100;
    public final int TANK_HEIGHT = 100;

    protected int health;

    protected double gunAngle = 0;

    protected double tankAngle = 0;

    private long lastRotateTime;
    private long rechargeRotationTime;

    protected Upgrade upgrade;

    private Boolean hasShield;
    private int momentHealth;

    protected Gun selectedGun;
    protected MissileGun missileGun;
    protected MachineGun machineGun;

    //constructor
    public Tank()
    {

    }

    /**
     * @param x
     * @param y
     * @param health
     * @param id
     */
    public Tank(double x, double y, int health, ObjectId id)
    { // gun must be added manually after making tank
        super(x, y, id);

        missileGun = new MissileGun(this.x, this.y);
        this.setMissileGun(missileGun);

        machineGun = new MachineGun(this.x, this.y);
        this.setMachineGun(machineGun);

        this.selectedGun = missileGun;
        this.health = health;

        upgrade = null;

        hasShield = false;

        lastRotateTime = getCurrentTime();
        rechargeRotationTime = 10;

        setVelX(12); //  set X velocity
        setVelY(12); // set y velocity
    }

    /**
     * @param x
     * @param y
     * @param health
     * @param id
     * @param upgrade
     */
    public Tank(double x, double y, int health, ObjectId id, Upgrade upgrade)
    { // gun must be added manually after making tank
        super(x, y, id);

        missileGun = new MissileGun(this.x, this.y);
        this.setMissileGun(missileGun);

        machineGun = new MachineGun(this.x, this.y);
        this.setMachineGun(machineGun);

        this.selectedGun = missileGun;
        this.health = health;

        this.upgrade = upgrade;

        lastRotateTime = getCurrentTime();
        rechargeRotationTime = 10;

        setVelX(12); //  set X velocity
        setVelY(12); // set y velocity
    }

    //methods

    /**
     * prevents tank from rotating so fast. it checks whether enough time has been passed from
     * last rotate time or not.
     *
     * @return
     */
    public boolean readyForRotate()
    {
        if (getCurrentTime() - lastRotateTime > rechargeRotationTime)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * to get current system time with millisecond accuracy.
     *
     * @return current system time
     */
    private long getCurrentTime()
    {
        long time = new Date().getTime();
        return time;
    }


    /**
     * when tank whats to move, its direction must be set to where its heading.this set suitable value for tank direction angle
     * based on which direction its heading.
     *
     * @param up    is tank moving up
     * @param down  is tank moving down
     * @param right is tank moving right
     * @param left  is tank moving left
     */
    public void rotate(Boolean up, Boolean down, Boolean right, Boolean left)
    {
        if (readyForRotate())
        {
            double angle = 7 * 0.0174533;
            if (up && !right && !left && tankAngle < Math.PI / 2 && tankAngle != -Math.PI / 2)
            {
                tankAngle += angle; //degree * (1degree to radian) - total value is based on radian
                if (tankAngle < angle + Math.PI / 2 && tankAngle > +Math.PI / 2 - angle)
                {
                    tankAngle = Math.PI / 2;
                }
            }

            if (down && !right && !left && tankAngle > -Math.PI / 2 && tankAngle != Math.PI / 2)
            {
                tankAngle -= angle; //degree * (1degree to radian) - total value is based on radian
                if (tankAngle > -Math.PI / 2 - angle && tankAngle < -Math.PI / 2 + angle)
                {
                    tankAngle = -Math.PI / 2;
                }
            }

            if ((right || left) && !up && !down && tankAngle != 0)
            {
                if (tankAngle > 0)
                {
                    tankAngle -= angle;
                }
                else if (tankAngle < 0)
                {
                    tankAngle += angle;
                }
                if (tankAngle < angle && tankAngle > -1 * angle)
                {
                    tankAngle = 0;
                }
            }

            //two direction at same time
            if (up && right && !left && !down)
            {
                if (tankAngle < -Math.PI / 4 - angle)
                {
                    tankAngle += angle;
                }
                else if (tankAngle > -Math.PI / 4 + angle)
                {
                    tankAngle -= angle;
                }
            }
            else if (up && left && !right && !down)
            {
                if (tankAngle < Math.PI / 4 - angle)
                {
                    tankAngle += angle;
                }
                else if (tankAngle > Math.PI / 4 + angle)
                {
                    tankAngle -= angle;
                }
            }
            else if (down && left && !right && !up)
            {
                if (tankAngle < -Math.PI / 4 - angle)
                {
                    tankAngle += angle;
                }
                else if (tankAngle > -Math.PI / 4 + angle)
                {
                    tankAngle -= angle;
                }
            }
            else if (down && right && !left && !up)
            {
                if (tankAngle < Math.PI / 4 - angle)
                {
                    tankAngle += angle;
                }
                else if (tankAngle > Math.PI / 4 + angle)
                {
                    tankAngle -= angle;
                }
            }
            lastRotateTime = getCurrentTime();
        }
    }

    /**
     * set an angle for tank to rotate
     *
     * @param tankAngle
     */
    public void setTankAngle(double tankAngle)
    {
        this.tankAngle = tankAngle;
    }

    /**
     * the angle that tank is currently there.
     *
     * @return angle which is direction of tank
     */
    public double getTankAngle()
    {
        return tankAngle;
    }

    /**
     * make a shield around the tank that can diminish some damage before
     * getting dead.
     */
    public void activateShield()
    {
        health += 100;
        hasShield = true;
        momentHealth = health;
    }

    /**
     * if shield get a specified amount of damage it gets dead and disabled.
     * this checks to disable that or not;
     */
    public void checkShieldHeath()
    {
        if (health - momentHealth < 0)
        {
            hasShield = false;
        }
    }

    /**
     * swap gun between missile and machine guns.
     */
    public void swapGun()
    {
        if (selectedGun == missileGun)
        {
            selectedGun = machineGun;
        }
        else if (selectedGun == machineGun)
        {
            selectedGun = missileGun;
        }
    }

    /**
     * set an angle that gun must be rotated
     *
     * @param angle
     */
    public void setGunAngle(double angle)
    {
        gunAngle = angle;
    }

    /**
     * set amount of health for tank.
     *
     * @param health
     */
    public void setHealth(int health)
    {
        this.health = health;
    }

    /**
     * set a Missile gun for tank.
     *
     * @param gun
     */
    public void setMissileGun(MissileGun gun)
    {
        this.missileGun = gun;
    }

    /**
     * set a Machine gun for tank.
     *
     * @param gun
     */
    public void setMachineGun(MachineGun gun)
    {
        this.machineGun = gun;
    }

    /**
     * set the gun for tank to use(equip gun).
     */
    public void setSelectedGun(Gun selectedGun)
    {
        this.selectedGun = selectedGun;
    }

    /**
     * @return amount of health remained for tank
     */
    public int getHealth()
    {
        return health;
    }

    /**
     * @return Missile gun of tank
     */
    public MissileGun getMissileGun()
    {
        return missileGun;
    }

    /**
     * @return Machine gun of tank
     */
    public MachineGun getMachineGun()
    {
        return machineGun;
    }

    /**
     * @return the gun that tank is using right now(equipped gun).
     */
    public Gun getSelectedGun()
    {
        return selectedGun;
    }


    /**
     * angle that gun must be rotated
     *
     * @return angle between mouse and tank
     */
    public double getGunAngle()
    {
        return gunAngle;
    }

    /**
     * when tank dies and its upgrade field is not null, this method get called
     * and upgrade gets activated.
     */
    public void releaseUpgrade()
    {
        upgrade.setActivation(true);
    }

    /**
     * @return whether the tank has shield or not
     */
    public Boolean getHasShield()
    {
        return hasShield;
    }

    /**
     * set the tank to have shield or not
     *
     * @param hasShield
     */
    public void setHasShield(Boolean hasShield)
    {
        this.hasShield = hasShield;
    }

    /**
     * @return the upgrade that is under the tank
     */
    public Upgrade getUpgrade()
    {
        return upgrade;
    }

    /**
     * set the upgrade that is under the tank
     *
     * @param upgrade
     */
    public void setUpgrade(Upgrade upgrade)
    {
        this.upgrade = upgrade;
    }
}
