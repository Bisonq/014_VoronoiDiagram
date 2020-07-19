package gui;

import core.Line;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Panel extends JPanel {

    private Set<Integer> set;

    private Point intersectionPoint;

    public Panel() {
        super();
        setBackground(new Color(38, 38, 38));
        setLocation(40, 30);
        setSize(new Dimension(600, 500));
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

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if(set.isEmpty()){
            set.clear();
            drawNumbers(this.set);
        }

        Graphics2D g2d = (Graphics2D) g.create();

        Iterator<Integer> iterator = this.set.iterator();

        int x1 = iterator.next(), y1 = iterator.next();
        int x2 = iterator.next(), y2 = iterator.next();
        int x3 = iterator.next(), y3 = iterator.next();

        g2d.setColor(Color.BLUE);
        Line blueLine = drawLineComponent(g2d, x1, y1, x2, y2);

        g2d.setColor(Color.GREEN);
        Line greenLine = drawLineComponent(g2d, x2, y2, x3, y3);

        g2d.setColor(Color.red);
        Line redLine = drawLineComponent(g2d, x3, y3, x1, y1);

        drawIntersectionPointOfTwoLines(g2d, x1, y1, x2, y2, x3, y3);
    }

    private void drawIntersectionPointOfTwoLines(Graphics2D g, int x1, int y1, int x2, int y2, int x3, int y3) {
        Line line1 = new Line(x1, y1, x2, y2);
        Line pLine1 = new Line(-(1/line1.getA()), new Point(line1.getCenter()));

        Line line2 = new Line(x2, y2, x3, y3);
        Line pLine2 = new Line(-(1/line2.getA()), new Point(line2.getCenter()));

        double x = (pLine1.getA() - pLine2.getA());
        double b = (pLine2.getB() - pLine1.getB());
        x = b/x;
        double y = (pLine1.getA() * x) + pLine1.getB();

        this.intersectionPoint = new Point((int)x, (int)y);

        g.setStroke(new BasicStroke(2, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER));
        g.setColor(Color.yellow);
        g.drawOval((int)(x - 2), (int)(y - 2), 4, 4);
        g.fillOval((int)(x - 2), (int)(y - 2), 4, 4);
        g.setStroke(new BasicStroke(1, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER));
        g.drawOval((int)(x - 7), (int)(y - 7), 14, 14);
    }

    private Line drawLineComponent(Graphics2D g, int x1, int y1, int x2, int y2) {
        drawTrianglePoints(g, x1, y1);
        g.setStroke(new BasicStroke(3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER));
        g.drawLine(x1, y1, x2, y2);
        Line line = new Line(x1, y1, x2, y2);
        Line pLine = new Line(-(1/line.getA()), new Point(line.getCenter()));
        return drawPerpendicularStraightLine(g, line, pLine);
    }

    private void drawTrianglePoints(Graphics2D g, int x1, int y1) {
        g.drawOval(x1 - 4, y1 - 4, 8, 8);
        g.fillOval(x1 - 4, y1 - 4, 8, 8);

        g.setStroke(new BasicStroke(2, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER));
        g.drawOval(x1 - 10, y1 - 10, 20, 20);
    }

    private Line drawPerpendicularStraightLine(Graphics2D g, Line line, Line pLine) {
        g.setStroke(new BasicStroke(1, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER));

        int y1 = 0;
        double x1 = (y1 - pLine.getB())/pLine.getA();
        g.drawLine((int)line.getCenter().getX(), (int)line.getCenter().getY(), (int)x1, y1);

        int y2= 500;
        double x2 = (y2 - pLine.getB())/pLine.getA();
        g.drawLine((int)line.getCenter().getX(), (int)line.getCenter().getY(), (int)x2, y2);

        return new Line((int)x1, y1, (int)x2, y2);
    }
}
