package gamework.components;

import java.util.ArrayList;

import javax.swing.JPanel;

import gamework.rendering.Renderable;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class GamePanel extends JPanel {
    private ArrayList<Renderable> renderables;
    private static final double PIXELS_PER_UNIT = 100;
    private int width;
    private int height;
    private double xScale;
    private double yScale;

    public GamePanel(ArrayList<Renderable> renderables, int width, int height) {
        super();

        this.renderables = renderables;

        this.setPreferredSize(new Dimension(width, height));
        this.width = width;
        this.height = height;

        this.xScale = width / PIXELS_PER_UNIT;
        this.yScale = height / PIXELS_PER_UNIT;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics = (Graphics2D) g;
        graphics.translate(width / 2, height / 2);
        graphics.scale(xScale, yScale);
        
        for (Renderable object : renderables) {
            object.render(graphics);
        }
    }
}
