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
 * Advent of Code 2024, Day 17 - Chronospatial Computer
 * Problem Description: http://adventofcode.com/2024/day/17
 * Blog Post/Commentary: https://todd.ginsberg.com/post/advent-of-code/2024/17/
 */
class Day17(input: List<String>) {

    private val computer: Computer = Computer.of(input)

    fun solvePart1(): String =
        computer.runToEnd().joinToString(",")

    fun solvePart2(): Long =
        computer.program
            .reversed()
            .map { it.toLong() }
            .fold(listOf(0L)) { candidates, instruction ->
                candidates.flatMap { candidate ->
                    val shifted = candidate shl 3
                    (shifted..shifted + 8).mapNotNull { attempt ->
                        computer.copy().run {
                            registerA = attempt
                            attempt.takeIf { runToEnd().first() == instruction }
                        }
                    }
                }
            }.first()

    private data class Computer(
        var registerA: Long,
        var registerB: Long,
        var registerC: Long,
        val program: List<Int>
    ) {
        private var instructionPointer: Int = 0
        private val output = mutableListOf<Long>()

        fun runToEnd(): List<Long> {
            var executed = true
            while (executed) {
                executed = executeInstruction()
            }
            return output
        }

        fun executeInstruction(): Boolean {
            if (instructionPointer > program.lastIndex) return false
            else {
                val operand = program[instructionPointer + 1]
                val comboOperand = operand.toComboOperand()
                when (program[instructionPointer]) {
                    0 -> {
                        registerA = registerA shr comboOperand.toInt()
                        instructionPointer += 2
                    }

                    1 -> {
                        registerB = registerB xor operand.toLong()
                        instructionPointer += 2
                    }

                    2 -> {
                        registerB = comboOperand % 8
                        instructionPointer += 2
                    }

                    3 -> {
                        instructionPointer = if (registerA == 0L) instructionPointer + 2
                        else operand
                    }

                    4 -> {
                        registerB = registerB xor registerC
                        instructionPointer += 2
                    }

                    5 -> {
                        output.add(comboOperand % 8)
                        instructionPointer += 2
                    }

                    6 -> {
                        registerB = registerA shr comboOperand.toInt()
                        instructionPointer += 2
                    }

                    7 -> {
                        registerC = registerA shr comboOperand.toInt()
                        instructionPointer += 2
                    }
                }
                return true
            }
        }

        private fun Int.toComboOperand(): Long =
            when (this) {
                in 0..3 -> toLong()
                4 -> registerA
                5 -> registerB
                6 -> registerC
                else -> throw IllegalArgumentException("Invalid operand: $this")
            }

        companion object {
            fun of(input: List<String>): Computer =
                Computer(
                    input[0].substringAfterLast(" ").toLong(),
                    input[1].substringAfterLast(" ").toLong(),
                    input[2].substringAfterLast(" ").toLong(),
                    input[4].substringAfterLast(" ").split(",").map { it.toInt() }
                )
        }
    }
}
