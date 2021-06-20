package gamework;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

public abstract class Game {
    private JPanel gamePanel;

    protected Game(int width, int height, String title) {
        SwingUtilities.invokeLater(() -> createAndDisplayGUI(width, height, title));
    }

    /**
     * Sets up the initial GUI for a game window.
     * @param width The width of the window.
     * @param height The height of the window.
     * @param title The title for the window.
     */
    private void createAndDisplayGUI(int width, int height, String title) {
        // Create and setup the JFrame
        JFrame gameFrame = new JFrame();
        gameFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        gameFrame.setTitle(title);

        // Setup the JPanel. All drawing will take place here.
        gamePanel = new JPanel();
        gamePanel.setPreferredSize(new Dimension(width, height));

        gameFrame.add(gamePanel);
        gameFrame.setVisible(true);
        gameFrame.pack();
    }

    /**
     * Gets the width of the game screen.
     * @return The width of the game screen.
     */
    public int getWidth() {
        return gamePanel.getWidth();
    }

    /**
     * Gets the height of the game screen.
     * @return The height of the game screen.
     */
    public int getHeight() {
        return gamePanel.getHeight();
    }

    /**
     * A function that can be overridden to create the game logic.
     */
    protected abstract void gameLoop();

    /**
     * A function to perform renderings for all components
     * existing in the game.
     */
    protected void renderLoop() {}
}