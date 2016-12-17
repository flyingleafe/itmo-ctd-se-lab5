package ru.ifmo.ctd.se.dmukhutdinov.drawing;

import javafx.application.Application;

/**
 * @author flyingleafe
 */
public class JfxDrawingApi implements DrawingApi {
    private final JfxDrawingApp appInstance;

    public JfxDrawingApi() {
        new Thread(() -> Application.launch(JfxDrawingApp.class)).start();
        appInstance = JfxDrawingApp.waitForApp();
    }

    @Override
    public long getDrawingAreaWidth() {
        return appInstance.getDrawingAreaWidth();
    }

    @Override
    public long getDrawingAreaHeight() {
        return appInstance.getDrawingAreaHeight();
    }

    @Override
    public void drawCircle(String label, Point center) {
        appInstance.drawCircle(label, center);
    }

    @Override
    public void drawLine(Point a, Point b) {
        appInstance.drawLine(a, b);
    }
}
