package Models;

import GUI.DownloadPanel;

import javax.net.ssl.HttpsURLConnection;
import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Downloader extends SwingWorker<Void, Void>
{
    private URL url;
    private String path;


    public Downloader(Download input, DownloadPanel panel)
    {
        try
        {
            this.url = new URL(input.getURL());
            checkLink();
        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
        }
        path = input.getPath();
        panel.getNameOfFile().setText(getFileName(url));
        input.setSize(getSizeFile(url, panel));
        setFileName(input);
    }

    public void checkLink()
    {
        HttpURLConnection connection;
        try
        {
            if ("http".equals(url.getProtocol()))
            {
                connection = (HttpURLConnection) url.openConnection();
            }
            else if ("https".equals(url.getProtocol()))
            {
                connection = (HttpsURLConnection) url.openConnection();
            }
            else
            {
                return;
            }
            connection.connect();

            if (connection.getResponseCode() == 301 || connection.getResponseCode() == 302)
            {
                // get redirect url from "location" header field
                String newUrl = connection.getHeaderField("Location");
                // get the cookie if need, for login
                String cookies = connection.getHeaderField("Set-Cookie");
                // open the new connection again
                connection = (HttpURLConnection) new URL(newUrl).openConnection();
                connection.setRequestProperty("Cookie", cookies);
                connection.addRequestProperty("Accept-Language", "en-US,en;q=0.8");
                connection.addRequestProperty("User-Agent", "Mozilla");
                connection.addRequestProperty("Referer", "google.com");

                System.out.println("Redirect to URL : " + newUrl);
                this.url = new URL(newUrl);
            }
        }
        catch (IOException ex)
        {
            return;
        }


    }

    public void setFileName(Download input)
    {
        String fileName = url.getFile();
        input.setName(fileName.substring(fileName.lastIndexOf('/') + 1));
    }

    public String getFileName(URL url)
    {
        String fileName = url.getFile();
        return fileName.substring(fileName.lastIndexOf('/') + 1);
    }

    public String getSizeFile(URL url, DownloadPanel panel)
    {
        HttpURLConnection connection;
        try
        {
            if ("http".equals(url.getProtocol()))
            {
                connection = (HttpURLConnection) url.openConnection();
            }
            else if ("https".equals(url.getProtocol()))
            {
                connection = (HttpsURLConnection) url.openConnection();
            }
            else
            {
                return "";
            }
            connection.connect();

            //make sure response code is in the 200 range
            if (connection.getResponseCode() / 100 != 2)
            {
                throw new IOException(connection.getResponseCode() + "");
            }
        }
        catch (IOException ex)
        {
            return "";
        }
        long contentLength = connection.getContentLength();
        if ((contentLength / 1024) > 1024)
        {
            panel.getSizeOfFile().setText(String.valueOf(contentLength / 1024 / 1024) + "MB");
            return String.valueOf(contentLength / 1024 / 1024) + "MB";
        }
        else
        {
            panel.getSizeOfFile().setText(String.valueOf(contentLength / 1024) + "KB");
            return String.valueOf(contentLength / 1024) + "KB";
        }
    }

    @Override
    protected Void doInBackground()
    {
        HttpURLConnection connection;
        try
        {
            if ("http".equals(url.getProtocol()))
            {
                connection = (HttpURLConnection) url.openConnection();
            }
            else if ("https".equals(url.getProtocol()))
            {
                connection = (HttpsURLConnection) url.openConnection();
            }
            else
            {
                System.err.println("UnSupported Protocol!");
                return null;
            }
            connection.connect();

            //make sure response code is in the 200 range
            if (connection.getResponseCode() / 100 != 2)
            {
                throw new IOException(connection.getResponseCode() + "");
            }
        }
        catch (IOException ex)
        {
            System.err.println("Failed To Open Connection!" + ex);
            return null;
        }

        File file = new File(path + "/" + getFileName(url));
        long contentLength = connection.getContentLength();
        System.out.println("Content Length = " + contentLength);

        try (InputStream in = connection.getInputStream(); FileOutputStream out = new FileOutputStream(file))
        {
            int totalRead = 0;
            byte[] buffer = new byte[10_000];
            while (totalRead < contentLength)
            {
                int read = in.read(buffer);
                if (read == -1)
                {
                    break;
                }
                out.write(buffer, 0, read);
                totalRead += read;
                setProgress((int) ((float)totalRead / contentLength * 100));
            }
            System.out.println("Download Finished.");
        }
        catch (IOException ex)
        {
            System.err.println("Download Destroyed!");
        }
        return null;
    }

}
