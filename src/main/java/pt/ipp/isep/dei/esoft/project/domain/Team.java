package pt.ipp.isep.dei.esoft.project.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Team implements Serializable {
    private List<Collaborator> collaborators;

    public Team(List<Collaborator> collaborators) {
        this.collaborators = collaborators;
        setCollaboratorTeam();
    }

    public List<Collaborator> getCollaborators() {
        return collaborators;
    }

    private void setCollaboratorTeam() {
        for (Collaborator c : collaborators) {
            c.setTeam(this);
        }
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
        return new Team(this.collaborators);
    }
}
