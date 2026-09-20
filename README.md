# DAA Assignment 1 — Fast Sorting & Selection Engine

Java implementation of MergeSort, QuickSort and QuickSelect with performance metrics,
a benchmark suite and JUnit 5 tests, for the "Design and Analysis of Algorithms" course.

## Project structure

src/main/java/daa/
algorithms/ MergeSort, QuickSort, QuickSelect
metrics/ Metrics — comparisons, recursion depth, timing
benchmark/ Benchmark, InputGenerator
src/test/java/daa/
SortingTest, QuickSelectTest
plots/ Time/Depth/Ratio vs n charts (PNG)
results.csv Benchmark output
REPORT.md Asymptotic analysis, recurrences, plots, discussion


## Requirements

- JDK 17+
- Maven 3.8+

## Build

mvn clean compile


## Run tests

mvn test


Runs all JUnit 5 tests: correctness against `Arrays.sort` (100 random arrays per algorithm),
edge cases (empty, single element, all-equal, sorted), QuickSort recursion-depth bound check,
and QuickSelect correctness against a sorted reference.

## Run the benchmark

mvn exec:java


Runs MergeSort, QuickSort and QuickSelect on n = 1,000 / 10,000 / 100,000 / 1,000,000,
for random / sorted / duplicate inputs, 5 repeats each (median time reported), and writes
`results.csv` in the project root.

## Algorithms summary

- **MergeSort** — single reusable buffer allocated once, cutoff to Insertion Sort at size ≤ 15, linear O(n) merge.
- **QuickSort** — random pivot, 3-way (Dutch National Flag) partition, recurses into the smaller side and loops over the larger side to bound recursion depth at O(log n).
- **QuickSelect** — reuses QuickSort's 3-way partition, continues only in the side containing k ("one side only"), throws `IllegalArgumentException` on invalid input.

See `REPORT.md` for asymptotic bounds, recurrence analysis (Master Theorem) and discussion of results.