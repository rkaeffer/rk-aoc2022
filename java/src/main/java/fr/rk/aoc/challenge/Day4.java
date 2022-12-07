package fr.rk.aoc.challenge;

import java.util.*;

public final class Day4 {

    public static long getCommonAssignement(List<String> input) {
        return input.stream()
                .map(SectionAssignement::new)
                .filter(SectionAssignement::hasCommonSection)
                .count();
    }

    public static long getOverlappingAssignement(List<String> input) {
        return input.stream()
                .map(SectionAssignement::new)
                .filter(SectionAssignement::hasOverlap)
                .count();
    }

    static class SectionAssignement {

        Integer min1;
        Integer max1;
        Integer min2;
        Integer max2;

        public SectionAssignement(String input) {
            String[] section = input.split(",");
            String[] section1 = section[0].split("-");
            String[] section2 = section[1].split("-");
            min1 = Integer.parseInt(section1[0]);
            max1 = Integer.parseInt(section1[1]);
            min2 = Integer.parseInt(section2[0]);
            max2 = Integer.parseInt(section2[1]);
        }

        public boolean hasCommonSection() {
            return (min1 >= min2 && max1 <= max2) || (min2>= min1 && max2 <= max1);
        }

        public boolean hasOverlap() {
            return min1 <= max2 && min2 <= max1;
        }

    }
}
