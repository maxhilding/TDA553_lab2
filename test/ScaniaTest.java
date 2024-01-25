import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

public class ScaniaTest {

    Scania myScania;

    @Before
    public void setUp() {
        myScania = new Scania();
    }

    @Test
    public void testGetBedAngleWorks(){
        assertEquals(0, myScania.getBedAngle(), 0.0);
    }

    @Test
    public void testSpeedFactor() {
        assertEquals(7.7, myScania.speedFactor(), 0);
    }

    @Test
    public void testGasNotWorkWhenBedUp() {
        myScania.raiseBed();
        myScania.gas(0.2);
        assertEquals(0, myScania.getCurrentSpeed(), 0);
    }

    @Test
    public void testGasWorkWhenBedDown() {
        myScania.gas(0.2);
        assertEquals(1.54, myScania.getCurrentSpeed(), 0);
    }

    @Test
    public void testRaiseBedAngleNotWorkWhenDriving() {
        myScania.gas(0.2);
        myScania.raiseBed();
        assertEquals(0, myScania.getBedAngle(), 0);
    }

    @Test
    public void testLowerBedWorks(){
        myScania.raiseBed();
        myScania.raiseBed();
        myScania.lowerBed();
        assertEquals(10, myScania.getBedAngle(), 0);
    }

    @Test
    public void testBrake() {
        myScania.gas(0.5);
        myScania.brake(1);
        assertEquals(0, myScania.getCurrentSpeed(), 0);
    }

}