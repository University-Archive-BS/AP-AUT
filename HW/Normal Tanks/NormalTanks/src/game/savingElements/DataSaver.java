package game.savingElements;

import game.Utils.SharedData;
import game.elements.*;

import java.io.*;
import java.util.ArrayList;

/**
 * saves game state to load later it user wanted to continue its save.
 */
public class DataSaver
{
    //fields


    public DataSaver(Objects objects)
    {

        SavingData data = new SavingData();

        data.setPlayers(objects.getPlayers());
        data.setBullets(objects.getBullets());
        data.setRobots(objects.getRobots());
        data.setTanks(objects.getTanks());
        data.setUpgrades(objects.getUpgrades());
        data.setTurrets(objects.getTurrets());
        data.setWalls(objects.getMap().getSoftWall());

        int whichMap = 1;
        if(SharedData.getData().whichMap.equals(ObjectId.FirstMap)){
            whichMap = 1;
        }
        else if(SharedData.getData().whichMap.equals(ObjectId.SecondMap)){
            whichMap = 2;
        }
        else if(SharedData.getData().whichMap.equals(ObjectId.ThirdMap)){
            whichMap = 3;
        }
        else if(SharedData.getData().whichMap.equals(ObjectId.FourthMap)){
            whichMap = 4;
        }
        data.setMapId(whichMap);

        writeFile(data);
    }

    /**
     * writes a save file to be read later.
     * it serialize SavingData object.
     *
     * @param data
     */
    private void writeFile(SavingData data)
    {
        FileOutputStream fout = null;
        try
        {
            fout = new FileOutputStream("res/save.ser");
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        ObjectOutputStream oos = null;
        try
        {
            oos = new ObjectOutputStream(fout);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        try
        {
            oos.writeObject(data);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
