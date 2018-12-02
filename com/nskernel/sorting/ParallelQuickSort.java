package com.nskernel.sorting;

import java.lang.*;
import java.util.*;
import java.util.concurrent.*;

public class ParallelQuickSort {
    private ArrayList<Integer> InputArray;
    private int Count;
    private ForkJoinPool ThreadPool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());


    protected static int ThreadCount = 1;

    public ParallelQuickSort(ArrayList<Integer> Values) {
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
        ThreadPool.invoke(new ParallelQuickSortWorker(InputArray, 0, Count - 1));
        long TimeEnd = System.currentTimeMillis();
        System.out.println("ParallelSort: Parallel quicksort \u001B[32m@\u001B[0m " + (TimeEnd - TimeStart) + " ms");
        ThreadPool.shutdown();
        return InputArray;
    }

    private class ParallelQuickSortWorker extends RecursiveAction {
        private ArrayList<Integer> InputArray;
        private int Low;
        private int High;

        
        public ParallelQuickSortWorker(ArrayList<Integer> Values, int low, int high) {
            InputArray = Values;
            Low = low;
            High = high;
        }

        public void compute() {
            QuickSortWorker(Low, High);
        }

        private void SerialQuickSortWorker(int low, int high) {
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
                SerialQuickSortWorker(low, j);
            if (i < high)
                SerialQuickSortWorker(i, high);
        }

        private void QuickSortWorker(int low, int high) {
            int i = low, j = high;
            int pivot = InputArray.get(low + (high-low)/2);
            List<ParallelQuickSortWorker> Tasks = new ArrayList<ParallelQuickSortWorker>();

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
            if (low < j) {
                if (ThreadCount++ < Runtime.getRuntime().availableProcessors())
                    Tasks.add(new ParallelQuickSortWorker(InputArray, low, j));
                else
                    SerialQuickSortWorker(low, j);
                //System.out.println("* Add Left from " + low + " to " + j);
            }
            if (i < high) {
                if (ThreadCount++ < Runtime.getRuntime().availableProcessors())
                    Tasks.add(new ParallelQuickSortWorker(InputArray, i, high));
                else
                    SerialQuickSortWorker(i, high);
                //System.out.println("/ Add Left from " + i + " to " + high);
            }
            try {
                if (!Tasks.isEmpty())
                    this.invokeAll(Tasks);
                return;
            } catch (Exception e) {
                // Ignore
                System.out.println("ParallelSort: Unexpected error in ParallelQuickSort");
            }
        }

        private void Exchange(int i, int j) {
            int Temp = InputArray.get(i);
            InputArray.set(i, InputArray.get(j));
            InputArray.set(j, Temp);
        }
    }
}