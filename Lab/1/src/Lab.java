import java.util.ArrayList;

public class Lab
{
    ArrayList<Student> studentInfo;

    public Lab (int capacity)
    {
        studentInfo = new ArrayList<Student>(capacity);
    }

    //methods
    public void enroll(int i,String fName, String lName, int sID)
    {
        Student x = new Student(fName, lName, sID);
        studentInfo.add(x);
    }
}
