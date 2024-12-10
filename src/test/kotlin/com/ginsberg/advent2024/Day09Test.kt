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

import com.ginsberg.advent2024.Resources.resourceAsText
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 9")
class Day09Test {

    // Arrange
    private val input = "2333133121414131402"

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `Matches example`() {
            // Act
            val answer = Day09(input).solvePart1()

            // Assert
            assertThat(answer).isEqualTo(1_928)
        }

        @Test
        fun `Actual answer`() {
            // Act
            val answer = Day09(resourceAsText("day09.txt")).solvePart1()

            // Assert
            assertThat(answer).isEqualTo(6_242_766_523_059)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {

        @Test
        fun `Matches example`() {
            // Act
            val answer = Day09(input).solvePart2()

            // Assert
            assertThat(answer).isEqualTo(2_858)
        }

        @Test
        fun `Actual answer`() {
            // Act
            val answer = Day09(resourceAsText("day09.txt")).solvePart2()

            // Assert
            assertThat(answer).isEqualTo(6_272_188_244_509)
        }
    }
}