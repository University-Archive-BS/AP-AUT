public class Insurance
{
    //fields
    private String name;

    //constructor
    public Insurance(String insuranceName)
    {
        name = insuranceName;
    }

    //method
    public int register(Employee employee)
    {
        if (employee.firstName.equals(employee.firstNameOfSpouse) && employee.lastName.equals(employee.lastNameOfSpouse))
        {
            System.out.println("Insurance record for employee "
                    + employee.firstName + " " + employee.lastName +
                    " cannot be registered!");
            return 0;
        }
        else
        {
            System.out.println("An insurance record for employee "
                    + employee.firstName + " "
                    + employee.lastName +
                    " successfully registered by company "
                    + name + ".");
            return 1;
        }
    }
}
