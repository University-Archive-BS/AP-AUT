public class Second
{
    public int value;
    public int limit;
    public SevenSegment left;
    public SevenSegment right;

    public Second ()
    {
        this.value = 0;
        this.limit = 60;
        right = new SevenSegment();
        left = new SevenSegment();
    }

    public int changeSecond(Minute minute)
    {
        value++;
        if (value == limit)
        {
            value = 0;
            minute.value++;
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
