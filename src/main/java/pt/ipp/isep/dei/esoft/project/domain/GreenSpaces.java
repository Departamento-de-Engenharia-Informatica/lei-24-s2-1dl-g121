package pt.ipp.isep.dei.esoft.project.domain;

public class GreenSpaces {
    private String type;
    private double area;
    private String address;
    private String name;
    private String email;

    public GreenSpaces(String type, double area, String address, String name, String email) {
        this.type = type;
        this.area = area;
        this.address = address;
        this.name = name;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Method to register a green space
    public void registerGreenSpace() {
        System.out.println("Green space registered:");
        System.out.println("Type: " + type);
        System.out.println("Area: " + area + " square meters");
        System.out.println("Address: " + address);
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
    }


    public GreenSpaces clone() {
        return new GreenSpaces(this.type, this.area, this.address, this.name, this.email);
    }
}
