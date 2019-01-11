import java.util.ArrayList;
import java.util.Random;
import java.util.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class DogaPoyrazTahanHW3<E> {

    public static void main(String[] args) throws FileNotFoundException {
        Random r = new Random();

        PrintWriter pw = new PrintWriter(new File("./output.csv"));
        StringBuilder sb = new StringBuilder();

        //Sequence Size; Array List Search Time; Hash Map Search Time; Linked List Search Time; BST Search Time
        sb.append("Sequence Size; Array List Search Time; Hash Map Search Time; Linked List Search Time; BST Search Time");
        sb.append('\n');

        for (int size = 1000; size <= 100000; size += 1000) { // set size

            /* ******* CREATING THE RANDOM ARRAY ****** */
            int[] array = new int[size];
            for (int i = 1; i < array.length; i++) { // initialize
                array[i] = i;
            }
            for (int j = 0; j < array.length; j++) {  // Everyday I am shuffling :)
                int a = r.nextInt(size);
                int temp = array[j];
                array[j] = array[a];
                array[a] = temp;
            }

            /* ArrayList */
            ArrayList<Integer> arrayList = new ArrayList<Integer>();
            for (int i = 0; i < size; i++) {
                arrayList.add(i, array[i]); // adding numbers to ArrayList
            }

            /* ********* Array List Search ********* */
            long start = System.nanoTime();
            for (int t = 0; t < arrayList.size(); t++) {
                arrayList.contains(t);
            }
            long finish = System.nanoTime();

            double arrayListSearchTime = (finish - start) / 1000000.0;// arrayList search time

            HashMap<Integer, Integer> hash = new HashMap<Integer, Integer>();
            for (int q = 0; q < size; q++) {
                hash.put(q, array[q]);
            }

            /* ********* Hash Map Search ********* */
            long start2 = System.nanoTime();
            for (int t = 0; t < size; t++) {
                hash.get(array[t]);
            }
            long finish2 = System.nanoTime();// finishing time

            double hashSearchTime = (finish2 - start2) / 1000000.0;// Hash search time


            /* ********* Linked List Search ********* */
            LinkedList<Integer> linkedList = new LinkedList<Integer>();
            for (int q = 0; q < size; q++) {
                linkedList.add(q, array[q]);
            }

            long start1 = System.nanoTime();
            for (int t = 0; t < size; t++) {
                linkedList.contains(t);
            }
            long finish1 = System.nanoTime();

            double linkedListSearchTime = (finish1 - start1) / 1000000.0; // Linked List search time







            System.out.printf(size + " :" + arrayListSearchTime);
            sb.append(size+";"+arrayListSearchTime);

            System.out.printf("; " + hashSearchTime);
            sb.append(";" + hashSearchTime);

            System.out.printf(" ;" + linkedListSearchTime);
            sb.append(";" + linkedListSearchTime + '\n');

            System.out.println(" ");



        }

        pw.write(sb.toString());
        pw.close();

    }
 }