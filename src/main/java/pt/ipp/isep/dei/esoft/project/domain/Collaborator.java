package pt.ipp.isep.dei.esoft.project.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Collaborator class.
 */
public class Collaborator {
    private String name;
    private String birthDetails;
    private String issuingDetails;
    private String address;
    private String phoneNumber;
    private String email;
    private String identificationDocument;
    private Job job;
    private List<Skill> skillList;

    public Collaborator(String name, String birthDetails, String issuingDetails, String address, String phoneNumber, String email, String identificationDocument, Job job){
        this.name= name;
        this.birthDetails= birthDetails;
        this.issuingDetails= issuingDetails;
        this.address= address;
        this.phoneNumber= phoneNumber;
        this.email= email;
        this.identificationDocument= identificationDocument;
        this.job= job;
        this.skillList= new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getIdentificationDocument() {
        return identificationDocument;
    }

    public boolean addSkill(Skill skill){
        if(!this.skillList.contains(skill)){
            this.skillList.add(skill);
            return true;
        }
        return false;
    }

    public void removeSkill(Skill skill){
        this.skillList.remove(skill);
    }

    /**
     * Checks if this collaborator is equal to another object.
     *
     * @param o the other object to compare
     * @return true if the other object is a Collaborator and has the same ID, false otherwise
     */
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

    /**
     * Clone method.
     *
     * @return A clone of the current instance.
     */
    public Collaborator clone() {
        return new Collaborator(this.name, this.birthDetails, this.issuingDetails, this.address, this.phoneNumber, this.email, this.identificationDocument, this.job);
    }

    public String getCollaboratorID() {
        return this.identificationDocument;
    }

    public Skill[] getSkills() {
        return this.skillList.toArray(new Skill[0]);
    }
}