public class Minute
{
    public int value;
    public int limit;
    public SevenSegment left;
    public SevenSegment right;

    public Minute ()
    {
        this.value = 0;
        this.limit = 60;
        right = new SevenSegment();
        left = new SevenSegment();
    }

    public int changeMinute(Hour hour)
    {
        if (value == limit)
        {
            value = 0;
            hour.value++;
            sevenSeg();
            return 1;
        }
        sevenSeg();
        return 0;
    }

    public void sevenSeg()
    {
        right.number = value % 10;
        left.number = value / 10;
    }
}
