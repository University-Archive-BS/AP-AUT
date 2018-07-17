package game.Utils;

import game.elements.Bullet;
import game.elements.ObjectId;
import game.elements.Tank;

/**
 * a SingleTon patterned class that shares needed info between all classes
 */
public class SharedData
{

    public ObjectId gameType = ObjectId.SinglePlayer;
    public ObjectId playerType;
    public Boolean clientShot = false;
    public Bullet clientLastShotBullet;
    public ObjectId difficulty = ObjectId.EasyMode;
    public ObjectId whichMap = ObjectId.FirstMap;
    public String ip;
    public Boolean playerDied = false;
    public Tank playerToRemove;
    public ObjectId startingType = ObjectId.NewGame;
    public ObjectId result = ObjectId.Won; // win or lose
    public Boolean clientLost = false;
    public Boolean ServerLost = false;
    public int clientTakenDamage = 0;
    public Boolean gameDone = false;
    //Singleton
    private static SharedData sharedData;

    /**
     * init the object
     */
    public SharedData()
    {
        sharedData = this;
    }

    /**
     * @return sharedData
     */
    public static SharedData getData()
    {
        return sharedData;
    }
}
