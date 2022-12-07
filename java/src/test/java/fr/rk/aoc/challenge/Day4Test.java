package fr.rk.aoc.challenge;

import fr.rk.aoc.challenge.utils.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

@Slf4j
public class Day4Test {

    ArrayList<String> inputTest = new ArrayList<>(Arrays.asList(
            "2-4,6-8",
            "2-3,4-5",
            "5-7,7-9",
            "2-8,3-7",
            "6-6,4-6",
            "2-6,4-8"));

    @Test
    public void testInputHas2CommonSection() {
        MatcherAssert.assertThat("Test section has 2 commons section", Day4.getCommonAssignement(inputTest), Matchers.equalTo(2L));
    }

    @Test
    public void testInputHas4OverlappingSection() {
        MatcherAssert.assertThat("Test section has 4 overlapping section", Day4.getOverlappingAssignement(inputTest), Matchers.equalTo(4L));
    }

    @Test
    public void getFirstChallengeResult() {
        FileUtils.readInputFileAsList("input.txt", 4).ifPresent(lines -> log.info("The answer is {}", Day4.getCommonAssignement(lines)));
    }

    @Test
    public void getSecondChallengeResult() {
        FileUtils.readInputFileAsList("input.txt", 4).ifPresent(lines -> log.info("The answer is {}", Day4.getOverlappingAssignement(lines)));
    }
}
