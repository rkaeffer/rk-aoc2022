package fr.rk.aoc.challenge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Day7 {

    public static long getSumOfDirectoriesAtMost100000(List<String> input) {
        ElvesDirectory mainDirectory = parseDirectories(input);
        return getSubdirAtMost100000Size(mainDirectory);
    }

    public static long getSizeOfDirectoryToDelete(List<String> input) {
        ElvesDirectory mainDirectory = parseDirectories(input);
        long toDelete = 30000000L - (70000000L - mainDirectory.getDirectorySize());
        List<Long> subDirSize = getAllSubdirSize(mainDirectory);
        Collections.sort(subDirSize);
        for(Long cursize : subDirSize) {
            if(cursize >= toDelete) {
                return cursize;
            }
        }
        return -1L;
    }

    public static ElvesDirectory parseDirectories(List<String> input) {
        ElvesDirectory mainDirectory = new ElvesDirectory("/");
        ElvesDirectory curentDirectory = mainDirectory;
        //Parsing
        for(int i=1; i<input.size()-1; i++) {
            String curLine = input.get(i);
            String[] command = curLine.split(" ");
            if(command[1].equals("ls")) {
                String peek;
                while(!(peek = input.get(i+1)).startsWith("$")) {
                    String[] lsLineResult = peek.split(" ");
                    if(lsLineResult[0].equals("dir")) {
                        ElvesDirectory elvesDirectory = new ElvesDirectory(lsLineResult[1]);
                        elvesDirectory.parent = curentDirectory;
                        curentDirectory.subdirectories.add(elvesDirectory);
                    } else {
                        curentDirectory.files.add(new ElvesFiles(peek));
                    }
                    if(i+1 == input.size() - 1) {
                        break;
                    } else {
                        i++;
                    }
                }
            } else if(command[1].equals("cd")) {
                if(command[2].equals("..")) {
                    curentDirectory = curentDirectory.parent;
                } else {
                    for(ElvesDirectory subdir : curentDirectory.subdirectories) {
                        if(subdir.name.equals(command[2])) {
                            curentDirectory = subdir;
                            break;
                        }
                    }
                }
            }
        }
        return mainDirectory;
    }

    private static long getSubdirAtMost100000Size(ElvesDirectory mainDirectory) {
        long total = 0;
        for(ElvesDirectory subdir : mainDirectory.subdirectories) {
            long subDirSize = subdir.getDirectorySize();
            if(subDirSize < 100000L) {
                total += subDirSize;
            }
            total += getSubdirAtMost100000Size(subdir);
        }
        return total;
    }

    private static List<Long> getAllSubdirSize(ElvesDirectory mainDirectory) {
        List<Long> sizes = new ArrayList<>();
        for(ElvesDirectory subdir : mainDirectory.subdirectories) {
            sizes.add(subdir.getDirectorySize());
            sizes.addAll(getAllSubdirSize(subdir));
        }
        return sizes;
    }

    static class ElvesDirectory {
        String name;
        ElvesDirectory parent;
        List<ElvesDirectory> subdirectories;
        List<ElvesFiles> files;

        public ElvesDirectory(String name) {
            this.name = name;
            this.subdirectories = new ArrayList<>();
            this.files = new ArrayList<>();
        }

        public long getDirectorySize() {
            long total = 0;
            total += subdirectories.stream().mapToLong(ElvesDirectory::getDirectorySize).sum();
            total += files.stream().mapToLong(f -> f.size).sum();
            return total;
        }
    }

    static class ElvesFiles {
        String name;
        long size;

        public ElvesFiles(String line) {
            String[] data = line.split(" ");
            this.size = Long.parseLong(data[0]);
            this.name = data[1];
        }
    }

}
