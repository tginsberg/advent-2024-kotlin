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
 * Advent of Code 2024, Day 22 - Monkey Market
 * Problem Description: http://adventofcode.com/2024/day/22
 * Blog Post/Commentary: https://todd.ginsberg.com/post/advent-of-code/2024/22/
 */
class Day22(input: List<String>) {

    private val buyerNumbers: List<Long> = input.map { it.toLong() }

    fun solvePart1(): Long =
        buyerNumbers.sumOf {
            it.secretNumbers().drop(2000).first()
        }

    fun solvePart2(): Int = buildMap {
        buyerNumbers
            .map { it.secretNumbers().take(2001).map { i -> (i % 10).toInt() }.toList() }
            .forEach { sequence ->
                sequence
                    .windowed(5, 1)
                    .map { it.zipWithNext { first, second -> second - first } to it.last() }
                    .distinctBy { it.first }
                    .forEach { (key, value) ->
                        this[key] = (this[key] ?: 0) + value
                    }
            }
    }.maxOf { it.value }

    private fun Long.secretNumbers(): Sequence<Long> =
        generateSequence(this) { secret ->
            secret
                .mixAndPrune { it shl 6 }
                .mixAndPrune { it shr 5 }
                .mixAndPrune { it shl 11 }
        }

    private fun Long.mixAndPrune(function: (Long) -> Long): Long =
        (this xor function(this)) % 16777216L

}
