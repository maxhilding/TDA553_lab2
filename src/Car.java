import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Vector;


public abstract class Car implements Moveable{

    private static final double handling = 90;

    private double current_degree = 90;

    private final int nrDoors; // Number of doors on the car
    private final double enginePower; // Engine power of the car
    private double currentSpeed; // The current speed of the car
    private Color color; // Color of the car
    private final String modelName; // The car model name

    private Point2D.Double position;

    private Point2D.Double direction;

    public Car(int nrDoors, double enginePower, Color color, String modelName) {
        this.nrDoors = nrDoors;
        this.enginePower = enginePower;
        this.color = color;
        this.modelName = modelName;
        position = new Point2D.Double(0, 0);
        direction = new Point2D.Double(0,1);
        stopEngine();
    }

    public int getNrDoors(){
        return nrDoors;
    }

    public double getEnginePower(){
        return enginePower;
    }

    public double getCurrentSpeed(){
        return currentSpeed;
    }

    public Color getColor(){
        return color;
    }

    public void setColor(Color clr){
        color = clr;
    }

    public String getModelName() {
        return modelName;
    }

    public void startEngine(){
        currentSpeed = 0.1;
    }

    public void stopEngine(){
        currentSpeed = 0;
    }

    protected abstract double speedFactor();

    protected void incrementSpeed(double amount){
        currentSpeed = Math.min(getCurrentSpeed() + speedFactor() * amount,enginePower);
    }
    protected void decrementSpeed(double amount){
        currentSpeed = Math.max(getCurrentSpeed() - speedFactor() * amount,0);
    }

    public void gas(double amount){
        if(amount>=0 && amount<= 1){
            incrementSpeed(amount);
        }
        else {
            throw new IllegalArgumentException(amount + " not allowed as an argument");
        }
    }

    public void brake(double amount){
        if(amount>=0 && amount<= 1){
            decrementSpeed(amount);
        }
        else {
            throw new IllegalArgumentException(amount + " not allowed as an argument");
        }
    }

    public Point2D.Double getPosition(){
        return position;
    }

    public Point2D.Double getDirection(){
        return direction;
    }


    protected void setPosition(double newX, double newY){
        position.setLocation(newX, newY);
    }

    public void move(){
        double newX = getCurrentSpeed() * getDirection().getX() + getPosition().getX();
        double newY = getCurrentSpeed() * getDirection().getY() + getPosition().getY();
        setPosition(newX, newY);
    }
    public void turnLeft(){
        current_degree += handling;
        direction.setLocation(Math.round(Math.cos(Math.toRadians(current_degree))),
                Math.round(Math.sin(Math.toRadians(current_degree))));
    }
    public void turnRight() {
        current_degree -= handling;
        direction.setLocation(Math.round(Math.cos(Math.toRadians(current_degree))),
                Math.round(Math.sin(Math.toRadians(current_degree))));
    }


}




