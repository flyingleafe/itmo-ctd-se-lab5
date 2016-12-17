package ru.ifmo.ctd.se.dmukhutdinov;

import org.apache.commons.cli.*;
import ru.ifmo.ctd.se.dmukhutdinov.drawing.AWTDrawingApi;
import ru.ifmo.ctd.se.dmukhutdinov.drawing.DrawingApi;
import ru.ifmo.ctd.se.dmukhutdinov.drawing.JfxDrawingApi;
import ru.ifmo.ctd.se.dmukhutdinov.graph.EdgeListGraph;
import ru.ifmo.ctd.se.dmukhutdinov.graph.Graph;
import ru.ifmo.ctd.se.dmukhutdinov.graph.IncidenceMatrixGraph;

public class Main {

    private static final Options opts;

    static {
        opts = new Options();
        Option graphType = Option.builder("g")
                .longOpt("graph-type")
                .argName("TYPE")
                .hasArg()
                .desc("Type of graph representation (`incidence-matrix` or `edge-list`)")
                .build();
        Option apiType = Option.builder("a")
                .longOpt("api-type")
                .argName("TYPE")
                .hasArg()
                .desc("Type of drawing API (`awt` of `javafx`)")
                .build();
        opts.addOption(graphType);
        opts.addOption(apiType);
    }

    public static void main(String[] args) {
        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine line = parser.parse(opts, args);

            if (!line.hasOption('g') || !line.hasOption('a')) {
                new HelpFormatter().printHelp("lab5", opts);
                return;
            }

            Graph graph;
            switch (line.getOptionValue('g')) {
                case "incidence-matrix":
                    graph = new IncidenceMatrixGraph(System.in);
                    break;
                case "edge-list":
                    graph = new EdgeListGraph(System.in);
                    break;
                default:
                    throw new RuntimeException("Invalid graph type");
            }

            DrawingApi api;
            switch (line.getOptionValue('a')) {
                case "awt":
                    api = new AWTDrawingApi();
                    break;
                case "javafx":
                    api = new JfxDrawingApi();
                    break;
                default:
                    throw new RuntimeException("Invalid drawing api type");
            }

            graph.drawGraph(api);

        } catch (ParseException exp) {
            System.err.println("Options parsing error: " + exp);
        }
    }
}
