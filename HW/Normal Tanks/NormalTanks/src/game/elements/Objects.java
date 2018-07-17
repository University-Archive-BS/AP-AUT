package game.elements;

import game.Utils.SharedData;
import game.map.*;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * a class containing all created objects like tanks, bullets , turrets , ... in game
 * Ali call this class handler
 */
public class Objects implements Serializable
{

    //fields
    private ArrayList<Tank> players;
    private ArrayList<AITank> tanks;
    private ArrayList<Bullet> bullets;
    private ArrayList<Turret> turrets;
    private ArrayList<BuriedRobot> robots;
    private ArrayList<Upgrade> upgrades;
    private ArrayList<GameObject> deletedItems;
    private Map map;


    //constructor

    /**
     * init the arrayLists and also place the first player
     */
    public Objects()
    {
        players = new ArrayList<>();
        tanks = new ArrayList<>();
        bullets = new ArrayList<>();
        turrets = new ArrayList<>();
        robots = new ArrayList<>();
        upgrades = new ArrayList<>();
        deletedItems = new ArrayList<>();
        if (SharedData.getData().whichMap.equals(ObjectId.FirstMap))
        {
            players.add(new Tank(1150, 6300, 400, ObjectId.Player));
        }
        else if (SharedData.getData().whichMap.equals(ObjectId.SecondMap))
        {
            players.add(new Tank(700, 100, 400, ObjectId.Player));
        }
        else if (SharedData.getData().whichMap.equals(ObjectId.ThirdMap))
        {
            players.add(new Tank(650, 2600, 400, ObjectId.Player));
        }
        else if (SharedData.getData().whichMap.equals(ObjectId.FourthMap))
        {
            players.add(new Tank(1150, 6300, 400, ObjectId.Player));
        }
        map = new Map(this);
    }

    //methods

    /**
     * if the game hasn't been started before it needs to be initialized. player's tank must be create.
     */
    /**
     * init and place the second player
     */
    public void init()
    {
        //Second Player tank initialization
        if (SharedData.getData().gameType.equals(ObjectId.TwoPlayer))
        {
            if (SharedData.getData().whichMap.equals(ObjectId.FirstMap))
            {
                players.add(new Tank(1150, 6300, 400, ObjectId.Player));
            }
            else if (SharedData.getData().whichMap.equals(ObjectId.SecondMap))
            {
                players.add(new Tank(700, 100, 400, ObjectId.Player));
            }
            else if (SharedData.getData().whichMap.equals(ObjectId.ThirdMap))
            {
                players.add(new Tank(650, 2600, 400, ObjectId.Player));
            }
            else if (SharedData.getData().whichMap.equals(ObjectId.FourthMap))
            {
                players.add(new Tank(1150, 6300, 400, ObjectId.Player));
            }
        }
    }

    /**
     * add a new player tank.
     *
     * @param tank
     */
    public void addPlayerTank(Tank tank)
    {
        players.add(tank);
    }


    /**
     * add a tank to tanks ArrayList which contains all available tanks in game
     *
     * @param tank the tank which must be added
     */
    public void addTank(AITank tank)
    {
        tanks.add(tank);
    }

    /**
     * add a bullet to bullets ArrayList which contains all available bullets in game
     *
     * @param bullet the bullet which must be added
     */
    public void addBullet(Bullet bullet)
    {
        bullets.add(bullet);
    }

    /**
     * add a turret to turrets ArrayList which contains all available turrets in game
     *
     * @param turret the turret which must be added
     */
    public void addTurret(Turret turret)
    {
        turrets.add(turret);
    }

    /**
     * remove a tank from ArrayList which contains all available tanks in game
     *
     * @param tank the tank that must be removed
     */
    public void removeTank(Tank tank)
    {
        tanks.remove(tank);
    }

    /**
     * remove a bullet from ArrayList which contains all available bullets in game
     *
     * @param bullet the bullet that must be removed
     */
    public void removeBullet(Bullet bullet)
    {
        bullets.remove(bullet);
    }

    /**
     * remove a turret from ArrayList which contains all available turrets in game
     *
     * @param turret the turret that must be removed
     */
    public void removeTurret(Turret turret)
    {
        turrets.remove(turret);
    }

    /**
     * set a new arrayList for tanks
     *
     * @param tanks
     */
    public void setTanks(ArrayList<AITank> tanks)
    {
        this.tanks = tanks;
    }

    /**
     * set a new arrayList for bullets
     *
     * @param bullets
     */
    public void setBullets(ArrayList<Bullet> bullets)
    {
        this.bullets = bullets;
    }

    /**
     * set a new arrayList for turrets
     *
     * @param turrets
     */
    public void setTurrets(ArrayList<Turret> turrets)
    {
        this.turrets = turrets;
    }

    /**
     * set a new arrayList for buried robots
     *
     * @param robots
     */
    public void setRobots(ArrayList<BuriedRobot> robots)
    {
        this.robots = robots;
    }

    /**
     * set the upgrade ArrayList
     *
     * @param upgrades
     */
    public void setUpgrades(ArrayList<Upgrade> upgrades)
    {
        this.upgrades = upgrades;
    }

    /**
     * return players tanks
     *
     * @return player tank
     */
    public ArrayList<Tank> getPlayers()
    {
        return players;
    }

    /**
     * get arrayList that contains all tanks
     *
     * @return
     */
    public ArrayList<AITank> getTanks()
    {
        return tanks;
    }

    /**
     * get arrayList that contains all bullets
     *
     * @return
     */
    public ArrayList<Bullet> getBullets()
    {
        return bullets;
    }

    /**
     * get arrayList that contains all turrets
     *
     * @return
     */
    public ArrayList<Turret> getTurrets()
    {
        return turrets;
    }

    /**
     * get arrayList that contains all buried robots
     *
     * @return
     */
    public ArrayList<BuriedRobot> getRobots()
    {
        return robots;
    }

    /**
     * @return the upgrades arrayList
     */
    public ArrayList<Upgrade> getUpgrades()
    {
        return upgrades;
    }

    /**
     * replace one of the players with a Tank.
     *
     * @param tank
     * @param number
     */
    public void replacePlayerTank(Tank tank, int number)
    {
        players.remove(players.get(number));
        players.add(tank);
    }

    /**
     * set the players ArrayList.
     *
     * @param players
     */
    public void setPlayers(ArrayList<Tank> players)
    {
        this.players = players;
    }

    /**
     * @return the map
     */
    public Map getMap()
    {
        return map;
    }

    /**
     * set the map
     *
     * @param map
     */
    public void setMap(Map map)
    {
        this.map = map;
    }

    /**
     * @return the arrayList of the deadObjects
     */
    public ArrayList<GameObject> getDeletedItems()
    {
        return deletedItems;
    }

    /**
     * set the arrayList of the dead objects
     *
     * @param deletedItems
     */
    public void setDeletedItems(ArrayList<GameObject> deletedItems)
    {
        this.deletedItems = deletedItems;
    }
}
