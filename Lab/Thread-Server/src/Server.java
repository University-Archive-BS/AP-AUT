import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class Server
{
    private ServerSocket serverSocket;
    private Vector<ImageProcessor> clients;

    public Server() throws IOException {
        serverSocket = new ServerSocket(6513);
        clients = new Vector<ImageProcessor>();

        while (true)
        {
            Socket newClient = serverSocket.accept();
            clients.add(new ImageProcessor(newClient, this));
        }

    }

    public void delete (ImageProcessor imageProcessor)
    {
        clients.remove(imageProcessor);
    }

    public static void main(String[] args)
    {
        try
        {
            new Server();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
