import java.io.Serializable;

public class Image implements Serializable
{
    private int[] pixels;

    public Image()
    {
        pixels = new int[100];

        for (int i = 0; i < 100; i++)
        {
            pixels[i] = i;
        }

    }

    public int[] getPixels()
    {
        return pixels;
    }

    public void setPixels(int[] pixels)
    {
        this.pixels = pixels;
    }
}
