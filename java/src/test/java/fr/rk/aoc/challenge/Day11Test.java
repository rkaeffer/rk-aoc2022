package fr.rk.aoc.challenge;

import fr.rk.aoc.challenge.utils.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

@Slf4j
public class Day11Test {

    @Test
    public void testMonkeyBusinessLevel() {
        MatcherAssert.assertThat("Monkey business example is 10605",
                FileUtils.readInputFileAsList("example.txt", 11).map( lines -> Day11.getMonKeyBusinessAfterAnyRound(lines, 20, true)).get(),
                Matchers.equalTo(10605L));
    }

    @Test
    public void testMonkeyBusinessLevelWithoutDivide() {
        MatcherAssert.assertThat("Monkey business example is 2713310158",
                FileUtils.readInputFileAsList("example.txt", 11).map( lines -> Day11.getMonKeyBusinessAfterAnyRound(lines, 10000, false)).get(),
                Matchers.equalTo(2713310158L));
    }

    @Test
    public void getFirstChallengeResult() {
        FileUtils.readInputFileAsList("input.txt", 11).ifPresent(lines -> log.info("The answer is {}", Day11.getMonKeyBusinessAfterAnyRound(lines, 20, true)));
    }

    @Test
    public void getSecondChallengeResult() {
        FileUtils.readInputFileAsList("input.txt", 11).ifPresent(lines -> log.info("The answer is {}", Day11.getMonKeyBusinessAfterAnyRound(lines, 10000, false)));
    }
}
