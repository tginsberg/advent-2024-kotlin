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
 * Advent of Code 2024, Day 23 - LAN Party
 * Problem Description: http://adventofcode.com/2024/day/23
 * Blog Post/Commentary: https://todd.ginsberg.com/post/advent-of-code/2024/23/
 */
class Day23(input: List<String>) {

    private val connections: Map<String, Set<String>> = parse(input)

    fun solvePart1(): Int =
        connections.keys
            .filter { it.startsWith("t") }
            .flatMap { findCliques(it) }
            .distinct()
            .size

    fun solvePart2(): String =
        findLargestClique(connections.keys).sorted().joinToString(",")

    private fun findCliques(start: String): List<List<String>> =
        connections
            .getValue(start)
            .allPairs()
            .filter { (b, c) -> c in connections.getValue(b) }
            .map { (b, c) -> listOf(start, b, c).sorted() }

    private fun findLargestClique(
        p: Set<String>,
        r: Set<String> = emptySet(),
        x: Set<String> = emptySet()
    ): Set<String> =
        if (p.isEmpty() && x.isEmpty()) r
        else {
            val withMostNeighbors: String = (p + x).maxBy { connections.getValue(it).size }
            p.minus(connections.getValue(withMostNeighbors)).map { v ->
                findLargestClique(
                    p intersect connections.getValue(v),
                    r + v,
                    x intersect connections.getValue(v)
                )
            }.maxBy { it.size }
        }

    private fun <T> Set<T>.allPairs(): List<Pair<T, T>> =
        toList().let { list ->
            list.flatMapIndexed { index, first ->
                list.drop(index + 1).map { second ->
                    first to second
                }
            }
        }

    private fun parse(input: List<String>): Map<String, Set<String>> =
        input.map { it.split("-") }
            .flatMap { (left, right) -> listOf(left to right, right to left) }
            .groupBy({ it.first }, { it.second })
            .mapValues { it.value.toSet() }
}
