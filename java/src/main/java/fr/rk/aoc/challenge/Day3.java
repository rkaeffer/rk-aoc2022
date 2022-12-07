package fr.rk.aoc.challenge;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.ListUtils;

import javax.swing.*;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
public final class Day3 {

    public static long getPrioritizeItemSumScore(List<String> input) {
        return input.stream()
                .map(Rucksacks::new)
                .map(Rucksacks::findOnlyCommonChar)
                .mapToLong(Rucksacks::getCharValue)
                .sum();
    }

    public static long getCommonGroupItemSumScore(List<String> input) {
        List<Rucksacks> rucksacks = input.stream()
                .map(Rucksacks::new)
                .collect(Collectors.toList());
        List<List<Rucksacks>> partitionnedRucksacks = ListUtils.partition(rucksacks, 3);
        return partitionnedRucksacks.stream()
                .map(Rucksacks::findOnlyCommonChar)
                .mapToLong(Rucksacks::getCharValue)
                .sum();
    }

    private static class Rucksacks {

        String original;
        String partOne;
        String partTwo;

        public Rucksacks(String original) {
            this.original = original;
            this.partOne = this.original.substring(0, (original.length() / 2));
            this.partTwo = this.original.substring(original.length() / 2, original.length());
            this.print();
        }

        public Character findOnlyCommonChar() {
            for(Character c : partOne.toCharArray()) {
                if(partTwo.contains(c + "")) {
                    return c;
                }
            }
            return 0;
        }

        public static Character findOnlyCommonChar(List<Rucksacks> rucksacks) {
            for(Character c : rucksacks.get(0).original.toCharArray()) {
                if(rucksacks.get(1).original.contains(c + "") && rucksacks.get(2).original.contains(c + "")) {
                    return c;
                }
            }
            return 0;
        }

        public static Long getCharValue(Character c) {
            if(Character.isUpperCase(c)) {
                //log.info("{} UPPER has score {}", c, c - 37L);
                return c - 38L;
            } else {
                //log.info("{} LOWER has score {}", c, c - 96L);
                return c - 96L;
            }
        }

        public void print() {
            log.info("Rucksacks {} : {} / {}", original, partOne, partTwo);
        }
    }
}
