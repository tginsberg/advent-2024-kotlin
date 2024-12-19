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
 * Advent of Code 2024, Day 19 - Linen Layout
 * Problem Description: http://adventofcode.com/2024/day/19
 * Blog Post/Commentary: https://todd.ginsberg.com/post/advent-of-code/2024/19/
 */
class Day19(input: List<String>) {

    private val patterns: List<String> = input.first().split(",").map { it.trim() }
    private val designs: List<String> = input.drop(2)

    fun solvePart1(): Int =
        designs.count { makeDesign(it) > 0 }

    fun solvePart2(): Long =
        designs.sumOf { makeDesign(it) }

    private fun makeDesign(design: String, cache: MutableMap<String, Long> = mutableMapOf()): Long =
        if (design.isEmpty()) 1
        else cache.getOrPut(design) {
            patterns.filter { design.startsWith(it) }.sumOf {
                makeDesign(design.removePrefix(it), cache)
            }
        }
}
