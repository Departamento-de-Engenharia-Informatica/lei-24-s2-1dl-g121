package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.GreenSpaces;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GreenSpacesRepository implements Serializable {
    private static final long serialVersionUID = 1L;

    private final List<GreenSpaces> greenSpaces;
    public GreenSpacesRepository() {
        greenSpaces = new ArrayList<>();
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

    private boolean validateGreenSpace(GreenSpaces greenSpace) {return greenSpaces.stream().noneMatch(gs -> gs.equals(greenSpace));}

    public List<GreenSpaces> getGreenSpaces() {
        return List.copyOf(greenSpaces);
    }
}
