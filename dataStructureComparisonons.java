package datastructer;
/**
 *
 *
 * @author Doga Poyraz Tahan 
 * @since 28.12.2018
 * @version 1.0
 * 
 */


import java.util.Arrays;
import java.util.Random;


public class Doga_Poyraz_Tahan {    
    public static void main(String[] args) {
        int size = 40000; 
        int test_size = 10; 
        double [][] test_times = new double[test_size][3];
        
        for (int j = 0 ; j<test_size ; j++ ){
            // creating a random array with 'size'
            int [] randomArray = createRamdomArray(size);

            // copy all random array to 
            int[] arraySelection = copy_array(randomArray,size);
            int[] arrayHeap = copy_array(randomArray,size);
            int[] arrayJava = copy_array(randomArray,size);

            // testing 
            double elapsedTimeMilliSecondsJava = test_Java(arrayJava);
            double elapsedTimeMilliSecondsSelection = test_SelectionSort(arraySelection);
            double elapsedTimeMilliSecondsHeap = test_Heap(arrayHeap);

            test_times[j][0] = elapsedTimeMilliSecondsSelection;
            test_times[j][1] = elapsedTimeMilliSecondsHeap;
            test_times[j][2] = elapsedTimeMilliSecondsJava;                   
        }                
        print_results(test_times);

    }
    
    private static int[] createRamdomArray( int size ) {
        Random rand = new Random(); // Create a random object
        int[] randomArray = new int[size];
        for (int i = 0 ; i< size ; i++ ){
            randomArray[i] = rand.nextInt(10000000); // Assign random integers to array
        }
        return randomArray;
    }

    private static int[] copy_array(int[] randomArray, int size) {
        int[] array = new int [size];
        System.arraycopy(randomArray, 0, array, 0, size);
        return array;
    }

    private static double test_SelectionSort(int[] arraySelection) {
        double start = 0.0, finish = 0.0, time = 0.0;
        start = System.nanoTime();
        selectionSort(arraySelection);
        finish = System.nanoTime();
        return (finish - start) / 1000000.0;
    }

    private static double test_Heap(int[] arrayHeap) {
        double start = 0.0, finish = 0.0, time = 0.0;
        start = System.nanoTime();
        heapSort(arrayHeap);
        finish = System.nanoTime();
        return (finish - start) / 1000000.0;
    }

    private static double test_Java(int[] arrayJava) {
        double start = 0.0, finish = 0.0, time = 0.0;
        start = System.nanoTime();
        Arrays.sort(arrayJava); 
        finish = System.nanoTime();
        return (finish - start) / 1000000.0;
    }

    private static void selectionSort(int[] array) {       
        int size = array.length, index, temp, i, j;
        for (i = 0; i < size - 1; i++) 
        {
            index = i;
            for (j = i + 1; j < size; j++) 
                    if (array[j] < array[index])
                            index = j;
            temp = array[index];
            array[index] = array[i];
            array[i] = temp;
        }
    }

    private static void heapSort(int[] arrayHeap) {
        int size = arrayHeap.length, i;
        Heap heap = new Heap(); //create 
        for (int k = 0; k < size ; k++) //add element to heap
            heap.add( arrayHeap[k] );
        // Heap Sorting as removing
        for (i = 0; i < size; i++)
            heap.remove(); // Remove element from Heap
    }

    private static void print_results(double[][] test_times) {
        double elapsedTimeMilliSecondsSelection = 0.0, elapsedTimeMilliSecondsHeap = 0.0, elapsedTimeMilliSecondsJava = 0.0;
        for (int i = 0 ; i< test_times.length ; i++){            
            elapsedTimeMilliSecondsSelection += test_times[i][0];
            elapsedTimeMilliSecondsHeap += test_times[i][1];
            elapsedTimeMilliSecondsJava += test_times[i][2];
        }                                                              
        elapsedTimeMilliSecondsSelection /= test_times.length;
        elapsedTimeMilliSecondsHeap /= test_times.length;
        elapsedTimeMilliSecondsJava /= test_times.length;
        
        // Printing
        String time_selection = String.format("%.2f", elapsedTimeMilliSecondsSelection);
        String time_Heap = String.format("%.2f", elapsedTimeMilliSecondsHeap );
        String time_Java = String.format("%.2f", elapsedTimeMilliSecondsJava);
        
        for (int i = 0 ; i< test_times.length ; i++)           
            System.out.println((i + 1) + ". Selection " + String.format("%.3f", test_times[i][0]));        
        System.out.println("Average Sort Time for Selection Sort " + time_selection + " msec");
        System.out.println("---------------------------------\n\n");
                
                
        for (int i = 0 ; i< test_times.length ; i++)            
            System.out.println((i + 1) + ". Heap " + String.format("%.3f", test_times[i][1]));        
        System.out.println("Average Sort Time for Heap Sort " + time_Heap + " msec");            
        System.out.println("---------------------------------\n\n");
        
        for (int i = 0 ; i< test_times.length ; i++)            
            System.out.println((i + 1) + ". Java " + String.format("%.3f", test_times[i][2]));        
        System.out.println("Average Sort Time for Java Sort " + time_Java + " msec");       
        System.out.println("---------------------------------\n\n");
        
    }
    
}
