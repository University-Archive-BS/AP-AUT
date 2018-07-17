package game.map;

import game.Utils.SharedData;
import game.Utils.Utility;
import game.elements.*;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * a class for process the png of the map and fill the arrayLists of the elements.
 */
public class Map
{
    BufferedImage mapImage;
    private ArrayList<HardWall> hardWall = new ArrayList<>();
    private ArrayList<Plant> plant = new ArrayList<>();
    private ArrayList<SoftWall> softWall = new ArrayList<>();
    private ArrayList<Teazel> teazel = new ArrayList<>();
    private ArrayList<Soil> soil = new ArrayList<>();
    private End end;

    public Map(Objects objects)
    {
        //first map
        if (SharedData.getData().whichMap.equals(ObjectId.FirstMap))
        {
            if (SharedData.getData().difficulty.equals(ObjectId.EasyMode))
            {
                mapImage = Utility.firstEasyMap;
            }
            else if (SharedData.getData().difficulty.equals(ObjectId.MediumMode))
            {
                mapImage = Utility.firstMediumMap;
            }
            else if (SharedData.getData().difficulty.equals(ObjectId.HardMode))
            {
                mapImage = Utility.firstHardMap;
            }
        }

        //second map
        else if (SharedData.getData().whichMap.equals(ObjectId.SecondMap))
        {
            if (SharedData.getData().difficulty.equals(ObjectId.EasyMode))
            {
                mapImage = Utility.secondEasyMap;
            }
            else if (SharedData.getData().difficulty.equals(ObjectId.MediumMode))
            {
                mapImage = Utility.secondMediumMap;
            }
            else if (SharedData.getData().difficulty.equals(ObjectId.HardMode))
            {
                mapImage = Utility.secondHardMap;
            }
        }

        //third map
        else if (SharedData.getData().whichMap.equals(ObjectId.ThirdMap))
        {
            if (SharedData.getData().difficulty.equals(ObjectId.EasyMode))
            {
                mapImage = Utility.thirdEasyMap;
            }
            else if (SharedData.getData().difficulty.equals(ObjectId.MediumMode))
            {
                mapImage = Utility.thirdMediumMap;
            }
            else if (SharedData.getData().difficulty.equals(ObjectId.HardMode))
            {
                mapImage = Utility.thirdHardMap;
            }
        }

        //fourth map
        else if (SharedData.getData().whichMap.equals(ObjectId.FourthMap))
        {
            if (SharedData.getData().difficulty.equals(ObjectId.EasyMode))
            {
                mapImage = Utility.fourthEasyMap;
            }
            else if (SharedData.getData().difficulty.equals(ObjectId.MediumMode))
            {
                mapImage = Utility.fourthMediumMap;
            }
            else if (SharedData.getData().difficulty.equals(ObjectId.HardMode))
            {
                mapImage = Utility.fourthHardMap;
            }
        }

        //process the image
        for (int xx = 0; xx < mapImage.getWidth(); xx++)
        {
            for (int yy = 0; yy < mapImage.getHeight(); yy++)
            {
                int pixel = mapImage.getRGB(xx, yy);
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = (pixel) & 0xff;

                if ((red == 255) && (green == 255) && (blue == 0)) //HardWall
                {
                    hardWall.add(new HardWall(xx * 100, yy * 100));
                }
                else if ((red == 0) && (green == 0) && (blue == 255)) //SoftWall
                {
                    soil.add(new Soil(xx * 100, yy * 100));
                    softWall.add(new SoftWall(xx * 100, yy * 100));
                }
                else if ((red == 0) && (green == 255) && (blue == 0)) //Plant
                {
                    soil.add(new Soil(xx * 100, yy * 100));
                    plant.add(new Plant(xx * 100, yy * 100));
                }
                else if ((red == 255) && (green == 255) && (blue == 255)) //Soil
                {
                    soil.add(new Soil(xx * 100, yy * 100));
                }
                else if ((red == 100) && (green == 0) && (blue == 0)) //end
                {
                    end = new End(xx * 100, yy * 100);
                }
                else if ((red == 255) && (green == 0) && (blue == 255)) //Teazel
                {
                    soil.add(new Soil(xx * 100, yy * 100));
                    teazel.add(new Teazel(xx * 100, yy * 100));
                }
                else if ((red == 208) && (green == 135) && (blue == 190)) //player
                {
                    soil.add(new Soil(xx * 100, yy * 100));
                    //objects.getPlayers().add(new Tank(xx * 100, yy * 100, 100, ObjectId.Player));
                }
                else if ((red == 0) && (green == 0) && (blue == 0)) //buriedRobot
                {
                    soil.add(new Soil(xx * 100, yy * 100));
                    objects.getRobots().add(new BuriedRobot(xx * 100, yy * 100));
                }
                else if ((red == 166) && (green == 166) && (blue == 76)) //turret1
                {
                    soil.add(new Soil(xx * 100, yy * 100));
                    objects.getTurrets().add(new Turret(xx * 100, yy * 100, objects.getPlayers(), ObjectId.MissileGun));
                }
                else if ((red == 255) && (green == 114) && (blue == 0)) //AITank
                {
                    soil.add(new Soil(xx * 100, yy * 100));
                    AITank tank = new AITank(xx * 100, yy * 100, 140, ObjectId.AITank, new MissileGun());
                    objects.getTanks().add(tank);
                }
                else if ((red == 88) && (green == 162) && (blue == 230)) //MissileGunUpgrade
                {
                    soil.add(new Soil(xx * 100, yy * 100));
                    Upgrade temp = new Upgrade(xx * 100, yy * 100, ObjectId.MissileGunUpgrade);
                    temp.setActivation(true);
                    objects.getUpgrades().add(temp);
                }
                else if ((red == 143) && (green == 202) && (blue == 160)) //MachineGunUpgrade
                {
                    soil.add(new Soil(xx * 100, yy * 100));
                    Upgrade temp = new Upgrade(xx * 100, yy * 100, ObjectId.MachineGunUpgrade);
                    temp.setActivation(true);
                    objects.getUpgrades().add(temp);
                }
                else if ((red == 22) && (green == 62) && (blue == 34)) //turret2
                {
                    soil.add(new Soil(xx * 100, yy * 100));
                    objects.getTurrets().add(new Turret(xx * 100, yy * 100, objects.getPlayers(), ObjectId.MachineGun));
                }
                else if ((red == 90) && (green == 90) && (blue == 90)) //shieldUpgrade
                {
                    soil.add(new Soil(xx * 100, yy * 100));
                    Upgrade temp = new Upgrade(xx * 100, yy * 100, ObjectId.ShieldUpgrade);
                    temp.setActivation(true);
                    objects.getUpgrades().add(temp);
                }
                else if ((red == 213) && (green == 236) && (blue == 6)) //healthUpgrade with AITank
                {
                    soil.add(new Soil(xx * 100, yy * 100));
                    Upgrade temp = new Upgrade(xx * 100, yy * 100, ObjectId.HealthUpgrade);
                    objects.getUpgrades().add(temp);
                    AITank tank = new AITank(xx * 100, yy * 100, 140, ObjectId.AITank, new MachineGun(), temp);
                    objects.getTanks().add(tank);
                }
                else if ((red == 255) && (green == 0) && (blue == 0)) //damageUpgrade with AITank
                {
                    soil.add(new Soil(xx * 100, yy * 100));
                    Upgrade temp = new Upgrade(xx * 100, yy * 100, ObjectId.DamageUpgrade);
                    objects.getUpgrades().add(temp);
                    AITank tank = new AITank(xx * 100, yy * 100, 140, ObjectId.AITank, new MachineGun(), temp);
                    objects.getTanks().add(tank);
                }
            }
        }
    }

    /**
     * @return the map image
     */
    public BufferedImage getMapImage()
    {
        return mapImage;
    }

    /**
     * set the map image
     *
     * @param mapImage
     */
    public void setMapImage(BufferedImage mapImage)
    {
        this.mapImage = mapImage;
    }

    /**
     * @return the hardWall list
     */
    public ArrayList<HardWall> getHardWall()
    {
        return hardWall;
    }

    /**
     * set the hardWall list
     *
     * @param hardWall
     */
    public void setHardWall(ArrayList<HardWall> hardWall)
    {
        this.hardWall = hardWall;
    }

    /**
     * @return the plant list
     */
    public ArrayList<Plant> getPlant()
    {
        return plant;
    }

    /**
     * set the plant list
     *
     * @param plant
     */
    public void setPlant(ArrayList<Plant> plant)
    {
        this.plant = plant;
    }

    /**
     * @return the softWall list
     */
    public ArrayList<SoftWall> getSoftWall()
    {
        return softWall;
    }

    /**
     * set the softWall list
     *
     * @param softWall
     */
    public void setSoftWall(ArrayList<SoftWall> softWall)
    {
        this.softWall = softWall;
    }

    /**
     * @return the teazel list
     */
    public ArrayList<Teazel> getTeazel()
    {
        return teazel;
    }

    /**
     * set the teazel list
     *
     * @param teazel
     */
    public void setTeazel(ArrayList<Teazel> teazel)
    {
        this.teazel = teazel;
    }

    /**
     * @return the soil list
     */
    public ArrayList<Soil> getSoil()
    {
        return soil;
    }

    /**
     * set the soil list
     *
     * @param soil
     */
    public void setSoil(ArrayList<Soil> soil)
    {
        this.soil = soil;
    }

    /**
     * @return the end
     */
    public End getEnd()
    {
        return end;
    }

    /**
     * set the end
     *
     * @param end
     */
    public void setEnd(End end)
    {
        this.end = end;
    }
}
