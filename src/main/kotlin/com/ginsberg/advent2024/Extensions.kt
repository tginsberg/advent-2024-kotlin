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

fun <T> List<T>.midpoint(): T =
    this[lastIndex / 2]

operator fun List<CharArray>.get(at: Point2D): Char =
    this[at.y][at.x]

operator fun List<CharArray>.set(at: Point2D, value: Char) {
    this[at.y][at.x] = value
}

fun List<String>.findSingle(target: Char): Point2D =
    flatMapIndexed { y, row ->
        row.mapIndexedNotNull { x, c ->
            if (c == target) Point2D(x, y) else null
        }
    }.first()

fun <T> List<T>.binarySearchFirst(fn: (T) -> Boolean): T =
    binarySearchFirstOrNull(fn) ?:
        throw IllegalStateException("No elements match predicate")

fun <T> List<T>.binarySearchFirstOrNull(fn: (T) -> Boolean): T? =
    binarySearchIndexOfFirstOrNull(fn)?.let { get(it) }

fun <T> List<T>.binarySearchIndexOfFirst(fn: (T) -> Boolean): Int =
    binarySearchIndexOfFirstOrNull(fn) ?:
        throw IllegalStateException("No elements match predicate")

fun <T> List<T>.binarySearchIndexOfFirstOrNull(fn: (T) -> Boolean): Int? {
    var low = 0
    var high = lastIndex
    var firstFind: Int? = null
    while (low <= high) {
        val mid = (low + high) shr 1
        if (fn(get(mid))) {
            firstFind = mid
            high = mid - 1
        } else {
            low = mid + 1
        }
    }
    return firstFind
}