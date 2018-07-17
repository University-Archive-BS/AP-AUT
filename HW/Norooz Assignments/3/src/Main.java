import java.util.ArrayList;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);

        ArrayList<Animal> computer = new ArrayList<Animal>();
        ArrayList<Animal> firstPerson = new ArrayList<Animal>();
        ArrayList<Animal> secondPerson = new ArrayList<Animal>();

        Starter start1 = new Starter();
        Starter start2 = new Starter();

        System.out.print("[1]. One Player\n[2]. Two Player\nso: ");
        int input = sc.nextInt();

        switch (input)
        {
            case 1:
            {
                System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tYour turn: \n");
                computer = start2.run2();
                firstPerson = start1.run1();
                AIPlayerManager game = new AIPlayerManager(firstPerson, computer);
                game.run();
                break;
            }
            case 2:
            {
                System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tFirstPlayer: \n");
                firstPerson = start1.run1();
                System.out.println("\n\n\n\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tNow SecondPlayer: \n");
                secondPerson = start2.run1();
                HumanPlayerManager game = new HumanPlayerManager(firstPerson, secondPerson);
                game.run();
                break;
            }
        }
    }
}
