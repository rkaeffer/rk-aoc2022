package fr.rk.aoc.challenge;

import fr.rk.aoc.challenge.utils.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

@Slf4j
public class Day3Test {

    ArrayList<String> inputTest = new ArrayList<>(Arrays.asList(
            "vJrwpWtwJgWrhcsFMMfFFhFp",
            "jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL",
            "PmmdzqPrVvPwwTWBwg",
            "wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn",
            "ttgJtRGJQctTZtZT",
            "CrZsJsPPZsGzwwsLwLmpwMDw"));

    @Test
    public void rucksackPriorizeSumIs157() {
        MatcherAssert.assertThat("Test score is 157", Day3.getPrioritizeItemSumScore(inputTest), Matchers.equalTo(157L));
    }

    @Test
    public void rucksackGroupPriorizeSumIs70() {
        MatcherAssert.assertThat("Test score is 70", Day3.getCommonGroupItemSumScore(inputTest), Matchers.equalTo(70L));
    }

    @Test
    public void getFirstChallengeResult() {
        FileUtils.readInputFileAsList("input.txt", 3).ifPresent(lines -> log.info("The answer is {}", Day3.getPrioritizeItemSumScore(lines)));
    }

    @Test
    public void getSecondChallengeResult() {
        FileUtils.readInputFileAsList("input.txt", 3).ifPresent(lines -> log.info("The answer is {}", Day3.getCommonGroupItemSumScore(lines)));
    }


}
