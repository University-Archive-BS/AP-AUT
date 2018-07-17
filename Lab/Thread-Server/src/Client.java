import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client
{
    private Socket socket;
    private ObjectInputStream input;
    private ObjectOutputStream output;


    public Client() throws IOException, ClassNotFoundException
    {
        socket = new Socket("127.0.0.1", 6513);
        output = new ObjectOutputStream(socket.getOutputStream());
        input = new ObjectInputStream(socket.getInputStream());

        run();
    }

    public void run()
    {
        try
        {
            Image imageOut = new Image();
            output.writeObject(imageOut);
            output.flush();

            Image imageIn = (Image) input.readObject();
            for (int i = 0; i < 100; i++)
            {
                System.out.println(imageIn.getPixels()[i]);
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    public static void main(String[] args)
    {
        try {
            new Client();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
