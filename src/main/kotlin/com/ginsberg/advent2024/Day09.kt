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

import java.util.PriorityQueue

/**
 * Advent of Code 2024, Day 9 - Disk Fragmenter
 * Problem Description: http://adventofcode.com/2024/day/9
 * Blog Post/Commentary: https://todd.ginsberg.com/post/advent-of-code/2024/9/
 */
class Day09(input: String) {

    private val disk: List<Long?> = parse(input.trim())

    fun solvePart1(): Long {
        val emptyBlocks = disk.indices.filter { disk[it] == null }.toMutableList()
        return disk.withIndex().reversed().sumOf { (index, value) ->
            if (value != null) {
                value * (emptyBlocks.removeFirstOrNull() ?: index)
            } else {
                emptyBlocks.removeLastOrNull()
                0
            }
        }
    }

    fun solvePart2(): Long {
        val allBlocks = findAllBlocks(disk)
        val freeSpace: MutableMap<Int, PriorityQueue<Int>> = allBlocks
            .filter { it.fileId == null }
            .groupBy({ it.length }, { it.start })
            .mapValues { (_, v) -> PriorityQueue(v) }
            .toMutableMap()

        return allBlocks.filterNot { it.fileId == null }.reversed().sumOf { block ->
            block.checksum(
                freeSpace.findSpace(block)
            )
        }
    }

    private fun MutableMap<Int, PriorityQueue<Int>>.findSpace(block: Block): Int =
        (block.length..9).mapNotNull { trySize ->
            if (this[trySize]?.isNotEmpty() == true) trySize to this.getValue(trySize).first()
            else null
        }.sortedBy { it.second }.filter { it.second < block.start }.firstNotNullOfOrNull { (blockSize, startAt) ->
            this[blockSize]?.poll()
            if (blockSize != block.length) {
                val diff = blockSize - block.length
                computeIfAbsent(diff) { _ -> PriorityQueue() }.add(startAt + block.length)
            }
            startAt
        } ?: block.start

    private fun findAllBlocks(disk: List<Long?>): List<Block> = buildList {
        var blockStart = -1
        var previousValue: Long? = -1L
        disk.withIndex().forEach { (index, value) ->
            if (previousValue == -1L) {
                blockStart = index
                previousValue = value
            } else if (previousValue != value) {
                add(Block(blockStart, index - blockStart, previousValue))
                blockStart = index
                previousValue = value
            }
        }
        if (blockStart != -1) {
            add(Block(blockStart, disk.size - blockStart, previousValue))
        }
    }

    private data class Block(val start: Int, val length: Int, val fileId: Long? = null) {
        fun checksum(index: Int = start): Long =
            (0..<length).sumOf {
                (index + it) * (fileId ?: 0L)
            }
    }

    //@formatter:off
    private fun parse(input: String): List<Long?> =
        input
            .windowed(2, 2, true)
            .withIndex()
            .flatMap { (index, value) ->
                List(value.first().digitToInt()) { _ -> index.toLong() } +
                List(value.getOrElse(1){ _ -> '0' }.digitToInt()) { null }
            }
    //@formatter:on
}
