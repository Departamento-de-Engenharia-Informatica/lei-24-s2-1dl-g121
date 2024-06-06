package pt.ipp.isep.dei.esoft.project.sorting;

import pt.ipp.isep.dei.esoft.project.domain.GreenSpaces;

import java.util.List;

public interface SortingStrategy {
    List<GreenSpaces> sort(List<GreenSpaces> greenSpaces);
}

