/*** In The Name of Allah ***/
package GameEngine;

import game.Utils.SharedData;
import game.Utils.Utility;
import game.elements.*;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import javax.swing.*;

/**
 * The window on which the rendering is performed.
 * This structure uses the modern BufferStrategy approach for
 * double-buffering; actually, it performs triple-buffering!
 * For more information on BufferStrategy check out:
 * http://docs.oracle.com/javase/tutorial/extra/fullscreen/bufferstrategy.html
 * http://docs.oracle.com/javase/8/docs/api/java/awt/image/BufferStrategy.html
 *
 * @author Seyed Mohammad Ghaffarian - Ali Nazari - Amirhossein Hediehloo
 */
public class GameFrame extends JFrame
{

    public static final int GAME_HEIGHT = 1024;                  // custom game resolution
    public static final int GAME_WIDTH = 16 * GAME_HEIGHT / 9;  // wide aspect ratio


    private BufferStrategy bufferStrategy;

    public GameFrame(String title)
    {
        super(title);

        Dimension dimension = new Dimension();
        dimension = Toolkit.getDefaultToolkit().getScreenSize();
        if (dimension.width >= 3840) //window mode
        {
            this.setUndecorated(true);
            // Size of the frame.
            this.setSize(GAME_WIDTH, GAME_HEIGHT);
            // Puts frame to center of the screen.
            this.setLocationRelativeTo(null);
            // So that frame cannot be resizable by the user.
            this.setResizable(false);
        }
        else //full screen
        {
            // Disables decorations for this frame.
            this.setUndecorated(true);
            // Puts the frame to full screen.
            this.setExtendedState(this.MAXIMIZED_BOTH);
        }
        // Initialize the JFrame ...
        //
        setCursor(Toolkit.getDefaultToolkit().createCustomCursor(Utility.cursor, new Point(20, 14), "custom cursor"));
    }

    /**
     * This must be called once after the JFrame is shown:
     * frame.setVisible(true);
     * and before any rendering is started.
     */
    public void initBufferStrategy()
    {
        // Triple-buffering
        createBufferStrategy(3);
        bufferStrategy = getBufferStrategy();
    }


    /**
     * Game rendering with triple-buffering using BufferStrategy.
     */
    public void render(GameState state)
    {
        // Get a new graphics context to render the current frame
        Graphics2D graphics = (Graphics2D) bufferStrategy.getDrawGraphics();
        try
        {
            graphics.translate(-state.camera.getX(), -state.camera.getY());
            // Do the rendering
            doRendering(graphics, state);

            graphics.translate(state.camera.getX(), state.camera.getY());

            drawIndependents(graphics, state);
        }
        finally
        {
            // Dispose the graphics, because it is no more needed
            graphics.dispose();
        }
        // Display the buffer
        bufferStrategy.show();
        // Tell the system to do the drawing NOW;
        // otherwise it can take a few extra ms and will feel jerky!
        Toolkit.getDefaultToolkit().sync();
    }

    /**
     * Rendering all game elements based on the game state.
     */
    private void doRendering(Graphics2D g2d, GameState state)
    {
        AffineTransform gameTransform = g2d.getTransform();

        runAnimations();
        drawSoil(state, g2d);
        drawHardWalls(state, g2d);
        drawSoftWalls(state, g2d);
        drawTeazel(state, g2d);
        drawEnd(state, g2d);
        drawBullets(state, g2d, gameTransform);
        drawPlayers(state, g2d, gameTransform);
        drawAITanks(state, g2d, gameTransform);
        drawTurrets(state, g2d, gameTransform);
        drawBuriedRobots(state, g2d, gameTransform);
        drawUpgrades(state, g2d);
        drawPlants(state, g2d);
        drawDeletedItems(state, g2d);
    }

    /**
     * draw animation when an object gets destroyed.
     *
     * @param state GameState object
     * @param g2d   Graphics2D
     */
    public void drawDeletedItems(GameState state, Graphics2D g2d)
    {
        for (int i = 0; i < state.objects.getDeletedItems().size(); i++)
        {
            g2d.drawImage(Utility.explosionGif, (int) state.objects.getDeletedItems().get(i).getX(), (int) state.objects.getDeletedItems().get(i).getY(), null);
            state.objects.getDeletedItems().remove(i);

        }
    }

    /**
     * draw the end tile for the ending of the game
     *
     * @param state GameState object
     * @param g2d   Graphics2D
     */
    private void drawEnd(GameState state, Graphics2D g2d)
    {
        g2d.drawImage(Utility.end, (int) state.objects.getMap().getEnd().getX(), (int) state.objects.getMap().getEnd().getY(), null);
    }

    /**
     * draw the background. it covers all area of the map.
     *
     * @param state GameState object
     * @param g2d   Graphics2D
     */
    private void drawSoil(GameState state, Graphics2D g2d)
    {
        for (int i = 0; i < state.objects.getMap().getSoil().size(); i++)
        {
            g2d.drawImage(Utility.soil, (int) state.objects.getMap().getSoil().get(i).getX(), (int) state.objects.getMap().getSoil().get(i).getY(), null);
        }
    }

    /**
     * draw the breakable walls that can get destroyed.
     *
     * @param state GameState object
     * @param g2d   Graphics2D
     */
    private void drawSoftWalls(GameState state, Graphics2D g2d)
    {
        for (int i = 0; i < state.objects.getMap().getSoftWall().size(); i++)
        {
            if (state.objects.getMap().getSoftWall().get(i).getHealth() > 300)
            {
                g2d.drawImage(Utility.softWall01, (int) state.objects.getMap().getSoftWall().get(i).getX(), (int) state.objects.getMap().getSoftWall().get(i).getY(), null);
            }
            else if (state.objects.getMap().getSoftWall().get(i).getHealth() > 200)
            {
                g2d.drawImage(Utility.softWall02, (int) state.objects.getMap().getSoftWall().get(i).getX(), (int) state.objects.getMap().getSoftWall().get(i).getY(), null);
            }
            else if (state.objects.getMap().getSoftWall().get(i).getHealth() > 100)
            {
                g2d.drawImage(Utility.softWall03, (int) state.objects.getMap().getSoftWall().get(i).getX(), (int) state.objects.getMap().getSoftWall().get(i).getY(), null);
            }
            else if (state.objects.getMap().getSoftWall().get(i).getHealth() > 0)
            {
                g2d.drawImage(Utility.softWall04, (int) state.objects.getMap().getSoftWall().get(i).getX(), (int) state.objects.getMap().getSoftWall().get(i).getY(), null);
            }
        }
    }

    /**
     * draw teazel thing over the map. they r not passable.
     * * @param state GameState object
     *
     * @param g2d Graphics2D
     */
    private void drawTeazel(GameState state, Graphics2D g2d)
    {
        for (int i = 0; i < state.objects.getMap().getTeazel().size(); i++)
        {
            g2d.drawImage(Utility.teazel, (int) state.objects.getMap().getTeazel().get(i).getX(), (int) state.objects.getMap().getTeazel().get(i).getY(), null);
        }
    }

    /**
     * drawing plant over the map
     *
     * @param state GameState object
     * @param g2d   Graphics2D
     */
    private void drawPlants(GameState state, Graphics2D g2d)
    {
        for (int i = 0; i < state.objects.getMap().getPlant().size(); i++)
        {
            g2d.drawImage(Utility.plant, (int) state.objects.getMap().getPlant().get(i).getX(), (int) state.objects.getMap().getPlant().get(i).getY(), null);
        }
    }

    /**
     * draw hard walls over the map
     *
     * @param state
     * @param g2d
     */
    private void drawHardWalls(GameState state, Graphics2D g2d)
    {
        for (int i = 0; i < state.objects.getMap().getHardWall().size(); i++)
        {
            g2d.drawImage(Utility.hardWall, (int) state.objects.getMap().getHardWall().get(i).getX(), (int) state.objects.getMap().getHardWall().get(i).getY(), null);
        }
    }

    /**
     * draw all bullet (no exception for AI or player bullets)
     * * @param state GameState object
     *
     * @param g2d Graphics2D
     */
    private void drawBullets(GameState state, Graphics2D g2d, AffineTransform gameTransform)
    {
        //draw bullets
        ArrayList<Bullet> bullets = state.objects.getBullets();
        for (int i = 0; i < bullets.size(); i++)
        {
            AffineTransform bulletTransform = g2d.getTransform();
            double bulletAngle;
            if (bullets.get(i).isThrown())
            {
                bulletAngle = bullets.get(i).getThrownAngle();
            }
            else
            {
                bulletAngle = bullets.get(i).getShootDirectionAngle();
                if (bullets.get(i).getTargetX() < bullets.get(i).getX())
                {
                    bulletAngle += Math.PI;
                }
                bullets.get(i).setThrownAngle(bulletAngle);
                bullets.get(i).setThrown(true);
            }
            bulletTransform.rotate(bulletAngle, (int) bullets.get(i).getX() + 50, (int) bullets.get(i).getY() + 50);
            g2d.setTransform(bulletTransform);
            if (bullets.get(i).getId().equals(ObjectId.HeavyBullet))
            {
                g2d.drawImage(Utility.heavyBullet, (int) bullets.get(i).getX() + 52, (int) bullets.get(i).getY() + 50, null);
            }
            else
            {
                g2d.drawImage(Utility.lightBullet, (int) bullets.get(i).getX() + 52, (int) bullets.get(i).getY() + 50, null);
            }
            g2d.setTransform(gameTransform);
        }
        //
    }

    /**
     * draws the thing that moving camera must not affect them.
     *
     * @param state GameState object
     * @param g2d   Graphics2D
     */
    private void drawIndependents(Graphics2D g2d, GameState state)
    {
        //draw number of bullets
        g2d.drawImage(Utility.numberOfHeavyBullet, 10, 10, null);
        String heavyBullets;
        int numberOfHeavyBullets = state.objects.getPlayers().get(0).getMissileGun().getAmmo();
        if (numberOfHeavyBullets / 10 == 0)
        {
            heavyBullets = "0" + numberOfHeavyBullets;
        }
        else
        {
            heavyBullets = String.valueOf(numberOfHeavyBullets);
        }
        g2d.setFont(new Font("Titillium Web", Font.BOLD, 20));
        g2d.setColor(Color.green);
        g2d.drawString(heavyBullets, 40, 60);

        g2d.drawImage(Utility.numberOfLightBullet, 10, 85, null);
        String lightBullets;
        int numberOfLightBullets = state.objects.getPlayers().get(0).getMachineGun().getAmmo();
        if (numberOfLightBullets / 10 == 0)
        {
            lightBullets = "0" + numberOfLightBullets;
        }
        else
        {
            lightBullets = String.valueOf(numberOfLightBullets);
        }
        g2d.setFont(new Font("Titillium Web", Font.BOLD, 20));
        g2d.setColor(Color.green);
        g2d.drawString(lightBullets, 40, 135);
        //

        //draw save, exit, resume, pause
        g2d.drawImage(Utility.exit, 1750, 10, null);
        g2d.drawImage(Utility.save, 1750, 85, null);
        g2d.drawImage(Utility.pause, 1750, 160, null);
        g2d.drawImage(Utility.resume, 1750, 230, null);

        //draw health
        if (state.objects.getPlayers().get(0).getHealth() >= 400)
        {
            g2d.drawImage(Utility.health4, 820, 10, null);
        }
        else if (state.objects.getPlayers().get(0).getHealth() >= 300)
        {
            g2d.drawImage(Utility.health3, 820, 10, null);
        }
        else if (state.objects.getPlayers().get(0).getHealth() >= 200)
        {
            g2d.drawImage(Utility.health2, 820, 10, null);
        }
        else
        {
            g2d.drawImage(Utility.health1, 820, 10, null);
        }
    }

    private void runAnimations()
    {
        Utility.tankAnimation.runAnimation();
        Utility.buriedRobotAnimation.runAnimation();
    }

    /**
     * draw buried robots if they get activated.
     *
     * @param state         GameState object
     * @param g2d           Graphics2D
     * @param gameTransform
     */
    private void drawBuriedRobots(GameState state, Graphics2D g2d, AffineTransform gameTransform)
    {
        //draw robots
        for (int i = 0; i < state.objects.getRobots().size(); i++)
        {
            if (state.objects.getRobots().get(i).isActivated())
            {
                AffineTransform gunTrans = g2d.getTransform();
                gunTrans.rotate(state.objects.getRobots().get(i).getAngle(),
                        state.objects.getRobots().get(i).getX() + 50,
                        state.objects.getRobots().get(i).getY() + 50);

                g2d.setTransform(gunTrans);
                Utility.buriedRobotAnimation.drawAnimation(g2d, (int) state.objects.getRobots().get(i).getX(), (int) state.objects.getRobots().get(i).getY(), 0);
                g2d.setTransform(gameTransform);
            }
        }
        //
    }

    /**
     * draw AI tanks over the map.
     *
     * @param state         GameState object
     * @param g2d           Graphics2D
     * @param gameTransform
     */
    private void drawAITanks(GameState state, Graphics2D g2d, AffineTransform gameTransform)
    {
        //draw tanks
        ArrayList<AITank> tanks = state.objects.getTanks();
        for (int i = 0; i < tanks.size(); i++)
        {
            int centerX = (int) state.objects.getTanks().get(i).getX() + state.objects.getTanks().get(i).TANK_WIDTH / 2; //this is the X center of the player
            int centerY = (int) state.objects.getTanks().get(i).getY() + state.objects.getTanks().get(i).TANK_HEIGHT / 2; //this is the Y center of the player

            AffineTransform tankTrans = g2d.getTransform();
            tankTrans.rotate(state.objects.getTanks().get(i).getTankAngle(), centerX, centerY);
            g2d.setTransform(tankTrans);

            g2d.drawImage(Utility.AITank,
                    (int) state.objects.getTanks().get(i).getX(), //this is the X upper left corner of the tile
                    (int) state.objects.getTanks().get(i).getY(), //this is the Y upper left corner of the tile
                    null);
            g2d.setTransform(gameTransform);
        }
        //
    }

    /**
     * draw turrets on the map
     *
     * @param state         GameState object
     * @param g2d           Graphics2D
     * @param gameTransform
     */
    private void drawTurrets(GameState state, Graphics2D g2d, AffineTransform gameTransform)
    {
        //draw turrets
        ArrayList<Turret> turrets = state.objects.getTurrets();
        for (int i = 0; i < turrets.size(); i++)
        {
            g2d.drawImage(Utility.turretBody, null, (int) turrets.get(i).getX(), (int) turrets.get(i).getY());

            AffineTransform gunTrans = g2d.getTransform();
            gunTrans.rotate(state.objects.getTurrets().get(i).getGunAngle(),
                    state.objects.getTurrets().get(i).getX() + 50,
                    state.objects.getTurrets().get(i).getY() + 50);

            g2d.setTransform(gunTrans);
            if (state.objects.getTurrets().get(i).getGun().getId().equals(ObjectId.MissileGun))
            {
                g2d.drawImage(Utility.turretGun01, (int) state.objects.getTurrets().get(i).getX() + 25, (int) state.objects.getTurrets().get(i).getY(), null);
            }
            else if (state.objects.getTurrets().get(i).getGun().getId().equals(ObjectId.MachineGun))
            {
                g2d.drawImage(Utility.turretGun02, (int) state.objects.getTurrets().get(i).getX() + 25, (int) state.objects.getTurrets().get(i).getY(), null);
            }
            g2d.setTransform(gameTransform);
        }
        //
    }

    /**
     * draw player tanks. in single player its just 1 tank and in Coop its 2
     *
     * @param state         GameState object
     * @param g2d           Graphics2D
     * @param gameTransform
     */
    private void drawPlayers(GameState state, Graphics2D g2d, AffineTransform gameTransform)
    {
        //draw player tank
        for (int i = 0; i < state.objects.getPlayers().size(); i++)
        {
            int centerX = (int) state.objects.getPlayers().get(i).getX() + state.objects.getPlayers().get(i).TANK_WIDTH / 2; //this is the X center of the player
            int centerY = (int) state.objects.getPlayers().get(i).getY() + state.objects.getPlayers().get(i).TANK_HEIGHT / 2; //this is the Y center of the player

            AffineTransform tankTransform = g2d.getTransform();
            tankTransform.rotate(state.objects.getPlayers().get(i).getTankAngle(), centerX, centerY);
            g2d.setTransform(tankTransform);

            if (!state.isKeyDOWN() & !state.isKeyLEFT() & !state.isKeyRIGHT() & !state.isKeyUP())
            {
                g2d.drawImage(Utility.tank02,
                        (int) state.objects.getPlayers().get(i).getX(), //this is the X upper left corner of the tile
                        (int) state.objects.getPlayers().get(i).getY(), //this is the Y upper left corner of the tile
                        null);
            }
            else
            {
                Utility.tankAnimation.drawAnimation(g2d, (int) state.objects.getPlayers().get(i).getX(), (int) state.objects.getPlayers().get(i).getY(), 0);
            }
            if (state.objects.getPlayers().get(0).getHasShield())
            {
                g2d.setColor(Color.BLACK);
                g2d.drawRect((int) state.objects.getPlayers().get(0).getX(), (int) state.objects.getPlayers().get(0).getY(), 100, 100);
            }
            g2d.setTransform(gameTransform);
            //draw the Gun of the Player Tank and handle its rotation
            if (state.objects.getPlayers().get(i).getSelectedGun().getId().equals(ObjectId.MissileGun))
            {
                AffineTransform gunTransform = g2d.getTransform();
                //we know that atan2 return radian :)
                if (i == 0)
                {
                    double playerGunAngle = Math.atan2((state.getMouseY() - centerY), (state.getMouseX() - centerX));
                    gunTransform.rotate(playerGunAngle, centerX, centerY);
                    state.objects.getPlayers().get(0).setGunAngle(playerGunAngle); //set angle in tank info
                }
                else if (SharedData.getData().gameType.equals(ObjectId.TwoPlayer) && i == 1)
                {
                    gunTransform.rotate(state.objects.getPlayers().get(1).getGunAngle(), centerX, centerY);
                }
                g2d.setTransform(gunTransform);
                g2d.drawImage(Utility.gun01,
                        (int) state.objects.getPlayers().get(i).getX() + 18,
                        (int) state.objects.getPlayers().get(i).getY() + 5,
                        null);
            }
            else if (state.objects.getPlayers().get(i).getSelectedGun().getId().equals(ObjectId.MachineGun))
            {
                AffineTransform gunTransform = g2d.getTransform();
                if (i == 0)
                {
                    double playerGunAngle = Math.atan2((state.getMouseY() - centerY), (state.getMouseX() - centerX));
                    gunTransform.rotate(playerGunAngle, centerX, centerY);
                    state.objects.getPlayers().get(0).setGunAngle(playerGunAngle); //set angle in tank info
                }
                else if (i == 1)
                {
                    gunTransform.rotate(state.objects.getPlayers().get(1).getGunAngle(), centerX, centerY);
                }
                g2d.setTransform(gunTransform);
                g2d.drawImage(Utility.gun02,
                        (int) state.objects.getPlayers().get(i).getX() + 18,
                        (int) state.objects.getPlayers().get(i).getY(),
                        null);
            }
            g2d.setTransform(gameTransform);
        }
        //
    }

    /**
     * draw upgrades over the map
     *
     * @param state GameState object
     * @param g2d   Graphics2D
     */
    private void drawUpgrades(GameState state, Graphics2D g2d)
    {
        for (int i = 0; i < state.objects.getUpgrades().size(); i++)
        {
            if (state.objects.getUpgrades().get(i).getActivation())
            {
                if (state.objects.getUpgrades().get(i).getId().equals(ObjectId.MissileGunUpgrade))
                {
                    g2d.drawImage(Utility.missileGunUpgrade, null, (int) state.objects.getUpgrades().get(i).getX(), (int) state.objects.getUpgrades().get(i).getY());
                }
                else if (state.objects.getUpgrades().get(i).getId().equals(ObjectId.MachineGunUpgrade))
                {
                    g2d.drawImage(Utility.machineGunUpgrade, null, (int) state.objects.getUpgrades().get(i).getX(), (int) state.objects.getUpgrades().get(i).getY());
                }
                else if (state.objects.getUpgrades().get(i).getId().equals(ObjectId.ShieldUpgrade))
                {
                    g2d.drawImage(Utility.shieldUpgrade, null, (int) state.objects.getUpgrades().get(i).getX(), (int) state.objects.getUpgrades().get(i).getY());
                }
                else if (state.objects.getUpgrades().get(i).getId().equals(ObjectId.DamageUpgrade))
                {
                    g2d.drawImage(Utility.damageUpgrade, null, (int) state.objects.getUpgrades().get(i).getX(), (int) state.objects.getUpgrades().get(i).getY());
                }
                else if (state.objects.getUpgrades().get(i).getId().equals(ObjectId.HealthUpgrade))
                {
                    g2d.drawImage(Utility.healthUpgrade, null, (int) state.objects.getUpgrades().get(i).getX(), (int) state.objects.getUpgrades().get(i).getY());
                }
            }
        }
    }
}
