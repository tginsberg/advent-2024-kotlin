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

import java.util.PriorityQueue

/**
 * Advent of Code 2024, Day 10 - Hoof It
 * Problem Description: http://adventofcode.com/2024/day/10
 * Blog Post/Commentary: https://todd.ginsberg.com/post/advent-of-code/2024/10/
 */
class Day10(input: List<String>) {

    private val grid: List<IntArray> = input.map { row ->
        row.map { it.digitToInt() }.toIntArray()
    }

    fun solvePart1(): Int = scoreTrails(true)

    fun solvePart2(): Int = scoreTrails(false)

    private fun scoreTrails(memory: Boolean): Int =
        grid.mapIndexed { y, row ->
            row.mapIndexed { x, i ->
                if (i == 0) findPaths(Point2D(x, y), memory) else 0
            }.sum()
        }.sum()

    private operator fun List<IntArray>.contains(at: Point2D): Boolean =
        at.y in indices && at.x in get(at.y).indices

    private operator fun List<IntArray>.get(at: Point2D): Int =
        this[at.y][at.x]

    private fun findPaths(start: Point2D, memory: Boolean): Int {
        val queue = mutableListOf(start)
        val seen = mutableSetOf<Point2D>()
        var found = 0

        while (queue.isNotEmpty()) {
            val place = queue.removeFirst()
            if (place !in seen) {
                if (memory) seen += place
                if (grid[place] == 9) found++
                else {
                    queue.addAll(
                        place.cardinalNeighbors()
                            .filter { it in grid }
                            .filter { grid[it] == grid[place] + 1 }
                    )
                }
            }
        }
        return found
    }

}
