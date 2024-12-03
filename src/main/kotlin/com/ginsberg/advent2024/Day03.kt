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
 * Advent of Code 2024, Day 3 - Mull It Over
 * Problem Description: http://adventofcode.com/2024/day/3
 * Blog Post/Commentary: https://todd.ginsberg.com/post/advent-of-code/2024/day3/
 */
class Day03(private val input: String) {

    fun solvePart1(): Int =
        executeMuls(input)

    fun solvePart2(): Int =
        executeDisabled(input)

    private fun executeMuls(instructions: String): Int =
        """mul\((\d{1,3}),(\d{1,3})\)"""
            .toRegex()
            .findAll(instructions)
            .sumOf { match ->
                match.groupValues
                    .drop(1)
                    .map { it.toInt() }
                    .reduce(Int::times)
            }

    private fun executeDisabled(instructions: String): Int =
        """(^|do\(\)).*?($|don't\(\))"""
            .toRegex()
            .findAll(instructions)
            .sumOf { executeMuls(it.value) }
}

