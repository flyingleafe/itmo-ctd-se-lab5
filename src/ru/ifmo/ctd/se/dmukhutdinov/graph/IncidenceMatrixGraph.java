package ru.ifmo.ctd.se.dmukhutdinov.graph;

import ru.ifmo.ctd.se.dmukhutdinov.drawing.DrawingApi;
import ru.ifmo.ctd.se.dmukhutdinov.drawing.Point;
import ru.ifmo.ctd.se.dmukhutdinov.logic.DrawLogic;

import java.io.InputStream;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * @author flyingleafe
 */
public class IncidenceMatrixGraph extends Graph {

    class InvalidMatrixException extends RuntimeException {
        InvalidMatrixException(String message) {
            super(message);
        }
    }

    private boolean[][] matrix;

    public IncidenceMatrixGraph(InputStream source) {
        readGraph(source);
    }

    @Override
    public void drawGraph(DrawingApi api) {
        List<Point> points = DrawLogic.circlePointsOnCanvas(api, matrix.length);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = i + 1; j < matrix.length; j++) {
                if (matrix[i][j]) {
                    api.drawLine(points.get(i), points.get(j));
                }
            }
            api.drawCircle(Integer.toString(i), points.get(i));
        }
    }

    @Override
    public void readGraph(InputStream source) {
        Scanner sc = new Scanner(source);
        int n = sc.nextInt();
        matrix = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = sc.nextInt() > 0;
            }
        }
    }
}
