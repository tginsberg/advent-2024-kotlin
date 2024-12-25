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
 * Advent of Code 2024, Day 18 - RAM Run
 * Problem Description: http://adventofcode.com/2024/day/18
 * Blog Post/Commentary: https://todd.ginsberg.com/post/advent-of-code/2024/day18/
 */
class Day18(input: List<String>) {

    private val bytes: List<Point2D> = input.map { Point2D.of(it) }

    fun solvePart1(range: Point2D, fallingBytes: Int): Int =
        traverse(range, bytes.take(fallingBytes).toSet())
            ?: throw IllegalStateException()

    fun solvePart2(range: Point2D): String =
        bytes
            .withIndex()
            .toList()
            .binarySearchFirst { (index, _) ->
                traverse(range, bytes.take(index + 1).toSet()) == null
            }.let { (_, value) ->
                "${value.x},${value.y}"
            }

    private fun traverse(end: Point2D, barriers: Set<Point2D>): Int? {
        val queue = mutableListOf(Point2D.ORIGIN to 0)
        val seen = mutableSetOf<Point2D>()

        while (queue.isNotEmpty()) {
            val (place, cost) = queue.removeFirst()

            if (place == end) return cost
            else if (seen.add(place)) {
                place.cardinalNeighbors()
                    .filter { it.inRange(end) }
                    .filterNot { it in barriers }
                    .forEach { queue.add(it to cost + 1) }
            }
        }
        return null
    }

    private fun Point2D.inRange(end: Point2D): Boolean =
        x in 0..end.x && y in 0..end.y
}
