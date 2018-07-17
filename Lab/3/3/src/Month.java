public class Month
{
    public int value;
    public int limit;
    public SevenSegment left;
    public SevenSegment right;

    public Month ()
    {
        this.value = 0;
        this.limit = 12;
        right = new SevenSegment();
        left = new SevenSegment();
    }

    public int changeMonth(Year year)
    {
        if (value == limit)
        {
            year.value++;
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
