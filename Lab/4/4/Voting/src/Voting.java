import javax.print.attribute.standard.Finishings;
import java.util.*;

/**
 * This Voting part is use to manage other classes
 * and control the voting.
 *
 * @author Ali_Z
 *
 */
public class Voting
{
    public static void main(String[] args)
    {
        Scanner voting = new Scanner(System.in);

        ArrayList<User> users = new ArrayList();
        User temp;

        /**
         * Here we create a Question and continue to set it and then start voting.
         */
        Question newQuestion = new Question();

        //Here I create MYSELF :))))
        Admin Ali = new Admin();

        System.out.println("Tell me whether you are Admin or not?[Y/N]");
        String enterTheVoting = voting.nextLine();
        if (enterTheVoting.equals("Y"))
        {
            System.out.print("Enter your access code:");
            String access = voting.nextLine();
            if (access.equals("0000"))
            {
                System.out.println("The access code is TRUE.");
                newQuestion = Ali.newQuestion();
            }
            else
            {
                System.out.println("The access code is FALSE :|");
                return;
            }
        }
        else if (enterTheVoting.equals("N"))
        {
            System.out.println("We haven't any question now...Sorry!");
            return;
        }

        /**
         * Here we start adding users and voting.
         */
        for (int i = 0; i < 4; i++)
        {
            System.out.println("Enter your name:");
            String tempName = voting.nextLine();
            temp = new User(tempName);
            users.add(temp);

            newQuestion.printQuestion();

            System.out.println("Enter your vote:");
            int vote = voting.nextInt();
            //String y = voting.nextLine();
            voting.nextLine();
            newQuestion.addResult(vote - 1);
        }

        /**
         * And here at the end,
         * we print the results...
         */
        newQuestion.showResult();
    }
}
