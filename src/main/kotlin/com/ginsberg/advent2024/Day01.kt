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

import kotlin.math.absoluteValue

/**
 * Advent of Code 2024, Day 1 - Historian Hysteria
 * Problem Description: http://adventofcode.com/2024/day/1
 * Blog Post/Commentary: https://todd.ginsberg.com/post/advent-of-code/2024/day1/
 */
class Day01(private val input: List<String>) {

    fun solvePart1(): Int =
        parse(input).let { (left, right) ->
            left.sorted()
                .zip(right.sorted())
                .sumOf { (it.first - it.second).absoluteValue }
        }

    fun solvePart2(): Int =
        parse(input).let { (left, right) ->
            val frequencies = right.groupingBy { it }.eachCount()
            left.sumOf { it * frequencies.getOrDefault(it, 0) }
        }

    private fun parse(input: List<String>): Pair<List<Int>, List<Int>> {
        val left = mutableListOf<Int>()
        val right = mutableListOf<Int>()
        input.forEach {
            left.add(it.substringBefore(" ").toInt())
            right.add(it.substringAfterLast(" ").toInt())
        }
        return left to right
    }
}