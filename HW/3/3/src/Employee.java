public class Employee
{
    //fields
    public String firstName;
    public String lastName;
    public int age;
    public int numOfChildren;
    public String firstNameOfSpouse;
    public String lastNameOfSpouse;

    //constructors
    public Employee (String firstName, String lastName, int age)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        numOfChildren = 0;
    }
    public Employee (String firstName, String lastName, int age, String firstNameOfSpouse, String lastNameOfSpouse)
    {
        this(firstName, lastName, age);
        this.firstNameOfSpouse = firstNameOfSpouse;
        this.lastNameOfSpouse = lastNameOfSpouse;
    }

    //methods
    public void addChildren(int numOfAddedChildren)
    {
        numOfChildren += numOfAddedChildren;
    }
}
