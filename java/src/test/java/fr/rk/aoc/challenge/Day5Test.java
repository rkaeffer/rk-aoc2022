package fr.rk.aoc.challenge;

import fr.rk.aoc.challenge.utils.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

@Slf4j
public class Day5Test {

    ArrayList<String> inputTest = new ArrayList<>(Arrays.asList(
            "    [D]    ",
            "[N] [C]    ",
            "[Z] [M] [P]",
            " 1   2   3 ",
            "",
            "move 1 from 2 to 1",
            "move 3 from 1 to 3",
            "move 2 from 2 to 1",
            "move 1 from 1 to 2"));

    @Test
    public void testTopCrates9000AreCMZ() {
        MatcherAssert.assertThat("Top Crates are CMZ", Day5.getTopCrates(inputTest, false), Matchers.equalTo("CMZ"));
    }

    @Test
    public void testTopCrates9001AreMCD() {
        MatcherAssert.assertThat("Top Crates are MCD", Day5.getTopCrates(inputTest, true), Matchers.equalTo("MCD"));
    }

    /**
     * WARN : Intellij withdraw trailing char in first input.txt line, check before launch
     */
    @Test
    public void getFirstChallengeResult() {
        FileUtils.readInputFileAsList("input.txt", 5).ifPresent(lines -> log.info("The answer is {}", Day5.getTopCrates(lines, false)));
    }

    /**
     * WARN : Intellij withdraw trailing char in first input.txt line, check before launch
     */
    @Test
    public void getSecondChallengeResult() {
        FileUtils.readInputFileAsList("input.txt", 5).ifPresent(lines -> log.info("The answer is {}", Day5.getTopCrates(lines, true)));
    }

}
