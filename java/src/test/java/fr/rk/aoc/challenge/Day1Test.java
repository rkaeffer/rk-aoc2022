package fr.rk.aoc.challenge;

import fr.rk.aoc.challenge.utils.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

@Slf4j
public class Day1Test {

    ArrayList<String> inputTest = new ArrayList<>(Arrays.asList(
            "1000",
            "2000",
            "3000",
            "",
            "4000",
            "",
            "5000",
            "6000",
            "",
            "7000",
            "8000",
            "9000",
            "",
            "10000"));

    @Test
    public void testMaxElvesCalories() {
        MatcherAssert.assertThat("4th elves has maximum calories with 24000", Day1.getMaxCalories(Day1.parseInput(inputTest)), Matchers.equalTo(24000L));
    }

    @Test
    public void testMaxTopElvesCalories() {
        MatcherAssert.assertThat("3 top elves has 45000 calories", Day1.getThreeTopElvesMaxCalories(Day1.parseInput(inputTest)), Matchers.equalTo(45000L));
    }

    @Test
    public void getFirstChallengeResult() {
        FileUtils.readInputFileAsList("input.txt", 1).ifPresent(lines -> log.info("The answer is {}", Day1.getMaxCalories(Day1.parseInput(lines))));
    }

    @Test
    public void getSecondChallengeResult() {
        FileUtils.readInputFileAsList("input.txt", 1).ifPresent(lines -> log.info("The answer is {}", Day1.getThreeTopElvesMaxCalories(Day1.parseInput(lines))));
    }
}
