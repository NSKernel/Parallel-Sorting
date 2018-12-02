CLASS_PATH = com/nskernel/sorting
SRCS = $(shell find com -name "*.java")
CLASS_FILES = $(addprefix build/, $(addsuffix .class, $(basename $(SRCS))))
JAVAC = javac
INPUT_FILE =

.PHONY: run clean 

build/ParallelSort.class: $(CLASS_FILES) ParallelSort.java
	$(JAVAC) -cp build -d build ParallelSort.java

build/$(CLASS_PATH)/%.class: $(CLASS_PATH)/%.java
	$(JAVAC) -d build $<

run: build/ParallelSort.class
ifeq ($(INPUT_FILE),)
	cat test.txt | java -cp build ParallelSort
else
	cat $(INPUT_FILE) | java -cp build ParallelSort
endif

clean:
	rm -rf build
	rm -rf output1.txt
	rm -rf output2.txt
	rm -rf output3.txt
	rm -rf output4.txt
	rm -rf output5.txt
	rm -rf output6.txt
