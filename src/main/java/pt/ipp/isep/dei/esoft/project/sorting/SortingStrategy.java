package pt.ipp.isep.dei.esoft.project.sorting;

import pt.ipp.isep.dei.esoft.project.domain.GreenSpaces;

import java.util.List;

public interface SortingStrategy {
    void sort(List<GreenSpaces> greenSpaces);
}

