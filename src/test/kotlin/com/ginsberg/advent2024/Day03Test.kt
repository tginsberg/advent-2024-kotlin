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

import com.ginsberg.advent2024.Resources.resourceAsString
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 3")
class Day03Test {

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        // Arrange
        private val input = "xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))"

        @Test
        fun `Matches example`() {
            // Act
            val answer = Day03(input).solvePart1()

            // Assert
            assertThat(answer).isEqualTo(161)
        }

        @Test
        fun `Actual answer`() {
            // Act
            val answer = Day03(resourceAsString("day03.txt")).solvePart1()

            // Assert
            assertThat(answer).isEqualTo(169_021_493)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {

        // Arrange
        private val input = "xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))"

        @Test
        fun `Matches example`() {
            // Act
            val answer = Day03(input).solvePart2()

            // Assert
            assertThat(answer).isEqualTo(48)
        }

        @Test
        fun `Actual answer`() {
            // Act
            val answer = Day03(resourceAsString("day03.txt")).solvePart2()

            // Assert
            assertThat(answer).isEqualTo(111_762_583)
        }
    }
}