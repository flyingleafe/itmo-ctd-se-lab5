package ru.ifmo.ctd.se.dmukhutdinov.graph;

import ru.ifmo.ctd.se.dmukhutdinov.drawing.DrawingApi;
import ru.ifmo.ctd.se.dmukhutdinov.drawing.Point;
import ru.ifmo.ctd.se.dmukhutdinov.logic.DrawLogic;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author flyingleafe
 */
public class EdgeListGraph extends Graph {
    class Edge {
        int a;
        int b;

        Edge(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }

    private List<Edge> edgeList;
    private int n;

    private void calcN() {
        n = 0;
        for (Edge e : edgeList) {
            n = Math.max(n, Math.max(e.a, e.b));
        }
    }

    public EdgeListGraph(List<Edge> edgeList) {
        this.edgeList = edgeList;
        calcN();
    }

    public EdgeListGraph(InputStream in) {
        edgeList = new ArrayList<>();
        readGraph(in);
    }

    @Override
    public void drawGraph(DrawingApi api) {
        List<Point> points = DrawLogic.circlePointsOnCanvas(api, n);
        for (Edge e : edgeList) {
            api.drawLine(points.get(e.a), points.get(e.b));
        }

        for (int i = 0; i < n; i++) {
            api.drawCircle(Integer.toString(i), points.get(i));
        }
    }

    @Override
    public void readGraph(InputStream source) {
        Scanner sc = new Scanner(source);
        int m = sc.nextInt();
        edgeList.clear();
        for (int i = 0; i < m; i++) {
            edgeList.add(new Edge(sc.nextInt(), sc.nextInt()));
        }
        calcN();
    }
}
