package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Team;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CreateTeamRepository implements Serializable {
    private final List<Team> teams;

    public CreateTeamRepository() {
        teams = new ArrayList<>();
    }

    public Optional<Team> addTeam(Team team) {
        Optional<Team> newTeam = Optional.empty();
        if (validateTeam(team)) {
            newTeam = Optional.of(team);
            teams.add(newTeam.get());
        }
        return newTeam;
    }

    private boolean validateTeam(Team team) {
        boolean isValid = !teams.contains(team);
        return isValid;
    }

    public List<Team> getTeams() {
        return List.copyOf(teams);
    }
}
