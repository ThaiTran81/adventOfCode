package com.thaitran81.impl.day01;

import com.thaitran81.common.PuzzleInput;
import com.thaitran81.common.PuzzleResult;
import com.thaitran81.common.puzzle.AbtractPuzzleSolver;
import com.thaitran81.common.puzzle.Puzzle;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author ThaiTran81
 * @since 17/12/2023
 */
@Puzzle(year = SolverDay01.YEAR, day = SolverDay01.DAY)
public class SolverDay01 extends AbtractPuzzleSolver<SolverDay01.Input, SolverDay01.Result> {
    public static final int YEAR = 2023;
    public static final int DAY = 1;

    @Override
    protected int year() {
        return YEAR;
    }

    @Override
    protected int day() {
        return DAY;
    }

    @Override
    protected Input parseInput(List<String> lines) {
        var input = new Input();
        input.lines.addAll(lines);
        return input;
    }

    @Override
    protected Result solvePuzzlePart1(Input input) {

        Result result = new Result();
        int total = 0;

        for (String line : input.lines) {
            if (line.isEmpty()) {
                break;
            }

            int index = 0;
            int size = line.length();
            StringBuilder numStrBuilder = new StringBuilder();
            int lengthOfNumber = 0;

            while (index < size) {
                char character = line.charAt(index);
                if (character >= '0' && character <= '9') {
                    numStrBuilder.append(character);
                    lengthOfNumber++;
                }

                index++;
            }

            if (lengthOfNumber == 0) {
                continue;
            }

            char firstDigit = numStrBuilder.charAt(0);
            char lastDigit = numStrBuilder.charAt(lengthOfNumber - 1);
            String number = String.valueOf(firstDigit) + lastDigit;
            total += Integer.parseInt(number);
        }

        result.setTotal(total);
        return result;
    }

    static class Input implements PuzzleInput {
        List<String> lines = new ArrayList<>();
    }

    static class Result implements PuzzleResult {
        int total;

        @Override
        public String toString() {
            return "Result: " + total;
        }

        public void setTotal(int total) {
            this.total = total;
        }
    }
}
