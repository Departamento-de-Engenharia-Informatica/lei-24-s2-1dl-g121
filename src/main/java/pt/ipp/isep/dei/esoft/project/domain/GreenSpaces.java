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

    public boolean isValid() {
        return type != null && !type.isEmpty()
                && area > 0
                && address != null && !address.isEmpty()
                && name != null && !name.isEmpty()
                && email != null && !email.isEmpty();
    }

    public String getName() {
        return name;
    }

    public double getArea() {
        return area;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getType() {
        return type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setType(String type) {
        this.type = type;
    }

}
