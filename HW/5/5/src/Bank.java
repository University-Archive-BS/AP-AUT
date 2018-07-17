import java.util.ArrayList;

public class Bank
{
    //fields
    private ArrayList<Account> accounts;

    //constructor

    /**
     * This constructor will new the ArrayList of Accounts.
     */
    public Bank()
    {
        accounts = new ArrayList<Account>();
    }

    //methods

    /**
     * This method will add an index to the arraylist.
     * @param x
     */
    public void addAccount(Account x)
    {
        accounts.add(x);
    }

    /**
     * This method will
     * add the interest to SavingAccount
     * And
     * prints the info of the account --> if (remaining amount < overdraftLimit)
     *
     */
    public void update()
    {
        for (Account x : accounts)
        {
            if (x instanceof SavingsAccount)
            {
                SavingsAccount v = (SavingsAccount) x;
                x.despite((long) (x.getBalance() * v.getInterestRate()));
            }
            else if (x instanceof CurrentAccount)
            {
                x.print();
            }
        }
    }


}
