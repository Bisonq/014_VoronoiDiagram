package gui;

import core.Line;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Panel extends JPanel {

    private Set<Integer> set;

    public Panel() {
        super();
        setBackground(Color.GRAY);
        setLocation(100, 30);
        setSize(new Dimension(500, 500));
        this.set = new HashSet<>();
        drawNumbers(this.set);
    }

    public void drawNumbers(Set<Integer> set) {
        int max = 400;
        int min = 100;
        for (int i = 0; i < 6; i++) {
            set.add((int) ((Math.random() * ((max - min) + 1))) + min);
        }
    }

    public double getDistanceBetweenPoints(int x1, int y1, int x2, int y2){
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Iterator<Integer> iterator = this.set.iterator();

        int x1 = iterator.next(), y1 = iterator.next();
        int x2 = iterator.next(), y2 = iterator.next();
        int x3 = iterator.next(), y3 = iterator.next();

        g.setColor(Color.BLUE);
        drawLineComponent(g, x1, y1, x2, y2);

        g.setColor(Color.GREEN);
        drawLineComponent(g, x2, y2, x3, y3);

        g.setColor(Color.red);
        drawLineComponent(g, x3, y3, x1, y1);
    }

    private void drawLineComponent(Graphics g, int x1, int y1, int x2, int y2) {
        g.drawLine(x1, y1, x2, y2);
//        g.drawOval(x1, y1, 4, 4);
//        g.fillOval(x1, y1, 4, 4);
        Line line = new Line(x1, y1, x2, y2);
        Line pLine = new Line(-(1/line.getA()), new Point(line.getCenter()));
        drawPerpendicularStraightLine(g, line, pLine);
    }

    private void drawPerpendicularStraightLine(Graphics g, Line line, Line pLine) {
        int y = 0;
        double x = (y - pLine.getB())/pLine.getA();
        g.drawLine((int)line.getCenter().getX(), (int)line.getCenter().getY(), (int)x, y);

        y= 500;
        x = (y - pLine.getB())/pLine.getA();
        g.drawLine((int)line.getCenter().getX(), (int)line.getCenter().getY(), (int)x, y);
    }
}
