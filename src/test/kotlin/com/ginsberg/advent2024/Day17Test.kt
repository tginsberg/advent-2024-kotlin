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

@DisplayName("Day 17")
class Day17Test {


    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `Matches example 1`() {
            // Arrange
            val input = """
                Register A: 729
                Register B: 0
                Register C: 0
                
                Program: 0,1,5,4,3,0
                """.trimIndent().lines()

            // Act
            val answer = Day17(input).solvePart1()

            // Assert
            assertThat(answer).isEqualTo("4,6,3,5,6,3,5,2,1,0")
        }


        @Test
        fun `Matches example 2`() {
            val input = """
                Register A: 2024
                Register B: 0
                Register C: 0
                
                Program: 0,1,5,4,3,0
                """.trimIndent().lines()

            // Act
            val answer = Day17(input).solvePart1()

            // Assert
            assertThat(answer).isEqualTo("4,2,5,6,7,7,7,7,3,1,0")
        }

        @Test
        fun `Actual answer`() {
            // Act
            val answer = Day17(resourceAsListOfString("day17.txt")).solvePart1()

            // Assert
            assertThat(answer).isEqualTo("2,1,3,0,5,2,3,7,1")
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {

        @Test
        fun `Matches example`() {
            // Arrange
            val input = """
                Register A: 117440
                Register B: 0
                Register C: 0
                
                Program: 0,3,5,4,3,0
                """.trimIndent().lines()

            // Act
            val answer = Day17(input).solvePart2()

            // Assert
            assertThat(answer).isEqualTo(117440)
        }

        @Test
        fun `Actual answer`() {
            // Act
            val answer = Day17(resourceAsListOfString("day17.txt")).solvePart2()

            // Assert
            assertThat(answer).isEqualTo(107416732707226L)
        }
    }
}