package game.multiplayer;

import game.elements.*;

import java.io.Serializable;

/**
 * contains all data that must be sent to server side in each tick.
 * each field is null in default and it get init when sth must be sent.
 */
public class ClientSendingData implements Serializable
{
    //fields
    private Tank clientTank = null;
    private Bullet lastShotBullet = null;

    /**
     * set client tank to send it to server
     *
     * @param clientTank
     */
    public void setClientTank(Tank clientTank)
    {
        this.clientTank = clientTank;
    }

    /**
     * last bullet shot from client
     *
     * @param lastShotBullet last shot bullet
     */
    public void setLastShotBullet(Bullet lastShotBullet)
    {
        this.lastShotBullet = lastShotBullet;
    }

    /**
     * returns client tank
     *
     * @return client tank
     */
    public Tank getClientTank()
    {
        return clientTank;
    }

    /**
     * returns last shot bullet
     *
     * @return lsat fired bullet
     */
    public Bullet getLastShotBullet()
    {
        return lastShotBullet;
    }
}
