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
 * Advent of Code 2024, Day 13 - Claw Contraption
 * Problem Description: http://adventofcode.com/2024/day/13
 * Blog Post/Commentary: https://todd.ginsberg.com/post/advent-of-code/2024/13/
 *
 * I got help from [This reddit thread by /u/ThunderChaser](https://redd.it/1hd7irq)
 */
class Day13(input: List<String>) {

    private val clawMachines: List<ClawMachine> =
        input.chunked(4) { ClawMachine.of(it) }

    fun solvePart1(): Long =
        clawMachines.sumOf { it.pressButtons() }

    fun solvePart2(): Long =
        clawMachines
            .map { it.scale(10000000000000) }
            .sumOf { it.pressButtons() }

    private data class ClawMachine(
        val aX: Long, val aY: Long,
        val bX: Long, val bY: Long,
        val prizeX: Long, val prizeY: Long,
    ) {
        fun scale(factor: Long): ClawMachine =
            copy(
                prizeX = prizeX + factor,
                prizeY = prizeY + factor
            )

        fun pressButtons(): Long {
            val det = aX * bY - aY * bX
            val a = (prizeX * bY - prizeY * bX) / det
            val b = (aX * prizeY - aY * prizeX) / det
            return if (aX * a + bX * b == prizeX && aY * a + bY * b == prizeY) {
                a * 3 + b
            } else 0
        }

        companion object {
            fun of(input: List<String>): ClawMachine =
                ClawMachine(
                    input[0].substringAfter("+").substringBefore(",").toLong(),
                    input[0].substringAfterLast("+").toLong(),
                    input[1].substringAfter("+").substringBefore(",").toLong(),
                    input[1].substringAfterLast("+").toLong(),
                    input[2].substringAfter("=").substringBefore(",").toLong(),
                    input[2].substringAfterLast("=").toLong()
                )
        }
    }
}
