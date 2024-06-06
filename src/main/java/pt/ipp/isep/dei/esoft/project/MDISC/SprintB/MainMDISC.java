package pt.ipp.isep.dei.esoft.project.MDISC.SprintB;

import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;
import org.graphstream.ui.view.Viewer;
import org.graphstream.ui.view.ViewerPipe;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class MainMDISC {

    static final String fileSeparator = ";";

    static final String CSS = "graph {padding: 75px;} node {text-alignment: under;} edge {text-size: 15px; text-color: #000000;}";

    static class Edge {
        Vertice from, to;
        int weight; 

        public Edge(Vertice from, Vertice to, int weight) {
            this.from = from; 
            this.to = to; 
            this.weight = weight; 
        }
    }

    public static class Vertice{
        int index; 
        public String name;

        public Vertice(int index, String name) {
            this.index = index; 
            this.name = name; 
        }
    }

    static class Subset {
        int parent, rank; 

        public Subset(int parent, int rank) {
            this.parent = parent; 
            this.rank = rank; 
        }
    }

    public static ArrayList<Edge> readFile(String fileName) throws Exception{
        HashSet<Edge> edges = new HashSet<>();
        HashSet<String> verticeNamesHS = new HashSet<>();
        Scanner sc = new Scanner(new File(fileName));
        while(sc.hasNextLine()){
            String[] line = sc.nextLine().split(fileSeparator);
            verticeNamesHS.add(line[0]);
            verticeNamesHS.add(line[1]);
        }
        ArrayList<String> verticeNames = new ArrayList<>(verticeNamesHS);
        sc.close();
        Scanner sc1 = new Scanner(new File(fileName));
        while(sc1.hasNextLine()){
            String[] line = sc1.nextLine().split(fileSeparator);
            edges.add(
                new Edge(
                    new Vertice(verticeNames.indexOf(line[0]), line[0]),
                    new Vertice(verticeNames.indexOf(line[1]), line[1]),
                    Integer.parseInt(line[2])
                )
            );
        }
        sc1.close();
        return new ArrayList<Edge>(edges);
    }

    public static int find(Subset[] subsets, int i){
        if(subsets[i].parent != i){
            subsets[i].parent = find(subsets, subsets[i].parent);
        }
        return subsets[i].parent;
    }

    public static void union(Subset[] subsets, int x, int y){
        int xroot = find(subsets, x);
        int yroot = find(subsets, y);
        if(subsets[xroot].rank < subsets[yroot].rank){
            subsets[xroot].parent = yroot;
        }else if(subsets[xroot].rank > subsets[yroot].rank){
            subsets[yroot].parent = xroot;
        }else{
            subsets[yroot].parent = xroot;
            subsets[xroot].rank++;
        }
    }

    public static Graph drawGraph(ArrayList<Edge> edges, String graphTitle){
        System.setProperty("org.graphstream.ui", "swing");
        Graph graph = new SingleGraph(graphTitle);
        graph.setAttribute("ui.stylesheet", CSS);
        graph.setStrict(false);
        graph.setAutoCreate( true );
        for(Edge edge : edges){
            graph.addEdge((edge.from.name+edge.to.name),edge.from.name,edge.to.name).setAttribute("ui.label", edge.weight);

        }
        for (Node node : graph) {
            node.setAttribute("ui.label", node.getId());
            node.setAttribute("ui.style", "size: 14px;");
            node.setAttribute("ui.style", "text-size: 20px;");
        }
        return graph;
    }

    public static Graph highlightGraph(Graph g, ArrayList<Edge> result){
        for(Edge edge : result){
            g.getEdge((edge.from.name+edge.to.name)).setAttribute("ui.style", "fill-color: red; " +
                                                                                            "size: 3px;");
        }
        return g;
    }

    public static void outputGraphCSV(ArrayList<Edge> result, int cost) throws Exception{
        Path outputPath = Paths.get("output");
        if(!Files.exists(outputPath)){
            Files.createDirectory(outputPath);
        }
        FileWriter fw = new FileWriter(outputPath.resolve("output.csv").toString());
        for(Edge edge : result){
            fw.write(edge.from.name + fileSeparator + edge.to.name + fileSeparator + edge.weight + "\n");
        }
        fw.write("MST cost: " + cost);
        fw.close();
    }

    public static void kruskal(ArrayList<Edge> edges, int V, int mode) throws Exception{
        Graph graph = drawGraph(edges, "MST");
        long currentTime = System.nanoTime();
        ArrayList<Edge> result = new ArrayList<>();
        edges = sortArrayList(edges);
        Subset[] subsets = new Subset[V];
        for(int v = 0; v < V; v++){
            subsets[v] = new Subset(v, 0);
        }
        int e = 0;
        int i = 0;
        while(e < V - 1 && i < edges.size()){
            Edge next_edge = edges.get(i++);
            int x = find(subsets, next_edge.from.index);
            int y = find(subsets, next_edge.to.index);
            if(x != y){
                result.add(next_edge);
                union(subsets, x, y);
                e++;
            }
        }
        float execTime = (System.nanoTime() - currentTime)/1000000f;
        int mstCost = 0;
        for(Edge edge : result){
            System.out.println(edge.from.name + " - " + edge.to.name + ": " + edge.weight);
            mstCost+=edge.weight;
        }
        System.out.println("MST cost: " + mstCost);
        System.out.println("Time: " + execTime + "us");
        generateGraphVizSVG(edges, result, "Minimum Spanning Tree", mstCost);
        graph.setAttribute("ui.title", "Minimum Spanning Tree");
        graph.setAttribute("ui.quality");
        graph.setAttribute("ui.antialias");
        outputGraphCSV(result, mstCost);
        Graph highlightedGraph = highlightGraph(graph, result);
        addTextToGraph(highlightedGraph, "Cost: " + mstCost + " Time: " + execTime + "ms");
    }

    public static void addTextToGraph(Graph graph, String text) {
        Viewer viewer = graph.display();
        ViewerPipe pipe = viewer.newViewerPipe();

        JPanel panel = (JPanel) viewer.getDefaultView();
        JLabel label = new JLabel(text);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(label, BorderLayout.NORTH);

        panel.revalidate();
    }

    public static void generateGraphVizSVG(List<Edge> initialGraphEdges, List<Edge> minimalSpanningTreeEdges, String title, double cost) {
        try {
            String directoryPath = "output/MATDISC_graph_images";
            File directory = new File(directoryPath);

            if (!directory.exists()) {
                directory.mkdirs();
            }

            FileWriter writer = new FileWriter(directoryPath + "/graph.dot");

            writer.write("graph {\n");
            writer.write("fontname=\"Arial\";\n");
            //writer.write("overlap=\"scalexy\";\n");
            writer.write("splines=\"true\";\n");
            writer.write("sep=\"0.5\";\n");
            writer.write("labelloc=\"t\";\n");
            writer.write("label=\"" + title + " (total cost:" + cost + ")\";\n");
            writer.write("fontsize=25;\n");
            writer.write("fontweight=bold;\n");

            for (Edge edge : initialGraphEdges) {
                String color = minimalSpanningTreeEdges.contains(edge) ? "red" : "black";
                String penwidth = minimalSpanningTreeEdges.contains(edge) ? "4.0" : "1.0";
                writer.write("    \"" + edge.from.name + "\" -- \"" + edge.to.name + "\" [label=\"" + edge.weight + "\", color=\"" + color + "\", len=2, penwidth=" + penwidth + ", fontname=\"Arial\"];\n");
            }

            writer.write("}\n");
            writer.close();
            title = title.substring(0, title.length() - 4);
            try {
                Runtime.getRuntime().exec("neato -Tsvg " + directoryPath + "/graph.dot -o " + directoryPath + "/" + title.replaceAll(" ", "_") + "_MST.svg");
            } catch (IOException e) {
                System.out.println("Error generating SVG file");
            }
        } catch (IOException e) {
            System.out.println("Error writing to file");
        }
    }

    @SuppressWarnings("unused")
    public static void kruskalTest() throws Exception{
        System.out.println((!new File("output").exists()));
        if(!new File("output").exists()){
            new File("output").mkdir();
            System.out.println("file created");
        }
        FileWriter fw = new FileWriter("output/execTimes.csv");
        FileWriter fw2 = new FileWriter("output/execInfo.txt");
        File folder = new File(getTestFilesPath());
        int cont = 1;
        for(int j = 1; j <= folder.listFiles().length; j++){
            System.out.println("\nFile: us14_" + j + ".csv");
            ArrayList<Edge> edges = readFile(folder+"/us14_" + j + ".csv");
            ArrayList<Edge> result = new ArrayList<>();
            int V = countVertices(edges);
            long currentTime = System.nanoTime();
            edges = sortArrayList(edges);
            Subset[] subsets = new Subset[V];
            for(int v = 0; v < V; v++){
               subsets[v] = new Subset(v, 0);
            }
            int e = 0;
            int i = 0;
            while(e < V - 1 && i < edges.size()){
                Edge next_edge = edges.get(i++);
                int x = find(subsets, next_edge.from.index);
                int y = find(subsets, next_edge.to.index);
                if(x != y){
                    result.add(next_edge);
                    union(subsets, x, y);
                    e++;
                }
            }
            float execTime = (System.nanoTime()  - currentTime)/1000000f;
            System.out.println("File " + cont + " Time: " + execTime + "ms");
            System.out.println("Input vertices number - " + V);
            fw.write(edges.size() + fileSeparator + execTime + "\n");
            fw2.write("File " + cont + " Vertices counted: " + V + " Time: " + execTime + "us\n");
            cont++;
        }
        fw.close();
        fw2.close();
    }

    public static int countVertices(ArrayList<Edge> edges){
        ArrayList<String> vertices = new ArrayList<>();
        for(Edge edge : edges){
            if(!ArrListContains(vertices, edge.from.name)){
                vertices.add(edge.from.name);
            }
            if(!ArrListContains(vertices, edge.to.name)){
                vertices.add(edge.to.name);
            }
        }
        return vertices.size();
    }

    public static boolean ArrListContains(ArrayList list, Object obj){
        for(Object o : list){
            if(o.equals(obj))
                return true;
        }
        return false;
    }
    
    public static String getFilePath(){
        System.out.println("Paste the file path for the input file or leave empty to use default path (waterData.csv). ");
        Scanner sc = new Scanner(System.in);
        String path = sc.nextLine();
        File test = new File(path);
        if(path.equals("") || !test.exists())
            return "src/main/java/pt/ipp/isep/dei/esoft/project/MDISC/resouces/waterData.csv";
        else
            return path;
    }

    public static String getTestFilesPath() throws Exception{
        System.out.println("Input the test files' path or leave empty to use default path (testInput/). ");
        Scanner sc = new Scanner(System.in);
        String answer = sc.nextLine();
        File path = new File(answer);
        if(path.exists() && path.listFiles().length > 0) {
            return answer;
        }
        else {
            System.out.println("Using default path.");
            return  "testInput";
        }
    }
    public static ArrayList<Edge> sortArrayList(ArrayList<Edge> edges){
        for (int i = 0; i < edges.size(); i++) {
            for (int j = edges.size() - 1; j > i; j--) {
                if (edges.get(i).weight > edges.get(j).weight) {
                    Edge tmp = edges.get(i);
                    edges.set(i,edges.get(j));
                    edges.set(j,tmp);
                }
            }
        }
        return edges;
    }

    public static void main(String[] args) throws Exception {
        System.out.println( "1 - Run Kruskal's algorithm.\n" +
                            "2 - Test Kruskal's algorithm from folder.");
        Scanner sc = new Scanner(System.in);
        int option = sc.nextInt();
        if(option == 1){
            ArrayList<Edge> edges = readFile(getFilePath());
            kruskal(edges, countVertices(edges), option);
        }else if(option == 2){
            kruskalTest();
            Runtime commandPrompt = Runtime.getRuntime();
            commandPrompt.exec("gnuplot --persist C:\\Users\\salva\\OneDrive\\Documentos\\GitHub\\lei-24-s2-1dl-g121\\src\\main\\java\\pt\\ipp\\isep\\dei\\esoft\\project\\MDISC\\resouces\\plot.gp");
        }
        sc.close();
    }
}
