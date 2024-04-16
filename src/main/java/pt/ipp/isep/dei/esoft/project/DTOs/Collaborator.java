package pt.ipp.isep.dei.esoft.project.DTOs;

public class Collaborator {
    private String name;
    private String birthDetails;
    private String issuingDetails;
    private String address;
    private String phoneNumber;
    private String email;
    private String ID;

public Collaborator(String name, String birthDetails, String issuingDetails, String address, String phoneNumber, String email, String ID){
    this.name= name;
    this.birthDetails= birthDetails;
    this.issuingDetails= issuingDetails;
    this.address= address;
    this.phoneNumber= phoneNumber;
    this.email= email;
    this.ID= ID;
}
    public String getName() {
        return name;
    }

    public String getBirthDetails() {
        return birthDetails;
    }

    public String getIssuingDetails() {
        return issuingDetails;
    }
    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public String getEmail() {
        return email;
    }

    public String getID() {
        return ID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthDetails(String birthDetails) {
        this.birthDetails = birthDetails;
    }

    public void setIssuingDetails(String issuingDetails) {
        this.issuingDetails = issuingDetails;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
}
