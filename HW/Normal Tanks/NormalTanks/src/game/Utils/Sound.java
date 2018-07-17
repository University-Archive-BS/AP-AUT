package game.Utils;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * a class for playing sound and manage them.
 */
public class Sound
{

    private File audio;
    private Clip clip;
    private boolean isLoop;
    private boolean isStoped = false;

    /**
     * @param audio
     * @param isLoop
     */
    public Sound(File audio, boolean isLoop)
    {
        this.isLoop = isLoop;

        this.audio = audio;

        try
        {
            clip = AudioSystem.getClip();

            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(audio);
            clip.open(audioInputStream);
        }
        catch (Exception e)
        {
            System.err.println(e.getMessage());
        }

    }

    /**
     * a method for stop the sound.
     */
    public synchronized void stopSound()
    {
        clip.stop();

    }

    /**
     * a method for start playing the sound.
     */
    public synchronized void playSound()
    {
        new Thread(new Runnable()
        {

            public void run()
            {

                try
                {
                    if (isLoop)
                    {
                        clip.loop(1000);
                    }

                    clip.start();
                }
                catch (Exception e)
                {
                    System.err.println(e.getMessage());
                }

            }
        }).start();
    }

    /**
     * @return that is the clip active or not
     */
    public boolean isActive()
    {
        return clip.isActive();
    }
}
