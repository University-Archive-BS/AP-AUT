public class Student
{
    //fields
    private String firstName;
    private String lastName;
    private float grade;
    private int id;
    private int money;

    //constructors
    public Student (String fName, String lName, int sID)
    {
        firstName = fName;
        lastName = lName;
        grade = 20;
        id = sID;
        money = 50000;
    }

    //methods

    public int getId() {
        return id;
    }

    public void print()
    {
        System.out.println(
                "first name: " + firstName +
                ", last name: " + lastName +
                ", grade: " + grade +
                ", money: " + money);
    }

    public void delay()
    {
        money -= 2500;
        grade -= 0.25;
    }
}
