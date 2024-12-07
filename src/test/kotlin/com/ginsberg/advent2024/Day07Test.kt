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

import com.ginsberg.advent2024.Resources.resourceAsListOfString
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 7")
class Day07Test {

    // Arrange
    private val input = """
        190: 10 19
        3267: 81 40 27
        83: 17 5
        156: 15 6
        7290: 6 8 6 15
        161011: 16 10 13
        192: 17 8 14
        21037: 9 7 18 13
        292: 11 6 16 20
        """.trimIndent().lines()

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `Matches example`() {
            // Act
            val answer = Day07(input).solvePart1()

            // Assert
            assertThat(answer).isEqualTo(3_749)
        }

        @Test
        fun `Actual answer`() {
            // Act
            val answer = Day07(resourceAsListOfString("day07.txt")).solvePart1()

            // Assert
            assertThat(answer).isEqualTo(1_620_690_235_709)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {

        @Test
        fun `Matches example`() {
            // Act
            val answer = Day07(input).solvePart2()

            // Assert
            assertThat(answer).isEqualTo(11_387)
        }

        @Test
        fun `Actual answer`() {
            // Act
            val answer = Day07(resourceAsListOfString("day07.txt")).solvePart2()

            // Assert
            assertThat(answer).isEqualTo(145_397_611_075_341)
        }
    }
}