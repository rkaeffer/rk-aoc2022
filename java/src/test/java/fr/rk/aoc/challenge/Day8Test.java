package fr.rk.aoc.challenge;

import fr.rk.aoc.challenge.utils.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

@Slf4j
public class Day8Test {

    ArrayList<String> inputTest = new ArrayList<>(Arrays.asList(
            "30373",
            "25512",
            "65332",
            "33549",
            "35390"));

    @Test
    public void testCalculateVisibleTrees() {
        MatcherAssert.assertThat("Visible trees is 21", Day8.getTotoalVisibleTree(inputTest), Matchers.equalTo(21L));
    }

    @Test
    public void testHighestScenirScoreTrees() {
        MatcherAssert.assertThat("Highest Scenic Score is 8", Day8.getHighestScenicScore(inputTest), Matchers.equalTo(8L));
    }

    @Test
    public void getFirstChallengeResult() {
        FileUtils.readInputFileAsList("input.txt", 8).ifPresent(lines -> log.info("The answer is {}", Day8.getTotoalVisibleTree(lines)));
    }

    @Test
    public void getSecondChallenge() {
        FileUtils.readInputFileAsList("input.txt", 8).ifPresent(lines -> log.info("The answer is {}", Day8.getHighestScenicScore(lines)));
    }

}
