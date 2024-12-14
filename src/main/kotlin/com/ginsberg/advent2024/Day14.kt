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

import java.io.File

/**
 * Advent of Code 2024, Day 14 - Restroom Redoubt
 * Problem Description: http://adventofcode.com/2024/day/14
 * Blog Post/Commentary: https://todd.ginsberg.com/post/advent-of-code/2024/14/
 */
class Day14(input: List<String>, private val area: Point2D = Point2D(101, 103)) {

    private val robots: List<Robot> = input.map { Robot.of(it) }

    fun solvePart1(): Int =
        robots
            .map { it.move(100, area) }
            .groupingBy { it.quadrant(area / 2) }
            .eachCount()
            .filterNot { it.key == 0 }
            .values
            .reduce(Int::times)

    fun solvePart2a() {
        var printTheseRobots = robots
        File("10_000_robots.txt").printWriter().use { out ->
            repeat(10_000) { move ->
                printTheseRobots = printTheseRobots.map { it.move(1, area) }
                val uniquePlaces = printTheseRobots.map { it.position }.toSet()
                out.println("::::$move::::")
                repeat(area.y) { y ->
                    repeat(area.x) { x ->
                        out.print(if (Point2D(x, y) in uniquePlaces) "#" else '.')
                    }
                    out.println()
                }
            }
        }
    }

    fun solvePart2b(area: Point2D = Point2D(101, 103)): Int {
        var moves = 0
        var robotsThisTurn = robots
        do {
            moves++
            robotsThisTurn = robotsThisTurn.map { it.move(1, area) }
        } while (robotsThisTurn.distinctBy { it.position }.size != robotsThisTurn.size)
        return moves
    }

    private data class Robot(
        val position: Point2D,
        val velocity: Point2D
    ) {
        fun move(moves: Int, area: Point2D): Robot =
            copy(
                position = (position + (velocity * moves)).wrap(area)
            )

        fun Point2D.wrap(other: Point2D): Point2D {
            val nextX = x % other.x
            val nextY = y % other.y
            return Point2D(
                if (nextX < 0) nextX + other.x else nextX,
                if (nextY < 0) nextY + other.y else nextY
            )
        }

        fun quadrant(midpoint: Point2D): Int =
            when {
                position.x < midpoint.x && position.y < midpoint.y -> 1
                position.x > midpoint.x && position.y < midpoint.y -> 2
                position.x < midpoint.x && position.y > midpoint.y -> 3
                position.x > midpoint.x && position.y > midpoint.y -> 4
                else -> 0
            }

        companion object {
            fun of(input: String): Robot =
                Robot(
                    position = Point2D(
                        input.substringAfter("=").substringBefore(",").toInt(),
                        input.substringAfter(",").substringBefore(" ").toInt()
                    ),
                    velocity = Point2D(
                        input.substringAfterLast("=").substringBefore(",").toInt(),
                        input.substringAfterLast(",").toInt()
                    )
                )
        }
    }
}
