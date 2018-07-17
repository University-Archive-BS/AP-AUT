package game.elements;

import java.io.Serializable;

/**
 * the enemy Tank that use AIHandler to play with player. the gun for this must be determined
 * by last parameter in constructor. each tank use MachineGun or MissileGun, not both.
 * its speed is lower than player tank.
 */
public class AITank extends Tank implements Serializable
{
    private boolean activated;
    private Tank target;

    public AITank(double x, double y, int health, ObjectId id, Gun gun)
    {
        super(x, y, health, id);

        if (gun instanceof MissileGun)
        {
            selectedGun = missileGun;
        }
        else if (gun instanceof MachineGun)
        {
            selectedGun = machineGun;
        }

        selectedGun.setReloadTime(500);
        selectedGun.setAmmo(1000);

        setVelX(8);
        setVelY(8);

        activated = false;
    }

    public AITank(double x, double y, int health, ObjectId id, Gun gun, Upgrade upgrade)
    {
        super(x, y, health, id);

        if (gun instanceof MissileGun)
        {
            selectedGun = missileGun;
        }
        else if (gun instanceof MachineGun)
        {
            selectedGun = machineGun;
        }

        selectedGun.setReloadTime(500);
        selectedGun.setAmmo(1000);

        setVelX(8);
        setVelY(8);

        this.upgrade = upgrade;

        activated = false;
    }

    /**
     * if tank is playing or its sleep(waiting to be invoked).
     *
     * @return activated or not
     */
    public boolean isActivated()
    {
        return activated;
    }

    /**
     * determine that tank is On or Off.
     *
     * @param activated true if On - false if Off
     */
    public void setActivated(boolean activated)
    {
        this.activated = activated;
    }

    /**
     * set a target for AI tank to move and shoot at.
     *
     * @param target the tank that must be target of this
     */
    public void setTarget(Tank target)
    {
        this.target = target;
    }

    /**
     * the tank that AI tank is moving and shooting at(target)
     *
     * @return the target of the tank
     */
    public Tank getTarget()
    {
        return target;
    }
}
