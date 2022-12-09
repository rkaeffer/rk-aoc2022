package fr.rk.aoc.challenge;

import fr.rk.aoc.challenge.utils.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

@Slf4j
public class Day9Test {

    ArrayList<String> inputTest = new ArrayList<>(Arrays.asList(
            "R 4",
            "U 4",
            "L 3",
            "D 1",
            "R 4",
            "D 1",
            "L 5",
            "R 2"));
    ArrayList<String> inputTest2 = new ArrayList<>(Arrays.asList(
            "R 5",
            "U 8",
            "L 8",
            "D 3",
            "R 17",
            "D 10",
            "L 25",
            "U 20"));

    @Test
    public void testGetUniqueTailPosition() {
        MatcherAssert.assertThat("Unique tail position are 13", Day9.getUniqueTailPosition(inputTest, 2), Matchers.equalTo(13L));
        MatcherAssert.assertThat("Unique tail position are 1", Day9.getUniqueTailPosition(inputTest, 10), Matchers.equalTo(1L));
        MatcherAssert.assertThat("Unique tail position are 36", Day9.getUniqueTailPosition(inputTest2, 10), Matchers.equalTo(36L));

    }

    @Test
    public void getFirstChallengeResult() {
        FileUtils.readInputFileAsList("input.txt", 9).ifPresent(lines -> log.info("The answer is {}", Day9.getUniqueTailPosition(lines, 2)));
    }

    @Test
    public void getSecondChallengeResult() {
        FileUtils.readInputFileAsList("input.txt", 9).ifPresent(lines -> log.info("The answer is {}", Day9.getUniqueTailPosition(lines, 10)));
    }


}
