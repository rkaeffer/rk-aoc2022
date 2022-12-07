package fr.rk.aoc.challenge;

import fr.rk.aoc.challenge.utils.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

@Slf4j
public class Day7Test {

    ArrayList<String> inputTest = new ArrayList<>(Arrays.asList(
            "$ cd /",
            "$ ls",
            "dir a",
            "14848514 b.txt",
            "8504156 c.dat",
            "dir d",
            "$ cd a",
            "$ ls",
            "dir e",
            "29116 f",
            "2557 g",
            "62596 h.lst",
            "$ cd e",
            "$ ls",
            "584 i",
            "$ cd ..",
            "$ cd ..",
            "$ cd d",
            "$ ls",
            "4060174 j",
            "8033020 d.log",
            "5626152 d.ext",
            "7214296 k"));

    @Test
    public void testSumSmallDirectories() {
        MatcherAssert.assertThat("Directories with at most 100000 ", Day7.getSumOfDirectoriesAtMost100000(inputTest), Matchers.equalTo(95437L));
    }

    @Test
    public void testSpaceDirectoryToDelete() {
        MatcherAssert.assertThat("Directoriy to delete has size 24933642", Day7.getSizeOfDirectoryToDelete(inputTest), Matchers.equalTo(24933642L));
    }

    @Test
    public void getFirstChallengeResult() {
        FileUtils.readInputFileAsList("input.txt", 7).ifPresent(lines -> log.info("The answer is {}", Day7.getSumOfDirectoriesAtMost100000(lines)));
    }

    @Test
    public void getSecondChallengeResult() {
        FileUtils.readInputFileAsList("input.txt", 7).ifPresent(lines -> log.info("The answer is {}", Day7.getSizeOfDirectoryToDelete(lines)));
    }

}
