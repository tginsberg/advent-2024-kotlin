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
 * Advent of Code 2024, Day 16 - Reindeer Maze
 * Problem Description: http://adventofcode.com/2024/day/16
 * Blog Post/Commentary: https://todd.ginsberg.com/post/advent-of-code/2024/16/
 */
class Day16(private val maze: List<String>) {

    private val start: Point2D = maze.find('S')
    private val end: Point2D = maze.find('E')

    fun solvePart1(): Int = traverseMaze()

    fun solvePart2(): Int = traverseMazeWithPath()

    private fun traverseMaze(): Int {
        val queue = PriorityQueue<Pair<Location, Int>>(compareBy { it.second })
            .apply { add(Location(listOf(start), Point2D.EAST) to 0) }
        val seen = mutableMapOf<Pair<Point2D, Point2D>, Int>()

        while (queue.isNotEmpty()) {
            val (location, cost) = queue.poll()
            if (location.position() == end) {
                return cost
            } else if (seen.getOrDefault(location.key(), Int.MAX_VALUE) > cost) {
                seen[location.key()] = cost
                location.step().apply {
                    if (maze[position()] != '#') {
                        queue.add(this to cost + 1)
                    }
                }
                queue.add(location.clockwise() to cost + 1000)
                queue.add(location.antiClockwise() to cost + 1000)
            }
        }
        throw IllegalStateException("No path to goal")
    }

    private fun traverseMazeWithPath(): Int {
        val queue = PriorityQueue<Pair<Location, Int>>(compareBy { it.second })
            .apply { add(Location(listOf(start), Point2D.EAST) to 0) }
        val seen = mutableMapOf<Pair<Point2D, Point2D>, Int>()
        var costAtGoal: Int? = null
        val allSpotsInAllPaths: MutableSet<Point2D> = mutableSetOf()

        while (queue.isNotEmpty()) {
            val (location, cost) = queue.poll()

            if(costAtGoal != null && cost > costAtGoal) {
                return allSpotsInAllPaths.size
            } else if (location.position() == end) {
                costAtGoal = cost
                allSpotsInAllPaths.addAll(location.positions)
            } else if (seen.getOrDefault(location.key(), Int.MAX_VALUE) >= cost) {
                seen[location.key()] = cost
                location.step().apply {
                    if (maze[position()] != '#') {
                        queue.add(this to cost + 1)
                    }
                }
                queue.add(location.clockwise() to cost + 1000)
                queue.add(location.antiClockwise() to cost + 1000)
            }
        }
        return allSpotsInAllPaths.size
    }

    private operator fun List<String>.get(at: Point2D): Char =
        this[at.y][at.x]

    private fun List<String>.find(target: Char) =
        flatMapIndexed { y, row ->
            row.mapIndexed { x, c ->
                if (c == target) Point2D(x, y) else null
            }
        }.filterNotNull().first()

    private data class Location(
        val positions: List<Point2D>,
        val direction: Point2D
    ) {
        fun position(): Point2D =
            positions.last()

        fun key(): Pair<Point2D, Point2D> =
            position() to direction

        fun step(): Location =
            copy(
                positions = positions + (position() + direction)
            )

        fun clockwise(): Location =
            copy(
                direction = when (direction) {
                    Point2D.NORTH -> Point2D.EAST
                    Point2D.EAST -> Point2D.SOUTH
                    Point2D.SOUTH -> Point2D.WEST
                    Point2D.WEST -> Point2D.NORTH
                    else -> throw IllegalStateException("Invalid turning point: $this")
                }
            )

        fun antiClockwise(): Location =
            copy(
                direction = when (direction) {
                    Point2D.NORTH -> Point2D.WEST
                    Point2D.WEST -> Point2D.SOUTH
                    Point2D.SOUTH -> Point2D.EAST
                    Point2D.EAST -> Point2D.NORTH
                    else -> throw IllegalStateException("Invalid turning point: $this")
                }
            )
    }

}
