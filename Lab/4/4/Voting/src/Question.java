import java.util.ArrayList;

/**
 * This class is Question.
 * It use to create and manage a question and it options.
 *
 * @author Ali_Z
 */
public class Question
{
    //fields

    /**
     * We have three fields:
     * question that is a String and contain the question
     * An ArrayList that contain the options of the question
     * and an ArrayList that contain a number in each element that show how many times this element was chosen.
     */
    private String question;
    private ArrayList<String> options = new ArrayList();
    private int[] result;

    //constructor

    /**
     * This constructor will set the String of the question
     * and set the options of the question in its ArrayList
     * and at the end, we create the result ArrayList by size of the option ArrayList.
     *
     * @param question
     * @param options
     */
    public Question(String question, ArrayList options)
    {
        this.question = question;
        this.options = options;
        this.result = new int[4];

        for (int i = 0; i < 4; i++)
        {
            result[i] = 0;
        }
    }

    public Question(Question q)
    {
        question = q.question;
        options = q.options;
        result = new int[4];

        for (int i = 0; i < 4; i++)
        {
            result[i] = 0;
        }
    }

    public Question() { }

    //methods

    /**
     * This methood is use to get the String of the question.
     *
     * @return String
     */
    public String getQuestion()
    {
        return question;
    }

    /**
     * This method is use to get the options of the question.
     *
     * @return ArrayList
     */
    public ArrayList<String> getOptions()
    {
        return options;
    }

    public void printQuestion()
    {
        System.out.println(getQuestion());
        int i = 1;
        for (String a : options)
        {
            System.out.println(i + ". " + a);
            i++;
        }

    }

    /**
     * this method calls when an option select as a vote.
     * @param index
     */
    public void addResult(int index)
    {
        result[index]++;
    }

    /**
     * And this method show the result of voting.
     *
     */
    public void showResult()
    {
        System.out.println("The result show that:");
        int i = 1;
        for (String a : options)
        {
            System.out.println(i + ". " + a + " has selected " + result[i - 1] + " times." );
            i++;
        }
    }
}
