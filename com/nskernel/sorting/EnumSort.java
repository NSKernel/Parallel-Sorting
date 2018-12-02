package com.nskernel.sorting;

import java.lang.*;
import java.util.*;

public class EnumSort  {
    private ArrayList<Integer> InputArray;
    private ArrayList<Integer> OutputArray;
    private int Count;

    public EnumSort(ArrayList<Integer> Values) {
        if (Values == null || Values.size() == 0){
            InputArray = new ArrayList<Integer>();
        }
        else {
            this.InputArray = new ArrayList<Integer>(Values);
            this.OutputArray = new ArrayList<Integer>();
            Count = Values.size();
            for (int i = 0; i < Count; i++) {
                this.OutputArray.add(0);
            }
        }
    }

    public ArrayList<Integer> Sort() {
        long TimeStart = System.currentTimeMillis();
        EnumSortWorker();
        long TimeEnd = System.currentTimeMillis();
        System.out.println("ParallelSort: Serial enumsort    \u001B[32m@\u001B[0m " + (TimeEnd - TimeStart) + " ms");
        return OutputArray;
    }

    private void EnumSortWorker() {
        for (int i = 0; i < Count; i++) {
            int k = 0;
            for (int j = 0; j < Count; j++) {
                if (InputArray.get(i) > InputArray.get(j) || (i > j && InputArray.get(i) == InputArray.get(j))) {
                    k += 1;
                }
            }
            OutputArray.set(k, InputArray.get(i));
        }
    }
}