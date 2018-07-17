package game.savingElements;

import game.Utils.SharedData;
import game.elements.Objects;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * initialize game objects if there is any save from before.
 * it reads save and init objects
 */
public class DataInitializer
{

    public DataInitializer(Objects objects)
    {
        readFile(objects);
    }

    /**
     * reads save file and calls a method to initialize game objects.
     *
     * @param objects objects of the game
     */
    private void readFile(Objects objects)
    {
        ObjectInputStream ois = null;
        FileInputStream streamIn = null;
        try
        {
            streamIn = new FileInputStream("res/save.ser");
        }
        catch (IOException e)
        {
            showNoSaveFoundMenu();
        }
        try
        {
            ois = new ObjectInputStream(streamIn);
            SavingData data = (SavingData) ois.readObject();
            initializeObjects(objects, data);
        }
        catch (Exception e)
        {
//            e.printStackTrace();
        }
        finally
        {
            if (ois != null)
            {
                try
                {
                    ois.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * initialize game objects using data that was read from save file
     *
     * @param objects objects of the game
     * @param data    data read from save file
     */
    private void initializeObjects(Objects objects, SavingData data)
    {
        objects.setPlayers(data.getPlayers());
        objects.setUpgrades(data.getUpgrades());
        objects.setTurrets(data.getTurrets());
        objects.setRobots(data.getRobots());
        objects.setBullets(data.getBullets());
        objects.setTanks(data.getTanks());
        objects.getMap().setSoftWall(data.getWalls());
    }

    public static int numOfMap(){
        ObjectInputStream ois = null;
        FileInputStream streamIn = null;
        SavingData data = null;
        try {
            streamIn = new FileInputStream("res/save.ser");

        } catch (IOException e) {
            showNoSaveFoundMenu();
            return 3;
//            e.printStackTrace();
        }
        try {
            ois = new ObjectInputStream(streamIn);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            data = (SavingData) ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return data.getMapId();
    }

    private static void showNoSaveFoundMenu(){
        JFrame frame = new JFrame("NO SAVE FOUND");
        frame.setSize(600, 200);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setUndecorated(true);
        frame.setResizable(false);

        JPanel mainPanel = new JPanel(new GridLayout(3, 1, 5, 5));
        mainPanel.setBackground(Color.BLACK);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 70, 20, 20));
        mainPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.WHITE, Color.WHITE.brighter(), Color.WHITE, Color.WHITE.darker()));
        frame.setContentPane(mainPanel);

        JLabel label1 = new JLabel("There is no saved game to continue;", JLabel.CENTER);
        label1.setFont(new Font("Titillium Web", 4, 30));
        label1.setForeground(Color.WHITE);
        label1.setBackground(Color.BLACK);
        mainPanel.add(label1);

        JLabel label2 = new JLabel("So new game started...", JLabel.CENTER);
        label2.setFont(new Font("Titillium Web", 4, 30));
        label2.setForeground(Color.WHITE);
        label2.setBackground(Color.BLACK);
        mainPanel.add(label2);

        JButton ok = new JButton("OK");
        ok.setFont(new Font("Titillium Web", 4, 30));
        ok.setForeground(Color.WHITE);
        ok.setBackground(Color.BLACK);
        ok.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (e.getSource().equals(ok))
                {
                    frame.dispose();
                }
            }
        });
        mainPanel.add(ok);

        frame.setVisible(true);
    }
}
