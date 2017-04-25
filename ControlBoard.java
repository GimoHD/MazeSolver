import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * creates a JPanel on the gui containing controls for the maze
 */
public class ControlBoard extends JPanel {
    GUI gui;
    private JButton button;
    private JButton button2;
    private JTextField text;
    private JLabel label;

    /**
     * creates a JPanel on the gui containing controls for the maze
     * @param gui
     */
    public ControlBoard(GUI gui) {
        this.gui = gui;
        this.button = new JButton("Create maze");
        this.button2 = new JButton("Solve maze");
        this.text = new JTextField("20");
        text.setPreferredSize(new Dimension(50, 30));
        this.label = new JLabel("Size of maze:");
        button.addActionListener(e -> {
            gui.createNewMaze(text);
            gui.repaint();
        });
        button2.addActionListener(e -> {
            gui.solveMaze();
            gui.repaint();
        });
        add(label);
        add(text);
        add(button);
        add(button2);

        setPreferredSize(new Dimension(500, 40));
    }

}