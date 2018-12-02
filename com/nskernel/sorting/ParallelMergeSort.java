package com.nskernel.sorting;

import java.lang.*;
import java.util.*;
import java.util.concurrent.*;

public class ParallelMergeSort  {
    private ArrayList<Integer> InputArray;
    private ArrayList<Integer> Helper;
    private int Count;
    private ForkJoinPool ThreadPool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());

    protected static int ThreadCount = 1;

    public ParallelMergeSort(ArrayList<Integer> Values) {
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
        ThreadPool.invoke(new ParallelMergeSortWorker(InputArray, Helper, 0, Count - 1));
        long TimeEnd = System.currentTimeMillis();
        System.out.println("ParallelSort: Parallel mergesort \u001B[32m@\u001B[0m " + (TimeEnd - TimeStart) + " ms");
        ThreadPool.shutdown();
        return InputArray;
    }

    private class ParallelMergeSortWorker extends RecursiveAction {
        private ArrayList<Integer> InputArray;
        private ArrayList<Integer> Helper;
        private int Low;
        private int High;

        public ParallelMergeSortWorker(ArrayList<Integer> Values, ArrayList<Integer> HelperValues, int low, int high) {
            InputArray = Values;
            Helper = HelperValues;
            Low = low;
            High = high;
        }

        public void compute() {
            MergeSortWorker(Low, High);
        }

        private void SerialMergeSortWorker(int low, int high) {
            if (low < high) {
                int middle = low + (high - low) / 2;
                SerialMergeSortWorker(low, middle);
                SerialMergeSortWorker(middle + 1, high);
                Merge(low, middle, high);
            }
        }

        private void MergeSortWorker(int low, int high) {
            List<ParallelMergeSortWorker> Tasks = new ArrayList<ParallelMergeSortWorker>();

            if (low < high) {
                int middle = low + (high - low) / 2;

                if (ThreadCount++ < Runtime.getRuntime().availableProcessors())
                    Tasks.add(new ParallelMergeSortWorker(InputArray, Helper, low, middle));
                else
                    SerialMergeSortWorker(low, middle);
                if (ThreadCount++ < Runtime.getRuntime().availableProcessors())
                    Tasks.add(new ParallelMergeSortWorker(InputArray, Helper, middle + 1, high));
                else
                    SerialMergeSortWorker(middle + 1, high);

                if (!Tasks.isEmpty()) {
                    invokeAll(Tasks);
                }

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

    
}