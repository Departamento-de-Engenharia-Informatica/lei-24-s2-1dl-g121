package pt.ipp.isep.dei.esoft.project.DTOs;

public class HRM {
    private String name;
    private String email;
    private String ID;
    private String phone;

    public HRM (String name, String email, String ID, String phone){
        this.name = name;
        this.email = email;
        this.ID = ID;
        this.phone = phone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public String getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }
}