import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Class responsible for reading the input file and inserting into the max heap
 */
public class InputFileReader {

    public static void main(String[] args) {

        List<Integer> list = InputFileReader.getListOfIntegers();

        MaxHeap<Integer> maxHeap = new MaxHeap<Integer>(100);

        // Add Integers into max Heap
        for (Integer entry: list) {
            maxHeap.add(entry);
            System.out.println(entry);
        }

        // First 10 sequential
        outputFirst10Sequential(maxHeap);

        System.out.println();
        System.out.println();
        System.out.println();

        // First 10 smart
        Integer[] smartList = InputFileReader.getListOfIntegers().toArray(new Integer[0]);
        MaxHeap<Integer> smartHeap = new MaxHeap<Integer>(smartList);
        outputFirst10Smart(smartHeap);
    }

    // Use File reader to input data into array list
    static List<Integer> getListOfIntegers() {
        File file = new File("data.txt");
        Scanner input = null;
        List<Integer> list = new ArrayList<Integer>();
        try {
            input = new Scanner(file);
            while (input.hasNextLine()) {
                list.add(Integer.parseInt(input.nextLine()));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

    static void outputFirst10Sequential(MaxHeap<Integer> heap) {

       System.out.print("Heap built using sequential insertions: ");
       heap.printFirst10();
       System.out.println();

       System.out.print("Number of swaps in the heap creation: " + heap.numSwaps);
       System.out.println();

       // Remove first 10 and print
       for (int i = 0; i < 10; i++) {
           heap.removeMax();
       }
       System.out.print("Heap after 10 removals: ");
       heap.printFirst10();
    }

    static void outputFirst10Smart(MaxHeap<Integer> heap) {

        System.out.print("Heap built using sequential insertions: ");
        heap.printFirst10();
        System.out.println();

        System.out.print("Heap built using optimal method: ");
        heap.printFirst10();
        System.out.println();

        System.out.print("Number of swaps in the heap creation: " + heap.numSmartSwaps);
        System.out.println();

        // Remove first 10 and print
        for (int i = 0; i < 10; i++) {
            heap.removeMax();
        }
        System.out.print("Heap after 10 removals: ");
        heap.printFirst10();
    }
}
