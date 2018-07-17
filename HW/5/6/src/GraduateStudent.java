public class GraduateStudent extends Student
{
    //fields
    private static final int TOTAL = 32;
    private String advisor;
    private String lastUniversity;

    //constructor
    public GraduateStudent(String firstName, String lastName, int id, String advisor, String lastUniversity)
    {
        super(firstName, lastName, id);
        this.advisor = advisor;
        this.lastUniversity = lastUniversity;
    }

    //methods

    /**
     * set the advisor
     * @param advisor
     */
    public void setAdvisor(String advisor)
    {
        this.advisor = advisor;
    }

    /**
     * @return advisor
     */
    public String getAdvisor()
    {
        return advisor;
    }

    /**
     * set the lastUniversity
     * @param lastUniversity
     */
    public void setLastUniversity(String lastUniversity)
    {
        this.lastUniversity = lastUniversity;
    }

    /**
     * @return lastUniversity
     */
    public String getLastUniversity()
    {
        return lastUniversity;
    }

    /**
     * Prints All of the fields
     */
    public void displayStudentInformation()
    {
        super.displatInfo();
        System.out.println("TOTAL: " + TOTAL + "\t\tAdvisor: " + getAdvisor()
                + "\t\tLastUniversity: " + getLastUniversity());
    }
}
