import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import java.awt.geom.Point2D;
public class CarTransportTest {
    CarTransport myTransport;
    Saab95 saab;
    Volvo240 volvo;
    Scania scania;

    CarTransport transporter2;

    @Before
    public void setUp() {
        myTransport = new CarTransport(7, "Transporter Pro");
        saab = new Saab95();
        volvo = new Volvo240();
        scania = new Scania();
        transporter2 = new CarTransport(1,"Transporter Basic");
    }


    @Test
    public void testSpeedFactor() {
        assertEquals(8.0, myTransport.speedFactor(), 0);
    }

    @Test
    public void testGasNotWorkWhenBedDown() {
        myTransport.lowerBed();
        myTransport.gas(0.2);
        assertEquals(0, myTransport.getCurrentSpeed(), 0);
    }

    @Test
    public void testGasWorkWhenBedUp() {
        myTransport.gas(0.2);
        assertEquals(1.6, myTransport.getCurrentSpeed(), 0);
    }

    @Test
    public void testLowerBedNotWorkWhenDriving() {
        myTransport.gas(0.2);
        myTransport.lowerBed();
        assertTrue(myTransport.getBedIsUp());
    }

    @Test
    public void testLoadWorks(){
        myTransport.setPosition(0,0);
        saab.setPosition(0,1);
        myTransport.lowerBed();
        myTransport.loadOn(saab);
        myTransport.raiseBed();
        assertEquals(1, myTransport.getLoadedCars().size());
    }


    @Test
    public void testCantLoadCarWhenTooFar() {
        saab.setPosition(5,5);
        myTransport.setPosition(0,0);
        RuntimeException exception = assertThrows(RuntimeException.class, () -> myTransport.loadOn(saab));
        assertTrue(exception.getMessage().contains("Can't load car"));
    }

    @Test
    public void testCantLoadTrucks () {
        RuntimeException exception = assertThrows(RuntimeException.class, () -> myTransport.loadOn(scania));
        assertTrue(exception.getMessage().contains("Can't load car"));
    }

    @Test
    public void testLoadedCarFollowsTransporterPosition(){
        myTransport.lowerBed();
        myTransport.loadOn(saab);
        myTransport.raiseBed();
        myTransport.gas(1);
        for(int i = 0; i < 30; i++){
            myTransport.move();
        }
        assertEquals(saab.getPosition(), myTransport.getPosition());
    }
    @Test
    public void testLoadOffNotWorkWhenMoving() {
        myTransport.lowerBed();
        myTransport.loadOn(saab);
        myTransport.raiseBed();
        myTransport.gas(0.5);
        RuntimeException exception = assertThrows(RuntimeException.class, () -> myTransport.loadOff());
        assertTrue(exception.getMessage().contains("Can't unload car"));
    }

    @Test
    public void testLoadOffNotWorkWhenBedIsUp() {
        myTransport.lowerBed();
        myTransport.loadOn(saab);
        myTransport.raiseBed();
        RuntimeException exception = assertThrows(RuntimeException.class, () -> myTransport.loadOff());
        assertTrue(exception.getMessage().contains("Can't unload car"));
    }

    @Test
    public void testLoadOffWork() {
        myTransport.lowerBed();
        myTransport.loadOn(saab);
        myTransport.loadOff();
        assertEquals(0, myTransport.getLoadedCars().size());
    }

    @Test
    public void testLoadOffInOrder() {
        myTransport.lowerBed();
        myTransport.loadOn(saab);
        myTransport.loadOn(volvo);
        myTransport.loadOff();
        assertTrue(myTransport.getLoadedCars().pop() instanceof Saab95);
    }

    @Test
    public void testCarTransportIsFull(){
        transporter2.lowerBed();
        transporter2.loadOn(saab);
        RuntimeException exception = assertThrows(RuntimeException.class, () -> transporter2.loadOn(volvo));
        assertTrue(exception.getMessage().contains("Can't load car"));

    }

}
