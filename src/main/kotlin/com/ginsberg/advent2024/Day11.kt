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
 * Advent of Code 2024, Day 11 - Plutonian Pebbles
 * Problem Description: http://adventofcode.com/2024/day/11
 * Blog Post/Commentary: https://todd.ginsberg.com/post/advent-of-code/2024/11/
 */
class Day11(input: String) {

    private val stones: List<Long> = input.split(" ").map { it.toLong() }
    private val cache: MutableMap<Pair<Long, Int>, Long> = mutableMapOf()

    fun solvePart1(): Long =
        sumBlinks(25)

    fun solvePart2(): Long =
        sumBlinks(75)

    private fun sumBlinks(times: Int): Long = 
        stones.sumOf { blink(it, times) }

    private fun blink(
        stone: Long,
        blinks: Int,
        key: Pair<Long, Int> = stone to blinks
    ): Long =
        when {
            blinks == 0 -> 1
            key in cache -> cache.getValue(key)
            else -> {
                val result = when {
                    stone == 0L -> blink(1, blinks - 1)
                    stone.hasEvenDigits() -> stone.split().sumOf { blink(it, blinks - 1) }
                    else -> blink(stone * 2_024, blinks - 1)
                }
                cache[key] = result
                result
            }
        }

    private fun Long.hasEvenDigits(): Boolean =
        toString().length % 2 == 0

    private fun Long.split(): List<Long> {
        val s = toString()
        return listOf(
            s.substring(0, s.length / 2).toLong(),
            s.substring(s.length / 2, s.length).toLong()
        )
    }

}
