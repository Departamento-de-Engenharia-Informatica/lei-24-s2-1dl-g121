package pt.ipp.isep.dei.esoft.project.sorting;

import pt.ipp.isep.dei.esoft.project.domain.GreenSpaces;
import java.util.List;

public class QuickSortStrategy implements SortingStrategy {
    @Override
    public void sort(List<GreenSpaces> greenSpaces) {
        if (greenSpaces == null || greenSpaces.size() <= 1) {
            return;
        }
        quickSort(greenSpaces, 0, greenSpaces.size() - 1);
    }

    private void quickSort(List<GreenSpaces> list, int low, int high) {
        if (low < high) {
            int pi = partition(list, low, high);
            quickSort(list, low, pi - 1);
            quickSort(list, pi + 1, high);
        }
    }

    private int partition(List<GreenSpaces> list, int low, int high) {
        double pivot = list.get(high).getArea();
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (list.get(j).getArea() > pivot) {
                i++;
                swap(list, i, j);
            }
        }
        swap(list, i + 1, high);
        return i + 1;
    }

    private void swap(List<GreenSpaces> list, int i, int j) {
        if (i != j) {
            GreenSpaces temp = list.get(i);
            list.set(i, list.get(j));
            list.set(j, temp);
        }
    }
}
