package fr.rk.aoc.challenge;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public final class Day9 {

    public static long getUniqueTailPosition(List<String> input, int nbNode) {
        List<RopeMove> ropeMoves = input.stream().map(RopeMove::new).collect(Collectors.toList());
        Set<String> tailPosition = new HashSet<>();
        List<NodePosition> nodesPositions = new ArrayList<>();
        for(int i=0; i<nbNode; i++) {
            nodesPositions.add(new NodePosition());
        }

        for(RopeMove rm : ropeMoves) {
            //For each move
            for(int i=0; i<rm.nbMove; i++) {
                //Move HEAD once
                switch(rm.ropeDirection) {
                    case UP:
                        nodesPositions.get(0).y++;
                        break;
                    case DOWN:
                        nodesPositions.get(0).y--;
                        break;
                    case LEFT:
                        nodesPositions.get(0).x++;
                        break;
                    case RIGHT:
                        nodesPositions.get(0).x--;
                        break;
                }
                for(int j=1; j<nbNode; j++) {
                    //delta
                    int deltaX = nodesPositions.get(j-1).x - nodesPositions.get(j).x;
                    int deltaY = nodesPositions.get(j-1).y - nodesPositions.get(j).y;
                    //Move tail
                    if(Math.abs(deltaX) == 2) {
                        nodesPositions.get(j).x += (deltaX>0?1:-1);
                        if(Math.abs(deltaY) == 1) {
                            nodesPositions.get(j).y += (deltaY>0?1:-1);
                        }
                    }
                    if(Math.abs(deltaY) == 2) {
                        nodesPositions.get(j).y+= (deltaY>0?1:-1);
                        if(Math.abs(deltaX) == 1) {
                            nodesPositions.get(j).x += (deltaX>0?1:-1);
                        }
                    }
                }
                tailPosition.add(nodesPositions.get(nbNode-1).x + "/" + nodesPositions.get(nbNode-1).y);
            }
        }
        return tailPosition.size();
    }

    static class NodePosition {
        int x;
        int y;
        NodePosition() {
            this.x = 0;
            this.y = 0;
        }
    }

    static class RopeMove {
        ROPE_DIRECTION ropeDirection;
        int nbMove;

        public RopeMove(String move) {
            String[] moveDesc = move.split(" ");
            this.ropeDirection = ROPE_DIRECTION.getRopeDirectionByDirection(moveDesc[0]);
            this.nbMove = Integer.parseInt(moveDesc[1]);
        }
    }

    enum ROPE_DIRECTION {
        LEFT("L"),
        RIGHT("R"),
        UP("U"),
        DOWN("D");

        private String direction;

        ROPE_DIRECTION(String direction) {
            this.direction = direction;
        }

        public static ROPE_DIRECTION getRopeDirectionByDirection(String direction) {
            for(ROPE_DIRECTION r : values()) {
                if(r.direction.equals(direction)) {
                    return r;
                }
            }
            return null;
        }
    }
}
