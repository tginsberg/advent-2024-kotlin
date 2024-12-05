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
 * Advent of Code 2024, Day 5 - Print Queue
 * Problem Description: http://adventofcode.com/2024/day/5
 * Blog Post/Commentary: https://todd.ginsberg.com/post/advent-of-code/2024/day5/
 */
class Day05(input: List<String>) {

    private val rules: Set<String> = input
        .takeWhile { it.isNotEmpty() }
        .toSet()
    private val updates: List<List<String>> = input
        .dropWhile { it.isNotEmpty() }
        .drop(1)
        .map { row -> row.split(",") }

    private val comparator: Comparator<String> = Comparator { a, b ->
        when {
            "$a|$b" in rules -> -1
            "$b|$a" in rules -> 1
            else -> 0
        }
    }

    fun solvePart1(): Int =
        updates
            .map { formatCorrectly(it) }
            .filter { it.first == it.second }
            .sumOf { it.second.midpoint().toInt() }

    fun solvePart2(): Int =
        updates
            .map { formatCorrectly(it) }
            .filterNot { it.first == it.second }
            .sumOf { it.second.midpoint().toInt() }

    private fun formatCorrectly(update: List<String>): Pair<List<String>, List<String>> =
        update to update.sortedWith(comparator)
}

