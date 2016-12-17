package ru.ifmo.ctd.se.dmukhutdinov.drawing;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import ru.ifmo.ctd.se.dmukhutdinov.logic.DrawLogic;

import java.util.concurrent.CountDownLatch;

/**
 * @author flyingleafe
 */
public class JfxDrawingApp extends Application implements DrawingApi {
    private GraphicsContext gc;
    private static final CountDownLatch latch = new CountDownLatch(1);
    private static JfxDrawingApp appInstance = null;

    static JfxDrawingApp waitForApp() {
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return appInstance;
    }

    public JfxDrawingApp() {
        appInstance = this;
    }

    @Override
    public long getDrawingAreaWidth() {
        return (long) gc.getCanvas().getWidth();
    }

    @Override
    public long getDrawingAreaHeight() {
        return (long) gc.getCanvas().getHeight();
    }

    @Override
    public void drawCircle(String label, Point center) {
        double r = DrawLogic.GOOD_NODE_RADIUS;
        gc.fillOval(center.getX() - r, center.getY() - r, r * 2, r * 2);
        gc.strokeText(label, center.getX() - 5, center.getY() + 5);
    }

    @Override
    public void drawLine(Point a, Point b) {
        gc.strokeLine(a.getX(), a.getY(), b.getX(), b.getY());
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Lab 5: JFX backend");
        Group root = new Group();
        Canvas canvas = new Canvas(800, 600);
        gc = canvas.getGraphicsContext2D();
        gc.setStroke(Color.BLACK);
        gc.setFill(Color.AQUA);
        root.getChildren().add(canvas);
        stage.setScene(new Scene(root));
        stage.show();
        latch.countDown();
    }
}
