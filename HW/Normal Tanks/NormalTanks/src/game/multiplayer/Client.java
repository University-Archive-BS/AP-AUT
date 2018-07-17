package game.multiplayer;

import game.Utils.SharedData;
import game.elements.Objects;

import java.io.*;
import java.net.Socket;

/**
 * client side of the Coop. client side doesn't do some actions and instead
 * server side handles them. like moving bullets and updating game object states like turrets and AI Tanks.
 * it also sends data to server when it shoot or move. the data is passed by OOS and OIS.
 */
public class Client
{
    //fields
    private Socket socket;

    private ObjectOutputStream oos;
    private ObjectInputStream ois;

    private ClientSendingData clientData;

    private Objects objects;

    //constructor
    public Client(Objects objects)
    {
        try
        {
            socket = new Socket(SharedData.getData().ip, 6666);

            clientData = new ClientSendingData();
            this.objects = objects;

            clientData.setClientTank(objects.getPlayers().get(0));
        }
        catch (IOException e)
        {
            System.out.println("Couldn't connect to server");
        }
    }

    /**
     * ticks client. it first receive data from host then send some data to host if needed.
     */
    public void tick()
    {
        receiveData();
        if (!SharedData.getData().clientLost)
        {
            sendData();
        }
    }

    /**
     * gets data from server and call updateObject method to update all game objects states.
     */
    private void receiveData()
    {
        try
        {
            ois = new ObjectInputStream(socket.getInputStream());
            TransferringData data = (TransferringData) ois.readObject();

            updateObjects(data);
        }
        catch (IOException e)
        {
//            e.printStackTrace();
            System.out.println("couldn't read objects");
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Class not found while reading!");
        }
    }

    /**
     * gets data and update all game objects with that.
     * it also updates SharedData class from the data received from server.
     *
     * @param data taken data from server
     */
    private void updateObjects(TransferringData data)
    { // called in receive data
        objects.replacePlayerTank(data.getPlayers().get(0), 1);
        objects.setBullets(data.getBullets());
        objects.setRobots(data.getRobots());
        objects.setTanks(data.getTanks());
        objects.setTurrets(data.getTurrets());
        objects.setUpgrades(data.getUpgrades());
        objects.getMap().setSoftWall(data.getWalls());

        objects.getPlayers().get(0).setHealth(objects.getPlayers().get(0).getHealth() - data.getTakenDamage());

        if (!data.getClientIsAlive())
        {
            objects.replacePlayerTank(data.getPlayers().get(0), 0);
            SharedData.getData().clientLost = true;
        }
        if (!data.getServerIsAlive())
        {
            SharedData.getData().ServerLost = true;
        }
        if (data.getGameDone())
        {
            SharedData.getData().gameDone = true;
        }
    }

    /**
     * sends data to server side. it contains:
     * if client fired a bullet.
     * client tank position.
     */
    private void sendData()
    {
        try
        {
            oos = new ObjectOutputStream(socket.getOutputStream());
            //check which items must be sent
            if (SharedData.getData().clientShot)
            {
                addClientDataNewBullet();
            }
            //
            oos.writeObject(clientData);
            //
            //making clientData null ('cause of checking in server)
            clientData.setLastShotBullet(null);
        }
        catch (IOException e)
        {
            System.out.println("Server Disconnected");
//            e.printStackTrace();
        }
    }

    /**
     * if client fired a bullet it specify that bullet to be sent by sending Data
     */
    private void addClientDataNewBullet()
    {
        clientData.setLastShotBullet(SharedData.getData().clientLastShotBullet);
        SharedData.getData().clientShot = false;
    }
}
