import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
	private Item[] items;
	private int sz;
	
	public RandomizedQueue()                 // construct an empty randomized queue
	{
		sz = 0;
		items = (Item[]) new Object[1];
	}
	
	public boolean isEmpty()                 // is the randomized queue empty?
	{
		return sz == 0;
	}
	
	public int size()                        // return the number of items on the randomized queue
	{
		return sz;
	}
	
	public void enqueue(Item item)           // add the item
	{
		if (item == null)
			throw new IllegalArgumentException();
		
		if (items.length == sz)
			resize(sz * 2);
		items[sz++] = item;
			
	}
	
	private void resize(int size) {
		Item[] tp = (Item[]) new Object[size];
		for (int i = 0; i < sz; i++)
				tp[i] = items[i];
		items = tp;
	}
	
	public Item dequeue()                    // remove and return a random item
	{
		if (isEmpty())
			throw new NoSuchElementException();
		
		int index = StdRandom.uniform(sz);
		Item item = items[index];
		if (index != sz - 1) // 最后那个元素补上或者移除最后那个元素
			items[index] = items[sz - 1];
		items[sz- 1] = null;
		sz--;
		if (sz > 0 && sz <= items.length / 4)
			resize(items.length / 2);
		return item;
	}
	
	public Item sample()                     // return a random item (but do not remove it)
	{
		if (isEmpty())
			throw new NoSuchElementException();
		
		return items[StdRandom.uniform(sz)];
	}
	
	public Iterator<Item> iterator()         // return an independent iterator over items in random order
	{
		return new RandomizedQueueIterator();
	}
	
	private class RandomizedQueueIterator implements Iterator<Item> 
	{
		private int i;

        public RandomizedQueueIterator() {
            i = sz - 1;
        }

        public boolean hasNext() {
            return i >= 0;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext())
            		throw new NoSuchElementException();
            return items[i--];
        }
	}
	
	public static void main(String[] args)   // unit testing (optional)
	{
		RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();
		rq.enqueue(1);
		System.out.println(rq.sz);
		rq.enqueue(2);
		System.out.println(rq.sz);
		rq.dequeue();
		System.out.println(rq.sz);
		rq.enqueue(3);
		rq.enqueue(4);
		System.out.println(rq.sz);
		System.out.println(rq.dequeue());
		System.out.println(rq.sz);
		System.out.println(rq.sample());
		System.out.println(rq.sz);
		for (int i : rq)
			System.out.println(i);
 	}
}
