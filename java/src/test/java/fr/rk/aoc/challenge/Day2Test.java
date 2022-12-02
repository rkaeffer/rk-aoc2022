package fr.rk.aoc.challenge;

import fr.rk.aoc.challenge.utils.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

@Slf4j
public class Day2Test {

    ArrayList<String> inputTest = new ArrayList<>(Arrays.asList(
            "A Y",
            "B X",
            "C Z"));

    @Test
    public void totalTestScoreIs15() {
        MatcherAssert.assertThat("Test score is 15", Day2.getTotalScore(inputTest, false), Matchers.equalTo(15L));
    }

    @Test
    public void totalTestScoreWithStrategyIs12() {
        MatcherAssert.assertThat("Test score with strategy is 12", Day2.getTotalScore(inputTest, true), Matchers.equalTo(12L));
    }

    @Test
    public void getFirstChallengeResult() {
        FileUtils.readInputFileAsList("input.txt", 2).ifPresent(lines -> log.info("The answer is {}", Day2.getTotalScore(lines, false)));
    }

    @Test
    public void getSecondChallengeResult() {
        FileUtils.readInputFileAsList("input.txt", 2).ifPresent(lines -> log.info("The answer is {}", Day2.getTotalScore(lines, true)));
    }


}
