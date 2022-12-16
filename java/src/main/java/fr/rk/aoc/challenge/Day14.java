package fr.rk.aoc.challenge;

import org.apache.commons.collections4.ListUtils;

import java.util.*;

public final class Day14 {

    public static long getNbOfUnitSandBeforeAbyssalFlow(List<String> input, boolean hasFloor)  {
        List<CaveUnit> rockCaveUnits = getRockUnit(input);
        int yMax = rockCaveUnits.stream().mapToInt(u -> u.y).max().getAsInt();
        int xMax = rockCaveUnits.stream().mapToInt(u -> u.x).max().getAsInt();
        int xMin = rockCaveUnits.stream().mapToInt(u -> u.x).min().getAsInt();
        int lineLength = xMax - xMin + 1;
        int sandStartPos = lineLength - (xMax - 500) - 1;
        if(hasFloor) {
            sandStartPos+=yMax;
        }
        CaveUnit[][] caveUnits = initCaveUnits(yMax + 1, lineLength, hasFloor);
        rockCaveUnits.forEach(cu -> caveUnits[cu.y][hasFloor ? cu.x - xMin + yMax : cu.x - xMin] = cu);
        displayCaveUnits(caveUnits);
        int nbSandIntegrated = 0;
        while(integrateSand(sandStartPos, 0, caveUnits)) {
            nbSandIntegrated++;
            //displayCaveUnits(caveUnits);
        }
        displayCaveUnits(caveUnits);
        return nbSandIntegrated;
    }

    public static boolean integrateSand(int xStart, int yStart, CaveUnit[][] caveUnits) {
        try {
            if(caveUnits[yStart + 1][xStart].unitDesc == UnitDesc.SPACE) {
                return integrateSand(xStart, yStart +1, caveUnits);
            } else if (caveUnits[yStart + 1][xStart - 1].unitDesc == UnitDesc.SPACE) {
                return integrateSand(xStart - 1, yStart +1, caveUnits);
            } else if (caveUnits[yStart + 1][xStart + 1].unitDesc == UnitDesc.SPACE) {
                return integrateSand(xStart + 1, yStart +1, caveUnits);
            } else {
                //For part 2
                if(caveUnits[yStart][xStart].unitDesc == UnitDesc.SAND) {
                    return false;
                }
                caveUnits[yStart][xStart] = new CaveUnit(xStart, yStart, UnitDesc.SAND);
                return true;
            }
        } catch(IndexOutOfBoundsException ex) {
            return false;
        }
    }

    private static void displayCaveUnits(CaveUnit[][] cus) {
        for(int i=0; i<cus.length; i++) {
            for (int j = 0; j < cus[i].length; j++) {
                System.out.print(cus[i][j].unitDesc.desc);
            }
            System.out.println();
        }
    }

    private static CaveUnit[][] initCaveUnits(int height, int width, boolean hasFloor) {
        CaveUnit[][] caveUnits = new CaveUnit[hasFloor? height + 2 : height][hasFloor ? width + (2*height): width];
        for(int i=0; i<caveUnits.length; i++) {
            for(int j=0; j<caveUnits[i].length; j++) {
                if(hasFloor && i == caveUnits.length - 1) {
                    caveUnits[i][j] = new CaveUnit(j, i, UnitDesc.ROCK);
                } else {
                    caveUnits[i][j] = new CaveUnit(j, i, UnitDesc.SPACE);
                }
            }
        }
        return caveUnits;
    }

    private static List<CaveUnit> getRockUnit(List<String> input) {
        List<CaveUnit> rockCaveUnits = new ArrayList<>();
        for(String rockDesc : input) {
            String[] rockLines = rockDesc.split("->");
            for(int i=0; i< rockLines.length-1; i++) {
                boolean isFisrt = i == 0;
                String[] start = rockLines[i].trim().split(",");
                String[] end = rockLines[i+1].trim().split(",");
                int xStart = Integer.parseInt(start[0]);
                int yStart = Integer.parseInt(start[1]);
                int xEnd = Integer.parseInt(end[0]);
                int yEnd = Integer.parseInt(end[1]);
                if(xStart == xEnd) {
                    if(yStart < yEnd)  {
                        for(int j=isFisrt ? yStart : yStart+1; j<=yEnd; j++) {
                            CaveUnit cu = new CaveUnit(xStart, j, UnitDesc.ROCK);
                            rockCaveUnits.add(cu);
                        }
                    } else {
                        for(int j=isFisrt ? yStart : yStart - 1; j>=yEnd; j--) {
                            CaveUnit cu = new CaveUnit(xStart, j, UnitDesc.ROCK);
                            rockCaveUnits.add(cu);
                        }
                    }
                } else {
                    if(xStart < xEnd)  {
                        for(int j=isFisrt ? xStart : xStart + 1; j<=xEnd; j++) {
                            CaveUnit cu = new CaveUnit(j, yStart, UnitDesc.ROCK);
                            rockCaveUnits.add(cu);
                        }
                    } else {
                        for(int j=isFisrt ? xStart : xStart - 1; j>=xEnd; j--) {
                            CaveUnit cu = new CaveUnit(j, yStart, UnitDesc.ROCK);
                            rockCaveUnits.add(cu);
                        }
                    }
                }
            }
        }
        return rockCaveUnits;
    }

    static class CaveUnit {
        UnitDesc unitDesc;
        int x;
        int y;

        public CaveUnit(int x, int y, UnitDesc unitDesc) {
            this.x = x;
            this.y = y;
            this.unitDesc = unitDesc;
        }
    }

    enum UnitDesc {
        SAND('o'),
        SPACE('.'),
        ROCK('#');

        char desc;

        UnitDesc(char desc) {
            this.desc = desc;
        }
    }
}
