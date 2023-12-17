package com.thaitran81;

import com.google.common.reflect.ClassPath;
import com.thaitran81.common.puzzle.Puzzle;
import com.thaitran81.common.puzzle.Solver;

import java.io.IOException;
import java.util.Optional;

public class Main {
    public static void main(String[] args) throws Exception {
        final int year = 2023;
        final int day = 1;
        Boolean needTest = false;
//        if (args.length == 3) {
//            needTest = Boolean.valueOf(args[2]);
//        }

        Optional<Solver> solver = findSolver(year, day, needTest);

        if (solver.isPresent()) {
            solver.get().solve();
        } else {
            System.err.println("Cannot find the Solver!!!");
        }
    }

    private static Optional<Solver> findSolver(int year, int day, Boolean needTest) throws IOException {
        return ClassPath.from(ClassLoader.getSystemClassLoader())
                .getTopLevelClassesRecursive("com.thaitran81")
                .stream()
                .map(ClassPath.ClassInfo::load)
                .filter(clazz -> {
                    if (!clazz.isAnnotationPresent(Puzzle.class)) {
                        return false;
                    }

                    Puzzle puzzle = clazz.getAnnotation(Puzzle.class);
                    return puzzle.year() == year && puzzle.day() == day;
                })
                .findFirst()
                .map(clazz -> {
                    try {
                        Solver solver = (Solver) clazz.getDeclaredConstructor().newInstance();
                        solver.needTest(needTest);
                        return solver;
                    } catch (Exception e) {
                        System.err.println("Exception when instantiating a PuzzleSolver");
                        e.printStackTrace();
                        return null;
                    }
                });
    }
}