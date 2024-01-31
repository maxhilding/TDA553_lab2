import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class RepairShopTest {

    RepairShop<Volvo240> volvo240RepairShop;
    RepairShop<Car> anyCarsRepairShop;

    RepairShop<Car> anyCarShopLow;

    Saab95 saab;
    Volvo240 volvo1;
    Volvo240 volvo2;


    @Before
    public void setUp() {
        saab = new Saab95();
        volvo1 = new Volvo240();
        volvo2 = new Volvo240();
        volvo240RepairShop = new RepairShop<>(10, "Volvo Repair Shop");
        anyCarsRepairShop = new RepairShop<>(10, "Every Car Repair Shop");
        anyCarShopLow = new RepairShop<>(1, "Low Capacity");
    }

    @Test
    public void testRepairShopName(){
        assertEquals("Volvo Repair Shop", volvo240RepairShop.getRepairShopName());
    }


    @Test
    public void testGetMaxNumberOfLoadedCars(){
        assertEquals(10, volvo240RepairShop.getMaxNumberOfLoadedCars());
    }

    @Test
    public void testSpecificLoadOnWorks(){
        volvo240RepairShop.loadOn(volvo1);
        assertEquals(1, volvo240RepairShop.getLoadedCars().size());
        volvo240RepairShop.loadOn(volvo2);
        assertEquals(2, volvo240RepairShop.getLoadedCars().size());
    }

    @Test
    public void testAnyCarLoadOnWorks(){
        anyCarsRepairShop.loadOn(volvo1);
        assertEquals(1, anyCarsRepairShop.getLoadedCars().size());
        anyCarsRepairShop.loadOn(saab);
        assertEquals(2, anyCarsRepairShop.getLoadedCars().size());

    }


    @Test
    public void testSpecificLoadOffWorks(){
        volvo240RepairShop.loadOn(volvo1);
        volvo240RepairShop.loadOn(volvo2);
        volvo240RepairShop.loadOff();
        assertEquals(1, volvo240RepairShop.getLoadedCars().size());
    }

    @Test
    public void testanyCarLoadOffWorks(){
        anyCarsRepairShop.loadOn(volvo1);
        anyCarsRepairShop.loadOn(saab);
        anyCarsRepairShop.loadOff();
        assertEquals(1, anyCarsRepairShop.getLoadedCars().size());
    }

    @Test
    public void testRepairShopIsFull(){
        anyCarShopLow.loadOn(volvo1);
        RuntimeException exception = assertThrows(RuntimeException.class, () -> anyCarShopLow.loadOn(volvo2));
        assertTrue(exception.getMessage().contains("The repairshop is full"));

    }
    /*
    Don't need to test since it doesn't let you run the code.

    @Test
    public void testCantRepairOnThisCarHere() {
        volvo240RepairShop.loadOn(saab);
    }
    */

}
