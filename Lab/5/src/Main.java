import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        Scanner inputs = new Scanner(System.in);

        Paint x = new Paint();

        //for (boolean client = true; client != false; )
        while(true)
        {
            System.out.println("[1].Insert new shape\nNotice: In each step you Can Enter [9] to Exit.");
            int menu1 = inputs.nextInt();
            switch (menu1)
            {
                case 1:
                {
                    break;
                }
                case 9:
                {
                    System.out.println("Bye Bye");
                    return;
                }
                default:
                {
                    System.out.println("Are you crazy? :|");
                    return;
                }
            }

            System.out.println("[1].Circle\n[2].PolyGon");
            int menu2 = inputs.nextInt();
            switch (menu2)
            {
                case 1:
                {
                    System.out.print("Radius:");
                    double r = inputs.nextDouble();
                    Shape c = new Circle(r);
                    x.addShape(c);
                    break;
                }
                case 2:
                {
                    System.out.println("[1].Rectangle\n[2].Triangle");
                    int menu3 = inputs.nextInt();
                    switch (menu3)
                    {
                        case 1:
                        {
                            double[] sides = new double[4];
                            for (int i = 0; i < 4; i++)
                            {
                                System.out.print("Side" + (i + 1) + ": ");
                                sides[i] = inputs.nextDouble();
                            }
                            Shape r = new Rectangle(sides);
                            x.addShape(r);
                            break;
                        }
                        case 2:
                        {
                            double[] sides = new double[3];
                            for (int i = 0; i < 3; i++)
                            {
                                System.out.print("Side" + (i + 1) + ": ");
                                sides[i] = inputs.nextDouble();
                            }
                            Shape t = new Triangle(sides);
                            x.addShape(t);
                            break;
                        }
                        case 9:
                        {
                            System.out.println("Bye Bye");
                            return;
                        }
                        default:
                        {
                            System.out.println("Are you crazy? :|");
                            return;
                        }
                    }
                    break;
                }
                case 9:
                {
                    System.out.println("Bye Bye");
                    return;
                }
                default:
                {
                    System.out.println("Are you crazy? :|");
                    return;
                }

            }
            System.out.println("[1].Continue inserting shapes\n[2].Show data");
            int menu4 = inputs.nextInt();
            switch (menu4)
            {
                case 1:
                {
                    break;
                }
                case 2:
                {
                    x.drawAll();
                }
                case 9:
                {
                    System.out.println("Bye Bye");
                    return;
                }
                default:
                {
                    System.out.println("Are you crazy? :|");
                    return;
                }
            }
        }
    }
}
