package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Team;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TeamRepository implements Serializable {
    private final List<Team> teams;

    public TeamRepository() {
        teams = new ArrayList<>();
    }

    public Optional<Team> add(Team team) {
        Optional<Team> newTeam = Optional.empty();
        if (true/*validateTeam(team)*/) {
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

    public List<String> getCollaboratorsNames(String TeamName) {
        Team team = getTeamByReference(TeamName);

        return new ArrayList<>(team.getCollaboratorsNames());
    }

    public List<String> getNames() {
        List<String> names = new ArrayList<>();
        for (Team team : teams) {
            names.add(team.getReference());
        }
        return names;
    }

    public Team getTeamByReference(String reference) {
        for (Team team : teams) {
            if (team.getReference().equals(reference)) {
                return team;
            }
        }
        return null;
    }
}
