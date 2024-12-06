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
 * Advent of Code 2024, Day 6 - Guard Gallivant
 * Problem Description: http://adventofcode.com/2024/day/6
 * Blog Post/Commentary: https://todd.ginsberg.com/post/advent-of-code/2024/day6/
 */
class Day06(input: List<String>) {

    private val grid: List<CharArray> = input.map { it.toCharArray() }
    private val start: Point2D = grid
        .flatMapIndexed { y, row ->
            row.mapIndexed { x, c ->
                if (c == '^') Point2D(x, y) else null
            }
        }.filterNotNull().first()

    fun solvePart1(): Int = traverse().first.size

    fun solvePart2(): Int =
        traverse()
            .first
            .filterNot { it == start }
            .count { candidate ->
                grid[candidate] = '#'
                traverse().also { grid[candidate] = '.' }.second
            }

    private fun traverse(): Pair<Set<Point2D>, Boolean> {
        val seen = mutableSetOf<Pair<Point2D, Point2D>>()
        var location = start
        var direction = Point2D.NORTH

        while (grid[location] != null && (location to direction) !in seen) {
            seen += location to direction
            val next = location + direction

            if (grid[next] == '#') direction = direction.turn()
            else location = next
        }
        return seen.map { it.first }.toSet() to (grid[location] != null)
    }

    private operator fun List<CharArray>.get(at: Point2D): Char? =
        getOrNull(at.y)?.getOrNull(at.x)

    private operator fun List<CharArray>.set(at: Point2D, c: Char) {
        this[at.y][at.x] = c
    }

    private fun Point2D.turn(): Point2D =
        when (this) {
            Point2D.NORTH -> Point2D.EAST
            Point2D.EAST -> Point2D.SOUTH
            Point2D.SOUTH -> Point2D.WEST
            Point2D.WEST -> Point2D.NORTH
            else -> throw IllegalStateException("Bad direction: $this")
        }
}

