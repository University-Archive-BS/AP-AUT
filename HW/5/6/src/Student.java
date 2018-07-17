public abstract class Student
{
    //fields
    private String firstName;
    private String lastName;
    private int id;

    //constructor
    public Student(String firstName, String lastName, int id)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
    }

    //methods

    /**
     * Set the firstName.
     * @param firstName
     */
    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }
    /**
     * @return firstName
     */
    public String getFirstName()
    {
        return firstName;
    }

    /**
     * set the lastName
     * @param lastName
     */
    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }
    /**
     * @return lastName
     */
    public String getLastName()
    {
        return lastName;
    }

    /**
     * set the id
     * @param id
     */
    public void setId(int id)
    {
        this.id = id;
    }

    /**
     * @return id
     */
    public int getId()
    {
        return id;
    }


    public void displatInfo()
    {
        System.out.println("FirsName: " + getFirstName()
        + "\t\tLastName: " + getLastName()
        + "\t\tID: " + getId());
    }
}
