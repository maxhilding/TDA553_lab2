import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.awt.geom.Point2D;

import static org.junit.Assert.*;

public class Volvo240Test {

    Volvo240 myVolvo;

    @Before
    public void setUp() {
        myVolvo = new Volvo240();
    }

    @Test
    public void testGetNrDoors() {

        assertEquals(4, myVolvo.getNrDoors());
    }

    @Test
    public void testGetEnginePower() {

        assertEquals(100, myVolvo.getEnginePower(), 0);
    }

    @Test
    public void testGetCurrentSpeed() {
        assertEquals(0, myVolvo.getCurrentSpeed(), 0);

    }

    @Test
    public void testGetColor() {
        assertEquals(Color.black, myVolvo.getColor());
    }


    @Test
    public void testGetModelName() {
        assertEquals("Volvo240", myVolvo.getModelName());
    }

    @Test
    public void testSetColor() {
        myVolvo.setColor(Color.cyan);
        assertEquals(Color.cyan, myVolvo.getColor());
    }

    @Test
    public void testStartEngine() {
        myVolvo.startEngine();
        assertEquals(0.1, myVolvo.getCurrentSpeed(), 0);
    }

    @Test
    public void testStopEngine() {
        myVolvo.startEngine();
        myVolvo.stopEngine();
        assertEquals(0, myVolvo.getCurrentSpeed(), 0);
    }

    @Test
    public void testSpeedFactor() {
        assertEquals(1.25, myVolvo.speedFactor(), 0);
    }

    @Test
    public void testIncrementSpeed() {
        myVolvo.incrementSpeed(2);
        assertEquals(2.5, myVolvo.getCurrentSpeed(), 0);
        myVolvo.incrementSpeed(2000);
        assertEquals(100, myVolvo.getCurrentSpeed(), 0);
    }

    @Test
    public void testDecrementSpeed() {
        myVolvo.incrementSpeed(1000);
        myVolvo.decrementSpeed(20);
        assertEquals(75, myVolvo.getCurrentSpeed(), 0);
    }

    @Test
    public void testGas() {
        myVolvo.gas(0.2);
        assertEquals(0.25, myVolvo.getCurrentSpeed(), 0);
    }

    @Test
    public void testGasThrowsExceptionTooLarge() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                myVolvo.gas(2));
        assertTrue(exception.getMessage().contains("2.0 not allowed as an argument"));
    }

    @Test
    public void testGasThrowsExceptionTooSmall() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                myVolvo.gas(-1));
        assertTrue(exception.getMessage().contains("-1.0 not allowed as an argument"));
    }

    @Test
    public void testBrake() {
        myVolvo.gas(0.5);
        myVolvo.brake(1);
        assertEquals(0, myVolvo.getCurrentSpeed(), 0);
    }

    @Test
    public void testBrakeThrowsExceptionTooLarge() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                myVolvo.brake(2));
        assertTrue(exception.getMessage().contains("2.0 not allowed as an argument"));
    }

    @Test
    public void testBrakeThrowsExceptionTooSmall() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                myVolvo.brake(-1));
        assertTrue(exception.getMessage().contains("-1.0 not allowed as an argument"));
    }

    @Test
    public void testMove() {
        myVolvo.gas(1);
        myVolvo.move();
        assertEquals(myVolvo.getPosition(), new Point2D.Double(0, 1.25));
    }

    @Test
    public void testTurnLeft() {
        myVolvo.turnLeft();
        assertEquals(myVolvo.getDirection(), new Point2D.Double(-1, 0));
        myVolvo.turnLeft();
        assertEquals(myVolvo.getDirection(), new Point2D.Double(0, -1));
        myVolvo.turnLeft();
        assertEquals(myVolvo.getDirection(), new Point2D.Double(1, 0));
        myVolvo.turnLeft();
        assertEquals(myVolvo.getDirection(), new Point2D.Double(0, 1));
    }

    @Test
    public void testTurnRight() {
        myVolvo.turnRight();
        assertEquals(myVolvo.getDirection(), new Point2D.Double(1, 0));
        myVolvo.turnRight();
        assertEquals(myVolvo.getDirection(), new Point2D.Double(0, -1));
        myVolvo.turnRight();
        assertEquals(myVolvo.getDirection(), new Point2D.Double(-1, 0));
        myVolvo.turnRight();
        assertEquals(myVolvo.getDirection(), new Point2D.Double(0, 1));
    }

}