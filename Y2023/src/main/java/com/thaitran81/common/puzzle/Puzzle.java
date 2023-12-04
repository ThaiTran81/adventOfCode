package com.thaitran81.common.puzzle;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author ThaiTran81
 * @since 04/12/2023
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Puzzle {
    /**
     * return the year of the Advent of code organized
     * @return the year of the Advent of code organized
     */
    int year();

    /**
     * return the day (or the puzzle id) of the current Advent of code
     * @return the day (or the puzzle id) of the current Advent of code
     */
    int day();
}
