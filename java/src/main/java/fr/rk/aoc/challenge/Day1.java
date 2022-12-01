package fr.rk.aoc.challenge;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public final class Day1 {

    public static List<Long> parseInput(List<String> input) {
        List<Long> elvesCalories = new ArrayList<>();
        long curCalories = 0;
        for(String calories : input) {
            if(StringUtils.EMPTY.equals(calories)) {
                elvesCalories.add(curCalories);
                curCalories = 0;
                continue;
            }
            curCalories += Long.parseLong(calories);
        }
        elvesCalories.add(curCalories);
        for(int i=0; i<elvesCalories.size(); i++) {
            log.info("Elve {} has {} calories", i, elvesCalories.get(i));
        }
        return elvesCalories;
    }

    public static long getMaxCalories(List<Long> elvesCalories) {
        return elvesCalories.stream().max(Long::compareTo).get();
    }

    public static long getThreeTopElvesMaxCalories(List<Long> elvesCalories) {
        long totalCalories = 0;
        for(int i=0; i<3; i++) {
            long currMax = getMaxCalories(elvesCalories);
            elvesCalories.remove(elvesCalories.indexOf(currMax));
            totalCalories += currMax;
        }
        return totalCalories;
    }


}
