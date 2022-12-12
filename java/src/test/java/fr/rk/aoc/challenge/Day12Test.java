package fr.rk.aoc.challenge;

import fr.rk.aoc.challenge.utils.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.util.*;

@Slf4j
public class Day12Test {

    ArrayList<String> inputTest = new ArrayList<>(Arrays.asList(
            "Sabqponm",
            "abcryxxl",
            "accszExk",
            "acctuvwj",
            "abdefghi"));

    @Test
    public void testShortestPath() {
        MatcherAssert.assertThat("Shortest Path is 31 step", Day12.getShortestPathLength(inputTest, false), Matchers.equalTo(31L));
    }

    @Test
    public void testShortestPathFromAnyPosition() {
        MatcherAssert.assertThat("Shortest Path is 29 step", Day12.getShortestPathLength(inputTest, true), Matchers.equalTo(29L));
    }

    @Test
    public void getFirstChallengeResult() {
        FileUtils.readInputFileAsList("input.txt", 12).ifPresent(lines -> log.info("The answer is {}", Day12.getShortestPathLength(lines, false)));
    }

    @Test
    public void getSecondChallengeResult() {
        FileUtils.readInputFileAsList("input.txt", 12).ifPresent(lines -> log.info("The answer is {}", Day12.getShortestPathLength(lines, true)));
    }


}
