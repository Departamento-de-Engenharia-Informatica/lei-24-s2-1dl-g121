package pt.ipp.isep.dei.esoft.project.domain;

import java.util.Objects;

public class Collaborator {
    private String name;
    private String birthDetails;
    private String issuingDetails;
    private String address;
    private String phoneNumber;
    private String email;
    private String identificationDocument;

    public Collaborator(String name, String birthDetails, String issuingDetails, String address, String phoneNumber, String email, String identificationDocument){
        this.name= name;
        this.birthDetails= birthDetails;
        this.issuingDetails= issuingDetails;
        this.address= address;
        this.phoneNumber= phoneNumber;
        this.email= email;
        this.identificationDocument= identificationDocument;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Collaborator)) {
            return false;
        }
        Collaborator employee = (Collaborator) o;
        return email.equals(employee.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }

    public boolean hasEmail(String email) {
        return this.email.equals(email);
    }


    /**
     * Clone method.
     *
     * @return A clone of the current instance.
     */
    public Collaborator clone() {
        return new Collaborator(this.name, this.birthDetails, this.issuingDetails, this.address, this.phoneNumber, this.email, this.identificationDocument);
    }
}