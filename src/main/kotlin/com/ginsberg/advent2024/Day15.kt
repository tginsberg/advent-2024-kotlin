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
 * Advent of Code 2024, Day 15 - Warehouse Woes
 * Problem Description: http://adventofcode.com/2024/day/15
 * Blog Post/Commentary: https://todd.ginsberg.com/post/advent-of-code/2024/15/
 */
class Day15(input: List<String>) {

    private val warehouse: List<CharArray> = input
        .takeWhile { it.isNotEmpty() }
        .map { it.toCharArray() }

    private val movements: List<Point2D> = input
        .dropWhile { it.isNotEmpty() }
        .dropWhile { it.isEmpty() }
        .flatMap { row ->
            row.map { it.toDirection() }
        }

    fun solvePart1(): Int =
        warehouse
            .doMovements()
            .findAll('O')
            .sumOf { it.gps() }

    fun solvePart2(): Int =
        warehouse
            .map { it.remap() }
            .doMovements()
            .findAll('[')
            .sumOf { it.gps() }

    private fun List<CharArray>.doMovements(): List<CharArray> {
        val start: Point2D = findAll('@').first()
        var place = start
        movements.forEach { direction ->
            val next = place + direction
            when (this[next]) {
                in "[O]" -> {
                    push(next, direction)?.let { moves ->
                        moves.forEach { (from, to) ->
                            this[to] = this[from]
                            this[from] = '.'
                        }
                        place = next
                    }
                }

                !in "#" -> {
                    place = next
                }
            }
        }
        return this
    }

    private fun List<CharArray>.push(
        position: Point2D,
        direction: Point2D
    ): List<Pair<Point2D, Point2D>>? {
        val safePushes = mutableListOf<Pair<Point2D, Point2D>>()
        val seen = mutableSetOf<Point2D>()
        val queue = mutableListOf(position)

        while (queue.isNotEmpty()) {
            val thisPosition = queue.removeFirst()
            if (thisPosition !in seen) {
                seen += thisPosition
                if (direction in setOf(Point2D.NORTH, Point2D.SOUTH)) {
                    when (get(thisPosition)) {
                        ']' -> queue.add(thisPosition + Point2D.WEST)
                        '[' -> queue.add(thisPosition + Point2D.EAST)
                    }
                }
                val nextPosition = thisPosition + direction
                when (get(nextPosition)) {
                    '#' -> return null // Wall! Can't push anything!
                    in "[O]" -> queue.add(nextPosition)
                }
                safePushes.add(thisPosition to nextPosition)
            }
        }
        return safePushes.reversed()
    }

    private fun CharArray.remap(): CharArray =
        joinToString("") {
            when (it) {
                '#' -> "##"
                'O' -> "[]"
                '.' -> ".."
                '@' -> "@."
                else -> throw IllegalArgumentException("Invalid $it")
            }
        }.toCharArray()

    private fun List<CharArray>.findAll(target: Char): List<Point2D> =
        flatMapIndexed { y, row ->
            row.mapIndexed { x, c ->
                if (c == target) Point2D(x, y) else null
            }
        }.filterNotNull()

    private fun Point2D.gps(): Int =
        (100 * y) + x

    private fun Char.toDirection(): Point2D =
        when (this) {
            '^' -> Point2D.NORTH
            '>' -> Point2D.EAST
            'v' -> Point2D.SOUTH
            '<' -> Point2D.WEST
            else -> throw IllegalArgumentException("Invalid direction: $this")
        }
}
