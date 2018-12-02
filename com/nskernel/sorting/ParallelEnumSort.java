package com.nskernel.sorting;

import java.lang.*;
import java.util.*;
import java.util.concurrent.*;

public class ParallelEnumSort  {
    private ArrayList<Integer> InputArray;
    private ArrayList<Integer> OutputArray;
    private int Count;

    private ExecutorService ThreadPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    public ParallelEnumSort(ArrayList<Integer> Values) {
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
        ArrayList<ParallelEnumSortWorker> Tasks = new ArrayList<ParallelEnumSortWorker>();
        long TimeStart = System.currentTimeMillis();
        for (int i = 0; i < Count; i++) {
            Tasks.add(new ParallelEnumSortWorker(InputArray, OutputArray, i, Count));
        }
        try {
            if (!Tasks.isEmpty())
                ThreadPool.invokeAll(Tasks);
        } catch (Exception e) {
            // Ignore
            System.out.println("ParallelSort: Unexpected error in ParallelEnumSort");
        }
        long TimeEnd = System.currentTimeMillis();
        System.out.println("ParallelSort: Parallel enumsort  \u001B[32m@\u001B[0m " + (TimeEnd - TimeStart) + " ms");
        ThreadPool.shutdown();
        return OutputArray;
    }

    private class ParallelEnumSortWorker implements Callable<Void> {
        private ArrayList<Integer> InputArray;
        private ArrayList<Integer> OutputArray;
        private int Index;
        private int Count;

        public ParallelEnumSortWorker(ArrayList<Integer> Values, ArrayList<Integer> OutputValues, int Index, int Count) {
            this.InputArray = Values;
            this.OutputArray = OutputValues;
            this.Index = Index;
            this.Count = Count;
        }

        public Void call() {
            EnumSortWorker();
            return null;
        }

        private void EnumSortWorker() {
            int k = 0;
            for (int j = 0; j < Count; j++) {
                if (InputArray.get(Index) > InputArray.get(j) || (Index > j && InputArray.get(Index) == InputArray.get(j))) {
                    k += 1;
                }
            }
            OutputArray.set(k, InputArray.get(Index));
        }
    }
    
}