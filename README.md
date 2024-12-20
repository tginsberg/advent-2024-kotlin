## Advent of Code 2024 Solutions in Kotlin

[![license](https://img.shields.io/github/license/tginsberg/advent-2024-kotlin)]()

This repo is my personal attempt at solving the [Advent of Code 2024](http://adventofcode.com/2024) set of problems with
the Kotlin programming language.

I will attempt to solve each puzzle (hopefully) on the day they are posted with a nice solution in 
Kotlin, and to blog my solutions. However, given the enormous amount of time that takes, I will not promise day-of 
solutions and blogs, instead aiming to be eventually consistent. 

I've been doing this for a few years; here are my past efforts, also in Kotlin:

* 2017 - [GitHub](https://github.com/tginsberg/advent-2017-kotlin/)
  and [Blog Posts](https://todd.ginsberg.com/post/advent-of-code/2017/)
* 2018 - [GitHub](https://github.com/tginsberg/advent-2018-kotlin/)
  and [Blog Posts](https://todd.ginsberg.com/post/advent-of-code/2018/)
* 2019 - [GitHub](https://github.com/tginsberg/advent-2019-kotlin/)
  and [Blog Posts](https://todd.ginsberg.com/post/advent-of-code/2019/)
* 2020 - [GitHub](https://github.com/tginsberg/advent-2020-kotlin/)
  and [Blog Posts](https://todd.ginsberg.com/post/advent-of-code/2020/)
* 2021 - [GitHub](https://github.com/tginsberg/advent-2021-kotlin/)
  and [Blog Posts](https://todd.ginsberg.com/post/advent-of-code/2021/)
* 2022 - [GitHub](https://github.com/tginsberg/advent-2022-kotlin/)
  and [Blog Posts](https://todd.ginsberg.com/post/advent-of-code/2022/)
* 2023 - [GitHub](https://github.com/tginsberg/advent-2023-kotlin/)
  and [Blog Posts](https://todd.ginsberg.com/post/advent-of-code/2023/)

| Day | Title                  |                                  Blog Link                                   |                                                   Solution Code                                                    |                Puzzle Text                 |
|-----|:-----------------------|:----------------------------------------------------------------------------:|:------------------------------------------------------------------------------------------------------------------:|:------------------------------------------:|
| 1   | Historian Hysteria     | [Blog/Commentary](https://todd.ginsberg.com/post/advent-of-code/2024/day1/)  | [Code](https://github.com/tginsberg/advent-2024-kotlin/blob/main/src/main/kotlin/com/ginsberg/advent2024/Day01.kt) | [AoC](http://adventofcode.com/2024/day/1)  |
| 2   | Red-Nosed Reports      | [Blog/Commentary](https://todd.ginsberg.com/post/advent-of-code/2024/day2/)  | [Code](https://github.com/tginsberg/advent-2024-kotlin/blob/main/src/main/kotlin/com/ginsberg/advent2024/Day02.kt) | [AoC](http://adventofcode.com/2024/day/2)  |
| 3   | Mull It Over           | [Blog/Commentary](https://todd.ginsberg.com/post/advent-of-code/2024/day3/)  | [Code](https://github.com/tginsberg/advent-2024-kotlin/blob/main/src/main/kotlin/com/ginsberg/advent2024/Day03.kt) | [AoC](http://adventofcode.com/2024/day/3)  |
| 4   | Ceres Search           | [Blog/Commentary](https://todd.ginsberg.com/post/advent-of-code/2024/day4/)  | [Code](https://github.com/tginsberg/advent-2024-kotlin/blob/main/src/main/kotlin/com/ginsberg/advent2024/Day04.kt) | [AoC](http://adventofcode.com/2024/day/4)  |
| 5   | Print Queue            | [Blog/Commentary](https://todd.ginsberg.com/post/advent-of-code/2024/day5/)  | [Code](https://github.com/tginsberg/advent-2024-kotlin/blob/main/src/main/kotlin/com/ginsberg/advent2024/Day05.kt) | [AoC](http://adventofcode.com/2024/day/5)  |
| 6   | Guard Gallivant        | [Blog/Commentary](https://todd.ginsberg.com/post/advent-of-code/2024/day6/)  | [Code](https://github.com/tginsberg/advent-2024-kotlin/blob/main/src/main/kotlin/com/ginsberg/advent2024/Day06.kt) | [AoC](http://adventofcode.com/2024/day/6)  |
| 7   | Bridge Repair          | [Blog/Commentary](https://todd.ginsberg.com/post/advent-of-code/2024/day7/)  | [Code](https://github.com/tginsberg/advent-2024-kotlin/blob/main/src/main/kotlin/com/ginsberg/advent2024/Day07.kt) | [AoC](http://adventofcode.com/2024/day/7)  |
| 8   | Resonant Collinearity  | [Blog/Commentary](https://todd.ginsberg.com/post/advent-of-code/2024/day8/)  | [Code](https://github.com/tginsberg/advent-2024-kotlin/blob/main/src/main/kotlin/com/ginsberg/advent2024/Day08.kt) | [AoC](http://adventofcode.com/2024/day/8)  |
| 9   | Disk Fragmenter        | [Blog/Commentary](https://todd.ginsberg.com/post/advent-of-code/2024/day9/)  | [Code](https://github.com/tginsberg/advent-2024-kotlin/blob/main/src/main/kotlin/com/ginsberg/advent2024/Day09.kt) | [AoC](http://adventofcode.com/2024/day/9)  |
| 10  | Hoof It                | [Blog/Commentary](https://todd.ginsberg.com/post/advent-of-code/2024/day10/) | [Code](https://github.com/tginsberg/advent-2024-kotlin/blob/main/src/main/kotlin/com/ginsberg/advent2024/Day10.kt) | [AoC](http://adventofcode.com/2024/day/10) |
| 11  | Plutonian Pebbles      | [Blog/Commentary](https://todd.ginsberg.com/post/advent-of-code/2024/day11/) | [Code](https://github.com/tginsberg/advent-2024-kotlin/blob/main/src/main/kotlin/com/ginsberg/advent2024/Day11.kt) | [AoC](http://adventofcode.com/2024/day/11) |
| 12  | Garden Groups          | [Blog/Commentary](https://todd.ginsberg.com/post/advent-of-code/2024/day12/) | [Code](https://github.com/tginsberg/advent-2024-kotlin/blob/main/src/main/kotlin/com/ginsberg/advent2024/Day12.kt) | [AoC](http://adventofcode.com/2024/day/12) |
| 13  | Claw Contraption       | [Blog/Commentary](https://todd.ginsberg.com/post/advent-of-code/2024/day13/) | [Code](https://github.com/tginsberg/advent-2024-kotlin/blob/main/src/main/kotlin/com/ginsberg/advent2024/Day13.kt) | [AoC](http://adventofcode.com/2024/day/13) |
| 14  | Restroom Redoubt       | [Blog/Commentary](https://todd.ginsberg.com/post/advent-of-code/2024/day14/) | [Code](https://github.com/tginsberg/advent-2024-kotlin/blob/main/src/main/kotlin/com/ginsberg/advent2024/Day14.kt) | [AoC](http://adventofcode.com/2024/day/14) |
| 15  | Warehouse Woes         | [Blog/Commentary](https://todd.ginsberg.com/post/advent-of-code/2024/day15/) | [Code](https://github.com/tginsberg/advent-2024-kotlin/blob/main/src/main/kotlin/com/ginsberg/advent2024/Day15.kt) | [AoC](http://adventofcode.com/2024/day/15) |
| 16  | Reindeer Maze          | [Blog/Commentary](https://todd.ginsberg.com/post/advent-of-code/2024/day16/) | [Code](https://github.com/tginsberg/advent-2024-kotlin/blob/main/src/main/kotlin/com/ginsberg/advent2024/Day16.kt) | [AoC](http://adventofcode.com/2024/day/16) |
| 17  | Chronospatial Computer | [Blog/Commentary](https://todd.ginsberg.com/post/advent-of-code/2024/day17/) | [Code](https://github.com/tginsberg/advent-2024-kotlin/blob/main/src/main/kotlin/com/ginsberg/advent2024/Day17.kt) | [AoC](http://adventofcode.com/2024/day/17) |
| 18  | RAM Run                | [Blog/Commentary](https://todd.ginsberg.com/post/advent-of-code/2024/day18/) | [Code](https://github.com/tginsberg/advent-2024-kotlin/blob/main/src/main/kotlin/com/ginsberg/advent2024/Day18.kt) | [AoC](http://adventofcode.com/2024/day/18) |
| 19  | Linen Layout           | [Blog/Commentary](https://todd.ginsberg.com/post/advent-of-code/2024/day19/) | [Code](https://github.com/tginsberg/advent-2024-kotlin/blob/main/src/main/kotlin/com/ginsberg/advent2024/Day19.kt) | [AoC](http://adventofcode.com/2024/day/19) |
| 20  | Race Condition         | [Blog/Commentary](https://todd.ginsberg.com/post/advent-of-code/2024/day20/) | [Code](https://github.com/tginsberg/advent-2024-kotlin/blob/main/src/main/kotlin/com/ginsberg/advent2024/Day20.kt) | [AoC](http://adventofcode.com/2024/day/20) |

Copyright &copy; 2024 by Todd Ginsberg.
