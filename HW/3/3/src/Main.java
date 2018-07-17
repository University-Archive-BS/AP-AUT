import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        Scanner inputs = new Scanner(System.in);

        Insurance iran = new Insurance("Iran");
        Insurance sina = new Insurance("Sina");

        Employee a, b, c;

        for (int i = 0; i < 3; i++)
        {
            System.out.print("First Name:");
            String firstN = inputs.nextLine();

            System.out.print("Last Name:");
            String lastN = inputs.nextLine();

            System.out.print("Age:");
            int age = inputs.nextInt();
            String x = inputs.nextLine();

            System.out.print("First Name of Spouse:");
            String firstNS = inputs.nextLine();

            System.out.print("Last Name of Spouse:");
            String lastNS = inputs.nextLine();

            System.out.print("Number of your children:");
            int NOC = inputs.nextInt();
            x = inputs.nextLine();

            switch (i)
            {
                case 0:
                    a = new Employee(firstN, lastN, age, firstNS, lastNS);
                    a.addChildren(NOC);
                    iran.register(a);
                    sina.register(a);
                    break;

                case 1:
                    b = new Employee(firstN, lastN, age, firstNS, lastNS);
                    b.addChildren(NOC);
                    iran.register(b);
                    sina.register(b);
                    break;

                case 2:
                    c = new Employee(firstN, lastN, age, firstNS, lastNS);
                    c.addChildren(NOC);
                    iran.register(c);
                    sina.register(c);
                    break;

            }

        }

    }
}
