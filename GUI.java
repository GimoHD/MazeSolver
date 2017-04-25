import amazed.GenerateMaze;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * A GUI showing the maze and options, and initializes the Algorithms
 */
public class GUI extends JFrame {
    private static final long serialVersionUID = 5514566716849599754L;
    private MazeBoard panel;
    private ControlBoard cboard;
    private Maze maze;
    private AStar astar;
    private JPanel mainp;

    /**
     * Creates a GUI
     */
    public GUI() {
        super();
        astar = new AStar();

        cboard = new ControlBoard(this);
        panel = new MazeBoard(this);
        mainp = new JPanel();

        mainp.setLayout(new BorderLayout());
        mainp.add(panel, BorderLayout.SOUTH);
        mainp.add(cboard, BorderLayout.NORTH);

        this.getContentPane().setLayout(null);
        this.setContentPane(mainp);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    /**
     * @return the generated maze
     */
    public Maze getMaze() {
        return maze;
    }

    /**
     * generates a new maze with the value inserted in a JTextField
     * @param field
     */
    public void createNewMaze(JTextField field) {
        String entered = field.getText();
        int size = Integer.parseInt(entered);
        GenerateMaze generateMaze = new GenerateMaze(size);

        java.util.List<String> lines = generateMaze.toListOfStrings();
        String filename = "maze.txt";

        Path file = Paths.get(filename);
        try {
            Files.write(file, lines, Charset.forName("UTF-8"));
            System.out.println(filename + " saved");
        } catch (IOException e) {
            e.printStackTrace();
        }

        maze = new Maze();
    }

    /**
     * solves the maze
     */
    public void solveMaze() {
        if (maze != null) {
            if (maze.getStart() != null && maze.getEnd() != null) {
                astar.calculateAStar(maze.getStart(), maze.getEnd());
                drawMaze();
                System.out.println(maze);
            } else {
                System.out.println("Can't find begin or end");
            }
        }
    }

    /**
     * draws the maze by redrawing the GUI
     */
    void drawMaze() {
        repaint();
    }
}