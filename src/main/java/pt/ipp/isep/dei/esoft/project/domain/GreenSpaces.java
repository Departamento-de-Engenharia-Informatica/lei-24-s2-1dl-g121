package pt.ipp.isep.dei.esoft.project.domain;

public class GreenSpaces {
    private String type;
    private double area;
    private String address;
    private String name;

    public GreenSpaces(String type, double area, String address, String name) {
        this.type = type;
        this.area = area;
        this.address = address;
        this.name = name;
    }

    // Getters and setters
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Method to register a green space
    public void registerGreenSpace() {
        System.out.println("Green space registered:");
        System.out.println("Type: " + type);
        System.out.println("Area: " + area + " square meters");
        System.out.println("Address: " + address);
        System.out.println("Name: " + name);
    }

//    public static void main(String[] args) {
//        // Example usage
//        GreenSpaces greenSpace = new GreenSpaces("Park", 1000, "123 Green Street", "Big Guy");
//        greenSpace.registerGreenSpace();
//    }
}
