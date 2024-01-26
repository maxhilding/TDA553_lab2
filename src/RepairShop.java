import javax.imageio.ImageReadParam;
import java.util.*;

public class RepairShop<T extends Car> implements Loadable<T>{

    private final int maxNumberOfLoadedCars;

    private final String repairShopName;

    private Stack<T> loadedCars;

    public RepairShop(int maxLoad, String repairShopName){
        maxNumberOfLoadedCars = maxLoad;
        loadedCars = new Stack<>();
        this.repairShopName = repairShopName;
        }
    public int getMaxNumberOfLoadedCars() {
        return maxNumberOfLoadedCars;
    }

    public Stack<T> getLoadedCars() {
        return loadedCars;
    }

    public String getRepairShopName() {
        return repairShopName;
    }

    @Override
    public T loadOff() {
        return loadedCars.pop();
    }

    public void loadOn(T car) {
        if (getLoadedCars().size() < getMaxNumberOfLoadedCars()) {
            getLoadedCars().push(car);
        }
        else {
            throw new RuntimeException("The repairshop is full"); //throwing 'wrong' exception here hehe

        }
    }

}

