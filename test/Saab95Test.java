import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.awt.geom.Point2D;

import static org.junit.Assert.*;

public class Saab95Test {

    Saab95 mySaab;

    @Before
    public void setUp() {
        mySaab = new Saab95();
    }

    @Test
    public void testGetNrDoors() {

        assertEquals(2, mySaab.getNrDoors());
    }

    @Test
    public void testGetEnginePower() {

        assertEquals(125, mySaab.getEnginePower(), 0);
    }

    @Test
    public void testGetCurrentSpeed() {
        assertEquals(0, mySaab.getCurrentSpeed(), 0);

    }

    @Test
    public void testGetColor() {
        assertEquals(Color.red, mySaab.getColor());
    }

    @Test
    public void testGetModelName() {
        assertEquals("Saab95", mySaab.getModelName());
    }

    @Test
    public void testSetColor() {
        mySaab.setColor(Color.cyan);
        assertEquals(Color.cyan, mySaab.getColor());
    }

    @Test
    public void testStartEngine() {
        mySaab.startEngine();
        assertEquals(0.1, mySaab.getCurrentSpeed(), 0);
    }

    @Test
    public void testStopEngine() {
        mySaab.startEngine();
        mySaab.stopEngine();
        assertEquals(0, mySaab.getCurrentSpeed(), 0);
    }

    @Test
    public void testSetTurboOn(){
        mySaab.setTurboOn();
        assertTrue(mySaab.getTurboOn());
    }

    @Test
    public void testSetTurboOff(){
        mySaab.setTurboOff();
        assertFalse(mySaab.getTurboOn());
    }

    @Test
    public void testSpeedFactor() {
        assertEquals(1.25, mySaab.speedFactor(), 0);
        mySaab.setTurboOn();
        assertEquals(1.625, mySaab.speedFactor(), 0);
    }

    @Test
    public void testIncrementSpeed() {
        mySaab.incrementSpeed(2);
        assertEquals(2.5, mySaab.getCurrentSpeed(), 0);
        mySaab.incrementSpeed(2000);
        assertEquals(125, mySaab.getCurrentSpeed(), 0);
    }

    @Test
    public void testDecrementSpeed() {
        mySaab.incrementSpeed(1000);
        mySaab.decrementSpeed(20);
        assertEquals(100, mySaab.getCurrentSpeed(), 0);
    }

    @Test
    public void testGas() {
        mySaab.gas(0.2);
        assertEquals(0.25, mySaab.getCurrentSpeed(), 0);
    }

    @Test
    public void testGasThrowsExceptionTooLarge() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                mySaab.gas(2));
        assertTrue(exception.getMessage().contains("2.0 not allowed as an argument"));
    }

    @Test
    public void testGasThrowsExceptionTooSmall() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                mySaab.gas(-1));
        assertTrue(exception.getMessage().contains("-1.0 not allowed as an argument"));
    }

    @Test
    public void testBrake() {
        mySaab.gas(0.5);
        mySaab.brake(1);
        assertEquals(0, mySaab.getCurrentSpeed(), 0);
    }

    @Test
    public void testBrakeThrowsExceptionTooLarge() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                mySaab.brake(2));
        assertTrue(exception.getMessage().contains("2.0 not allowed as an argument"));
    }

    @Test
    public void testBrakeThrowsExceptionTooSmall() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                mySaab.brake(-1));
        assertTrue(exception.getMessage().contains("-1.0 not allowed as an argument"));
    }

    @Test
    public void testMove() {
        mySaab.gas(1);
        mySaab.move();
        assertEquals(mySaab.getPosition(), new Point2D.Double(0, 1.25));
    }

    @Test
    public void testTurnLeft() {
        mySaab.turnLeft();
        assertEquals(mySaab.getDirection(), new Point2D.Double(-1, 0));
        mySaab.turnLeft();
        assertEquals(mySaab.getDirection(), new Point2D.Double(0, -1));
        mySaab.turnLeft();
        assertEquals(mySaab.getDirection(), new Point2D.Double(1, 0));
        mySaab.turnLeft();
        assertEquals(mySaab.getDirection(), new Point2D.Double(0, 1));
    }

    @Test
    public void testTurnRight() {
        mySaab.turnRight();
        assertEquals(mySaab.getDirection(), new Point2D.Double(1, 0));
        mySaab.turnRight();
        assertEquals(mySaab.getDirection(), new Point2D.Double(0, -1));
        mySaab.turnRight();
        assertEquals(mySaab.getDirection(), new Point2D.Double(-1, 0));
        mySaab.turnRight();
        assertEquals(mySaab.getDirection(), new Point2D.Double(0, 1));
    }

}