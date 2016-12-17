package ru.ifmo.ctd.se.dmukhutdinov.drawing;

import ru.ifmo.ctd.se.dmukhutdinov.graph.Graph;
import ru.ifmo.ctd.se.dmukhutdinov.logic.DrawLogic;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;

/**
 * @author flyingleafe
 */
public class AWTDrawingApi extends Frame implements DrawingApi {

    public AWTDrawingApi() throws HeadlessException {

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });

        setSize(800, 600);
        setTitle("Lab 5: AWT backend");
        setLayout(new FlowLayout());
        setVisible(true);
    }

    @Override
    public long getDrawingAreaWidth() {
        return getWidth();
    }

    @Override
    public long getDrawingAreaHeight() {
        return getHeight();
    }

    @Override
    public void drawCircle(String label, Point center) {
        Graphics2D g = (Graphics2D) getGraphics();
        double r = DrawLogic.GOOD_NODE_RADIUS;
        Ellipse2D ell = new Ellipse2D.Double(center.getX() - r, center.getY() - r, r * 2, r * 2);

        g.setColor(Color.cyan);
        g.fill(ell);

        g.setColor(Color.black);
        g.drawString(label, (float) center.getX() - 5, (float) center.getY() + 5);
    }

    @Override
    public void drawLine(Point a, Point b) {
        Graphics2D g = (Graphics2D) getGraphics();
        Path2D path = new Path2D.Double();
        path.moveTo(a.getX(), a.getY());
        path.lineTo(b.getX(), b.getY());

        g.setColor(Color.black);
        g.draw(path);
    }
}
