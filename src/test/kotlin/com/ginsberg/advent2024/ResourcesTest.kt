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

import com.ginsberg.advent2024.Resources.resourceAsListOfInt
import com.ginsberg.advent2024.Resources.resourceAsListOfLong
import com.ginsberg.advent2024.Resources.resourceAsListOfString
import com.ginsberg.advent2024.Resources.resourceAsString
import com.ginsberg.advent2024.Resources.resourceAsText
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class ResourcesTest {

    @Nested
    inner class ResourceAsStringTests {
        @Test
        fun `concatenates lines without delimiter`() {
            assertThat(resourceAsString("read_file_test_1.txt"))
                .isEqualTo("123")
        }

        @Test
        fun `concatenates lines with delimiter`() {
            assertThat(resourceAsString(fileName = "read_file_test_1.txt", delimiter = ":::"))
                .isEqualTo("1:::2:::3")
        }

        @Test
        fun `throws exception when file does not exist`() {
            assertThatThrownBy {
                resourceAsString("this_does_not_exist.txt", delimiter = "???")
            }.isInstanceOf(IllegalArgumentException::class.java)
        }
    }

    @Nested
    inner class ResourceAsTextTests {
        @Test
        fun `reads file as-is into one String`() {
            assertThat(resourceAsText("read_file_test_1.txt"))
                .isEqualTo(
                    """
                    1
                    2
                    3
                """.trimIndent()
                )
        }
    }

    @Nested
    inner class ResourceAsListTests {
        @Test
        fun `reads lines as Strings`() {
            assertThat(resourceAsListOfString("read_file_test_1.txt"))
                .hasSize(3)
                .containsExactly("1", "2", "3")
        }

        @Test
        fun `reads lines as Ints`() {
            assertThat(resourceAsListOfInt("read_file_test_1.txt"))
                .hasSize(3)
                .containsExactly(1, 2, 3)
        }

        @Test
        fun `reads lines as Longs`() {
            assertThat(resourceAsListOfLong("read_file_test_1.txt"))
                .hasSize(3)
                .containsExactly(1L, 2L, 3L)
        }

        @Test
        fun `throws exception when file does not exist`() {
            assertThatThrownBy {
                resourceAsListOfString("this_does_not_exist.txt")
            }.isInstanceOf(IllegalArgumentException::class.java)
        }
    }

}