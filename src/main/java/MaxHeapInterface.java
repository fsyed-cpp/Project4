/**
 * Max Heap Interface - Generic implementation using type T
 */
public interface MaxHeapInterface<T extends Comparable<? super T>> {

    /**
     * Creates a new entry in the Max Heap
     * @param newEntry The new entry to be added
     */
    public void add(T newEntry);

    /**
     * Removes the max heap root node
     */
    public T removeMax();

    /**
     * Returns the value of the max heap root node
     */
    public T getMax();

    /**
     * Checks if the max heap is empty
     */
    public boolean isEmpty();

    /**
     * Returns the size of the max heap
     */
    public int getSize();

    /**
     * Deletes the max heap
     */
    public void clear();
}
