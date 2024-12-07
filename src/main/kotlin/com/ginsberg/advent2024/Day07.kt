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
 * Advent of Code 2024, Day 7 - Bridge Repair
 * Problem Description: http://adventofcode.com/2024/day/7
 * Blog Post/Commentary: https://todd.ginsberg.com/post/advent-of-code/2024/day7/
 */
class Day07(input: List<String>) {

    private val equations: List<List<Long>> = input
        .map { row -> row.split("""\D+""".toRegex()).map { it.toLong() } }

    private val operators: List<(Long, Long) -> Long> = listOf(
        { a, b -> a + b },
        { a, b -> a * b }
    )

    fun solvePart1(): Long =
        solve(operators)

    fun solvePart2(): Long =
        solve(operators + { a, b -> "$a$b".toLong() })

    private fun solve(validOperators: List<(Long, Long) -> Long>): Long =
        equations
            .filter { hasSolution(validOperators, it[0], it[1], it.subList(2, it.size)) }
            .sumOf { it.first() }

    private fun hasSolution(
        operators: List<(Long, Long) -> Long>,
        target: Long,
        sum: Long,
        remaining: List<Long>
    ): Boolean =
        when {
            remaining.isEmpty() -> target == sum
            sum > target -> false
            else -> operators.any { operator ->
                hasSolution(
                    operators,
                    target,
                    operator.invoke(sum, remaining[0]),
                    remaining.subList(1, remaining.size)
                )
            }
        }
}
