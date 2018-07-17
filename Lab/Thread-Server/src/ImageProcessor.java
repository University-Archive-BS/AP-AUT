import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ImageProcessor extends Thread
{
    private Socket socket;
    private ObjectInputStream input;
    private ObjectOutputStream output;
    private Server server;

    public ImageProcessor(Socket socket, Server server) throws IOException
    {
        this.socket = socket;
        input = new ObjectInputStream(socket.getInputStream());
        output = new ObjectOutputStream(socket.getOutputStream());
        start();
    }

    @Override
    public void run()
    {
        System.out.println("Yes");

        try {
            Image image = (Image) input.readObject();
            int[] pixels = image.getPixels();
            Thread thread1 = new Thread(new Runnable() {
                @Override
                public void run()
                {
                    for (int i = 0; i < 50; i++)
                    {
                        pixels[i] *= 2;
                    }
                }
            });

            Thread thread2 = new Thread(new Runnable() {
                @Override
                public void run()
                {
                    for (int i = 50; i < 100; i++)
                    {
                        pixels[i] *= 2;
                    }
                }
            });

            thread1.start();
            thread2.start();

            thread1.join();
            thread2.join();


            image.setPixels(pixels);
            output.writeObject(image);
            output.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        finalization();
        server.delete(this);
    }

    public void finalization()
    {
        try
        {
            output.close();
            input.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
