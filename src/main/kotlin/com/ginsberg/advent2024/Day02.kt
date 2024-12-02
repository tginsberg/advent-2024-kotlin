/*
 * Copyright 2024 Todd Ginsberg
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ginsberg.advent2024

/**
 * Advent of Code 2024, Day 2 - Red-Nosed Reports
 * Problem Description: http://adventofcode.com/2024/day/2
 * Blog Post/Commentary: https://todd.ginsberg.com/post/advent-of-code/2024/day2/
 */
class Day02(input: List<String>) {

    private val reports: List<List<Int>> = parse(input)

    fun solvePart1(): Int =
        reports.count { isSafe(it) }

    fun solvePart2(): Int =
        reports.count { isSafeDampened(it) }

    private fun isSafe(report: List<Int>): Boolean {
        val diffs = report.zipWithNext().map { (left, right) -> right - left }
        return diffs.all { it in 1..3 } || diffs.all { it in -3..-1 }
    }

    private fun isSafeDampened(report: List<Int>): Boolean =
        report.indices.any { removeThis ->
            isSafe(report.filterIndexed { index, _ -> removeThis != index })
        }

    private fun parse(input: List<String>): List<List<Int>> =
        input.map { row ->
            row.split(" ").map { it.toInt() }
        }

}