package gamework.demo;

import java.awt.Graphics2D;

import gamework.Game;
import gamework.objects.GameObject;

public class Demo {
    public static void main(String[] args) {
        Game.createGame(800, 600, "DemoGame");

        Circle c = new Circle(0, 0, 10);
        Game.addGameObject(c);
    }
}

class Circle extends GameObject {
    private double radius;

    public Circle(double x, double y, double radius) {
        super(x, y);
        this.radius = radius;
    }

    @Override
    public void render(Graphics2D g) {
        g.fillOval((int)this.x, (int)this.y, (int)this.radius, (int)this.radius);
    }

    @Override
    public void update() {
        
    }
    
}