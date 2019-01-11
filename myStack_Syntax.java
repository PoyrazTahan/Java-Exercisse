import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @Author: Berk Gökberk, Doga Poyraz Tahan   041503044
 * @Date: 11-11-18
 * Stack Implemenataion
 */
public class myStack<Item> implements Iterable<Item> {
    private Item[] a; // array of items
    private int n; // number of elements on stack
    private int CAPACITY = 100;

    public myStack() {
        a = (Item[]) new Object[CAPACITY];
        n = 0;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    private void resize(int capacity) {
        assert capacity >= n;
        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            temp[i] = a[i];
        }
        a = temp;
    }

    public void push(Item item) {
        if (n == a.length) {
            resize(2 * a.length);
        }
        a[n++] = item; // add item
    }

    public Item pop() {
        if (isEmpty())
            throw new NoSuchElementException("Stack underflow");
        Item item = a[n - 1];
        a[n - 1] = null; // to avoid loitering
        n--;
        // shrink size of array if necessary
        if (n > 0 && n == a.length / 4)
            resize(a.length / 2);
        return item;
    }

    public Item peek() {
        if (isEmpty())
            throw new NoSuchElementException("Stack underflow");
        return a[n - 1];
    }

    public Iterator<Item> iterator() {
        return new ReverseArrayIterator();
    }

    public void printStack(){
        String str = "Stack Contents: [top] ";
        for(int i=a.length-1;i>=0; i--)
            if (a[i]!=null)
                str += a[i] + " ";
        /*
        for(Item c: a){
            if(c!=null)
                str += c + " ";
        }
        */
        System.out.println(str + "[bottom]");
    }

    private class ReverseArrayIterator implements Iterator<Item> {
        private int i;

        public ReverseArrayIterator() {
            i = n - 1;
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
            return a[i--];
        }
    }

}
