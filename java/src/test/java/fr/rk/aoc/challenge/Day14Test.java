package fr.rk.aoc.challenge;

import fr.rk.aoc.challenge.utils.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

@Slf4j
public class Day14Test {

    ArrayList<String> inputTest = new ArrayList<>(Arrays.asList(
            "498,4 -> 498,6 -> 496,6",
            "503,4 -> 502,4 -> 502,9 -> 494,9"));

    @Test
    public void testNbSandComeBeforeFallingIntoAbyss() {
        MatcherAssert.assertThat("Nb sand thrown before abyss is 24", Day14.getNbOfUnitSandBeforeAbyssalFlow(inputTest, false), Matchers.equalTo(24L));
    }

    @Test
    public void testNbSandComeBeforeSafePlace() {
        MatcherAssert.assertThat("Nb sand thrown before safe place is 93", Day14.getNbOfUnitSandBeforeAbyssalFlow(inputTest, true), Matchers.equalTo(93L));
    }

    @Test
    public void getFirstChallengeResult() {
        FileUtils.readInputFileAsList("input.txt", 14).ifPresent(lines -> log.info("The answer is {}", Day14.getNbOfUnitSandBeforeAbyssalFlow(lines, false)));
    }

    @Test
    public void getSecondChallengeResult() {
        FileUtils.readInputFileAsList("input.txt", 14).ifPresent(lines -> log.info("The answer is {}", Day14.getNbOfUnitSandBeforeAbyssalFlow(lines, true)));
    }
}
