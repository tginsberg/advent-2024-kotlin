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
 * Advent of Code 2024, Day 24 - Crossed Wires
 * Problem Description: http://adventofcode.com/2024/day/24
 * Blog Post/Commentary: https://todd.ginsberg.com/post/advent-of-code/2024/24/
 */
class Day24(input: List<String>) {

    private val wires: MutableMap<String, Int> = parseWires(input)
    private val gates: MutableList<Gate> = parseGates(input)

    fun solvePart1(): Long {
        simulate()
        return wires
            .filter { it.key.startsWith("z") }
            .entries
            .sortedByDescending { it.key }
            .map { it.value }
            .joinToString("")
            .toLong(2)
    }

    fun solvePart2() {
        val z = gates.filter { it.out.startsWith("z") }.map { it.out }.sorted().joinToString("->")
        val x = z.replace('z', 'x')
        val y = z.replace('z', 'y')

        println(
            """
            digraph G {
                subgraph {
                   node [style=filled,color=green]
                    $z
                }
                subgraph {
                    node [style=filled,color=gray]
                    $x
                }
                subgraph {
                    node [style=filled,color=gray]
                    $y
                }
                subgraph {
                    node [style=filled,color=pink]
                    ${gates.filter { gate -> gate.op == "AND" }.joinToString(" ") { gate -> gate.out }}
                }
                subgraph {
                    node [style=filled,color=yellow];
                    ${gates.filter { gate -> gate.op == "OR" }.joinToString(" ") { gate -> gate.out }}
                }
                subgraph {
                    node [style=filled,color=lightblue];
                    ${gates.filter { gate -> gate.op == "XOR" }.joinToString(" ") { gate -> gate.out }}
                }
                """.trimIndent()
        )
        gates.forEach { (left, right, _, out) ->
            println("    $left -> $out")
            println("    $right -> $out")
        }
        println("}")
    }

    private fun simulate() {
        while (gates.isNotEmpty()) {
            gates
                .findAndRemoveReady()
                .forEach { (left, right, op, out) ->
                    wires[out] = when (op) {
                        "AND" -> wires.getValue(left) and wires.getValue(right)
                        "OR" -> wires.getValue(left) or wires.getValue(right)
                        "XOR" -> wires.getValue(left) xor wires.getValue(right)
                        else -> throw IllegalArgumentException("Invalid op: $op")
                    }
                }
        }
    }

    private fun MutableList<Gate>.findAndRemoveReady(): List<Gate> =
        filter {
            it.left in wires && it.right in wires
        }.also { removeAll(it) }

    private fun parseWires(input: List<String>): MutableMap<String, Int> =
        input
            .takeWhile { it.isNotEmpty() }
            .associate { it.substringBefore(":") to it.last().digitToInt() }
            .toMutableMap()

    private fun parseGates(input: List<String>): MutableList<Gate> =
        input
            .dropWhile { it.isNotEmpty() }
            .drop(1)
            .map { Gate.of(it) }
            .toMutableList()

    private data class Gate(val left: String, val right: String, val op: String, val out: String) {
        companion object {
            fun of(input: String): Gate =
                input.split(" ").let { Gate(it[0], it[2], it[1], it[4]) }
        }
    }

}
