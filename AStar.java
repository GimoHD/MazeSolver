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
        //noinspection unchecked
        @SuppressWarnings("unchecked") PriorityQueue<MazeNode> openList = new PriorityQueue<>(fComparator);
        HashSet<MazeNode> closedList = new HashSet<>();

        MazeNode currentMazeNode = p1;
        currentMazeNode.setParent(null);
        currentMazeNode.setG(0);
        openList.add(currentMazeNode);

        while (!openList.isEmpty()) {
            currentMazeNode = openList.peek();

            if (currentMazeNode.equals(p2)) {
                return this.getPath(p2);
            }

            openList.remove(currentMazeNode);
            closedList.add(currentMazeNode);

            for (MazeNode adjMazeNode : currentMazeNode.getSurroundings()) {

                if (adjMazeNode.isWall()) {
                    continue;
                }

                if (!closedList.contains(adjMazeNode)) {
                    if (!openList.contains(adjMazeNode)) {
                        adjMazeNode.setParent(currentMazeNode);
                        adjMazeNode.calcG(currentMazeNode);
                        adjMazeNode.calcH(p2);
                        openList.add(adjMazeNode);
                    } else {
                        if (adjMazeNode.getG() < currentMazeNode.getG()) {
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