import java.util.Arrays;
public final class MaxHeap<T extends Comparable <? super T>>
    implements MaxHeapInterface<T>
{
    private T[] heap;   // Array of Heap entries
    private int lastIndex;  // Index of last entry
    private boolean initialized = false;
    private static final int DEFAULT_CAPACITY = 100;
    private static final int MAX_CAPACITY = 10000;

    public MaxHeap(){
        this (DEFAULT_CAPACITY); // Call next constructor
    } // end default constructor

    public MaxHeap(int initialCapacity){
        // Is initializeCapacity too small?
        if (initialCapacity < DEFAULT_CAPACITY)
            initialCapacity = DEFAULT_CAPACITY;
        else // Is initialCapacity too big?
            checkCapacity(initialCapacity);

        // The cast is safe because the new array contains all null entries
        @SuppressWarnings("unchecked")
        T[] tempHeap = (T[]) new Comparable[initialCapacity +1];
        heap = tempHeap;
        lastIndex = 0;
        initialized = true;
    } // end constructor

    @Override
    public void add(T newEntry) {
        checkIntegrity();
        int newIndex = lastIndex + 1;
        int parentIndex = newIndex / 2;
        while ((parentIndex > 0) && newEntry.compareTo(heap[parentIndex]) > 0){
            heap[newIndex] = heap[parentIndex];
            newIndex = parentIndex;
            parentIndex = newIndex / 2;
        } // end while
        heap[newIndex] = newEntry;
        lastIndex++;
        checkCapacity(heap.length);
    } // end add

    @Override
    public T removeMax()
    {
        checkIntegrity();
        T root = null;
        if(!isEmpty()){
            root = heap[1];
            heap[1] = heap[lastIndex];  // form a semiheap
            lastIndex--;                // decrease size
            reheap(1);                  // transform to a heap
        } // end if
        return null;
    } // end removeMax

    @Override
    public T getMax()
    {
        checkIntegrity();
        T root = null;
        if (!isEmpty())
            root = heap[1];
        return root;
    }

    @Override
    public boolean isEmpty() {
        return lastIndex < 1;
    } // end isEmpty

    @Override
    public int getSize() {
        return lastIndex;
    } // end getSize

    @Override
    public void clear()
    {
        checkIntegrity();
        while (lastIndex > -1){
            heap[lastIndex] = null;
            lastIndex--;
        } // end while
        lastIndex = 0;
    } // end clear

    /**
     * This method checks the capacity of the array
     * throw an exception if the capacity exceed MAX_CAPACITY
     */
    private void checkCapacity (int capacity){
        if (capacity > MAX_CAPACITY)
        {
            throw new IllegalStateException
                    ("Requested capacity exceeds maximum of " + MAX_CAPACITY + ".");
        }
    }

    /**
     * Throws an exception if this object is not initialized
     */
    private void checkIntegrity(){
        if(!initialized){
            throw new SecurityException("Can not contain null data");
        }
    }

    private void reheap (int rootIndex){
        boolean done = false;
        T orphan = heap[rootIndex];
        int leftChildIndex = 2 * rootIndex;

        while (!done && (leftChildIndex <= lastIndex))
        {
            int largerChildIndex = leftChildIndex;  // assume larger
            int rightChildIndex = leftChildIndex + 1;
            if ( (rightChildIndex <= lastIndex) &&
                heap[rightChildIndex].compareTo(heap[largerChildIndex]) > 0){
                largerChildIndex = rightChildIndex;
            } // end if

            if (orphan.compareTo(heap[largerChildIndex]) < 0)
            {
                heap[rootIndex] = heap[largerChildIndex];
                rootIndex = largerChildIndex;
                leftChildIndex = 2 * rootIndex;
            }
            else
                done = true;
        } // end reheap
    }

}
