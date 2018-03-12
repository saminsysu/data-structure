import java.util.NoSuchElementException;
import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
	private Node first, last;
	private int sz; // size of Deque
	private class Node {
		Item item;
		Node next, previous; 
	}
	
	private class DequeIterator implements Iterator<Item> {
		private Node current = first;
		
        public boolean hasNext()  
        { 
        		return current != null;                     
    		}
        public void remove()      
        { 
        		throw new UnsupportedOperationException();  
    		}

        public Item next() {
            if (!hasNext()) 
            		throw new NoSuchElementException();
            Item item = current.item;
            current = current.next; 
            return item;
        }
	}
	
	public Iterator<Item> iterator()         // return an iterator over items in order from front to end
	{
		return new DequeIterator();
	}
	
	public Deque()                           // construct an empty deque
	{
		first = last = null;
		sz = 0;
	}
	
	public boolean isEmpty()                 // is the deque empty?
	{
		return sz == 0;
	}
	
	public int size()                        // return the number of items on the deque
	{
		return sz;
	}
	
	public void addFirst(Item item)          // add the item to the front
	{
		if (item == null)
			throw new IllegalArgumentException();
		
		Node lfirst = first;
		first = new Node();
		first.item = item;
		first.previous = null;
		first.next = lfirst;
		
		if (!isEmpty())
			lfirst.previous = first;
		else
			last = first;
		
		sz++;
	}
	
	public void addLast(Item item)           // add the item to the end
	{
		if (item == null)
			throw new IllegalArgumentException();
		
		Node llast = last;
		last = new Node();
		last.item = item;
		last.next =  null;
		last.previous = llast;
		
		if (!isEmpty())
			llast.next = last;
		else
			first = last;
		
		sz++;
	}
	
	public Item removeFirst()                // remove and return the item from the front
	{
		if (isEmpty())
			throw new NoSuchElementException();
		
		Item item = first.item;
		first = first.next;
		sz--;
		
		if (!isEmpty())
			first.previous = null;
		else
			last = first;
		
		return item;
	}
	
	public Item removeLast()                 // remove and return the item from the end
	{
		if (isEmpty())
			throw new NoSuchElementException();
		
		Item item = last.item;
		last = last.previous;
		sz--;
		
		if (!isEmpty())
			last.next = null;
		else
			first = last;
		
		return item;
	}
	
	public static void main(String[] args)   // unit testing (optional)
	{
		Deque<Integer> d = new Deque<Integer>();
		
		d.addFirst(1);
		System.out.println(d.removeFirst());
		
		d.addFirst(2);
		System.out.println(d.removeLast());
		
		d.addFirst(3);
		d.addLast(4);
		d.addFirst(2);
		d.addFirst(1);
		
		for (Integer i : d)
			System.out.println(i);
		
		d.removeFirst();
		
		for (Integer i : d)
			System.out.println(i);
		
		d.removeLast();
		
		for (Integer i : d)
			System.out.println(i);
	}
	
}