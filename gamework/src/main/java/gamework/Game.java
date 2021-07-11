package gamework;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import gamework.objects.GameObject;
import gamework.rendering.Renderable;

public class Game {
    private static GamePanel gamePanel;
    private static List<GameObject> objects;
    private static List<Renderable> renderables;
    private static long frameStartTime;

    private Game() {}

    public static void createGame(int width, int height, String title) {
        objects = new ArrayList<>();
        renderables = new ArrayList<>();
        
        SwingUtilities.invokeLater(() -> createAndDisplayGUI(width, height, title));
    }

    /**
     * Starts the main game loop
     */
    public static void start() {
        // Set the start time so we can start calculating our
        // delta value.
        frameStartTime = System.nanoTime();

        SwingUtilities.invokeLater(() -> {
            update();
            gamePanel.repaint();
        });
    }

    /**
     * Sets up the initial GUI for a game window.
     * @param width The width of the window.
     * @param height The height of the window.
     * @param title The title for the window.
     */
    private static void createAndDisplayGUI(int width, int height, String title) {
        // Create and setup the JFrame
        JFrame gameFrame = new JFrame();
        gameFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        gameFrame.setTitle(title);

        // Setup the JPanel. All drawing will take place here.
        gamePanel = new GamePanel(width, height);

        gameFrame.add(gamePanel);
        gameFrame.setVisible(true);
        gameFrame.pack();
    }

    /**
     * Gets the width of the game screen.
     * @return The width of the game screen.
     */
    public static int getWidth() {
        return gamePanel.getWidth();
    }

    /**
     * Gets the height of the game screen.
     * @return The height of the game screen.
     */
    public static int getHeight() {
        return gamePanel.getHeight();
    }

    /**
     * A function that can be overridden to create the game logic.
     */
    private static void update() {
        long frameEndTime = System.nanoTime();

        // Get our sec/frame
        double deltaTime = (frameEndTime - frameStartTime) / 1e9;

        frameStartTime = frameEndTime;

        updatePosition(deltaTime);

        runUpdateLogic();
    }

    private static void updatePosition(double deltaTime) {
        // Move all objects based on their velocity
        for (GameObject object : objects) {
            // Calculate how many units the object needs to move
            double deltaX = object.getVelocity().getX() * deltaTime;
            double deltaY = object.getVelocity().getY() * deltaTime;

            // Move the object
            object.setX(object.getX() + deltaX);
            object.setY(object.getY() + deltaY);

            // Update the objects velocity based on the acceleration
            double deltaAccelX = object.getAcceleration().getX() * deltaTime;
            double deltaAccelY = object.getAcceleration().getY() * deltaTime;

            object.setVelocity(object.getVelocity().getX() + deltaAccelX, object.getVelocity().getY() + deltaAccelY);
        }
    }

    private static void runUpdateLogic() {
        for (GameObject object : objects) {
            object.update();
        }
    }

    /**
     * Adds the game object to the list of objects. This allows it to be updated and
     * rendered.
     * @param go The game object to add.
     */
    public static void addGameObject(GameObject go) {
        objects.add(go);
        renderables.add(go);
    }

    /**
     * Removes the game object so it will no longer be rendered or updated.
     * @param go The object to remove.
     */
    public static void removeGameObject(GameObject go) {
        objects.remove(go);
        renderables.remove(go);
    }

    /**
    * A class which represents the screen that the game is rendered on.
    */
    static class GamePanel extends JPanel {
        /**
         * Sets the number of units that the height is considered to be.
         * This is used to handle scaling a game to different resolutions.
         */
        private static final double FIXED_UNITS_HEIGHT = 100;

        /**
         * Used to convert from units back to pixels. It's
         * dependent on the height of the game window.
         */
        private final double pixelsPerUnit;

        private double xScale;
        private double yScale;

        /**
         * Constructs a game panel for rendering components of the game.
         * @param width The width of the game panel.
         * @param height The height of the game panel.
         */
        public GamePanel(int width, int height) {
            super();

            this.setPreferredSize(new Dimension(width, height));

            this.pixelsPerUnit = height / FIXED_UNITS_HEIGHT;
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D graphics = (Graphics2D) g;
            graphics.translate(getWidth() / 2, getHeight() / 2);
            graphics.scale(xScale, yScale);
            
            for (Renderable object : renderables) {
                object.render(graphics);
            }
        }
    }
}