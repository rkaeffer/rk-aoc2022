package fr.rk.aoc.challenge;

import java.util.List;

public final class Day8 {

    public static long getTotoalVisibleTree(List<String> input) {
        int[][] trees = parseTrees(input);
        boolean[][] interiorTrees = parseInteriorsTrees(trees);
        //Parcours ligne G->D
        int cnt=0;
        for(int i=0; i<interiorTrees.length; i++) {
            for(int j=0; j<interiorTrees[i].length; j++) {
                if(interiorTrees[i][j]) {
                    cnt++;
                }
            }
        }
        return ((trees.length + trees[0].length - 2) * 2L) + cnt;
    }

    public static long getHighestScenicScore(List<String> input) {
        int[][] trees = parseTrees(input);
        boolean[][] interiorTrees = parseInteriorsTrees(trees);
        boolean[][] allTrees = new boolean[interiorTrees.length + 2][interiorTrees.length + 2];
        //Everything to true
        for(int i=0; i<allTrees.length; i++) {
            for(int j=0; j<allTrees[i].length; j++) {
                allTrees[i][j] = true;
            }
        }
        for(int i=1; i<allTrees.length-1; i++) {
            for(int j=1; j<allTrees[i].length-1; j++) {
                allTrees[i][j] = interiorTrees[i-1][j-1];
            }
        }
        long highestScenicScore = -1L;
        for(int i=1; i<allTrees.length-1; i++) {
            for(int j=1; j<allTrees[i].length-1; j++) {
                if(allTrees[i][j]) {
                    long scenicScore = 1;
                    //Droite
                    int curY = i;
                    int curX = j;
                    int curCnt = 1;
                    while(curX < trees[curX].length - 2 && trees[i][j] > trees[curY][curX + 1]) {
                        curCnt++;
                        curX++;
                    }
                    scenicScore*=curCnt;
                    //Gauche
                    curY = i;
                    curX = j;
                    curCnt = 1;
                    while(curX > 1 && trees[i][j] > trees[curY][curX - 1]) {
                        curCnt++;
                        curX--;
                    }
                    scenicScore*=curCnt;
                    //Haut
                    curY = i;
                    curX = j;
                    curCnt = 1;
                    while(curY > 1 && trees[i][j] > trees[curY - 1][curX]) {
                        curCnt++;
                        curY--;
                    }
                    scenicScore*=curCnt;
                    //Bas
                    curY = i;
                    curX = j;
                    curCnt = 1;
                    while(curY < trees.length - 2 && trees[i][j] > trees[curY + 1][curX]) {
                        curCnt++;
                        curY++;
                    }
                    scenicScore*=curCnt;
                    highestScenicScore = Math.max(highestScenicScore, scenicScore);
                }
            }
        }
        return highestScenicScore;
    }

    private static boolean[][] parseInteriorsTrees(int[][] trees) {
        boolean[][] interiorTrees = new boolean[trees.length-2][trees[0].length-2];
        //Parcours ligne G->D
        for(int i=1; i<trees.length-1; i++) {
            int max = trees[i][0];
            for(int j=1; j<trees[i].length-1; j++) {
                if(max < trees[i][j]) {
                    interiorTrees[i-1][j-1] = true;
                }
                max = Math.max(max, trees[i][j]);
            }
        }
        //Parcours ligne D->G
        for(int i=1; i<trees.length-1; i++) {
            int max = trees[i][trees[i].length-1];
            for(int j=trees[i].length-2; j>0; j--) {
                if(max < trees[i][j]) {
                    interiorTrees[i-1][j-1] = true;
                }
                max = Math.max(max, trees[i][j]);
            }
        }
        //Parcours colonne H->B
        for(int j=1; j<trees[1].length-1; j++) {
            int max = trees[0][j];
            for(int i=1; i<trees.length-1; i++) {
                if(max < trees[i][j]) {
                    interiorTrees[i-1][j-1] = true;
                }
                max = Math.max(max, trees[i][j]);
            }
        }
        //Parcours colonne B->H
        for(int j=1; j<trees[1].length-1; j++) {
            int max = trees[trees.length-1][j];
            for(int i=trees.length-2; i>0; i--) {
                if(max < trees[i][j]) {
                    interiorTrees[i-1][j-1] = true;
                }
                max = Math.max(max, trees[i][j]);
            }
        }
        return interiorTrees;
    }

    private static int[][] parseTrees(List<String> input) {
        int[][] trees = new int[input.size()][input.get(0).length()];
        for(int i=0; i<input.size(); i++) {
            for(int j=0; j<input.get(i).length(); j++) {
                trees[i][j] = Integer.parseInt(input.get(i).charAt(j) + "");
            }
        }
        return trees;
    }
}
