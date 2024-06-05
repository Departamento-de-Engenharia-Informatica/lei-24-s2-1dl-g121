package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.GreenSpaces;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GreenSpacesRepository {
    private final List<GreenSpaces> greenSpaces;

    private static GreenSpacesRepository instance;

    public GreenSpacesRepository() {
        greenSpaces = new ArrayList<>();
    }

    public static synchronized GreenSpacesRepository getInstance() {
        if (instance == null) {
            instance = new GreenSpacesRepository();
        }
        return instance;
    }

    public Optional<GreenSpaces> add(GreenSpaces greenSpace) {
        Optional<GreenSpaces> newGreenSpaces = Optional.empty();
        boolean operationSuccess = false;

        if (validateGreenSpace(greenSpace)) {
            newGreenSpaces = Optional.of(greenSpace.clone());
            operationSuccess = greenSpaces.add(newGreenSpaces.get());
        }

        if (!operationSuccess) {
            newGreenSpaces = Optional.empty();
        }

        return newGreenSpaces;
    }

    private boolean validateGreenSpace(GreenSpaces greenSpace) {
        return !greenSpaces.contains(greenSpace);
    }

    public List<GreenSpaces> getGreenSpaces() {
        return List.copyOf(greenSpaces);
    }
}
