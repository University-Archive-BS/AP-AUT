import java.util.ArrayList;
import java.util.Scanner;

public abstract class PlayerManager
{
    Scanner sc = new Scanner(System.in);

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

    //fields
    ArrayList<Animal> firstPerson;
    private int numberOfRepairs;

    //constructor
    public PlayerManager(ArrayList<Animal> firstPerson, int numberOfRepairs)
    {
        this.firstPerson = firstPerson;
        this.numberOfRepairs = numberOfRepairs;
    }

    //methods
    public abstract void run();

    public void UI(ArrayList<Animal> a, ArrayList<Animal> b)
    {
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t" + ANSI_RED_BACKGROUND + "\t\t\t\t\tYour Cards\t\t\t\t\t\t  " + ANSI_RESET);
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t" + ANSI_RED_BACKGROUND + "######################################################" + ANSI_RESET);
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t" + ANSI_RED_BACKGROUND + "######################################################" + ANSI_RESET);
        String name = "Animal";
        String energy = "Energy";
        String health = "Health";
        System.out.printf("\t\t\t\t\t\t\t\t\t\t\t\t\t" + ANSI_RED_BACKGROUND + "\t\t" + "%-13s" + "\t\t" + "%-7s" + "\t\t" + "%-7s   " + "\t  " + ANSI_RESET , name, energy, health);
        System.out.printf("\n\t\t\t\t\t\t\t\t\t\t\t\t\t" + ANSI_RED_BACKGROUND + "\t\t\t\t\t\t\t\t\t\t\t\t\t  " + ANSI_RESET);
        System.out.println();
        int i = 0;
        for (Animal x : a)
        {
            System.out.printf("\t\t\t\t\t\t\t\t\t\t\t\t\t" + ANSI_RED_BACKGROUND + "\t" +"[%d]." + "%-13s" + "\t\t" + "%-4d" +
                    "\t\t" + "%-4d" + "\t\t  " + ANSI_RESET, i, x.getName(), x.getEnergy(), x.getHealth());
            System.out.println();
            i++;
        }
        System.out.println();
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t######################################################");
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t######################################################");
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t######################################################");
        System.out.println();
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t" + ANSI_CYAN_BACKGROUND + "\t\t\t\t\tHis Cards\t\t\t\t\t\t  " + ANSI_RESET);
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t" + ANSI_CYAN_BACKGROUND + "######################################################" + ANSI_RESET);
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t" + ANSI_CYAN_BACKGROUND + "######################################################" + ANSI_RESET);
        System.out.printf("\t\t\t\t\t\t\t\t\t\t\t\t\t" + ANSI_CYAN_BACKGROUND + "\t\t" + "%-13s" + "\t\t" + "%-7s" + "\t\t" + "%-7s   " + "\t  " + ANSI_RESET, name, energy, health);
        System.out.printf("\n\t\t\t\t\t\t\t\t\t\t\t\t\t" + ANSI_CYAN_BACKGROUND + "\t\t\t\t\t\t\t\t\t\t\t\t\t  " + ANSI_RESET);
        System.out.println();
        i = 0;
        for (Animal x : b)
        {
            System.out.printf("\t\t\t\t\t\t\t\t\t\t\t\t\t" + ANSI_CYAN_BACKGROUND + "\t" +"[%d]." + "%-13s" + "\t\t" + "%-4d" +
                    "\t\t" + "%-4d" + "\t\t  " + ANSI_RESET, i, x.getName(), x.getEnergy(), x.getHealth());
            System.out.println();
            i++;
        }
        System.out.println();
    }

    public void deleteCard(ArrayList<Animal> b, int i)
    {
        if (b.get(i).getHealth() <= 0)
        {
            b.remove(i);
        }
    }

    public void repairCard(Animal x)
    {
        if (numberOfRepairs >= 6)
        {
            System.out.println("You can't repair more!");
            return;
        }
        numberOfRepairs++;
        x.repair();
    }

    public void humanTurn(ArrayList<Animal> a, ArrayList<Animal> b)
    {
        int i = 0;
        int input1;

        System.out.print("Which Card do you want to use?");
        int input = sc.nextInt();

        System.out.println("[1]. Repair\n[2]. Attack");
        int input0 = sc.nextInt();
        switch (input0)
        {
            case 1:
            {
                System.out.print("Which card do you want to repair? ");
                input1 = sc.nextInt();
                repairCard(a.get(input1));
                break;
            }
            case 2:
            {
                System.out.print("Which Card do you want to attack?");
                input1 = sc.nextInt();

                for (AttackType x : a.get(input).getAttackTypes())
                {
                    System.out.println("\t\t" + "[" + i + "]. " + x.getType() + "\t" + x.getEffect());
                    i++;
                }
                System.out.print("Which AttackType do you want to use? (Enter 5 to skip your turn)    ");
                int input2 = sc.nextInt();
                if (input2 == 5)
                {
                    return;
                }

                if (a.get(input).getAttackTypes().get(input2).getEffect() <= a.get(input).getEnergy())
                {
                    a.get(input).setEnergy( ( a.get(input).getEnergy() ) - ( a.get(input).getAttackTypes().get(input2).getEffect() ) );
                    b.get(input1).setHealth( ( b.get(input1).getHealth() ) - ( a.get(input).getAttackTypes().get(input2).getEffect() ) );
                    deleteCard(b, input1);
                }
                else
                {
                    System.out.println("\nTry Again!\n");
                    humanTurn(a, b);
                }
                break;
            }
        }
    }

    public void finishTheGame(ArrayList<Animal> b)
    {
        if (b.size() == 0)
        {
            System.out.println("\n\n\n\n\n\n\n\n\n\n");
            System.out.println("\t\t##########################################################################################################" +
                        "\n" + "\t\t##########################################################################################################" +
                        "\n" + "\t\t#########################################YOU WIN##########################################################" +
                        "\n" + "\t\t##########################################################################################################" +
                        "\n" + "\t\t##########################################################################################################" +
                       "\n\n\n\n\n\n\n\n\n\n\n");
            System.exit(1);
        }
    }
}
