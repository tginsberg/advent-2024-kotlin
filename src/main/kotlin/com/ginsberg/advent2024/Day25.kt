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
 * Advent of Code 2024, Day 25 - Code Chronicle
 * Problem Description: http://adventofcode.com/2024/day/25
 * Blog Post/Commentary: https://todd.ginsberg.com/post/advent-of-code/2024/day25/
 */
class Day25(private val input: List<String>) {

    fun solvePart1(): Int =
        parse(input).let { (locks, keys) ->
            locks.sumOf { lock ->
                keys.count { key -> key fits lock }
            }
        }

    private infix fun IntArray.fits(lock: IntArray): Boolean =
        indices.all { this[it] + lock[it] <= 5 }

    private fun parse(input: List<String>): Pair<List<IntArray>, List<IntArray>> {
        val locks = mutableListOf<IntArray>()
        val keys = mutableListOf<IntArray>()

        input.chunked(8).map { pattern ->
            val pins = (0..4).map { col ->
                (0..6).count { row ->
                    pattern[row][col] == '#'
                } - 1
            }.toIntArray()
            if (pattern[0][0] == '#') locks.add(pins) else keys.add(pins)
        }
        return locks to keys
    }
}
