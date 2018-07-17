package game.multiplayer;

import game.Utils.SharedData;
import game.elements.Objects;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * the server side of the coop. it handles all the data that it has and also received from client side.
 * it ticks bullets , AI Tanks , turrets , buried Robots , ... , which client side just get them
 * from server side and doesn't handle them itself. it pass Objects object which contains all game objects.
 * this work is done by Serialization.
 */
public class Server
{
    //fields
    private ServerSocket serverSocket;
    private Socket socket;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    private Objects objects;

    public Server(Objects objects)
    {
        try
        {
            serverSocket = new ServerSocket(6666);
            System.out.println("Server created");
            socket = serverSocket.accept();
            System.out.println("Connection established!");
            this.objects = objects;
        }
        catch (IOException e)
        {
            System.out.println("Couldn't create server socket");
        }
    }

    /**
     * ticks Server. which means sending data and receiving data from client to
     * affect them in the game.
     *
     * @param objects
     */
    public void tick(Objects objects)
    {
        sendData();
        if (!SharedData.getData().clientLost)
        {
            receiveData();
        }
        SharedData.getData().clientTakenDamage = 0;
    }

    /**
     * makes a OOS to send data then Make a TransferringData that contains all needed
     * data to be sent to client side. then it sends them.
     */
    private void sendData()
    {
        try
        {
            oos = new ObjectOutputStream(socket.getOutputStream());
            TransferringData data = new TransferringData(objects);

            oos.writeObject(data);

            oos.flush();
        }
        catch (IOException e)
        {
            System.out.println("Sending Failed!");
//            e.printStackTrace();
        }
    }

    /**
     * receive data from client side and affect them in game
     */
    public void receiveData()
    {
        try
        {
            ois = new ObjectInputStream(socket.getInputStream());
            try
            {
                ClientSendingData data = (ClientSendingData) ois.readObject(); //
                if (objects.getPlayers().size() == 2)
                {
                    objects.replacePlayerTank(data.getClientTank(), 1);
                }
                else if (SharedData.getData().ServerLost)
                {
                    objects.replacePlayerTank(data.getClientTank(), 0);
                }
                if (data.getLastShotBullet() != null)
                {
                    objects.addBullet(data.getLastShotBullet());
                }
            }
            catch (ClassNotFoundException e)
            {
//                e.printStackTrace();
            }
        }
        catch (IOException e)
        {
//            e.printStackTrace();
            System.out.println("Client Disconnected");
        }
    }

}



