import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Starter
{
    Random rand = new Random();
    Scanner sc = new Scanner(System.in);

    int numberOfAnimals;

    public Starter()
    {
        numberOfAnimals = 0;
    }

    public int getNumberOfAnimals()
    {
        return numberOfAnimals;
    }

    public void newAnimal(ArrayList<Animal> temp, String name, int number)
    {
        for (int i = 0; i < number; i++)
        {
            Animal x = new Animal(name);
            temp.add(x);
        }
    }

    public void findRandomNumberForAnimals(ArrayList<Animal> temp, String name)
    {
        int number = rand.nextInt(6);

        numberOfAnimals += number;

        if (numberOfAnimals <= 30)
        {
            newAnimal(temp, name, number);
        }
        else
        {
            numberOfAnimals -= number;
            findRandomNumberForAnimals(temp, name);
        }
    }

    public void nameOfAnimals(ArrayList<Animal> temp)
    {
        findRandomNumberForAnimals(temp, "Lion");
        if (numberOfAnimals == 30)
        {
            return;
        }
        findRandomNumberForAnimals(temp, "Bear");
        if (numberOfAnimals == 30)
        {
            return;
        }
        findRandomNumberForAnimals(temp, "Tiger");
        if (numberOfAnimals == 30)
        {
            return;
        }
        findRandomNumberForAnimals(temp, "Vulture");
        if (numberOfAnimals == 30)
        {
            return;
        }
        findRandomNumberForAnimals(temp, "Fox");
        if (numberOfAnimals == 30)
        {
            return;
        }
        findRandomNumberForAnimals(temp, "Elephant");
        if (numberOfAnimals == 30)
        {
            return;
        }
        findRandomNumberForAnimals(temp, "Wolf");
        if (numberOfAnimals == 30)
        {
            return;
        }
        findRandomNumberForAnimals(temp, "Pig");
        if (numberOfAnimals == 30)
        {
            return;
        }
        findRandomNumberForAnimals(temp, "Hippopotamus");
        if (numberOfAnimals == 30)
        {
            return;
        }
        findRandomNumberForAnimals(temp, "Cow");
        if (numberOfAnimals == 30)
        {
            return;
        }
        findRandomNumberForAnimals(temp, "Rabbit");
        if (numberOfAnimals == 30)
        {
            return;
        }
        findRandomNumberForAnimals(temp, "Turtle");
        if (numberOfAnimals == 30)
        {
            return;
        }
        else
        {
            nameOfAnimals(temp);
        }
    }

    public void printInstruction(String name, int energy, int health,int wound,int kill,int injure,int attack,int bite)
    {
        System.out.printf("\t\t\t\t\t#\t%-13s" + "\t\t" +
                        "%-7d" + "\t\t" + "%-7d" + "\t\t" +
                        "%-7d" + "\t\t" + "%-7d" + "\t\t" +"%-7d" + "\t\t" + "%-7d" + "\t\t" + "%-7d" + "\t#\n"
                , name, energy, health, wound, kill, injure, attack, bite);    }

    public void print30Animal(ArrayList<Animal> temp)
    {
        String name = "Animal";
        String energy = "Energy";
        String health = "Health";
        String kill = "Kill";
        String injure = "Injure";
        String wound = "Wound";
        String bite = "Bite";
        String attack = "Attack";
        System.out.println("\t\t\t\t\t#########################################################################################################");
        System.out.printf("\t\t\t\t\t#\t%-13s" + "\t\t" +
                        "%-7s" + "\t\t" + "%-7s" + "\t\t" +
                        "%-7s" + "\t\t" + "%-7s" + "\t\t" +"%-7s" + "\t\t" + "%-7s" + "\t\t" + "%-7s" + "\t#\n"
                , name
                , energy, health
                , wound, kill, injure, attack, bite);

        System.out.println("\t\t\t\t\t#########################################################################################################");
        printInstruction("Lion", 1000, 900, 150, 500, 0, 0, 0);
        printInstruction("Bear", 900, 850, 130, 600, 0, 0, 0);
        printInstruction("Tiger", 850, 850, 120, 650, 0, 0, 0);
        printInstruction("Vulture", 600, 350, 100, 0, 0, 0, 0);
        printInstruction("Fox", 600, 400, 90, 0, 0, 0, 0);
        printInstruction("Elephant", 500, 1200, 0, 0, 70, 50, 0);
        printInstruction("Wolf", 700, 450, 0, 700, 0, 0, 0);
        printInstruction("Pig", 500, 1100, 0, 0, 80, 0, 0);
        printInstruction("Hippopotamus", 360, 1000, 0, 0, 0, 110, 0);
        printInstruction("Cow", 400, 750, 100, 0, 0, 90, 0);
        printInstruction("Rabbit", 350, 200, 0, 0, 0, 0, 80);
        printInstruction("Turtle", 230, 350, 0, 0, 0, 0, 200);
        System.out.println("\t\t\t\t\t#########################################################################################################");
        System.out.println("\n\n");

        for (int i = 0; i <= 27; i += 3)
        {
            System.out.printf("\t\t\t\t\t[" + i + "]. " + "%-13s" + "\t\t" +
                    "[" + (i + 1) + "]. " + "%-13s" + "\t\t" +
                    "[" + (i + 2) + "]. " + "%-13s" + "\n"
                    , temp.get(i).getName(), temp.get(i + 1).getName(), temp.get(i + 2).getName() );
        }
        System.out.println("\n");
    }

    public void setNewArrayList(ArrayList<Animal> myAnimal, ArrayList<Animal> temp, int i)
    {
        System.out.print("[" + (i + 1) + "] Card: ");
        int x = sc.nextInt();
        if (x > 29)
        {
            System.out.println("Invalid input!");
            setNewArrayList(myAnimal, temp, i);
            return;
        }
        if (x < 0)
        {
            System.out.println("Invalid input!");
            setNewArrayList(myAnimal, temp, i);
            return;
        }
        else
        {
            myAnimal.add(temp.get(x));
            return;
        }
    }

    public ArrayList<Animal> run1()
    {
        ArrayList<Animal> temp = new ArrayList<Animal>();
        nameOfAnimals(temp);
        print30Animal(temp);

        ArrayList<Animal> myAnimal = new ArrayList<Animal>();
        for (int i = 0; i <10; i++)
        {
            setNewArrayList(myAnimal, temp, i);
        }

        temp.clear();
        return myAnimal;
    }





    //From here we start methods of Finding Cards of the computer




    public ArrayList<Animal> run2()
    {
        ArrayList<Animal> random = new ArrayList<Animal>();
        PCAnimals(random);
        return random;
    }

    public void findRandomAnimals(ArrayList<Animal> random, String name)
    {
        int number = rand.nextInt(2);

        numberOfAnimals += number;

        if (numberOfAnimals <= 10)
        {
            newAnimal(random, name, number);
        }
        else
        {
            numberOfAnimals -= number;
            findRandomAnimals(random, name);
        }
    }

    public void PCAnimals(ArrayList<Animal> random)
    {
        findRandomAnimals(random, "Lion");
        if (numberOfAnimals == 10)
        {
            return;
        }
        findRandomAnimals(random, "Bear");
        if (numberOfAnimals == 10)
        {
            return;
        }
        findRandomAnimals(random, "Tiger");
        if (numberOfAnimals == 10)
        {
            return;
        }
        findRandomAnimals(random, "Vulture");
        if (numberOfAnimals == 10)
        {
            return;
        }
        findRandomAnimals(random, "Fox");
        if (numberOfAnimals == 10)
        {
            return;
        }
        findRandomAnimals(random, "Elephant");
        if (numberOfAnimals == 10)
        {
            return;
        }
        findRandomAnimals(random, "Wolf");
        if (numberOfAnimals == 10)
        {
            return;
        }
        findRandomAnimals(random, "Pig");
        if (numberOfAnimals == 10)
        {
            return;
        }
        findRandomAnimals(random, "Hippopotamus");
        if (numberOfAnimals == 10)
        {
            return;
        }
        findRandomAnimals(random, "Cow");
        if (numberOfAnimals == 10)
        {
            return;
        }
        findRandomAnimals(random, "Rabbit");
        if (numberOfAnimals == 10)
        {
            return;
        }
        findRandomAnimals(random, "Turtle");
        if (numberOfAnimals == 10)
        {
            return;
        }
        else
        {
            PCAnimals(random);
        }
    }
}
