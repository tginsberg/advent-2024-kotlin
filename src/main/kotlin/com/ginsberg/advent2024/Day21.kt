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
 * Advent of Code 2024, Day 21 - Keypad Conundrum
 * Problem Description: http://adventofcode.com/2024/day/21
 * Blog Post/Commentary: https://todd.ginsberg.com/post/advent-of-code/2024/21/
 */
class Day21(private val codes: List<String>) {

    //@formatter:off
    private val numericPad: Map<Point2D, Char> = mapOf(
        Point2D(0,0) to '7', Point2D(1,0) to '8', Point2D(2,0) to '9',
        Point2D(0,1) to '4', Point2D(1,1) to '5', Point2D(2,1) to '6',
        Point2D(0,2) to '1', Point2D(1,2) to '2', Point2D(2,2) to '3',
                             Point2D(1,3) to '0', Point2D(2,3) to 'A'
    )
    private val numericPaths: Map<Pair<Char, Char>, List<String>> = numericPad.allPaths()

    private val directionalPad: Map<Point2D, Char> = mapOf(
                             Point2D(1,0) to '^', Point2D(2,0) to 'A',
        Point2D(0,1) to '<', Point2D(1,1) to 'v', Point2D(2,1) to '>'
    )
    private val directionalPaths: Map<Pair<Char, Char>, List<String>> = directionalPad.allPaths()
    //@formatter:on

    fun solvePart1(): Long =
        solve(2)

    fun solvePart2(): Long =
        solve(25)

    private fun solve(depth: Int): Long =
        codes.sumOf { findCost(it, depth) * it.dropLast(1).toLong() }

    private fun <T> Collection<T>.allPairs(): List<Pair<T, T>> =
        flatMap { left ->
            map { right -> left to right }
        }

    private fun Map<Point2D, Char>.allPaths(): Map<Pair<Char, Char>, List<String>> =
        keys.allPairs()
            .associate {
                (getValue(it.first) to getValue(it.second)) to findLowestCostPaths(it.first, it.second)
            }

    private fun findCost(
        code: String,
        depth: Int,
        transitions: Map<Pair<Char, Char>, List<String>> = numericPaths,
        cache: MutableMap<Pair<String, Int>, Long> = mutableMapOf()
    ): Long =
        cache.getOrPut(code to depth) {
            "A$code".zipWithNext().sumOf { transition ->
                val paths: List<String> = transitions.getValue(transition)
                if (depth == 0) {
                    paths.minOf { it.length }.toLong()
                } else {
                    paths.minOf { path -> findCost(path, depth - 1, directionalPaths, cache) }
                }
            }
        }

    private fun Map<Point2D, Char>.findLowestCostPaths(start: Point2D, end: Point2D): List<String> {
        val queue = PriorityQueue<Pair<List<Point2D>, Int>>(compareBy { it.second })
            .apply { add(listOf(start) to 0) }
        val seen = mutableMapOf<Point2D, Int>()
        var costAtGoal: Int? = null
        val allPaths: MutableList<String> = mutableListOf()

        while (queue.isNotEmpty()) {
            val (path, cost) = queue.poll()
            val location = path.last()

            if (costAtGoal != null && cost > costAtGoal) {
                return allPaths
            } else if (path.last() == end) {
                costAtGoal = cost
                allPaths.add(path.zipWithNext().map { (from, to) -> from.diffToChar(to) }.joinToString("") + "A")
            } else if (seen.getOrDefault(location, Int.MAX_VALUE) >= cost) {
                seen[location] = cost
                location
                    .cardinalNeighbors()
                    .filter { it in keys }
                    .forEach { queue.add(path + it to cost + 1) }
            }
        }
        return allPaths
    }

    private fun Point2D.diffToChar(other: Point2D): Char =
        when (val result = other - this) {
            Point2D.NORTH -> '^'
            Point2D.EAST -> '>'
            Point2D.SOUTH -> 'v'
            Point2D.WEST -> '<'
            else -> throw IllegalArgumentException("Invalid direction $result")
        }
}
