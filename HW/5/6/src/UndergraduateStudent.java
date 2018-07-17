public class UndergraduateStudent extends Student
{
    //fields
    private static final int TOTAL = 140;
    private String howEntered;

    //constructor
    public UndergraduateStudent(String firstName, String lastName, int id, String howEntered)
    {
        super(firstName, lastName, id);
        this.howEntered = howEntered;
    }

    //methods

    /**
     * set the way of entering to university.
     * @param howEntered
     */
    public void setHowEntered(String howEntered)
    {
        this.howEntered = howEntered;
    }
    /**
     * @return howEntered
     */
    public String getHowEntered()
    {
        return howEntered;
    }

    /**
     * Prints All of the fields
     */
    public void displayStudentInformation()
    {
        super.displatInfo();
        System.out.println("TOTAL: " + TOTAL + "\t\tHowEntered: " + getHowEntered());
    }

}
