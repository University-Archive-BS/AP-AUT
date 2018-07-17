public class Account
{
    //fields
    private long balance;   //The current balance
    private long accountNumber; //The account number

    //constructor
    public Account(long num)
    {
        balance = 0;
        accountNumber = num;
    }

    //methods
    public void despite(long amount)
    {
        if (amount > 0)
        {
            balance += amount;
        }
        else
        {
            System.err.println("Invalid despite amount!");
        }
    }

    public void withdraw(long amount)
    {
        if (amount > 0 && amount <= balance)
        {
            balance -= amount;
        }
        else
        {
            System.err.println("Invalid withdraw amount!");
        }
    }

    public long getBalance()
    {
        return balance;
    }

    public long getAccountNumber()
    {
        return accountNumber;
    }

    public String toString()
    {
        return ("Account Number #" + accountNumber
                + "--> balance = " + balance);
    }

    public final void print()
    {
        //Dont't override this.
        //Override the toString method.
        System.out.println(toString());
    }
}
