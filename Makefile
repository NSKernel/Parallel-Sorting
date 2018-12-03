CLASS_PATH = com/nskernel/sorting
SRCS = $(shell find com -name "*.java")
CLASS_FILES = $(addprefix build/, $(addsuffix .class, $(basename $(SRCS))))
JAVAC = javac
JAR = jar
INPUT_FILE =

.PHONY: run clean 

build/ParallelSort.jar: build/ParallelSort.class
	$(JAR) -cfm build/ParallelSort.jar MANIFEST.MF -C build .

build/ParallelSort.class: $(CLASS_FILES) ParallelSort.java
	$(JAVAC) -cp build -d build ParallelSort.java

build/$(CLASS_PATH)/%.class: $(CLASS_PATH)/%.java
	$(JAVAC) -d build $<

run: build/ParallelSort.jar
ifeq ($(INPUT_FILE),)
	cat test.txt | java -jar build/ParallelSort.jar
else
	cat $(INPUT_FILE) | java -jar build/ParallelSort.jar
endif

clean:
	rm -rf build
	rm -rf output*.txt
