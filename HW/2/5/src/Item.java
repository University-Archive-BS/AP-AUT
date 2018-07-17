public class Item
{
    //fields
    private String name;
    private String producer;
    private int amount;

    //constructor
    public Item (String name, String producer)
    {
        this.name = name;
        this.producer = producer;
        this.amount = 0;
    }

    //methods
    public void incrementOrDecrement (int inputAmount)
    {
        amount += inputAmount - amount;
    }
    public void print ()
    {
        System.out.println("Item Name: " + name
                + ", Producer Name: " + producer
                + ", Remaining Amount: " + amount);
    }

}
