public class CurrentAccount extends Account
{
    //fields
    private long overdraftLimit;

    //constructor

    /**
     * This constructor will call the constructor of the Account
     * and then set the overdraftLimit.
     * @param num
     * @param overdraftLimit
     */
    public CurrentAccount(long num, long overdraftLimit)
    {
        super(num);
        this.overdraftLimit = overdraftLimit;
    }

    //methods

    @Override
    public String toString()
    {
        if (getBalance() < overdraftLimit)
        {
            return super.toString();
        }
        else
        {
            return "";
        }
    }

}
