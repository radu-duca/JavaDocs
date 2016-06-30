package ro.teamnet.zerotohero.oop.graphicshape;
import static java.lang.Math.PI;
/**
 * Created by user on 6/30/2016.
 */
public class Circle extends Shape{
    private int xPos;
    private int yPos;
    private int radius;

    public Circle(int yPos, int xPos, int radius) {
        this.yPos = yPos;
        this.xPos = xPos;
        this.radius = radius;
    }

    public Circle(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public Circle(int xPos) {
        this.xPos = xPos;

    }
    @Override
    public String toString(){
        return "x="+ xPos+ "y" + yPos + "z" + radius;
    }

    public  double area(){
    return  PI * radius * radius;
}


}
