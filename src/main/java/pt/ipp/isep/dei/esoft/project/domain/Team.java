package pt.ipp.isep.dei.esoft.project.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Team implements Serializable {
    private List<Collaborator> collaborators;
    private String reference;

    public Team(List<Collaborator> collaborators, String reference) {
        this.collaborators = collaborators;
        setCollaboratorTeam();
        this.reference = reference;
    }

    public List<Collaborator> getCollaborators() {
        return collaborators;
    }

    public List<String> getCollaboratorsNames() {
        List<String> collaboratorsNames = new ArrayList<>();
        for (Collaborator c : collaborators) {
            collaboratorsNames.add(c.getName());
        }
        return collaboratorsNames;
    }

    public String getReference() {
        return reference;
    }

    private void setCollaboratorTeam() {
        for (Collaborator c : collaborators) {
            c.setTeam(this);
        }
    }

    private void setReference(String reference) {
        this.reference = reference;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        Team team = (Team) o;
        return this.collaborators.equals(team.collaborators);
    }

    public Team clone() {
        return new Team(this.collaborators, this.reference);
    }
}
