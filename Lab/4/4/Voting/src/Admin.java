import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class is Admin to create a new voting.
 *
 * @author Ali_Z
 */
public class Admin
{
    Scanner inputs = new Scanner(System.in);
    private ArrayList<String> options = new ArrayList();

    //constructor
    public Admin() {}

    //methods

    /**
     * this method is here to create new question and
     * get the options of it.
     * @return Question
     */
    public Question newQuestion()
    {
        System.out.println("Enter the Question:");
        String question = inputs.nextLine();

        String newOption;

        for (int i = 0; i < 4; i++)
        {
            System.out.println("Enter the Option:");
            newOption = inputs.nextLine();
            options.add(newOption);
        }

        Question myQuestion = new Question(question, options);
        return myQuestion;
    }


}
