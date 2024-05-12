package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.TaskCategory;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Represents a repository for storing Collaborator objects.
 * Provides methods to add collaborators and retrieve them by their ID.
 */
public class CollaboratorRepository {

    private final List<Collaborator> collaborators;
    private JobRepository jobRepository;

    /**
     * Constructs a new CollaboratorRepository.
     */
    public CollaboratorRepository() {
        collaborators = new ArrayList<>();
    }

    /**
     * Adds a new collaborator to the repository.
     *
     * @param collaborator The collaborator to be added.
     * @return An optional containing the added collaborator if it was added successfully, empty otherwise.
     */
    public Optional<Collaborator> add(Collaborator collaborator) {

        Optional<Collaborator> newCollaborator = Optional.empty();
        boolean operationSuccess = false;

        if (validateCollaborator(collaborator)) {
            newCollaborator = Optional.of(collaborator.clone());
            operationSuccess = collaborators.add(newCollaborator.get());
        }

        if (!operationSuccess) {
            newCollaborator = Optional.empty();
        }

        return newCollaborator;
    }

    /**
     * Validates a collaborator before adding it to the repository.
     *
     * @param collaborator The collaborator to be validated.
     * @return true if the collaborator is valid, false otherwise.
     */
    private boolean validateCollaborator(Collaborator collaborator) {
        boolean isValid = !collaborators.contains(collaborator);
        return isValid;
    }

    /**
     * Returns a list of all collaborators in the repository.
     *
     * @return A list of all collaborators.
     */
    public List<Collaborator> getCollaborators() {
        //This is a defensive copy, so that the repository cannot be modified from the outside.
        return List.copyOf(collaborators);
    }

    /**
     * Retrieves a collaborator from the repository by their ID.
     *
     * @param id The ID of the collaborator to retrieve.
     * @return The collaborator with the given ID, or null if no such collaborator exists.
     */
    public Collaborator getCollaboratorById(String id) {
        for (Collaborator collaborator : collaborators) {
            if (collaborator.getCollaboratorID().equals(id)) {
                return collaborator;
            }
        }
        return null;
    }
}