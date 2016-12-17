package ru.ifmo.ctd.se.dmukhutdinov.drawing;

/**
 * @author flyingleafe
 */
public interface DrawingApi {
    long getDrawingAreaWidth();
    long getDrawingAreaHeight();
    void drawCircle(String label, Point center);
    void drawLine(Point a, Point b);
}
