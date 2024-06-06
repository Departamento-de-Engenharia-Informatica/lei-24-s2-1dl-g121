package pt.ipp.isep.dei.esoft.project.sorting;

import pt.ipp.isep.dei.esoft.project.domain.GreenSpaces;

import java.util.List;

public class BubbleSortStrategy implements SortingStrategy {
    @Override
    public List<GreenSpaces> sort(List<GreenSpaces> greenSpaces) {
        int n = greenSpaces.size();
        for (int i = 0; i < n-1; i++) {
            for (int j = 0; j < n-i-1; j++) {
                if (greenSpaces.get(j).getArea() < greenSpaces.get(j+1).getArea()) {
                    // Swap
                    GreenSpaces temp = greenSpaces.get(j);
                    greenSpaces.set(j, greenSpaces.get(j+1));
                    greenSpaces.set(j+1, temp);
                }
            }
        }
        return greenSpaces;
    }
}
