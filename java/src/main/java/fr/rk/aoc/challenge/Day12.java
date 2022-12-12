package fr.rk.aoc.challenge;

import java.util.*;

public final class Day12 {

    public static long getShortestPathLength(List<String> input, boolean fromAnyPosition) {
        Node[][] parsedGraph = new Node[input.size()][input.get(0).length()];
        Node startNode = null;
        Node endNode = null;
        for (int i = 0; i < parsedGraph.length; i++) {
            for (int j = 0; j < parsedGraph[i].length; j++) {
                char inputNode = input.get(i).charAt(j);
                int value = 'S' == inputNode ? 1 : 'E' == inputNode ? 26 : inputNode - 96;
                Node curNode = new Node(inputNode + "", value);
                if (inputNode == 'S') {
                    startNode = curNode;
                }
                if (inputNode == 'E') {
                    endNode = curNode;
                }
                parsedGraph[i][j] = curNode;
            }
        }
        createEdges(parsedGraph);
        if (!fromAnyPosition) {
            UniformCostSearch(startNode, endNode);
            List<Node> path = printPath(endNode);
            return path.size() - 1;
        } else {
            int minDist = Integer.MAX_VALUE;
            for (Node[] nodes : parsedGraph) {
                for (Node node : nodes) {
                    if (node.nodeVal == 1) {
                        UniformCostSearch(node, endNode);
                        int pathLength = printPath(endNode).size() - 1;
                        //Beware, they may ba no path to target.... Lost One hour on this trick ....
                        if (pathLength != 0 && pathLength < minDist) {
                            minDist = pathLength;
                        }
                        resetNodes(parsedGraph);
                    }
                }
            }
            return minDist;
        }
    }

    private static void resetNodes(Node[][] parsedGraph) {
        for (Node[] nodes : parsedGraph) {
            for (Node node : nodes) {
                node.parent = null;
                node.pathCost = 0d;
            }
        }
    }

    private static void createEdges(Node[][] parsedGraph) {
        for (int i = 0; i < parsedGraph.length; i++) {
            for (int j = 0; j < parsedGraph[i].length; j++) {
                //Bottom
                if (i < parsedGraph.length - 1) {
                    if (parsedGraph[i + 1][j].nodeVal <= parsedGraph[i][j].nodeVal + 1) {
                        parsedGraph[i][j].adjacencies.add(new Edge(parsedGraph[i + 1][j], 1));
                    }
                }
                //Right
                if (j < parsedGraph[i].length - 1) {
                    if (parsedGraph[i][j + 1].nodeVal <= parsedGraph[i][j].nodeVal + 1) {
                        parsedGraph[i][j].adjacencies.add(new Edge(parsedGraph[i][j + 1], 1));
                    }
                }
                //Left
                if (j > 0) {
                    if (parsedGraph[i][j - 1].nodeVal <= parsedGraph[i][j].nodeVal + 1) {
                        parsedGraph[i][j].adjacencies.add(new Edge(parsedGraph[i][j - 1], 1));
                    }
                }
                //Top
                if (i > 0) {
                    if (parsedGraph[i - 1][j].nodeVal <= parsedGraph[i][j].nodeVal + 1) {
                        parsedGraph[i][j].adjacencies.add(new Edge(parsedGraph[i - 1][j], 1));
                    }
                }
            }
        }
    }

    /**
     * Create a List of Node which represents the shortest path
     *
     * @param target target Node
     * @return List of Node which represents the shortest path
     */
    public static List<Node> printPath(Node target) {
        List<Node> path = new ArrayList<>();
        for (Node node = target; node != null; node = node.parent) {
            path.add(node);
        }
        Collections.reverse(path);
        return path;
    }

    /**
     * Perform a Uniform Cost Search
     *
     * @param source Source Node
     * @param goal   Target Node
     */
    public static void UniformCostSearch(Node source, Node goal) {
        source.pathCost = 0;
        //override compare method
        PriorityQueue<Node> queue = new PriorityQueue<Node>(20,
                (i, j) -> {
                    if (i.pathCost > j.pathCost) {
                        return 1;
                    } else if (i.pathCost < j.pathCost) {
                        return -1;
                    } else {
                        return 0;
                    }
                }
        );
        queue.add(source);
        Set<Node> explored = new HashSet<>();
        boolean found = false;
        //while frontier is not empty
        do {
            Node current = queue.poll();
            explored.add(current);
            //end if path is found
            if (current.value.equals(goal.value)) {
                found = true;
            }
            for (Edge e : current.adjacencies) {
                Node child = e.target;
                double cost = e.cost;
                //add node to queue if node has not been explored
                if (!explored.contains(child) && !queue.contains(child)) {
                    child.pathCost = current.pathCost + cost;
                    child.parent = current;
                    queue.add(child);
                }
                //current path is shorter than previous path found
                else if ((queue.contains(child)) && (child.pathCost > (current.pathCost + cost))) {
                    child.parent = current;
                    child.pathCost = current.pathCost + cost;
                    queue.remove(child);
                    queue.add(child);
                }
            }
        } while (!queue.isEmpty() && (found == false));
    }

    static class Node {
        public String value;
        public int nodeVal;
        public double pathCost;
        public List<Edge> adjacencies;
        public Node parent;

        public Node(String val, int nodeVal) {
            adjacencies = new ArrayList<>();
            this.value = val;
            this.nodeVal = nodeVal;
        }
    }

    /**
     * Class representing an Edge between two nodes
     */
    static class Edge {
        public final double cost;
        public final Node target;

        public Edge(Node targetNode, double costVal) {
            cost = costVal;
            target = targetNode;
        }
    }

}
