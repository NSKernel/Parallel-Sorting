# Parallel Sorting

An implementation of serial and parallel of quick sort, merge sort and enumeration sort in Java.

This project is the lab of Parallel Computing - Structures, Algorithms, Programming at Nanjing University instructed by Dr. Xie Lei. This project is opensourced under GPL v3.

### What does this project do
This project implements a serial and a parallel version of each of these three algorithms: quick sort, merge sort and enumeration sort. It will take a space-seperated input file, sort them and report the time consumed in each of these six sorting algorithms. Sorted numbers will be stored in `output1.txt` to `output6.txt`.

### Build and Run
To build this project, you need JDK and GNU Make. If you do not have GNU Make, you may still build it by wasting your time on manually compiling each of these `.java` files.

Suggested way of building this project is simply typing `make`. To run the project with a default test space, type `make run`. To run the project with your own data, type `make run INPUT_FILE=...` and replace the `...` with your file path. To cleanup, type `make clean`.
