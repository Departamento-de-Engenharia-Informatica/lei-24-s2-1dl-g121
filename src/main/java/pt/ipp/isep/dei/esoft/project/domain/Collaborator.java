package pt.ipp.isep.dei.esoft.project.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * Represents a Collaborator in the system.
 * A Collaborator has a name, birth details, issuing details, address, phone number, email, identification document, job, and a list of skills.
 */
public class Collaborator implements Serializable {
    private String name;
    private String birthDetails;
    private String issuingDetails;
    private String address;
    private String phoneNumber;
    private String email;
    private String taxPayerDocument;
    private String identificationDocument;
    private Job job;
    private List<Skill> skillList;
    private Team team;

    /**
     * Constructs a new Collaborator with the given details.
     *
     * @param name           the name of the Collaborator
     * @param birthDetails   the birth details of the Collaborator
     * @param issuingDetails the issuing details of the Collaborator
     * @param address        the address of the Collaborator
     * @param phoneNumber    the phone number of the Collaborator
     * @param email          the email of the Collaborator
     * @param job            the job of the Collaborator
     */
    public Collaborator(String name, String birthDetails, String issuingDetails, String address, String phoneNumber, String email, String taxPayerDocument,String identificationDocument, Job job){
        this.name= name;
        this.birthDetails= birthDetails;
        this.issuingDetails= issuingDetails;
        this.address= address;
        this.phoneNumber= phoneNumber;
        this.email= email;
        this.taxPayerDocument = taxPayerDocument;
        this.identificationDocument= identificationDocument;
        this.job= job;
        this.skillList= new ArrayList<>();
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Team getTeam() {
        return this.team;
    }

    /**
     * Returns the name of the Collaborator.
     *
     * @return the name of the Collaborator
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the identification document of the Collaborator.
     *
     * @return the identification document of the Collaborator
     */
    public String getIdentificationDocument() {
        return identificationDocument;
    }

    /**
     * Adds a skill to the Collaborator's skill list.
     *
     * @param skill the skill to add
     * @return true if the skill was added successfully, false otherwise
     */
    public boolean addSkill(Skill skill){
        if(!this.skillList.contains(skill)){
            this.skillList.add(skill);
            return true;
        }
        return false;
    }

    /**
     * Removes a skill from the Collaborator's skill list.
     *
     * @param skill the skill to remove
     */
    public void removeSkill(Skill skill){
        this.skillList.remove(skill);
    }

    /**
     * Checks if this Collaborator is equal to another object.
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
     * Returns a clone of the current Collaborator instance.
     *
     * @return a clone of the current Collaborator instance
     */
    public Collaborator clone() {
        return new Collaborator(this.name, this.birthDetails, this.issuingDetails, this.address, this.phoneNumber, this.email,this.taxPayerDocument,this.identificationDocument, this.job);
    }

    /**
     * Returns the ID of the Collaborator.
     *
     * @return the ID of the Collaborator
     */
    public String getCollaboratorID() {
        return this.identificationDocument;
    }

    /**
     * Returns the list of skills of the Collaborator.
     *
     * @return the list of skills of the Collaborator
     */
    public List<Skill> getSkillList() {
        return this.skillList;
    }

    public boolean hasAllSkills(List<Skill> requiredSkills) {
        for (Skill requiredSkill : requiredSkills) {
            boolean hasSkill = false;
            for (Skill skill : skillList) {
                if (skill.getName().equals(requiredSkill.getName())) {
                    hasSkill = true;
                    break;
                }
            }
            if (!hasSkill) {
                return false; // If the collaborator doesn't have a required skill, return false
            }
        }
        return true; // If the collaborator has all required skills, return true
    }

    public List<Skill> getSkills() {
        return this.skillList;
    }

    public String getEmail() {
        return this.email;
    }
}