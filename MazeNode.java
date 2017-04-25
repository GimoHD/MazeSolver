import java.util.ArrayList;
import java.util.List;

/**
 * A node in the maze containing coordinates and attributes of a maze
 */
public class MazeNode {

    private static int[][] directions = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    private final int MOVEMENT_COST = 10;
    private MazeNode parent;
    private int x;
    private int y;
    private int g; //points from start
    private int h; //heuristic, cost from node to endpoint
    private boolean wall = false;
    private boolean path = false;
    private Maze maze;

    /**
     * Creates a MazeNode with a maze, x-y coordinates and a boolean wall which tells whether it is a wall.
     * @param maze
     * @param x
     * @param y
     * @param wall
     */
    public MazeNode(Maze maze, int x, int y, boolean wall) {
        this.maze = maze;
        this.x = x;
        this.y = y;
        this.wall = wall;
    }

    /**
     * Gets the surrounding nodes (up,down,left,right)
     * @return a List with the surrounding nodes
     */
    List<MazeNode> getSurroundings() {
        List<MazeNode> res = new ArrayList<>();
        for (int[] direction : directions) {
            int cx = this.getX() + direction[0];
            int cy = this.getY() + direction[1];
            if (cy >= 0 && cy < maze.getMaze().length)
                if (cx >= 0 && cx < maze.getMaze()[cy].length)
                    res.add(maze.getMaze()[cx][cy]);
        }
        return res;
    }

    /**
     * gets the X coordinate
     * @return
     */
    private int getX() {
        return x;
    }

    /**
     * gets the Y coordinate
     * @return
     */
    private int getY() {
        return y;
    }

    /**
     * returns the G value
     * @return
     */
    public int getG() {
        return g;
    }

    /**
     * sets the G value
     * @param g
     */
    public void setG(int g) {
        this.g = g;
    }

    /**
     * Checks whether it is a wall
     * @return true if it is a wall
     */
    public boolean isWall() {
        return wall;
    }
    /**
     * Checks whether it is a path
     * @return true if it is a path
     */
    public boolean isPath() {
        return path;
    }

    /**
     * calculates the H value to a destination point
     * @param destPoint
     */
    public void calcH(MazeNode destPoint) {
        this.h = (Math.abs(x - destPoint.getX()) + Math.abs(y - destPoint.getY())) * this.MOVEMENT_COST;
    }

    /**
     * calculates the g value to a point
     * @param point
     */
    public void calcG(MazeNode point) {
        this.g = point.g + this.MOVEMENT_COST;
    }

    /**
     * gets the F value of the node
     * @return the F value
     */
    public int getF() {
        return this.g + this.h;
    }

    /**
     * sets the node as path
     * @param path
     */
    public void setPath(boolean path) {
        this.path = path;
    }

    /**
     * sets the parent
     * @param parent
     */
    public void setParent(MazeNode parent) {
        this.parent = parent;
    }

    /**
     * gets the parent
     * @return
     */
    public MazeNode getParent() {
        return parent;
    }

    /**
     * overriden toString
     * @return the string
     */
    @Override
    public String toString() {
        return "NODE: " + "x= " + x + " y= " + y;
    }

}