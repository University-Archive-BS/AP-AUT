import java.util.Scanner;

public class Run
{
    Scanner studentsInfo = new Scanner(System.in);

    public Run()
    {

    }

    public void inputInfo(int capacity, Lab newLab)
    {
        for (int i = 0 ; i < capacity ; i++)
        {
            System.out.print("Enter first name:");
            String fName = studentsInfo.nextLine();

            System.out.print("Enter last name:");
            String lName = studentsInfo.nextLine();

            System.out.print("Enter ID:");
            int sID = studentsInfo.nextInt();
            studentsInfo.nextLine();
            newLab.enroll(i, fName, lName, sID);
        }

    }

    public void run()
    {
        Scanner inputs = new Scanner(System.in);

        System.out.print("Enter the capacity of the Lab:");
        int capacity = inputs.nextInt();

        Lab newLab = new Lab(capacity);

        inputInfo(capacity, newLab);

        //delay part
        System.out.print("Enter the ID of those who delay:");

        int d1ID = inputs.nextInt();

        //
        for (int i = 0; i < capacity; i++)
        {
            if (newLab.studentInfo.get(i).getId() == d1ID)
            {
                newLab.studentInfo.get(i).delay();
                System.out.println("Are");
            }
        }

        for (int i = 0 ; i < capacity ; i++)
        {
            newLab.studentInfo.get(i).print();
        }

    }
}
