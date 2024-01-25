import java.awt.*;

public abstract class Truck extends Car{

    public Truck(int nrDoors, int enginePower, Color color, String modelName){
        super(nrDoors, enginePower, color, modelName);
    }

    protected double speedFactor(){
        return Math.max(getEnginePower() * 0.01,0);
    }

    public abstract void raiseBed();

    public abstract void lowerBed();

}
