package fr.rk.aoc.challenge;

import fr.rk.aoc.challenge.utils.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

@Slf4j
public class Day10Test {

    String finalCrtResult =
            "##..##..##..##..##..##..##..##..##..##.." +
            "###...###...###...###...###...###...###." +
            "####....####....####....####....####...." +
            "#####.....#####.....#####.....#####....." +
            "######......######......######......####" +
            "#######.......#######.......#######.....";

    @Test
    public void testSignalStrengthExample() {
        MatcherAssert.assertThat("Example has a signal strength of 13140",
                FileUtils.readInputFileAsList("example.txt", 10).map(Day10::getSumOfSignalStrength).get(),
                Matchers.equalTo(13140L));
    }

    @Test
    public void testCRTDisplayExample() {
        MatcherAssert.assertThat("CRT disaply is correct",
                FileUtils.readInputFileAsList("example.txt", 10).map(Day10::getCRTDisplay).get(),
                Matchers.equalTo(finalCrtResult));
    }


    @Test
    public void getFirstChallengeResult() {
        FileUtils.readInputFileAsList("input.txt", 10).ifPresent(lines -> log.info("The answer is {}", Day10.getSumOfSignalStrength(lines)));
    }

    @Test
    public void getSecondChallengeResult() {
        FileUtils.readInputFileAsList("input.txt", 10).ifPresent(lines -> log.info("The answer is {}", Day10.getCRTDisplay(lines)));
        //ERCREPCJ
    }



}
