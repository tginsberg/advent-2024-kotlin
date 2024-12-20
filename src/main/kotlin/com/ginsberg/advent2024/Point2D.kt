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

data class Point2D(val x: Int, val y: Int) {

    fun cardinalNeighbors(): Set<Point2D> =
        setOf(
            this + NORTH,
            this + EAST,
            this + SOUTH,
            this + WEST
        )

    fun distanceTo(other: Point2D): Int =
        (x - other.x).absoluteValue + (y - other.y).absoluteValue

    operator fun div(by: Int): Point2D =
        Point2D(x / by, y / by)

    operator fun minus(other: Point2D): Point2D =
        Point2D(x - other.x, y - other.y)

    operator fun plus(other: Point2D): Point2D =
        Point2D(x + other.x, y + other.y)

    operator fun times(times: Int): Point2D =
        Point2D(x * times, y * times)

    companion object {
        val ORIGIN = Point2D(0, 0)
        val NORTH = Point2D(0, -1)
        val EAST = Point2D(1, 0)
        val SOUTH = Point2D(0, 1)
        val WEST = Point2D(-1, 0)

        fun of(input: String): Point2D =
            input.split(",").let {
                Point2D(it.first().toInt(), it.last().toInt())
            }
    }
}