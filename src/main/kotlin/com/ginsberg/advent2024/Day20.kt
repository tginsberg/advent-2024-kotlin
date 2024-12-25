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
 * Advent of Code 2024, Day 20 - Race Condition
 * Problem Description: http://adventofcode.com/2024/day/20
 * Blog Post/Commentary: https://todd.ginsberg.com/post/advent-of-code/2024/day20/
 */
class Day20(track: List<String>) {

    private val path: List<Point2D> = parseTrack(track)

    fun solvePart1(goal: Int): Int =
        findCheats(goal, 2)

    fun solvePart2(goal: Int): Int =
        findCheats(goal, 20)

    private fun findCheats(savingsGoal: Int, cheatTime: Int): Int =
        path.indices.sumOf { start ->
            (start + savingsGoal..path.lastIndex).count { end ->
                val physicalDistance = path[start].distanceTo(path[end])
                physicalDistance <= cheatTime && physicalDistance <= end - start - savingsGoal
            }
        }

    private fun parseTrack(input: List<String>): List<Point2D> {
        val end = input.findSingle('E')
        return mutableListOf(input.findSingle('S')).apply {
            while (last() != end) {
                add(
                    last()
                        .cardinalNeighbors()
                        .filter { input[it.y][it.x] != '#' }
                        .first { it != getOrNull(lastIndex - 1) }
                )
            }
        }
    }
}
