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

@DisplayName("Day 16")
class Day16Test {

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `Matches example 1`() {
            // Act
            val answer = Day16(resourceAsListOfString("day16_sample1.txt")).solvePart1()

            // Assert
            assertThat(answer).isEqualTo(7036)
        }

        @Test
        fun `Matches example 2`() {

            // Act
            val answer = Day16(resourceAsListOfString("day16_sample2.txt")).solvePart1()

            // Assert
            assertThat(answer).isEqualTo(11048)
        }

        @Test
        fun `Actual answer`() {
            // Act
            val answer = Day16(resourceAsListOfString("day16.txt")).solvePart1()

            // Assert
            assertThat(answer).isEqualTo(65436)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {

        @Test
        fun `Matches example 1`() {
            // Act
            val answer = Day16(resourceAsListOfString("day16_sample1.txt")).solvePart2()

            // Assert
            assertThat(answer).isEqualTo(45)
        }

        @Test
        fun `Matches example 2`() {

            // Act
            val answer = Day16(resourceAsListOfString("day16_sample2.txt")).solvePart2()

            // Assert
            assertThat(answer).isEqualTo(64)
        }

        @Test
        fun `Actual answer`() {
            // Act
            val answer = Day16(resourceAsListOfString("day16.txt")).solvePart2()

            // Assert
            assertThat(answer).isEqualTo(489)
        }
    }
}