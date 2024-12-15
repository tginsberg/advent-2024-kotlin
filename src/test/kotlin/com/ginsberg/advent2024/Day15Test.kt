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

@DisplayName("Day 15")
class Day15Test {


    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `Matches small example`() {

            // Arrange
            val input = """
                ########
                #..O.O.#
                ##@.O..#
                #...O..#
                #.#.O..#
                #...O..#
                #......#
                ########
                
                <^^>>>vv<v>>v<<
                """.trimIndent().lines()

            // Act
            val answer = Day15(input).solvePart1()

            // Assert
            assertThat(answer).isEqualTo(2028)
        }

        @Test
        fun `Matches large example`() {
            // Act
            val answer = Day15(resourceAsListOfString("day15_sample.txt")).solvePart1()

            // Assert
            assertThat(answer).isEqualTo(10092)
        }

        @Test
        fun `Actual answer`() {
            // Act
            val answer = Day15(resourceAsListOfString("day15.txt")).solvePart1()

            // Assert
            assertThat(answer).isEqualTo(1371036)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {

        @Test
        fun `Matches small example`() {
            // Arrange
            val input = """
                #######
                #...#.#
                #.....#
                #..OO@#
                #..O..#
                #.....#
                #######

                <vv<<^^<<^^
                """.trimIndent().lines()

            // Act
            val answer = Day15(input).solvePart2()

            // Assert
            assertThat(answer).isEqualTo(618)
        }

        @Test
        fun `Matches large example`() {
            // Act
            val answer = Day15(resourceAsListOfString("day15_sample.txt")).solvePart2()

            // Assert
            assertThat(answer).isEqualTo(9021)
        }

        @Test
        fun `Actual answer`() {
            // Act
            val answer = Day15(resourceAsListOfString("day15.txt")).solvePart2()

            // Assert
            assertThat(answer).isEqualTo(1392847)
        }
    }
}