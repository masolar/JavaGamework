package gamework.util;

import org.junit.Test;
import static org.junit.Assert.*;

public class Vector2DTest {
    
    @Test
    public void testVectorAddition() {
        // Test along the x axis
        Vector2D v1 = new Vector2D(1, 0);
        Vector2D v2 = new Vector2D(3, 0);

        Vector2D expected = new Vector2D(4, 0);

        Vector2D sum = v1.add(v2);

        assertEquals(expected, sum);

        // Test that addition is commutative
        sum = v2.add(v1);

        assertEquals(expected, sum);

        // Test along the y axis
        v1 = new Vector2D(0, 1);
        v2 = new Vector2D(0, 3);

        expected = new Vector2D(0, 4);

        sum = v1.add(v2);

        assertEquals(expected, sum);

        // Test that addition is commutative
        sum = v2.add(v1);

        assertEquals(expected, sum);

        // Test with a vector not along the axes
        v1 = new Vector2D(1, 2);
        v2 = new Vector2D(4, 2);

        expected = new Vector2D(5, 4);

        sum = v1.add(v2);

        assertEquals(expected, sum);

        // Test that addition is commutative
        sum = v2.add(v1);

        assertEquals(expected, sum);
    }

    @Test
    public void testVectorGetLength() {
        Vector2D v1 = new Vector2D(3, 4);

        assertEquals(5.0, v1.getLength(), .0001);

        v1 = new Vector2D(30);

        assertEquals(1, v1.getLength(), .0001);

        Vector2D scaled = v1.scale(3);
        assertEquals(3, scaled.getLength(), .0001);
    }

    @Test
    public void testVectorDirection() {
        Vector2D v1 = new Vector2D(0);
        Vector2D v2 = new Vector2D(180);

        // Test vectors on opposite sides
        assertEquals(0, v1.getDirection(), .0001);
        assertEquals(180, v2.getDirection(), .0001);

        // Test a vector specified with x and y
        Vector2D v3 = new Vector2D(0, 1);

        assertEquals(90, v3.getDirection(), .0001);
    }

    @Test
    public void testVectorEquality() {
        Vector2D v1 = new Vector2D(2, 3);
        Vector2D v2 = new Vector2D(2, 3);
        Vector2D v3 = new Vector2D(3, 2);

        assertTrue(v1.equals(v2));
        assertTrue(v2.equals(v1));
        assertFalse(v1.equals(v3));
        assertFalse(v2.equals(v3));
        assertFalse(v3.equals(v1));
        assertFalse(v3.equals(v2));
    }

    @Test
    public void testVectorDotProduct() {
        Vector2D v1 = new Vector2D(1, 2);
        Vector2D v2 = new Vector2D(3, 4);

        assertEquals(11, v1.dot(v2), .0001);
        assertEquals(11, v2.dot(v1), .0001);
    }

    @Test
    public void testVectorUnit() {
        Vector2D v1 = new Vector2D(3, 5);

        assertEquals(1, v1.unit().getLength(), .0001);
    }
}