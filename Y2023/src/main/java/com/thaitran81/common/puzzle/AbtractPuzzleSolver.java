package com.thaitran81.common.puzzle;

import com.thaitran81.common.Constants;
import com.thaitran81.common.Importer;
import com.thaitran81.common.PuzzleInput;
import com.thaitran81.common.PuzzleResult;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.StopWatch;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author ThaiTran81
 * @since 04/12/2023
 */
public abstract class AbtractPuzzleSolver <I extends PuzzleInput, R extends PuzzleResult> implements Solver {

    private final Importer importer = new Importer();
    private List<String> rawTestInput;
    private List<String> rawActualInput;

    private I parsedTestInput;
    private I parsedActualInput;

    private boolean needTest = true;

    protected StopWatch stopWatch = StopWatch.create();

    protected abstract int year();

    protected abstract int day();

    protected abstract I parseInput(List<String> lines);

    @Override
    public void solve() throws Exception {
        importResources();

        timing(this::parsing, "Parsing input execution time:");

        prepareAndSolvePart1();
        prepareAndSolvePart2();

    }

    @Override
    public void needTest(boolean needTest) {
        this.needTest = needTest;
    }

    private void importResources() throws Exception {
        rawTestInput = importer.importTest(year(), day());
        rawActualInput = importer.importActual(year(), day());
    }

    private void timing(Runnable task, String title) {
        stopWatch.start();
        task.run();
        stopWatch.stop();
        System.out.println(StringUtils.joinWith(Constants.SPACE_DELIMITER, title, stopWatch.getTime(TimeUnit.MILLISECONDS), Constants.MILLISECONDS));
        stopWatch.reset();
    }

    private void parsing() {
        needTest &= CollectionUtils.isNotEmpty(rawTestInput);
        if (needTest) {
            parsedTestInput = parseInput(rawTestInput);
        }
        parsedActualInput = parseInput(rawActualInput);
    }

    private void prepareAndSolvePart1() {
        System.out.println("----------Part 1 result----------");
        R testResult;
        if (needTest) {
            testResult = solvePuzzlePart1(parsedTestInput);
            System.out.print("Test: ");
            displayResult(testResult);
        } else {
            timing(() -> {
                R result = solvePuzzlePart1(parsedActualInput);
                System.out.print("Actual: ");
                displayResult(result);
            }, "Part 1 execution time:");
        }
    }


    private void prepareAndSolvePart2() {
        System.out.println("----------Part 2 result----------");
        R testResult;
        if (needTest) {
            testResult = solvePuzzlePart2(parsedTestInput);
            System.out.print("Test: ");
            displayResult(testResult);
        } else {
            timing(() -> {
                R result = solvePuzzlePart2(parsedActualInput);
                System.out.print("Actual: ");
                displayResult(result);
            }, "Part 2 execution time:");
        }
    }

    protected R solvePuzzlePart1(I input) {
        System.err.println("Solution for part 1 is not yet implemented!");
        return null;
    }

    protected R solvePuzzlePart2(I input) {
        System.err.println("Solution for part 2 is not yet implemented!");
        return null;
    }



    protected void displayResult(R result) {
        System.out.println(result);
    }
}
