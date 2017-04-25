import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Maze {
    private int size;

    private MazeNode[][] maze = null;

    /**
     * Initializes the Maze and reads the file
     */
    public Maze() {
        this.loadFile("maze.txt");
    }

    /**
     * Loads a file with a given path
     *
     * @param fname
     */
    public void loadFile(String fname) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fname));

            String line;
            int col, row = 0;
            while ((line = reader.readLine()) != null) {
                if (maze == null) {
                    size = line.length();
                    maze = new MazeNode[size][size];
                }
                for (col = 0; col < line.length() && col < size; col++) {
                    boolean isWall = line.charAt(col) == 'X';
                    maze[col][row] = new MazeNode(this, col, row, isWall);
                }
                row++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets the maze
     * @return the maze array
     */
    public MazeNode[][] getMaze() {
        return maze;
    }

    /**
     * gets the start Node
     * @return the start MazeNode
     */
    public MazeNode getStart() {
        for (MazeNode n : maze[0]
                ) {
            if (!n.isWall()) {
                return n;
            }
        }
        return null;
    }

    /**
     * gets the ending Node
     * @return the end MazeNode
     */
    public MazeNode getEnd() {
        for (MazeNode n : maze[maze[0].length - 1]
                ) {
            if (!n.isWall()) {
                return n;
            }
        }
        return null;
    }

    /**
     * a toString from the complete maze
     * @return maze in string form
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                MazeNode cur = maze[col][row];
                if (cur.isWall())
                    str.append(" # ");
                else if (cur.isPath())
                    str.append(" o ");
                else str.append("   ");
            }
            str.append("\n");
        }
        return str.toString();
    }
}
