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
 * Advent of Code 2024, Day 8 - Resonant Collinearity
 * Problem Description: http://adventofcode.com/2024/day/8
 * Blog Post/Commentary: https://todd.ginsberg.com/post/advent-of-code/2024/day8/
 */
class Day08(private val input: List<String>) {

    private val nodes: Collection<List<Point2D>> = parseGrid(input)

    fun solvePart1(): Int =
        countAntiNodes(::antiNodesForPart1)

    fun solvePart2(): Int =
        countAntiNodes(::antiNodesForPart2)

    private fun countAntiNodes(worker: (Point2D, Point2D, Point2D) -> Set<Point2D>): Int =
        nodes.flatMap { nodeList ->
            nodeList.flatMapIndexed { i, a ->
                nodeList.drop(i + 1).flatMap { b ->
                    worker.invoke(a, b, a - b)
                }
            }.filter { it.isOnGrid() }
        }.toSet().size

    private fun antiNodesForPart1(a: Point2D, b: Point2D, diff: Point2D): Set<Point2D> =
        if (a.y > b.y) setOf(a - diff, b + diff)
        else setOf(a + diff, b - diff)

    private fun antiNodesForPart2(a: Point2D, b: Point2D, diff: Point2D): Set<Point2D> =
        generateSequence(a) { it - diff }.takeWhile { it.isOnGrid() }.toSet() +
        generateSequence(a) { it + diff }.takeWhile { it.isOnGrid() }.toSet()

    private fun Point2D.isOnGrid(): Boolean =
        y in input.indices && x in input[y].indices

    private fun parseGrid(input: List<String>): Collection<List<Point2D>> =
        input.flatMapIndexed { y, row ->
            row.mapIndexed { x, c ->
                if (c != '.') c to Point2D(x, y) else null
            }
        }.filterNotNull()
            .groupBy({ it.first }, { it.second })
            .values
}
