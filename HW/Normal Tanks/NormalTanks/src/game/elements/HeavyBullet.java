package game.elements;

/**
 * kind of bullet that has a high level damage.
 * it is fired from MissileGun
 */
public class HeavyBullet extends Bullet
{

    //constructor
    public HeavyBullet()
    {

    }

    public HeavyBullet(double startX, double startY, double mouseX, double mouseY, int damage, ObjectId shooter)
    {
        super(startX, startY, mouseX, mouseY, ObjectId.HeavyBullet, shooter);
        this.damage = damage;
    }

}
