import javax.swing.*;
import java.awt.*;

/**
 * A JPanel which prints out the maze
 */
public class MazeBoard extends JPanel {
    GUI gui;

    /**
     * creates a MazeBoard
     * @param gui
     */
    public MazeBoard(GUI gui) {
        this.gui = gui;
        setPreferredSize(new Dimension(500, 500));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (gui.getMaze() != null) {
            MazeNode[][] maze = gui.getMaze().getMaze();
            int size = (int) (((float) this.getWidth()) / maze[0].length);
            for (int row = 0; row < maze[0].length; row++) {
                for (int col = 0; col < maze[0].length; col++) {
                    MazeNode cur = maze[col][row];
                    if (cur.isWall()) {
                        g.setColor(Color.black);
                        g.fillRect(size * col, size * row, size, size);
                    } else if (cur.isPath()) {
                        g.setColor(Color.orange);
                        g.fillRect(size * col, size * row, size, size);
                    } else {
                        g.setColor(Color.white);
                        g.fillRect(size * col, size * row, size, size);
                    }
                }
            }
        }
    }

}