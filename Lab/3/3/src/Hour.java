public class Hour
{
    public int value;
    public int limit;
    public SevenSegment left;
    public SevenSegment right;

    public Hour ()
    {
        this.value = 0;
        this.limit = 24;
        right = new SevenSegment();
        left = new SevenSegment();
    }

    public int changeHour(Day day)
    {
        if (value == limit)
        {
            value = 0;
            day.value++;
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
