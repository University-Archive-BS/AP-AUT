public class Year
{
    public int value;
    public SevenSegment left;
    public SevenSegment secondLeft;
    public SevenSegment secondRight;
    public SevenSegment right;


    public Year ()
    {
        this.value = 0;
        left = new SevenSegment();
        secondLeft = new SevenSegment();
        secondRight = new SevenSegment();
        right = new SevenSegment();
    }

    public void sevenSeg()
    {
        right.number = value % 10;
        left.number = value / 10;
    }
}
