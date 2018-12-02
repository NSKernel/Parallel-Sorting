package com.nskernel.sorting;

import java.lang.*;
import java.util.*;

public class QuickSort  {
    private ArrayList<Integer> InputArray;
    private int Count;

    public QuickSort(ArrayList<Integer> Values) {
        if (Values == null || Values.size() == 0){
            InputArray = new ArrayList<Integer>();
        }
        else {
            this.InputArray = new ArrayList<Integer>(Values);
            Count = Values.size();
        }
    }

    public ArrayList<Integer> Sort() {
        long TimeStart = System.currentTimeMillis();
        QuickSortWorker(0, Count - 1);
        long TimeEnd = System.currentTimeMillis();
        System.out.println("ParallelSort: Serial quicksort   \u001B[32m@\u001B[0m " + (TimeEnd - TimeStart) + " ms");
        return InputArray;
    }

    private void QuickSortWorker(int low, int high) {
        int i = low, j = high;
        int pivot = InputArray.get(low + (high - low) / 2);

        while (i <= j) {
            while (InputArray.get(i) < pivot) {
                i++;
            }
            while (InputArray.get(j) > pivot) {
                j--;
            }

            if (i <= j) {
                Exchange(i, j);
                i++;
                j--;
            }
        }
        if (low < j)
            QuickSortWorker(low, j);
        if (i < high)
            QuickSortWorker(i, high);
    }

    private void Exchange(int i, int j) {
        int temp = InputArray.get(i);
        InputArray.set(i, InputArray.get(j));
        InputArray.set(j, temp);
    }
}