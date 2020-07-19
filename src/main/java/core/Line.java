package core;

import java.awt.*;

public class Line {

    private double a;
    private double b;

    private int x1;
    private int y1;
    private int x2;
    private int y2;

    private Point crossPoint;

    private Point center;

    public Line(double a, Point crossPoint) {
        this.a = a;
        this.crossPoint = crossPoint;
        setBCoefficient(a, crossPoint);
    }

    public Line(int x1, int y1, int x2, int y2){
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        setLineCoefficients(x1, y1, x2, y2);
        setCenterPoint(x1, y1, x2, y2);
    }

    public void setLineCoefficients(int x1, int y1, int x2, int y2){
        this.a = (double)(y1 - y2)/(x1-x2);
        this.b = y1 - (double)((x1*(y1 - y2))/(x1 - x2));
    }

    public void setCenterPoint(int x1, int y1, int x2, int y2){
        int xS = (x1 + x2) / 2;
        int yS = (y1 + y2) / 2;
        this.center = new Point(xS, yS);
    }

    public void setBCoefficient(double a, Point crossPoint){
        this.b = crossPoint.y - (a * crossPoint.getX());
    }

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }

    public int getX1() {
        return x1;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public int getY1() {
        return y1;
    }

    public void setY1(int y1) {
        this.y1 = y1;
    }

    public int getX2() {
        return x2;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public int getY2() {
        return y2;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }

    public Point getCenter() {
        return center;
    }
}
