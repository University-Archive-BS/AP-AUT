package game.multiplayer;

import game.Utils.SharedData;
import game.elements.*;
import game.map.SoftWall;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * all data that must be sent to client side by server.
 */
public class TransferringData implements Serializable
{
    //fields
    private ArrayList<Tank> players;
    private ArrayList<AITank> tanks;
    private ArrayList<Bullet> bullets;
    private ArrayList<Turret> turrets;
    private ArrayList<BuriedRobot> robots;
    private ArrayList<Upgrade> upgrades;
    private ArrayList<SoftWall> walls;
    //
    private int takenDamage;
    //
    private Boolean serverIsAlive;
    private Boolean clientIsAlive;
    private Boolean gameDone = SharedData.getData().gameDone;


    //methods

    /**
     * returns all alive players
     *
     * @return players
     */
    public ArrayList<Tank> getPlayers()
    {
        return players;
    }

    /**
     * returns all alive AI tanks
     *
     * @return AI tanks
     */
    public ArrayList<AITank> getTanks()
    {
        return tanks;
    }

    /**
     * returns all bullets of the game
     *
     * @return bullets
     */
    public ArrayList<Bullet> getBullets()
    {
        return bullets;
    }

    /**
     * returns all alive turrets
     *
     * @return
     */
    public ArrayList<Turret> getTurrets()
    {
        return turrets;
    }

    /**
     * returns all alive robots
     *
     * @return robots
     */
    public ArrayList<BuriedRobot> getRobots()
    {
        return robots;
    }

    /**
     * returns all alive upgrades
     *
     * @return upgrades
     */
    public ArrayList<Upgrade> getUpgrades()
    {
        return upgrades;
    }

    /**
     * true is server is alive and vise versa
     *
     * @return true - false(if dead)
     */
    public Boolean getServerIsAlive()
    {
        return serverIsAlive;
    }

    /**
     * true is client is alive and vise versa
     *
     * @return true - false(if dead)
     */
    public Boolean getClientIsAlive()
    {
        return clientIsAlive;
    }

    /**
     * true is game is done
     *
     * @return true - false(if dead)
     */
    public Boolean getGameDone()
    {
        return gameDone;
    }

    /**
     * the damage that client get.
     *
     * @return
     */
    public int getTakenDamage()
    {
        return takenDamage;
    }

    /**
     * all soft walls.
     *
     * @return soft walls of the game.
     */
    public ArrayList<SoftWall> getWalls()
    {
        return walls;
    }

    //constructor
    public TransferringData()
    {

    }

    //init all fields
    public TransferringData(Objects objects)
    {
        this.players = objects.getPlayers();
        this.tanks = objects.getTanks();
        this.bullets = objects.getBullets();
        this.turrets = objects.getTurrets();
        this.robots = objects.getRobots();
        this.upgrades = objects.getUpgrades();
        this.walls = objects.getMap().getSoftWall();

        takenDamage = SharedData.getData().clientTakenDamage;
        if (!SharedData.getData().ServerLost)
        {
            serverIsAlive = true;
        }
        else
        {
            serverIsAlive = false;
        }
        if (objects.getPlayers().size() > 1)
        {
            clientIsAlive = true;
        }
        else
        {
            clientIsAlive = false;
            SharedData.getData().clientLost = true;
        }
    }
}

