package pt.ipp.isep.dei.esoft.project.MDISC.SprintC;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class US_18 {

    static final String POINT_NAMES_PATH_US18 = "src/main/java/pt/ipp/isep/dei/esoft/project/MDISC/SprintC/resources/DatasetsUS17_18/us18_points_names.csv";
    static final String MATRIX_PATH_US18 = "src/main/java/pt/ipp/isep/dei/esoft/project/MDISC/SprintC/resources/DatasetsUS17_18/us18_matrix.csv";
    static final String fileSeparator = ";";

    static class Edge {
        Vertice from, to;
        int weight;

        public Edge(Vertice from, Vertice to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    static class Vertice {
        int index;
        String name;

        public Vertice(int index, String name) {
            this.index = index;
            this.name = name;
        }
    }

    public static ArrayList<String> getPointNames(String filepath) throws FileNotFoundException {

        File file = new File(filepath);
        Scanner sc = new Scanner(file);

        String line = sc.nextLine();
        String[] parts = line.split(fileSeparator);

        return new ArrayList<>(Arrays.asList(parts));
    }

    public static int[][] getDistancesMatrix(String filepath) throws FileNotFoundException {

        ArrayList<String> pointNames = getPointNames(filepath);
        int[][] distancesMatrix = new int[pointNames.size()][pointNames.size()];

        File file = new File(filepath);
        Scanner sc = new Scanner(file);

        int i = 0;
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            line = line.replaceAll("\uFEFF", "");
            String[] parts = line.split(fileSeparator);
            for (int j = 0; j < parts.length; j++) {
                distancesMatrix[i][j] = Integer.parseInt(parts[j]);
            }
            i++;
        }

        return distancesMatrix;
    }

    public static List dijkstra(ArrayList<String> pointNames, int[][] distancesMatrix, String source, String point, boolean finalPrint) throws IOException {
        int n = pointNames.size();
        int[] distance = new int[n];
        boolean[] visited = new boolean[n];
        int[] predecessor = new int[n];

        for (int i = 0; i < n; i++) {
            distance[i] = Integer.MAX_VALUE;
            visited[i] = false;
            predecessor[i] = -1;
        }

        int sourceIndex = pointNames.indexOf(source);
        distance[sourceIndex] = 0;

        for (int i = 0; i < n - 1; i++) {
            int u = minDistance(distance, visited);
            visited[u] = true;

            // If we've reached the target point, stop the algorithm
            if (pointNames.get(u).equals(point)) {
                break;
            }

            for (int v = 0; v < n; v++) {
                if (!visited[v] && distancesMatrix[u][v] != 0 && distance[u] != Integer.MAX_VALUE && distance[u] + distancesMatrix[u][v] < distance[v]) {
                    distance[v] = distance[u] + distancesMatrix[u][v];
                    predecessor[v] = u;
                }
            }
        }

        List output = new ArrayList();

        output.add(printSolution(distance, n, pointNames, predecessor, point));
        output.add(distance);

        return output;
    }

    private static String printSolution(int[] distance, int n, ArrayList<String> pointNames, int[] predecessor, String point) throws IOException {
        //System.out.println("Vertex \t\t       Distance from Source\t\tPath");

        String path = "";

        int i = pointNames.indexOf(point);
        if (i != pointNames.indexOf("AP")) {
            //System.out.printf("%-20s \t\t %10d\t\t", pointNames.get(i), distance[i]);

            if (distance[i] != Integer.MAX_VALUE) {
                path = printPath(i, predecessor, pointNames);
                String title = path.split(",")[0];
                title = title.replaceAll("\\(", "").replaceAll("\\)", "");
                //generateGraph(title, getEdgeListFromPath(path), distance[i]);
            } else {
                //System.out.println("NO PATH");
            }
        }

        return path;
    }

    private static List<Edge> getEdgeListFromPath(String path) {
        path = path.replaceAll("\\(", "").replaceAll("\\)", "");
        String[] points = path.split(",");
        List<Edge> pathEdges = new ArrayList<>();
        for (int i = 0; i < points.length - 1; i++) {
            for (Edge edge : edgeList) {
                if (edge.from.name.replaceAll("\uFEFF", "").equals(points[i]) &&
                        edge.to.name.replaceAll("\uFEFF", "").equals(points[i + 1]) ||
                        edge.to.name.replaceAll("\uFEFF", "").equals(points[i]) &&
                                edge.from.name.replaceAll("\uFEFF", "").equals(points[i + 1])) {
                    if (!pathEdges.contains(edge)) {
                        pathEdges.add(edge);
                    }
                }
            }
        }
        return pathEdges;
    }

    private static void generateGraph(String title, List<Edge> pathEdgeList, int cost) {
        for (Edge edge : edgeList) {
            edge.from.name = edge.from.name.replaceAll("\uFEFF", "");
            edge.to.name = edge.to.name.replaceAll("\uFEFF", "");
        }
        generateGraphVizSVG(edgeList, pathEdgeList, title, cost);
    }

    private static String printPath(int currentVertex, int[] predecessor, ArrayList<String> pointNames) {
        int[] path = new int[pointNames.size()];
        int pathLength = 0;
        StringBuilder pathString = new StringBuilder("(");
        for (int v = currentVertex; v != -1; v = predecessor[v]) {
            path[pathLength++] = v;
        }

        for (int i = 0; i < pathLength; i++) {
            //System.out.print(pointNames.get(path[i]) + " ");
            pathString.append(pointNames.get(path[i])).append(",");
        }
        //System.out.println();

        pathString = new StringBuilder(pathString.substring(0, pathString.length() - 1) + ")");

        return pathString.toString().replaceAll("\uFEFF", "");
    }

    private static int minDistance(int[] distance, boolean[] visited) {
        int min = Integer.MAX_VALUE;
        int minIndex = -1;

        for (int i = 0; i < distance.length; i++) {
            if (!visited[i] && distance[i] <= min) {
                min = distance[i];
                minIndex = i;
            }
        }

        return minIndex;
    }

    public static List<Edge> createEdgeList(int[][] distancesMatrix, List<String> pointNames) {
        HashSet<Edge> edgeList = new HashSet<>();
        for (int i = 0; i < distancesMatrix.length; i++) {
            for (int j = 0; j < distancesMatrix[i].length; j++) {
                if (distancesMatrix[i][j] != 0) {
                    if (!edgeList.contains(
                            new Edge(
                                    new Vertice(0, pointNames.get(i)),
                                    new Vertice(0, pointNames.get(j)),
                                    0))) {
                        edgeList.add(
                                new Edge(
                                        new Vertice(0, pointNames.get(i)),
                                        new Vertice(0, pointNames.get(j)),
                                        distancesMatrix[i][j]));
                    }
                }
            }
        }
        return new ArrayList<>(edgeList);
    }

    public static String getClosestAP(ArrayList<String> assemblyPoints, ArrayList<String> pointNames, int[][] distancesMatrix, String point) throws IOException {
        int minDistance = Integer.MAX_VALUE;
        String closestAP = null;

        for (String ap : assemblyPoints) {
            int[] distancesFromAP = (int[]) dijkstra(pointNames, distancesMatrix, ap, point, false).get(1);
            int distanceToPoint = distancesFromAP[pointNames.indexOf(point)];

            if (distanceToPoint < minDistance) {
                minDistance = distanceToPoint;
                closestAP = ap;
            }
        }

        return closestAP;
    }

    public static List<Edge> edgeList = new ArrayList<>();

    private static String selectPoint(ArrayList<String> pointNames) {
        System.out.println("Select the point:");
        for (int i = 0; i < pointNames.size(); i++) {
            System.out.println(i + " - " + pointNames.get(i));
        }

        Scanner sc = new Scanner(System.in);
        int selectedPoint = sc.nextInt();

        return pointNames.get(selectedPoint);
    }

    public static void outputCSVAllPoints(ArrayList<String> pointNames, int[][] distancesMatrix) throws IOException {
        FileWriter writer = new FileWriter("output/SprintC/us18_output.csv");

        ArrayList<String> assemblyPoints = new ArrayList<>();
        for (String point : pointNames) {
            if (point.contains("AP")) {
                assemblyPoints.add(point);
            }
        }
        if (assemblyPoints.isEmpty()) {
            System.out.println("No APs found in the input file.\n");
            return;
        }
        ArrayList<String> pointNamesCopy = new ArrayList<>(pointNames);
        pointNamesCopy.removeAll(assemblyPoints);
        for (String selectedPoint : pointNamesCopy) {
            String closestAP = getClosestAP(assemblyPoints, pointNames, distancesMatrix, selectedPoint);
            List output = dijkstra(pointNames, distancesMatrix, closestAP, selectedPoint, false);
            int[] distancesFromAP = (int[]) output.get(1);
            String path = (String) output.get(0);
            int distanceToPoint = distancesFromAP[pointNames.indexOf(selectedPoint)];
            path = path.replaceAll("\uFEFF", "").replaceAll(" ", ",");
            writer.write(path + "; " + distanceToPoint + "\n");
        }

        writer.close();
    }

    public static void main(String[] args) {
        try {
            ArrayList<String> pointNames = getPointNames(POINT_NAMES_PATH_US18);
            int[][] distancesMatrix = getDistancesMatrix(MATRIX_PATH_US18);
            edgeList = createEdgeList(distancesMatrix, pointNames);

            System.out.println("Generating output CSV file with all points...");
            outputCSVAllPoints(pointNames, distancesMatrix);
            System.out.println("Output CSV file generated successfully.\n");

            new Thread(() -> {
                try {
                    Thread.sleep(1000);
                    File f = new File("src/main/resources/MDISC/us17_18_output/us18_graphs/graph.dot");
                    f.delete();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void generateGraphVizSVG(List<Edge> initialGraphEdges, List<Edge> minimalSpanningTreeEdges, String title, double cost) {
        try {
            String directoryPath = "src/main/resources/MDISC/us17_18_output/us18_graphs";
            File directory = new File(directoryPath);

            if (!directory.exists()) {
                directory.mkdirs();
            }

            FileWriter writer = new FileWriter(directoryPath + "/graph.dot");

            writer.write("graph {\n");
            writer.write("fontname=\"Arial\";\n");
            writer.write("splines=\"true\";\n");
            writer.write("sep=\"0.5\";\n");
            writer.write("labelloc=\"t\";\n");
            writer.write("label=\"" + title + " (total cost:" + cost + ")\";\n");
            writer.write("fontsize=25;\n");
            writer.write("fontweight=bold;\n");

            List<Edge> addedEdges = new ArrayList<>();

            for (Edge edge : initialGraphEdges) {
                if (addedEdges.contains(edge)) {
                    continue;
                }
                String color = minimalSpanningTreeEdges.contains(edge) ? "red" : "black";
                String penwidth = minimalSpanningTreeEdges.contains(edge) ? "4.0" : "1.0";
                writer.write("    \"" + edge.from.name + "\" -- \"" + edge.to.name + "\" [label=\"" + edge.weight + "\", color=\"" + color + "\", len=2, penwidth=" + penwidth + ", fontname=\"Arial\"];\n");
                addedEdges.add(edge);
            }

            writer.write("}\n");
            writer.close();

            try {
                title = title.replaceAll(" ", "_");
                Runtime.getRuntime().exec("neato -Tsvg " + directoryPath + "/graph.dot -o " + directoryPath + "/" + title + ".svg").waitFor();
            } catch (IOException e) {
                System.out.println("Error generating SVG file");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            System.out.println("Error writing to file");
        }
    }
}


