import java.util.ArrayList;

/**
 * This class is User.
 * It use to create a new User to vote.
 *
 * @author Ali_Z
 */
public class User
{
    //fields
    /**
     * This class has a field that contain the name of who register to vote.
     */
    private String name;

    //constructor
    /**
     * This constructor will set the name of who register as a user to vote.
     */
    public User(String name)
    {
        this.name = name;
    }

    //methods

    /**
     * This method is use to get the name of the user.
     *
     * @return name
     */
    public String getName()
    {
        return name;
    }
}
