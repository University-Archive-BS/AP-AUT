import java.util.ArrayList;

public class Pelak
{
    //fields
    private String numberPlate;
    private boolean validation;
    private String firstName;
    private String lastName;
    ArrayList<String> penalty = new ArrayList();

    //constructor
    public Pelak(String numberPlate, String firstName, String lastName)
    {
        this.numberPlate = numberPlate;
        this.firstName = firstName;
        this.lastName = lastName;
        validation = true;
    }

    //methods
    public void printPenalties()
    {
        for (int i = 0; i < penalty.size(); i++)
        {
            System.out.println(penalty.get(i));
        }
    }
    public void addPenalty(String newPenalty)
    {
        penalty.add(newPenalty);
        if (penalty.size() >= 5)
        {
            validation = false;
        }
    }
    public void subPenalty(String oldPenalty)
    {
        for (int i = 0; i < penalty.size(); i++)
        {
            if (oldPenalty.equals( penalty.get(i) ) )
            {
                penalty.remove(i);
            }
            if (penalty.size() < 5)
            {
                validation = true;
            }
        }
    }
    public void showvalidation()
    {
        System.out.print("Your validation is:");
        if (validation == true)
        {
            System.out.print("PASS");
        }
        else
        {
            System.out.print("FAIL");
        }
    }
}
