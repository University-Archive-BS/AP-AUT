import java.util.ArrayList;

public class Queue
{
    //fields
    public ArrayList<Person> queue;

    //constructor
    public Queue(int size)
    {
        queue = new ArrayList(size);
    }

    //methods
    public void Enqueue(String name, int time)
    {
        queue.add(new Person(name, time));
    }

    public void Dequeue()
    {
        queue.remove(queue.size() - 1);
    }


}
