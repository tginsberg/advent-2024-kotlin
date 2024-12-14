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

@DisplayName("Day 14")
class Day14Test {

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `Matches example`() {

            // Arrange
            val input = """
                p=0,4 v=3,-3
                p=6,3 v=-1,-3
                p=10,3 v=-1,2
                p=2,0 v=2,-1
                p=0,0 v=1,3
                p=3,0 v=-2,-2
                p=7,6 v=-1,-3
                p=3,0 v=-1,-2
                p=9,3 v=2,3
                p=7,3 v=-1,2
                p=2,4 v=2,-3
                p=9,5 v=-3,-3
            """.trimIndent().lines()

            // Act
            val answer = Day14(input, Point2D(11, 7)).solvePart1()

            // Assert
            assertThat(answer).isEqualTo(12)
        }

        @Test
        fun `Actual answer`() {
            // Act
            val answer = Day14(resourceAsListOfString("day14.txt")).solvePart1()

            // Assert
            assertThat(answer).isEqualTo(218_965_032)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {

        @Test
        fun `Actual answer - File Method`() {
            // Act - Just use this as a main function
            Day14(resourceAsListOfString("day14.txt")).solvePart2a()
        }


        @Test
        fun `Actual answer - Unique Placement`() {
            // Act
            val answer = Day14(resourceAsListOfString("day14.txt")).solvePart2b()

            // Assert
            assertThat(answer).isEqualTo(7_037)
        }
    }
}