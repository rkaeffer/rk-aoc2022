package fr.rk.aoc.challenge;

import fr.rk.aoc.challenge.utils.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

@Slf4j
public class Day6Test {

    String inputTest1 = "mjqjpqmgbljsphdztnvjfqwrcgsmlb";
    String inputTest2 = "bvwbjplbgvbhsrlpgdmjqwftvncz";
    String inputTest3 = "nppdvjthqldpwncqszvftbrmjlhg";
    String inputTest4 = "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg";
    String inputTest5 = "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw";

    @Test
    public void testFirst4MarkerAreInCorrectPosition() {
        MatcherAssert.assertThat("test 1 has correct marker", Day6.getFirstMarker(inputTest1, 4), Matchers.equalTo(7L));
        MatcherAssert.assertThat("test 2 has correct marker", Day6.getFirstMarker(inputTest2,4), Matchers.equalTo(5L));
        MatcherAssert.assertThat("test 3 has correct marker", Day6.getFirstMarker(inputTest3,4), Matchers.equalTo(6L));
        MatcherAssert.assertThat("test 4 has correct marker", Day6.getFirstMarker(inputTest4,4), Matchers.equalTo(10L));
        MatcherAssert.assertThat("test 5 has correct marker", Day6.getFirstMarker(inputTest5,4), Matchers.equalTo(11L));
    }

    @Test
    public void testFirst14MarkerAreInCorrectPosition() {
        MatcherAssert.assertThat("test 1 has correct marker", Day6.getFirstMarker(inputTest1, 14), Matchers.equalTo(19L));
        MatcherAssert.assertThat("test 2 has correct marker", Day6.getFirstMarker(inputTest2,14), Matchers.equalTo(23L));
        MatcherAssert.assertThat("test 3 has correct marker", Day6.getFirstMarker(inputTest3,14), Matchers.equalTo(23L));
        MatcherAssert.assertThat("test 4 has correct marker", Day6.getFirstMarker(inputTest4,14), Matchers.equalTo(29L));
        MatcherAssert.assertThat("test 5 has correct marker", Day6.getFirstMarker(inputTest5,14), Matchers.equalTo(26L));
    }

    @Test
    public void getFirstChallengeResult() {
        FileUtils.readInputFileAsList("input.txt", 6).ifPresent(lines -> log.info("The answer is {}", Day6.getFirstMarker(lines.get(0),4)));
    }

    @Test
    public void getSecondChallengeResult() {
        FileUtils.readInputFileAsList("input.txt", 6).ifPresent(lines -> log.info("The answer is {}", Day6.getFirstMarker(lines.get(0),14)));
    }

}
