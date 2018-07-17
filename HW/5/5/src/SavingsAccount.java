public class SavingsAccount extends Account
{
    //fields
    private double interestRate;

    //constructor

    /**
     * This constructor will call the constructor of the Account
     * and then set the interestRate.
     * @param num
     * @param interestRate
     */
    public SavingsAccount(long num, double interestRate)
    {
        super(num);
        this.interestRate = interestRate;
    }

    //methods

    public double getInterestRate()
    {
        return interestRate;
    }

}
