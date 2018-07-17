package Models;

import java.io.Serializable;

public class Download implements Serializable
{
    private String name;
    private String size;
    private String URL;
    private String path;
    private String progress;
    //private Date startLabel;

    public Download(String URL, String path)
    {
        this.URL = URL;
        this.name = "Download Name";
        this.size = "0";
        this.path = path;
        this.progress = "0";
    }

    public String getName()
    {
        return name;
    }
    public String getSize()
    {
        return size;
    }
    public String getPath()
    {
        return path;
    }
    public String getURL()
    {
        return URL;
    }
    public String getProgress()
    {
        return progress;
    }

    public void setName(String name)
    {
        this.name = name;
    }
    public void setProgress(int progress)
    {
        this.progress = "" + progress;
    }
    public void setSize(String size)
    {
        this.size = size;
    }
}
