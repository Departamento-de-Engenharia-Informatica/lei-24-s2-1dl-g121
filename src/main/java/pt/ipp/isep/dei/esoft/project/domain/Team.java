package pt.ipp.isep.dei.esoft.project.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Team team = (Team) obj;
        return Objects.equals(collaborators, team.collaborators) &&
                Objects.equals(reference, team.reference);
    }

    public Team clone() {
        return new Team(this.collaborators, this.reference);
    }
}
