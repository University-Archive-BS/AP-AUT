package game.elements;

import game.Utils.SharedData;
import game.Utils.Sound;
import game.Utils.Utility;

import java.io.Serializable;

/**
 * an upgrade is options to make gun stronger or reset health.
 * they get activated in some conditions like killing some tanks.
 */
public class Upgrade extends GameObject implements Serializable
{

    //fields
    private Boolean activation;
    private Tank user;

    //constructor
    public Upgrade()
    {

    }

    /**
     * @param x
     * @param y
     * @param type
     */
    public Upgrade(double x, double y, ObjectId type)
    {
        super(x, y, type);
        activation = false;
    }

    //methods

    /**
     * ticks a upgrade
     * and determine the target and check to be used
     *
     * @param objects
     */
    public void tick(Objects objects)
    {
        determineTarget(objects);
        checkToBeUsed(objects);
    }

    /**
     * checks whether a tank is close to upgrade or not, if its close enough
     * it applies upgrade to tank based on the type(id) of the upgrade.
     */
    public void checkToBeUsed(Objects objects)
    {
        if (Math.abs(user.x - this.x) < 95 && Math.abs(user.y - this.y) < 95)
        {
            if (id.equals(ObjectId.DamageUpgrade))
            {
                user.getMissileGun().setDamage(user.getMissileGun().damage + 40);
                user.getMachineGun().setDamage(user.getMachineGun().damage + 40);
                user.getMissileGun().reloadTime -= 50;
                user.getMachineGun().reloadTime -= 40;
                makeSound();
                activation = false;
            }
            else if (id.equals(ObjectId.HealthUpgrade))
            {
                user.setHealth(user.health + 110);
                makeSound();
                activation = false;
            }
            else if (id.equals(ObjectId.MachineGunUpgrade))
            {
                user.getMachineGun().setAmmo(user.getMachineGun().ammo + 15);
                makeSound();
                activation = false;
            }
            else if (id.equals(ObjectId.MissileGunUpgrade))
            {
                user.getMissileGun().setAmmo(user.getMissileGun().ammo + 15);
                makeSound();
                activation = false;
            }
            else if (id.equals(ObjectId.ShieldUpgrade))
            {
                System.out.println("shield");
                user.activateShield();
                makeSound();
                activation = false;
            }
        }
    }

    /**
     * make a sound when the player get the upgrade
     */
    private void makeSound()
    {
        Sound sound = new Sound(Utility.upgrade, false);
        sound.playSound();
    }

    /**
     * set user to the tank which is closer to upgrade
     */
    public void determineTarget(Objects objects)
    {
        if (SharedData.getData().gameType.equals(ObjectId.TwoPlayer))
        {
            user = objects.getPlayers().get(0);
            for (int i = 0; i < objects.getPlayers().size(); i++)
            {
                double distance = Utility.calculateDistance(this, objects.getPlayers().get(i));
                if (distance <= Utility.calculateDistance(this, user))
                {
                    user = objects.getPlayers().get(i);
                }
            }
        }
        else
        { //in single player
            user = objects.getPlayers().get(0);
        }
    }

    /**
     * an upgrade is not activated in default. true if it is activated
     * and false if not
     *
     * @return
     */
    public Boolean getActivation()
    {
        return activation;
    }

    /**
     * for drawing in map and ticking in GameState upgrade must be activated.
     * when some tanks die or destroying wall it gets activated
     *
     * @param activated
     */
    public void setActivation(Boolean activated)
    {
        this.activation = activated;
    }
}
