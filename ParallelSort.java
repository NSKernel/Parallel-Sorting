import java.lang.*;
import java.util.*;
import java.io.*;

import com.nskernel.sorting.*;

public class ParallelSort{ 
    public static void main(String[] args) {
        ArrayList<Integer> InputArray = new ArrayList<Integer>();
        ArrayList<Integer> OutputArray;
        Scanner StdinScanner = new Scanner(System.in);

        // Scan in
        while(StdinScanner.hasNext()) {
            InputArray.add(Integer.parseInt(StdinScanner.next()));
        }

        StdinScanner.close();

        // Serial quicksort 
        QuickSort QSS = new QuickSort(InputArray);
        OutputArray = QSS.Sort();
        File Output1 = new File("output1.txt");
        try {
            FileWriter Writer1 = new FileWriter(Output1);
            for (int i = 0; i < OutputArray.size(); i++) {
                Writer1.write(OutputArray.get(i) + " ");
            }
            Writer1.close();
        } catch (Exception e) {
            // Ignore
            System.out.println("ParallelSort: Failed to write to file");
        }
        
        // Parallel quicksort
        ParallelQuickSort PQSS = new ParallelQuickSort(InputArray);
        OutputArray = PQSS.Sort();
        File Output2 = new File("output2.txt");
        try {
            FileWriter Writer2 = new FileWriter(Output2);
            for (int i = 0; i < OutputArray.size(); i++) {
                Writer2.write(OutputArray.get(i) + " ");
            }
            Writer2.close();
        } catch (Exception e) {
            // Ignore
            System.out.println("ParallelSort: Failed to write to file");
        }

        // Serial mergesort
        MergeSort MSS = new MergeSort(InputArray);
        OutputArray = MSS.Sort();
        File Output3 = new File("output3.txt");
        try {
            FileWriter Writer3 = new FileWriter(Output3);
            for (int i = 0; i < OutputArray.size(); i++) {
                Writer3.write(OutputArray.get(i) + " ");
            }
            Writer3.close();
        } catch (Exception e) {
            // Ignore
            System.out.println("ParallelSort: Failed to write to file");
        }

        // Parallel mergesort
        ParallelMergeSort PMSS = new ParallelMergeSort(InputArray);
        OutputArray = PMSS.Sort();
        File Output4 = new File("output4.txt");
        try {
            FileWriter Writer4 = new FileWriter(Output4);
            for (int i = 0; i < OutputArray.size(); i++) {
                Writer4.write(OutputArray.get(i) + " ");
            }
            Writer4.close();
        } catch (Exception e) {
            // Ignore
            System.out.println("ParallelSort: Failed to write to file");
        }

        // Serial enumsort
        EnumSort ESS = new EnumSort(InputArray);
        OutputArray = ESS.Sort();
        File Output5 = new File("output5.txt");
        try {
            FileWriter Writer5 = new FileWriter(Output5);
            for (int i = 0; i < OutputArray.size(); i++) {
                Writer5.write(OutputArray.get(i) + " ");
            }
            Writer5.close();
        } catch (Exception e) {
            // Ignore
            System.out.println("ParallelSort: Failed to write to file");
        }

        // Parallel enumsort
        ParallelEnumSort PESS = new ParallelEnumSort(InputArray);
        OutputArray = PESS.Sort();
        File Output6 = new File("output6.txt");
        try {
            FileWriter Writer6 = new FileWriter(Output6);
            for (int i = 0; i < OutputArray.size(); i++) {
                Writer6.write(OutputArray.get(i) + " ");
            }
            Writer6.close();
        } catch (Exception e) {
            // Ignore
            System.out.println("ParallelSort: Failed to write to file");
        }
    }
}