package game.elements;

import game.Utils.SharedData;
import game.Utils.Utility;
import GameEngine.Physics;

import java.util.ArrayList;

/**
 * Managing tank movement and shooting. it ticks each time when updating game state.
 * it cause tank to find a way to approach player and hit that.
 */
public class AITankHandler
{

    //fields
    private double range;
    private Objects objects;
    private ArrayList<Tank> players;

    //constructor
    public AITankHandler(Objects objects)
    {
        range = 500;
        this.players = objects.getPlayers();
        this.objects = objects;
    }

    //methods

    /**
     * ticks tanks. means checking that if they must be invoked , getting closer to player , shoot player.
     * it also checks if a tank must be disabled.
     */
    public void tick()
    {
        for (int i = 0; i < objects.getTanks().size(); i++)
        {
            for (int j = 0; j < objects.getTanks().size(); j++)
            {
                objects.getTanks().get(j).setTarget(objects.getPlayers().get(0)); // player 0 as default target for all tanks
            }
            if (SharedData.getData().gameType.equals(ObjectId.TwoPlayer))
            {
                determineTarget(objects.getTanks().get(i));
            }
            checkToActivate(objects.getTanks().get(i));
            checkToDisable(objects.getTanks().get(i));
            if (objects.getTanks().get(i).isActivated())
            {
                setAngle(objects.getTanks().get(i));
                move(objects.getTanks().get(i), objects.getTanks().get(i).getTarget());
                fire(objects.getTanks().get(i), objects.getTanks().get(i).getTarget());
            }
        }

    }

    /**
     * if conditions are okay the tank must get closer to player. but it has a bound
     * that it can't get closer than that.
     *
     * @param tank   AI Tank
     * @param target player tank
     */
    private void move(AITank tank, Tank target)
    {
        //movement in X direction
        if (target.x > tank.x && (Math.abs(target.x - tank.x) > 205))
        {
            if (!Physics.aiTankCheckCollisionRight(objects, tank))
            {
                tank.setX(tank.getX() + tank.getVelX());
            }
        }
        else if (target.x > tank.x && (Math.abs(target.x - tank.x) < 195))
        {
            if (!Physics.aiTankCheckCollisionLeft(objects, tank))
            {
                tank.setX(tank.getX() - tank.getVelX());
            }
        }
        else if (target.x == tank.x)
        {

        }
        else if (target.x < tank.x && (Math.abs(target.x - tank.x)) > 205)
        {
            if (!Physics.aiTankCheckCollisionLeft(objects, tank))
            {
                tank.setX(tank.getX() - tank.getVelX());
            }
        }
        else if (target.x < tank.x && (Math.abs(target.x - tank.x) < 195))
        {
            if (!Physics.aiTankCheckCollisionRight(objects, tank))
            {
                tank.setX(tank.getX() + tank.getVelX());
            }
        }
        //movement in Y direction
        if (target.y > tank.y && (Math.abs(target.y - tank.y) > 205))
        {
            if (!Physics.aiTankCheckCollisionDown(objects, tank))
            {
                tank.setY(tank.getY() + tank.getVelY());
            }
        }
        else if (target.y > tank.y && (Math.abs(target.y - tank.y) < 195))
        {
            if (!Physics.aiTankCheckCollisionUp(objects, tank))
            {
                tank.setY(tank.getY() - tank.getVelY());
            }
        }
        else if (target.y < tank.y && (Math.abs(target.y - tank.y) > 205))
        {
            if (!Physics.aiTankCheckCollisionUp(objects, tank))
            {
                tank.setY(tank.getY() - tank.getVelY());
            }
        }
        else if (target.y < tank.y && (Math.abs(target.y - tank.y) < 195))
        {
            if (!Physics.aiTankCheckCollisionDown(objects, tank))
            {
                tank.setY(tank.getY() + tank.getVelY());
            }
        }
        else if (target.y == tank.y)
        {
        }
    }

    /**
     * if tank is closer than tank range , tank must be invoked
     *
     * @param aiTank AI tank to be check whether to be invoked or not
     */
    private void checkToActivate(AITank aiTank)
    {
        if (Utility.calculateDistance(aiTank, aiTank.getTarget()) < range)
        {
            aiTank.setActivated(true);
        }
    }

    /**
     * if tank is more far than tank range , tank must be disabled.
     *
     * @param aiTank AI tank to be check whether to be disabled or not
     */
    private void checkToDisable(AITank aiTank)
    {
        if (Utility.calculateDistance(aiTank, aiTank.getTarget()) > range)
        {
            aiTank.setActivated(false);
        }
    }

    /**
     * if more than 1 player is playing then turret will shoot the player which is closer to turret
     */
    public void determineTarget(AITank aiTank)
    {
        for (int i = 0; i < players.size(); i++)
        {
            double distance = Utility.calculateDistance(players.get(i), aiTank);
            if (distance <= Utility.calculateDistance(aiTank.getTarget(), players.get(i)))
            {
                aiTank.setTarget(players.get(i));
            }
        }
    }


    /**
     * if conditions are true, tank must shoot at player tank.
     *
     * @param tank   tank that must shoot to player
     * @param target player tank as the target
     */
    private void fire(AITank tank, Tank target)
    {
        if (tank.getSelectedGun().readyForShoot())
        {
            objects.addBullet(tank.getSelectedGun().shoot(tank.x + tank.TANK_WIDTH / 2, tank.y + tank.TANK_HEIGHT / 2, target.x + target.TANK_WIDTH / 2, target.y + target.TANK_HEIGHT / 2, ObjectId.AIShooter));
        }
    }

    /**
     * set an angle for tank to rotate in that direction.
     *
     * @param tank
     */
    private void setAngle(AITank tank)
    {
        double angle = Math.atan((tank.getTarget().getY() - tank.getY()) / (tank.getTarget().getX() - tank.getX()));
        if (tank.getTarget().x > tank.x)
        {
            angle += Math.PI;
        }
        tank.setTankAngle(angle);
    }
}
