package ru.ifmo.ctd.se.dmukhutdinov.graph;

import ru.ifmo.ctd.se.dmukhutdinov.drawing.DrawingApi;

import java.io.InputStream;

/**
 * @author flyingleafe
 */
public abstract class Graph {
    public abstract void readGraph(InputStream source);
    public abstract void drawGraph(DrawingApi api);
}
