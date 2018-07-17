public class Main
{
    public static void main(String[] args)
    {
        Bank bank = new Bank();

        Account a1 = new SavingsAccount(9631075, 0.1);
        a1.withdraw(2000);
        bank.addAccount(a1);

        System.out.println("-------------------------------------");

        Account a2 = new SavingsAccount(9631001, 0.01);
        a2.despite(5000);
        bank.addAccount(a2);

        System.out.println("-------------------------------------");

        Account a3 = new SavingsAccount(9631078, 0.5);
        a3.print();
        bank.addAccount(a3);

        System.out.println("-------------------------------------");

        Account a4 = new SavingsAccount(9631016, 0.2);
        a4.despite((long) (a4.getBalance() * ((SavingsAccount) a4).getInterestRate()));
        bank.addAccount(a4);

        System.out.println("-------------------------------------");

        Account a5 = new SavingsAccount(9631043, 0.3);
        bank.addAccount(a5);

        System.out.println("-------------------------------------");
        System.out.println("-------------------------------------");

        Account b1 = new CurrentAccount(9631059, 0);
        b1.withdraw(2000);
        bank.addAccount(b1);

        System.out.println("-------------------------------------");

        Account b2 = new CurrentAccount(9631914, 1000);
        a2.despite(5000);
        bank.addAccount(b2);

        System.out.println("-------------------------------------");

        Account b3 = new CurrentAccount(9631002, 2000);
        a3.print();
        bank.addAccount(b3);

        System.out.println("-------------------------------------");

        Account b4 = new CurrentAccount(9631080, 3000);
        a2.despite(15000);
        bank.addAccount(b4);

        System.out.println("-------------------------------------");

        Account b5 = new CurrentAccount(9631006, 4000);
        bank.addAccount(b5);

        System.out.println("-------------------------------------");
        System.out.println("-------------------------------------");


        bank.update();



    }
}
