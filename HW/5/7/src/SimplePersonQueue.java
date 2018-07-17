import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class SimplePersonQueue extends PersonQueue {

    private ArrayList<Person> people;

    public SimplePersonQueue()
    {
        people = new ArrayList<Person>();
    }

    /**
     * TODO
     * Returns the number of elements in this collection.  If this collection
     * contains more than {@code Integer.MAX_VALUE} elements, returns
     * {@code Integer.MAX_VALUE}.
     *
     * @return the number of elements in this collection
     */
    @Override
    public int size() {
        return people.size();
    }

    /**
     * TODO
     * Returns {@code true} if this collection contains no elements.
     *
     * @return {@code true} if this collection contains no elements
     */
    @Override
    public boolean isEmpty() {
        if (people.isEmpty())
        {
            return true;
        }
        else
        {
            return false;
        }
    }


    /**
     * TODO
     * Retrieves, but does not remove, the head of this queue.  This method
     * differs from {@link #peek peek} only in that it throws an exception
     * if this queue is empty.
     *
     * @return the head of this queue
     */
    @Override
    public Person element() {
        //WRITE YOUR OWN IMPLEMENTATION
        if (!(isEmpty()))
        {
            System.err.println("null");
            return null;
        }
        else
        {
            return people.get(0);
        }
    }

    /**
     * TODO
     * Retrieves, but does not remove, the head of this queue,
     * or returns {@code null} if this queue is empty.
     *
     * @return the head of this queue, or {@code null} if this queue is empty
     */
    @Override
    public Person peek() {
        if (!(isEmpty()))
        {
            return null;
        }
        else
        {
            return people.get(0);
        }
    }

    /**
     * TODO
     * Returns {@code true} if this collection contains the specified element.
     * More formally, returns {@code true} if and only if this collection
     * contains at least one element {@code e} such that
     * {@code Objects.equals(o, e)}.
     *
     * @param o element whose presence in this collection is to be tested
     * @return {@code true} if this collection contains the specified
     * element
     */
    @Override
    public boolean contains(Object o) {
        Person p = (Person) o;
        for (Person x : people)
        {
            if (p.equals(x))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * TODO
     * Inserts the specified element into this queue if it is possible to do so
     * immediately without violating capacity restrictions, returning
     * {@code true} upon success and throwing an {@code IllegalStateException}
     * if no space is currently available.
     *
     * @param person the element to add
     * @return {@code true} (as specified by {@link Collection#add})
     */
    @Override
    public boolean add(Person person) {
        if (people.add(person))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * TODO
     * Removes a single instance of the specified element from this
     * collection, if it is present (optional operation).  More formally,
     * removes an element {@code e} such that
     * {@code Objects.equals(o, e)}, if
     * this collection contains one or more such elements.  Returns
     * {@code true} if this collection contained the specified element (or
     * equivalently, if this collection changed as a result of the call).
     *
     * @param o element to be removed from this collection, if present
     * @return {@code true} if an element was removed as a result of this call
     */
    @Override
    public boolean remove(Object o) {
        Person p = (Person) o;
        Iterator t = people.iterator();
        for (int i = 0; iterator().hasNext(); t.next())
        {
            if (p.equals(people.get(i)))
            {
                people.remove(i);
                return true;
            }
        }
        return false;
    }

    /**
     * TODO
     * Retrieves and removes the head of this queue.  This method differs
     * from {@link #poll poll} only in that it throws an exception if this
     * queue is empty.
     *
     * @return the head of this queue
     */
    @Override
    public Person remove() {
        return people.get(0);
    }

    /**
     * TODO
     * Retrieves and removes the head of this queue,
     * or returns {@code null} if this queue is empty.
     *
     * @return the head of this queue, or {@code null} if this queue is empty
     */
    @Override
    public Person poll() {
        if (!(isEmpty()))
        {
            return null;
        }
        else
        {
            return people.get(0);
        }
    }

    /**
     * TODO
     * Removes all of the elements from this collection (optional operation).
     * The collection will be empty after this method returns.
     */
    @Override
    public void clear() {
        Iterator t = people.iterator();
        for (int i = 0; iterator().hasNext(); t.next())
        {
            people.remove(i);
        }
    }

}
