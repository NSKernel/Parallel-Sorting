package com.nskernel.sorting;

import java.lang.*;
import java.util.*;

public class MergeSort  {
    private ArrayList<Integer> InputArray;
    private ArrayList<Integer> Helper;
    private int Count;

    public MergeSort(ArrayList<Integer> Values) {
        if (Values == null || Values.size() == 0){
            InputArray = new ArrayList<Integer>();
        }
        else {
            this.InputArray = new ArrayList<Integer>(Values);
            Count = Values.size();
            this.Helper = new ArrayList<Integer>();
            for (int i = 0; i < Count; i++) {
                this.Helper.add(0);
            }
        }
    }

    public ArrayList<Integer> Sort() {
        long TimeStart = System.currentTimeMillis();
        MergeSortWorker(0, Count - 1);
        long TimeEnd = System.currentTimeMillis();
        System.out.println("ParallelSort: Serial mergesort   \u001B[32m@\u001B[0m " + (TimeEnd - TimeStart) + " ms");
        return InputArray;
    }

    private void MergeSortWorker(int low, int high) {
        if (low < high) {
            int middle = low + (high - low) / 2;
            MergeSortWorker(low, middle);
            MergeSortWorker(middle + 1, high);
            Merge(low, middle, high);
        }
    }

    private void Merge(int low, int middle, int high) {
        for (int i = low; i <= high; i++) {
            Helper.set(i, InputArray.get(i));
        }

        int i = low;
        int j = middle + 1;
        int k = low;
        while (i <= middle && j <= high) {
            if (Helper.get(i) <= Helper.get(j)) {
                InputArray.set(k, Helper.get(i));
                i++;
            } else {
                InputArray.set(k, Helper.get(j));
                j++;
            }
            k++;
        }
        while (i <= middle) {
            InputArray.set(k, Helper.get(i));
            k++;
            i++;
        }

    }
}