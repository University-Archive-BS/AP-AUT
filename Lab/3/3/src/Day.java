public class Day
{
    public int value;
    public int limit;
    public SevenSegment left;
    public SevenSegment right;

    public Day ()
    {
        this.value = 0;
        this.limit = 30;
        right = new SevenSegment();
        left = new SevenSegment();
    }

    public int changeDay(Month month)
    {
        if (value == limit)
        {
            month.value++;
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
