import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;

/**
 * Implementation of the A* algorithm
 */
public class AStar {

    /**
     * A comparator to compare nodes to sort on ascending order (F Value is total path length)
     */
    private final Comparator fComparator = (Comparator<MazeNode>) (MazeNode a, MazeNode b) -> Integer.compare(a.getF(), b.getF());

    /**
     * Calculates the shortest path from p1 to p2
     * @param p1
     * @param p2
     * @return An arrayList with the best path from p1 to p2
     */
    public ArrayList<MazeNode> calculateAStar(MazeNode p1, MazeNode p2) {
        /* Make an open list, priority queue with comparator.
         * It lets the closest node to the destination be the next to check, for faster results
         */
        PriorityQueue<MazeNode> openList = new PriorityQueue<>(fComparator);

        /* As there only has to be added an element and has to be checked if it contains an element
         * The HashSet is ideal, as it only takes O(1) time to do the contains()
         */
        HashSet<MazeNode> closedList = new HashSet<>();

        //current node is the starting node
        MazeNode currentMazeNode = p1;
        //has no parent so null
        currentMazeNode.setParent(null);
        //cost from going to start to start is zero
        currentMazeNode.setG(0);
        //add to openlist to be the first element to check
        openList.add(currentMazeNode);

        while (!openList.isEmpty()) {
            //get the node with the best h value
            currentMazeNode = openList.peek();

            //if the current node is the end node, backtrack and return the path
            if (currentMazeNode.equals(p2)) {
                return this.getPath(p2);
            }
            //remove from list and add to new list
            openList.remove(currentMazeNode);
            closedList.add(currentMazeNode);

            //check all neighbors
            for (MazeNode adjMazeNode : currentMazeNode.getSurroundings()) {

                //If it is a wall ignore
                if (adjMazeNode.isWall()) {
                    continue;
                }
                //if the closing set contains the neighbor node, else continue
                if (!closedList.contains(adjMazeNode)) {
                    if (!openList.contains(adjMazeNode)) {
                        //add to open list to be checked later and add the G and H values
                        adjMazeNode.setParent(currentMazeNode);
                        adjMazeNode.calcG(currentMazeNode);
                        adjMazeNode.calcH(p2);
                        openList.add(adjMazeNode);
                    } else {
                        if (adjMazeNode.getG() < currentMazeNode.getG()) {
                            //calculate the G value of the neighboring node
                            adjMazeNode.calcG(currentMazeNode);
                            currentMazeNode = adjMazeNode;
                        }
                    }
                }
            }
        }

        return null;
    }

    /**
     * Gets the correct shortest path to a destination node (backtracks from destination node to start node)
     * @param destinationMazeNode
     * @return an ArrayList with the path of Nodes
     */
    private ArrayList<MazeNode> getPath(MazeNode destinationMazeNode) {
        ArrayList<MazeNode> path = new ArrayList<>();
        MazeNode mazeNode = destinationMazeNode;
        while (mazeNode != null) {
            path.add(mazeNode);
            mazeNode.setPath(true);
            mazeNode = mazeNode.getParent();
        }
        return path;
    }


}