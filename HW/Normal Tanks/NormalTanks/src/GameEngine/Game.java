/*** In The Name of Allah ***/
package GameEngine;

import game.Utils.SharedData;
import game.elements.ObjectId;

import java.awt.EventQueue;
import javax.swing.JFrame;

/**
 * Program start.
 *
 * @author Seyed Mohammad Ghaffarian - Ali Nazari - Amirhossein Hediehloo
 */
public class Game
{
    public Game(int startState, String ip, int difficulty, int whichMap)
    {
        // Initialize the global thread-pool
        ThreadPool.init();

        EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                GameFrame frame = new GameFrame("Normal Tanks");
                frame.setLocationRelativeTo(null); // put frame at center of screen
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
                frame.initBufferStrategy();
                // Create and execute the game-loop
                GameLoop game = new GameLoop(frame);
                SharedData.getData().ip = ip;
                switch (difficulty)
                {
                    case 1:
                        SharedData.getData().difficulty = ObjectId.EasyMode;
                        break;
                    case 2:
                        SharedData.getData().difficulty = ObjectId.MediumMode;
                        break;
                    case 3:
                        SharedData.getData().difficulty = ObjectId.HardMode;
                        break;
                }
                switch (whichMap)
                {
                    case 1:
                        SharedData.getData().whichMap = ObjectId.FirstMap;
                        break;
                    case 2:
                        SharedData.getData().whichMap = ObjectId.SecondMap;
                        break;
                    case 3:
                        SharedData.getData().whichMap = ObjectId.ThirdMap;
                        break;
                    case 4:
                        SharedData.getData().whichMap = ObjectId.FourthMap;
                        break;
                }
                if (startState == 10)
                {
                    System.out.println("Single Started");
                    SharedData.getData().gameType = ObjectId.SinglePlayer;
                    SharedData.getData().playerType = ObjectId.Alone;
                }
                else if (startState == 21 || startState == 22)
                {
                    System.out.println("Host ip: " + ip);
                    SharedData.getData().gameType = ObjectId.TwoPlayer;
                    if (startState == 22)
                    {
                        System.out.println("U R Host");
                        SharedData.getData().playerType = ObjectId.ServerPlayer;
                        frame.setTitle("Server");
                    }
                    else if (startState == 21)
                    {
                        System.out.println("U R Client");
                        frame.setTitle("Client");
                        SharedData.getData().playerType = ObjectId.ClientPlayer;
                    }
                }
                game.init();
                ThreadPool.execute(game);
                // and the game starts ...
            }
        });
    }
}
