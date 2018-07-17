package GameEngine;

import game.Utils.SharedData;
import game.Utils.Sound;
import game.Utils.Utility;
import game.elements.*;
import game.map.SoftWall;

import java.awt.*;

public class Physics
{
    ///////////////////////////////
    //////AI Tanks collision///////
    ///////////////////////////////

    /**
     * check the top collision of the AITanks.
     *
     * @param objects
     * @param tank
     * @return
     */
    public static boolean aiTankCheckCollisionUp(Objects objects, AITank tank)
    {
        for (int i = 0; i < objects.getMap().getHardWall().size(); i++)
        {
            if (tank.getBounds().intersects(objects.getMap().getHardWall().get(i).getBounds()))
            {
                if (tank.getY() > objects.getMap().getHardWall().get(i).getY())
                {
                    return true;
                }
            }
        }
        for (int i = 0; i < objects.getMap().getSoftWall().size(); i++)
        {
            if (tank.getBounds().intersects(objects.getMap().getSoftWall().get(i).getBounds()))
            {
                if (tank.getY() > objects.getMap().getSoftWall().get(i).getY())
                {
                    return true;
                }
            }
        }
        for (int i = 0; i < objects.getMap().getTeazel().size(); i++)
        {
            if (tank.getBounds().intersects(objects.getMap().getTeazel().get(i).getBounds()))
            {
                if (tank.getY() > objects.getMap().getTeazel().get(i).getY())
                {
                    return true;
                }
            }
        }
        for (int i = 0; i < objects.getTurrets().size(); i++)
        {
            if (tank.getBounds().intersects(objects.getTurrets().get(i).getBounds()))
            {
                if (tank.getY() > objects.getTurrets().get(i).getY())
                {
                    return true;
                }
            }
        }
        for (int i = 0; i < objects.getTanks().size(); i++)
        {
            if (tank.getBounds().intersects(objects.getTanks().get(i).getBounds()))
            {
                if (tank.getY() > objects.getTanks().get(i).getY())
                {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * check the down collision of the AITanks.
     *
     * @param objects
     * @param tank
     * @return
     */
    public static boolean aiTankCheckCollisionDown(Objects objects, AITank tank)
    {
        for (int i = 0; i < objects.getMap().getHardWall().size(); i++)
        {
            if (tank.getBounds().intersects(objects.getMap().getHardWall().get(i).getBounds()))
            {
                if (tank.getY() < objects.getMap().getHardWall().get(i).getY())
                {
                    return true;
                }
            }
        }
        for (int i = 0; i < objects.getMap().getSoftWall().size(); i++)
        {
            if (tank.getBounds().intersects(objects.getMap().getSoftWall().get(i).getBounds()))
            {
                if (tank.getY() < objects.getMap().getSoftWall().get(i).getY())
                {
                    return true;
                }
            }
        }
        for (int i = 0; i < objects.getMap().getTeazel().size(); i++)
        {
            if (tank.getBounds().intersects(objects.getMap().getTeazel().get(i).getBounds()))
            {
                if (tank.getY() < objects.getMap().getTeazel().get(i).getY())
                {
                    return true;
                }
            }
        }
        for (int i = 0; i < objects.getTurrets().size(); i++)
        {
            if (tank.getBounds().intersects(objects.getTurrets().get(i).getBounds()))
            {
                if (tank.getY() < objects.getTurrets().get(i).getY())
                {
                    return true;
                }
            }
        }
        for (int i = 0; i < objects.getTanks().size(); i++)
        {
            if (tank.getBounds().intersects(objects.getTanks().get(i).getBounds()))
            {
                if (tank.getY() < objects.getTanks().get(i).getY())
                {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * check the right collision of the AITanks.
     *
     * @param objects
     * @param tank
     * @return
     */
    public static boolean aiTankCheckCollisionRight(Objects objects, AITank tank)
    {
        for (int i = 0; i < objects.getMap().getHardWall().size(); i++)
        {
            if (tank.getBounds().intersects(objects.getMap().getHardWall().get(i).getBounds()))
            {
                if (tank.getX() < objects.getMap().getHardWall().get(i).getX())
                {
                    return true;
                }
            }
        }
        for (int i = 0; i < objects.getMap().getSoftWall().size(); i++)
        {
            if (tank.getBounds().intersects(objects.getMap().getSoftWall().get(i).getBounds()))
            {
                if (tank.getX() < objects.getMap().getSoftWall().get(i).getX())
                {
                    return true;
                }
            }
        }
        for (int i = 0; i < objects.getMap().getTeazel().size(); i++)
        {
            if (tank.getBounds().intersects(objects.getMap().getTeazel().get(i).getBounds()))
            {
                if (tank.getX() < objects.getMap().getTeazel().get(i).getX())
                {
                    return true;
                }
            }
        }
        for (int i = 0; i < objects.getTurrets().size(); i++)
        {
            if (tank.getBounds().intersects(objects.getTurrets().get(i).getBounds()))
            {
                if (tank.getX() < objects.getTurrets().get(i).getX())
                {
                    return true;
                }
            }
        }
        for (int i = 0; i < objects.getTanks().size(); i++)
        {
            if (tank.getBounds().intersects(objects.getTanks().get(i).getBounds()))
            {
                if (tank.getX() < objects.getTanks().get(i).getX())
                {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * check the left collision of the AITanks.
     *
     * @param objects
     * @param tank
     * @return
     */
    public static boolean aiTankCheckCollisionLeft(Objects objects, AITank tank)
    {
        for (int i = 0; i < objects.getMap().getHardWall().size(); i++)
        {
            if (tank.getBounds().intersects(objects.getMap().getHardWall().get(i).getBounds()))
            {
                if (tank.getX() > objects.getMap().getHardWall().get(i).getX())
                {
                    return true;
                }
            }
        }
        for (int i = 0; i < objects.getMap().getSoftWall().size(); i++)
        {
            if (tank.getBounds().intersects(objects.getMap().getSoftWall().get(i).getBounds()))
            {
                if (tank.getX() > objects.getMap().getSoftWall().get(i).getX())
                {
                    return true;
                }
            }
        }
        for (int i = 0; i < objects.getMap().getTeazel().size(); i++)
        {
            if (tank.getBounds().intersects(objects.getMap().getTeazel().get(i).getBounds()))
            {
                if (tank.getX() > objects.getMap().getTeazel().get(i).getX())
                {
                    return true;
                }
            }
        }
        for (int i = 0; i < objects.getTurrets().size(); i++)
        {
            if (tank.getBounds().intersects(objects.getTurrets().get(i).getBounds()))
            {
                if (tank.getX() > objects.getTurrets().get(i).getX())
                {
                    return true;
                }
            }
        }
        for (int i = 0; i < objects.getTanks().size(); i++)
        {
            if (tank.getBounds().intersects(objects.getTanks().get(i).getBounds()))
            {
                if (tank.getX() > objects.getTanks().get(i).getX())
                {
                    return true;
                }
            }
        }
        return false;
    }

    //////////////////////////////////
    //////BuriedRobot collision///////
    //////////////////////////////////

    /**
     * check the top collision of the BuriedRobots.
     *
     * @param objects
     * @param robot
     * @return
     */
    public static boolean BuriedRobotCollisionUp(Objects objects, BuriedRobot robot)
    {
        for (int i = 0; i < objects.getMap().getHardWall().size(); i++)
        {
            if (robot.getBounds().intersects(objects.getMap().getHardWall().get(i).getBounds()))
            {
                if (robot.getY() > objects.getMap().getHardWall().get(i).getY())
                {
                    return true;
                }
            }
        }
        for (int i = 0; i < objects.getTurrets().size(); i++)
        {
            if (robot.getBounds().intersects(objects.getTurrets().get(i).getBounds()))
            {
                if (robot.getY() > objects.getTurrets().get(i).getY())
                {
                    return true;
                }
            }
        }
        for (int i = 0; i < objects.getTanks().size(); i++)
        {
            if (robot.getBounds().intersects(objects.getTanks().get(i).getBounds()))
            {
                if (robot.getY() > objects.getTanks().get(i).getY())
                {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * check the down collision of the BuriedRobots.
     *
     * @param objects
     * @param robot
     * @return
     */
    public static boolean BuriedRobotCollisionDown(Objects objects, BuriedRobot robot)
    {
        for (int i = 0; i < objects.getMap().getHardWall().size(); i++)
        {
            if (robot.getBounds().intersects(objects.getMap().getHardWall().get(i).getBounds()))
            {
                if (robot.getY() < objects.getMap().getHardWall().get(i).getY())
                {
                    return true;
                }
            }
        }
        for (int i = 0; i < objects.getTurrets().size(); i++)
        {
            if (robot.getBounds().intersects(objects.getTurrets().get(i).getBounds()))
            {
                if (robot.getY() < objects.getTurrets().get(i).getY())
                {
                    return true;
                }
            }
        }
        for (int i = 0; i < objects.getTanks().size(); i++)
        {
            if (robot.getBounds().intersects(objects.getTanks().get(i).getBounds()))
            {
                if (robot.getY() < objects.getTanks().get(i).getY())
                {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * check the right collision of the BuriedRobots.
     *
     * @param objects
     * @param robot
     * @return
     */
    public static boolean BuriedRobotCollisionRight(Objects objects, BuriedRobot robot)
    {
        for (int i = 0; i < objects.getMap().getHardWall().size(); i++)
        {
            if (robot.getBounds().intersects(objects.getMap().getHardWall().get(i).getBounds()))
            {
                if (robot.getX() < objects.getMap().getHardWall().get(i).getX())
                {
                    return true;
                }
            }
        }
        for (int i = 0; i < objects.getTurrets().size(); i++)
        {
            if (robot.getBounds().intersects(objects.getTurrets().get(i).getBounds()))
            {
                if (robot.getX() < objects.getTurrets().get(i).getX())
                {
                    return true;
                }
            }
        }
        for (int i = 0; i < objects.getTanks().size(); i++)
        {
            if (robot.getBounds().intersects(objects.getTanks().get(i).getBounds()))
            {
                if (robot.getX() < objects.getTanks().get(i).getX())
                {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * check the left collision of the BuriedRobots.
     *
     * @param objects
     * @param robot
     * @return
     */
    public static boolean BuriedRobotCollisionLeft(Objects objects, BuriedRobot robot)
    {
        for (int i = 0; i < objects.getMap().getHardWall().size(); i++)
        {
            if (robot.getBounds().intersects(objects.getMap().getHardWall().get(i).getBounds()))
            {
                if (robot.getX() > objects.getMap().getHardWall().get(i).getX())
                {
                    return true;
                }
            }
        }
        for (int i = 0; i < objects.getTurrets().size(); i++)
        {
            if (robot.getBounds().intersects(objects.getTurrets().get(i).getBounds()))
            {
                if (robot.getX() > objects.getTurrets().get(i).getX())
                {
                    return true;
                }
            }
        }
        for (int i = 0; i < objects.getTanks().size(); i++)
        {
            if (robot.getBounds().intersects(objects.getTanks().get(i).getBounds()))
            {
                if (robot.getX() > objects.getTanks().get(i).getX())
                {
                    return true;
                }
            }
        }
        return false;
    }

    /////////////////////////////
    //////player collision///////
    /////////////////////////////

    /**
     * check the top collision of the
     *
     * @param objects
     * @return
     */
    public static boolean checkPlayerCollisionUp(Objects objects)
    {
        for (int i = 0; i < objects.getMap().getHardWall().size(); i++)
        {
            Rectangle rectangle = new Rectangle((int) objects.getMap().getHardWall().get(i).getX(), (int) objects.getMap().getHardWall().get(i).getY() + 20, 90, 83);
            if (objects.getPlayers().get(0).getBounds().intersects(rectangle))
            {
                if (objects.getPlayers().get(0).getY() > objects.getMap().getHardWall().get(i).getY())
                {
                    return true;
                }
            }
        }
        for (int i = 0; i < objects.getTurrets().size(); i++)
        {
            if (objects.getPlayers().get(0).getBounds().intersects(objects.getTurrets().get(i).getBounds()))
            {
                if (objects.getPlayers().get(0).getY() > objects.getTurrets().get(i).getY())
                {
                    return true;
                }
            }
        }
        for (int i = 0; i < objects.getTanks().size(); i++)
        {
            if (objects.getPlayers().get(0).getBounds().intersects(objects.getTanks().get(i).getBounds()))
            {
                if (objects.getPlayers().get(0).getY() > objects.getTanks().get(i).getY())
                {
                    return true;
                }
            }
        }
        for (int i = 0; i < objects.getMap().getSoftWall().size(); i++)
        {
            Rectangle rectangle = new Rectangle((int) objects.getMap().getSoftWall().get(i).getX(), (int) objects.getMap().getSoftWall().get(i).getY() + 20, 90, 83);
            if (objects.getPlayers().get(0).getBounds().intersects(rectangle))
            {
                if (objects.getPlayers().get(0).getY() > objects.getMap().getSoftWall().get(i).getY())
                {
                    return true;
                }
            }
        }
        for (int i = 0; i < objects.getMap().getTeazel().size(); i++)
        {
            Rectangle rectangle = new Rectangle((int) objects.getMap().getTeazel().get(i).getX(), (int) objects.getMap().getTeazel().get(i).getY() + 20, 90, 83);
            if (objects.getPlayers().get(0).getBounds().intersects(rectangle))
            {
                if (objects.getPlayers().get(0).getY() > objects.getMap().getTeazel().get(i).getY())
                {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * check the down collision of the
     *
     * @param objects
     * @return
     */
    public static boolean checkPlayerCollisionDown(Objects objects)
    {
        for (int i = 0; i < objects.getMap().getHardWall().size(); i++)
        {
            Rectangle rectangle = new Rectangle((int) objects.getMap().getHardWall().get(i).getX() + 10, (int) objects.getMap().getHardWall().get(i).getY(), 80, 83);
            if (objects.getPlayers().get(0).getBounds().intersects(rectangle))
            {
                if (objects.getPlayers().get(0).getY() < objects.getMap().getHardWall().get(i).getY())
                {
                    return true;
                }
            }
        }
        for (int i = 0; i < objects.getTurrets().size(); i++)
        {
            if (objects.getPlayers().get(0).getBounds().intersects(objects.getTurrets().get(i).getBounds()))
            {
                if (objects.getPlayers().get(0).getY() < objects.getTurrets().get(i).getY())
                {
                    return true;
                }
            }
        }
        for (int i = 0; i < objects.getTanks().size(); i++)
        {
            if (objects.getPlayers().get(0).getBounds().intersects(objects.getTanks().get(i).getBounds()))
            {
                if (objects.getPlayers().get(0).getY() < objects.getTanks().get(i).getY())
                {
                    return true;
                }
            }
        }
        for (int i = 0; i < objects.getMap().getSoftWall().size(); i++)
        {
            Rectangle rectangle = new Rectangle((int) objects.getMap().getSoftWall().get(i).getX(), (int) objects.getMap().getSoftWall().get(i).getY() + 20, 90, 83);
            if (objects.getPlayers().get(0).getBounds().intersects(rectangle))
            {
                if (objects.getPlayers().get(0).getY() < objects.getMap().getSoftWall().get(i).getY())
                {
                    return true;
                }
            }
        }
        for (int i = 0; i < objects.getMap().getTeazel().size(); i++)
        {
            Rectangle rectangle = new Rectangle((int) objects.getMap().getTeazel().get(i).getX(), (int) objects.getMap().getTeazel().get(i).getY() + 20, 90, 83);
            if (objects.getPlayers().get(0).getBounds().intersects(rectangle))
            {
                if (objects.getPlayers().get(0).getY() < objects.getMap().getTeazel().get(i).getY())
                {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * check the right collision of the
     *
     * @param objects
     * @return
     */
    public static boolean checkPlayerCollisionRight(Objects objects)
    {
        for (int i = 0; i < objects.getMap().getHardWall().size(); i++)
        {
            Rectangle rectangle = new Rectangle((int) objects.getMap().getHardWall().get(i).getX(), (int) objects.getMap().getHardWall().get(i).getY() + 20, 83, 80);
            if (objects.getPlayers().get(0).getBounds().intersects(rectangle))
            {
                if (objects.getPlayers().get(0).getX() < objects.getMap().getHardWall().get(i).getX())
                {
                    return true;
                }
            }
        }
        for (int i = 0; i < objects.getTurrets().size(); i++)
        {
            if (objects.getPlayers().get(0).getBounds().intersects(objects.getTurrets().get(i).getBounds()))
            {
                if (objects.getPlayers().get(0).getX() < objects.getTurrets().get(i).getX())
                {
                    return true;
                }
            }
        }
        for (int i = 0; i < objects.getTanks().size(); i++)
        {
            if (objects.getPlayers().get(0).getBounds().intersects(objects.getTanks().get(i).getBounds()))
            {
                if (objects.getPlayers().get(0).getX() < objects.getTanks().get(i).getX())
                {
                    return true;
                }
            }
        }
        for (int i = 0; i < objects.getMap().getSoftWall().size(); i++)
        {
            Rectangle rectangle = new Rectangle((int) objects.getMap().getSoftWall().get(i).getX(), (int) objects.getMap().getSoftWall().get(i).getY() + 20, 90, 83);
            if (objects.getPlayers().get(0).getBounds().intersects(rectangle))
            {
                if (objects.getPlayers().get(0).getX() < objects.getMap().getSoftWall().get(i).getX())
                {
                    return true;
                }
            }
        }
        for (int i = 0; i < objects.getMap().getTeazel().size(); i++)
        {
            Rectangle rectangle = new Rectangle((int) objects.getMap().getTeazel().get(i).getX(), (int) objects.getMap().getTeazel().get(i).getY() + 20, 90, 83);
            if (objects.getPlayers().get(0).getBounds().intersects(rectangle))
            {
                if (objects.getPlayers().get(0).getX() < objects.getMap().getTeazel().get(i).getX())
                {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * check the left collision of the
     *
     * @param objects
     * @return
     */
    public static boolean checkPlayerCollisionLeft(Objects objects)
    {
        for (int i = 0; i < objects.getMap().getHardWall().size(); i++)
        {
            Rectangle rectangle = new Rectangle((int) objects.getMap().getHardWall().get(i).getX() + 20, (int) objects.getMap().getHardWall().get(i).getY() + 20, 83, 80);
            if (objects.getPlayers().get(0).getBounds().intersects(rectangle))
            {
                if (objects.getPlayers().get(0).getX() > objects.getMap().getHardWall().get(i).getX())
                {
                    return true;
                }
            }
        }
        for (int i = 0; i < objects.getTurrets().size(); i++)
        {
            if (objects.getPlayers().get(0).getBounds().intersects(objects.getTurrets().get(i).getBounds()))
            {
                if (objects.getPlayers().get(0).getX() > objects.getTurrets().get(i).getX())
                {
                    return true;
                }
            }
        }
        for (int i = 0; i < objects.getTanks().size(); i++)
        {
            if (objects.getPlayers().get(0).getBounds().intersects(objects.getTanks().get(i).getBounds()))
            {
                if (objects.getPlayers().get(0).getX() > objects.getTanks().get(i).getX())
                {
                    return true;
                }
            }
        }
        for (int i = 0; i < objects.getMap().getSoftWall().size(); i++)
        {
            Rectangle rectangle = new Rectangle((int) objects.getMap().getSoftWall().get(i).getX(), (int) objects.getMap().getSoftWall().get(i).getY() + 20, 90, 83);
            if (objects.getPlayers().get(0).getBounds().intersects(rectangle))
            {
                if (objects.getPlayers().get(0).getX() > objects.getMap().getSoftWall().get(i).getX())
                {
                    return true;
                }
            }
        }
        for (int i = 0; i < objects.getMap().getTeazel().size(); i++)
        {
            Rectangle rectangle = new Rectangle((int) objects.getMap().getTeazel().get(i).getX(), (int) objects.getMap().getTeazel().get(i).getY() + 20, 90, 83);
            if (objects.getPlayers().get(0).getBounds().intersects(rectangle))
            {
                if (objects.getPlayers().get(0).getX() > objects.getMap().getTeazel().get(i).getX())
                {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * check to avoid cross the map bounds
     *
     * @param object
     */
    public static void checkMapBounds(GameObject object)
    {
        if (object.getX() > 3400)
        {
            object.setX(3400);
        }
        else if (object.getX() < 0)
        {
            object.setX(0);
        }

        if (object.getY() > 6400)
        {
            object.setY(6400);
        }
        else if (object.getY() < 0)
        {
            object.setY(0);
        }
    }

    /**
     * check the collision of the bullets
     *
     * @param objects
     */
    public static void checkBulletsCollision(Objects objects)
    {
        //hardWalls
        for (int i = 0; i < objects.getBullets().size(); i++)
        {
            //collision with walls
            for (int j = 0; j < objects.getMap().getHardWall().size(); j++)
            {
                if (objects.getBullets().get(i).getBounds().intersects(objects.getMap().getHardWall().get(j).getBounds()))
                {
                    Sound sound = new Sound(Utility.bulletHitHardWall, false);
                    sound.playSound();
                    objects.getBullets().remove(i);
                    break;
                }
            }
        }
        //collision with soft walls
        for (int i = 0; i < objects.getBullets().size(); i++)
        {
            //collision with walls
            for (int j = 0; j < objects.getMap().getSoftWall().size(); j++)
            {
                if (objects.getBullets().get(i).getBounds().intersects(objects.getMap().getSoftWall().get(j).getBounds()))
                {
                    if (objects.getBullets().get(i).getShooter().equals(ObjectId.PlayerShooter))
                    {
                        damageSoftWall(objects, objects.getMap().getSoftWall().get(j), objects.getBullets().get(i).getDamage());
                        objects.getBullets().remove(i);
                    }
                    else if (objects.getBullets().get(i).getShooter().equals(ObjectId.AIShooter))
                    {
                        objects.getBullets().remove(i);
                    }
                    break;
                }
            }
        }
        //collision with buried robots
        for (int i = 0; i < objects.getBullets().size(); i++)
        {
            if (objects.getBullets().get(i).getShooter().equals(ObjectId.PlayerShooter))
            { //disable friendly fire for AI
                for (int j = 0; j < objects.getRobots().size(); j++)
                {
                    if (objects.getBullets().get(i).getBounds().intersects(objects.getRobots().get(j).getBounds()))
                    {
                        damageRobot(objects, objects.getRobots().get(j), objects.getBullets().get(i).getDamage());
                        objects.getBullets().remove(i);
                        break;
                    }
                }
            }
        }

        //collision with AI tanks
        for (int i = 0; i < objects.getBullets().size(); i++)
        {
            for (int j = 0; j < objects.getTanks().size(); j++)
            {
                if (objects.getBullets().get(i).getShooter().equals(ObjectId.PlayerShooter))
                { //disable friendly fire for AI
                    if (objects.getBullets().get(i).getBounds().intersects(objects.getTanks().get(j).getBounds()))
                    {
                        damageAITank(objects, objects.getTanks().get(j), objects.getBullets().get(i).getDamage());
                        objects.getBullets().remove(i);
                        break;
                    }
                }
            }
        }

        //collision with Player Tank
        for (int i = 0; i < objects.getBullets().size(); i++)
        {
            for (int j = 0; j < objects.getPlayers().size(); j++)
            {
                if (objects.getBullets().get(i).getShooter().equals(ObjectId.AIShooter))
                {
                    if (objects.getBullets().get(i).getBounds().intersects(objects.getPlayers().get(j).getBounds()))
                    {
                        damagePlayerTank(objects, objects.getPlayers().get(j), objects.getBullets().get(i).getDamage());
                        objects.getBullets().remove(i);
                        break;
                    }
                }
            }
        }
        //collision with turret
        for (int i = 0; i < objects.getBullets().size(); i++)
        {
            for (int j = 0; j < objects.getTurrets().size(); j++)
            {
                if (objects.getBullets().get(i).getShooter().equals(ObjectId.PlayerShooter))
                {
                    if (objects.getBullets().get(i).getBounds().intersects(objects.getTurrets().get(j).getBounds()))
                    {
                        damageTurret(objects, objects.getTurrets().get(j), objects.getBullets().get(i).getDamage());
                        objects.getBullets().remove(i);
                        break;
                    }
                }
            }
        }
    }

    /**
     * check the collision of the player with buriedRobot
     *
     * @param objects
     */
    public static void checkBuriedRobotsCollisionWithPlayer(Objects objects)
    {
        for (int i = 0; i < objects.getRobots().size(); i++)
        {
            if (objects.getRobots().get(i).isActivated())
            { //just done for activated robots
                if ((Math.abs(objects.getRobots().get(i).getX() - objects.getRobots().get(i).getTarget().getX()) < 70) &&
                        (Math.abs(objects.getRobots().get(i).getY() - objects.getRobots().get(i).getTarget().getY()) < 70))
                {
                    damagePlayerTank(objects, objects.getRobots().get(i).getTarget(), 100); // damage of the robot(hits player)
                    damageRobot(objects, objects.getRobots().get(i), objects.getRobots().get(i).getHealth()); // makes robot die
                }
            }
        }
    }

    /**
     * check the health of the robots and play their sound
     *
     * @param objects
     * @param robot
     * @param damage
     */
    private static void damageRobot(Objects objects, BuriedRobot robot, int damage)
    {
        robot.setHealth(robot.getHealth() - damage);
        if (robot.getHealth() <= 0)
        { // if robot health get down to zero it gets destroyed and must be removed from objects
            Sound sound = new Sound(Utility.robotExplosion, false);
            sound.playSound();
            objects.getDeletedItems().add(robot);
            objects.getRobots().remove(robot);
        }
    }

    /**
     * check the health of the aiTanks and play their sound and release their upgrade
     *
     * @param objects
     * @param tank
     * @param damage
     */
    private static void damageAITank(Objects objects, AITank tank, int damage)
    {
        tank.setHealth(tank.getHealth() - damage);
        if (tank.getHealth() <= 0)
        {
            Sound sound = new Sound(Utility.explosion, false);
            sound.playSound();
            if (tank.getUpgrade() != null)
            {
                tank.getUpgrade().setX(tank.getX());
                tank.getUpgrade().setY(tank.getY());
                tank.releaseUpgrade();
            }
            objects.getDeletedItems().add(tank);
            objects.getTanks().remove(tank);
        }
    }

    /**
     * check the health of the player
     *
     * @param objects
     * @param tank
     * @param damage
     */
    private static void damagePlayerTank(Objects objects, Tank tank, int damage)
    {
        tank.setHealth(tank.getHealth() - damage);
        if (tank.getHealth() <= 0)
        {
            SharedData.getData().playerDied = true;
            SharedData.getData().playerToRemove = tank;
            if (SharedData.getData().gameType.equals(ObjectId.TwoPlayer) && tank == objects.getPlayers().get(0))
            {
                SharedData.getData().ServerLost = true;
            }
        }
        if (objects.getPlayers().size() == 2 && tank == objects.getPlayers().get(1))
        {
            SharedData.getData().clientTakenDamage = damage;
            if (tank.getHealth() <= 0)
            {
                SharedData.getData().clientLost = true;
            }
        }

    }

    /**
     * check the health of the turrets and play their sound and release their upgrade
     *
     * @param objects
     * @param turret
     * @param damage
     */
    private static void damageTurret(Objects objects, Turret turret, int damage)
    {
        turret.setHealth(turret.getHealth() - damage);
        if (turret.getHealth() <= 0)
        {
            objects.getTurrets().remove(turret);
        }
        objects.getDeletedItems().add(turret);
    }

    /**
     * check the health of the softWalls to change their textures
     *
     * @param objects
     * @param wall
     * @param damage
     */
    private static void damageSoftWall(Objects objects, SoftWall wall, int damage)
    {
        wall.setHealth(wall.getHealth() - damage);
        if (wall.getHealth() <= 0)
        {
            Sound sound = new Sound(Utility.softWallDestruction, false);
            sound.playSound();
            objects.getMap().getSoftWall().remove(wall);
        }
    }
}
