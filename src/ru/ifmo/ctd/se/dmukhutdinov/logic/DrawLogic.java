package ru.ifmo.ctd.se.dmukhutdinov.logic;

import ru.ifmo.ctd.se.dmukhutdinov.drawing.DrawingApi;
import ru.ifmo.ctd.se.dmukhutdinov.drawing.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * @author flyingleafe
 */
public final class DrawLogic {
    private DrawLogic() {
    }

    public static final double GOOD_NODE_RADIUS = 20;

    public static List<Point> pointsOnCircle(Point center, double radius, int number) {
        List<Point> res = new ArrayList<Point>();
        double stepAngle = 2 * Math.PI / (double) number;
        double curAngle = 0;
        for (int i = 0; i < number; i++) {
            double x = Math.sin(curAngle) * radius + center.getX();
            double y = Math.cos(curAngle) * radius + center.getY();
            res.add(new Point(x, y));
            curAngle += stepAngle;
        }
        return res;
    }

    public static double canvasCircleRadius(long w, long h, long margin) {
        return (double) (Math.min(w, h) / 2 - margin);
    }

    public static Point canvasCircleCenter(long w, long h) {
        return new Point(w / 2, h / 2);
    }

    public static List<Point> circlePointsOnCanvas(DrawingApi api, int number) {
        long canvasW = api.getDrawingAreaWidth();
        long canvasH = api.getDrawingAreaHeight();
        Point center = canvasCircleCenter(canvasW, canvasH);
        double radius = canvasCircleRadius(canvasW, canvasH, (long) GOOD_NODE_RADIUS * 2);
        return pointsOnCircle(center, radius, number);
    }
}
