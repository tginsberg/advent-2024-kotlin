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

@DisplayName("Day 18")
class Day18Test {

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `Matches example`() {
            // Act
            val answer = Day18(resourceAsListOfString("day18_sample.txt"))
                .solvePart1(Point2D(6,6), 12)

            // Assert
            assertThat(answer).isEqualTo(22)
        }

        @Test
        fun `Actual answer`() {
            // Act
            val answer = Day18(resourceAsListOfString("day18.txt"))
                .solvePart1(Point2D(70,70), 1024)

            // Assert
            assertThat(answer).isEqualTo(364)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {

        @Test
        fun `Matches example`() {
            // Act
            val answer = Day18(resourceAsListOfString("day18_sample.txt")).solvePart2(Point2D(6,6))

            // Assert
            assertThat(answer).isEqualTo("6,1")
        }

        @Test
        fun `Actual answer`() {
            // Act
            val answer = Day18(resourceAsListOfString("day18.txt")).solvePart2(Point2D(70,70))

            // Assert
            assertThat(answer).isEqualTo("52,28")
        }
    }
}