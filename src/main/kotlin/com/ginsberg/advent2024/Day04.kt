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
 * Advent of Code 2024, Day 4 - Ceres Search
 * Problem Description: http://adventofcode.com/2024/day/4
 * Blog Post/Commentary: https://todd.ginsberg.com/post/advent-of-code/2024/day4/
 */
class Day04(private val input: List<String>) {

    fun solvePart1(): Int =
        input.flatMapIndexed { y, row ->
            row.mapIndexed { x, c ->
                if (c == 'X') {
                    ALL_DIRECTIONS.count { vector ->
                        vectorFind("XMAS", x, y, vector)
                    }
                } else 0
            }
        }.sum()

    fun solvePart2(): Int =
        input.flatMapIndexed { y, row ->
            row.mapIndexed { x, c ->
                if (c == 'A') {
                    CORNERS
                        .map { (dx, dy) -> input.safeAt(x + dx, y + dy) }
                        .joinToString("") in setOf("MMSS", "MSSM", "SSMM", "SMMS")
                } else false
            }
        }.count { it }

    private fun List<String>.safeAt(x: Int, y: Int): Char =
        if (y in indices && x in this[y].indices) this[y][x] else ' '

    private tailrec fun vectorFind(target: String, x: Int, y: Int, vector: Pair<Int, Int>): Boolean =
        when {
            target.isEmpty() -> true
            target.first() != input.safeAt(x, y) -> false
            else -> vectorFind(target.substring(1), x + vector.first, y + vector.second, vector)
        }

    private companion object {
        val ALL_DIRECTIONS = listOf(
            -1 to -1, -1 to 0, -1 to 1,
            0 to -1, 0 to 1,
            1 to -1, 1 to 0, 1 to 1
        )
        val CORNERS = listOf(-1 to -1, -1 to 1, 1 to 1, 1 to -1)
    }

}

