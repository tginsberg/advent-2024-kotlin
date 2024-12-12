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
 * Advent of Code 2024, Day 12 - Garden Groups
 * Problem Description: http://adventofcode.com/2024/day/12
 * Blog Post/Commentary: https://todd.ginsberg.com/post/advent-of-code/2024/12/
 */
class Day12(private val garden: List<String>) {


    fun solvePart1(): Int =
        findRegions().sumOf { it.area * it.perimeter }

    fun solvePart2(): Int =
        findRegions().sumOf { it.area * it.sides }

    private fun findRegions(): List<Region> {
        val seen = mutableSetOf<Point2D>()
        return garden.flatMapIndexed { y, row ->
            row.mapIndexedNotNull { x, _ ->
                val place = Point2D(x, y)
                if (place !in seen) findRegion(place, seen)
                else null
            }
        }
    }

    private fun findRegion(start: Point2D, seen: MutableSet<Point2D>): Region {
        val target: Char = garden[start]!!
        val queue = mutableListOf(start)
        var area = 0
        var perimeter = 0
        var corners = 0

        while (queue.isNotEmpty()) {
            val place = queue.removeFirst()
            if (garden[place] == target && place !in seen) {
                seen += place
                area++
                val neighbors = place.cardinalNeighbors()
                queue.addAll(neighbors)
                perimeter += neighbors.count { garden[it] != target }
                corners += place.countCorners()
            }
        }
        return Region(target, area, perimeter, corners)
    }

    private operator fun List<String>.contains(at: Point2D): Boolean =
        at.y in indices && at.x in get(at.y).indices

    private operator fun List<String>.get(at: Point2D): Char? =
        if (at in this) this[at.y][at.x] else null

    private fun Point2D.countCorners(): Int =
        listOf(Point2D.NORTH, Point2D.EAST, Point2D.SOUTH, Point2D.WEST, Point2D.NORTH)
            .zipWithNext()
            .map { (first, second) ->
                listOf(
                    garden[this],
                    garden[this + first],
                    garden[this + second],
                    garden[this + first + second]
                )
            }.count { (target, side1, side2, corner) ->
                (target != side1 && target != side2) || (side1 == target && side2 == target && corner != target)
            }

    private data class Region(val name: Char, val area: Int, val perimeter: Int, val sides: Int)
}
