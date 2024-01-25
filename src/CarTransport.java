
import java.awt.*;
import java.awt.geom.Point2D;
import java.util.*;
public class CarTransport extends Truck {
    private boolean bedIsUp = true;
    private Stack<Car> loadedCars;
    
    private final int maxNumberOfLoadedCars;

    public CarTransport() {
        super(2, 800, Color.green, "Einride CarTransport Pro"); //a specific type of car transport, like Saab95
        loadedCars = new Stack<Car>();
        maxNumberOfLoadedCars = 7;
        raiseBed();
    }

    public boolean getBedIsUp(){
        return bedIsUp;
    }

    public void raiseBed(){
        if(getCurrentSpeed()==0){
            bedIsUp = true;
        }
    }

    public void lowerBed(){
        if (getCurrentSpeed()==0) {
            bedIsUp=false;
        }
    }
    @Override
    public void gas(double amount){
        if (bedIsUp) {
            super.gas(amount);
        }

    }
    /*
    assumes that you can't load Car Transport with trucks, and that those are the types of cars
    too big to be loaded
     */
    public void loadOn(Car car){
        if (!bedIsUp && !(car instanceof Truck) &&
                (maxNumberOfLoadedCars>loadedCars.size()) &&
                (car.getPosition().distance(this.getPosition()) < 2)) {
            loadedCars.push(car);
            car.setPosition(this.getPosition().getX(), this.getPosition().getY());
            //car.position = this.position;
            /*
            this commented solution uses aliasing to make the loaded cars follow the truck at all times. Make sure to
            change position in Car.java to protected then. Using this solution, one does not need to override
            "move" method.
            */
        }
    }


    public void loadOff(){
        if (!bedIsUp){
            Car releasedCar = loadedCars.pop();
            releasedCar.setPosition(this.getPosition().getX()-1, this.getPosition().getY()-1);
            //releasedCar.position = new Point2D.Double(this.getPosition().getX(), this.getPosition().getY()-1);
        }
    }

    @Override
    public void move() {
        double newX = getCurrentSpeed() * getDirection().getX();
        double newY = getCurrentSpeed() * getDirection().getY();
        this.setPosition(newX, newY);

        for (Car nextCar : loadedCars) {
            nextCar.setPosition(newX, newY);
        }
    }

    public Stack<Car> getLoadedCars() {
        return loadedCars;
    }

}
