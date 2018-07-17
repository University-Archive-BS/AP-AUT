import java.util.Random;

public class Main
{
    public static void main(String[] args)
    {
        Random generator = new Random();

        Queue test = new Queue(30);

        for (int i = 0; i < test.queue.size(); i++)
        {
            int t = test.queue(i - 1).time + generator.nextInt(10);
            test.queue(i) = new Person("Ali", 0);
        }

        for ( int i = 0; i < test.queue.size(); i++)
        {
            System.out.println("Came: " + test.queue(i).time +
                    "Went: " + (test.queue(i-1).time + 2) );
        }
    }
}
