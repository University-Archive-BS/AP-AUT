package game.elements;

import java.io.Serializable;

/**
 * primary gun of the tank. it has higher damage  but less fire rate than machine gun.
 */
public class MissileGun extends Gun implements Serializable
{

    //fields

    //constructor
    public MissileGun()
    {

    }

    public MissileGun(double x, double y)
    {
        super(x, y, ObjectId.MissileGun, "src/resource/tankGun01.png");
        damage = 100;
        ammo = 15;
        reloadTime = 1000; // millisecond
    }
    //methods

    /**
     * shoot a bullet from MissileGun.
     *
     * @param tankX  X of starting point for shoot
     * @param tankY  Y of starting point for shoot
     * @param mouseX X of ending point for shoot
     * @param mouseY Y of ending point for shoot
     * @return the bullet must be stored in arrayList so it is returned to be added to list in GameState Class.
     */
    public Bullet shoot(double tankX, double tankY, double mouseX, double mouseY, ObjectId shooter)
    {
        Bullet bullet = new HeavyBullet(tankX, tankY, mouseX, mouseY, damage, shooter);
        ammo--;
        lastShootTime = getCurrentTime();
        return bullet;
    }

}
