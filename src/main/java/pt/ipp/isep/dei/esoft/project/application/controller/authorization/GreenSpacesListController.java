package pt.ipp.isep.dei.esoft.project.application.controller.authorization;

import pt.ipp.isep.dei.esoft.project.application.controller.GreenSpacesController;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpaces;
import pt.ipp.isep.dei.esoft.project.repository.GreenSpacesRepository;

import java.util.List;

public class GreenSpacesListController {
    private GreenSpacesRepository greenSpacesRepository;
    private GreenSpacesController controller;

    public GreenSpacesListController(){
        // Initialize the repository here
        this.greenSpacesRepository = new GreenSpacesRepository();
    }

    public List<GreenSpaces> getGreenSpaces() {
        return greenSpacesRepository.getGreenSpaces();
    }
}
