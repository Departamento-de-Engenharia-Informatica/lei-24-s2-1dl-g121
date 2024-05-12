package pt.ipp.isep.dei.esoft.project.MDISC;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class US12 {
        public static void main(String[] args) {


        }
    public void importCsv(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.trim().split(",");
                if (data.length == 3) {
                    String pointX = data[0].trim();
                    String pointY = data[1].trim();
                    double distance = Double.parseDouble(data[2].trim());
                } else {
                    System.out.println("Invalid line in CSV: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("File not found: " + filename);
        }
    }
    }

