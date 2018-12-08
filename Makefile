CLASS_PATH = com/nskernel/sorting
SRCS = $(shell find com -name "*.java")
CLASS_FILES = $(addprefix build/, $(addsuffix .class, $(basename $(SRCS))))
JAVAC = javac
JAR = jar
INPUT_FILE =

.PHONY: run clean 

build/ParallelSort.jar: build/ParallelSort.class
	@$(JAR) -cfm build/ParallelSort.jar MANIFEST.MF -C build .
	@echo "  JAR    build"

build/ParallelSort.class: $(CLASS_FILES) ParallelSort.java
	@$(JAVAC) -cp build -d build ParallelSort.java
	@echo "  JAVAC  ParallelSort.java"

build/$(CLASS_PATH)/%.class: $(CLASS_PATH)/%.java
	@$(JAVAC) -d build $<
	@echo "  JAVAC " $<

run: build/ParallelSort.jar
ifeq ($(INPUT_FILE),)
	@echo "  Running with default input random.txt"
	@echo
	@cat random.txt | java -jar build/ParallelSort.jar
else
	@echo "  Running with "$(INPUT_FILE)
	@echo
	@cat $(INPUT_FILE) | java -jar build/ParallelSort.jar
endif

clean:
	@rm -rf build
	@echo "  REMOVED  build"
	@rm -rf output*.txt
	@echo "  REMOVED  output files"
