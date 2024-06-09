package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Entry;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpaces;
import pt.ipp.isep.dei.esoft.project.repository.GreenSpacesRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.sorting.BubbleSortStrategy;
import pt.ipp.isep.dei.esoft.project.sorting.QuickSortStrategy;
import pt.ipp.isep.dei.esoft.project.sorting.SortingStrategy;

import java.util.*;
import java.util.stream.Collectors;
import java.io.FileInputStream;
import java.io.IOException;


public class GreenSpacesController {
    private GreenSpacesRepository repository;
    private SortingStrategy sortingStrategy;
    private GreenSpaces greenSpaces;


    public GreenSpacesController() {
        getGreenSpacesRepository();
        sortingStrategy = loadSortingStrategy();
    }

    private void getGreenSpacesRepository() {
        if (repository == null) {
            Repositories repositories = Repositories.getInstance();
            repository = repositories.getGreenSpacesRepository();
        }
    }

    public List<String> getGreenSpacesNamesAndEmails() {
        List<GreenSpaces> greenSpacesList = repository.getGreenSpaces();
        return greenSpacesList.stream()
                .map(gs -> gs.getName() + " | " + gs.getEmail())
                .collect(Collectors.toList());
    }

    public Optional<GreenSpaces> createGreenSpace(String type, double area, String address, String name, String email) {
        return repository.add(new GreenSpaces(type, area, address, name, email));
    }

    public List<String> getGreenSpacesNames() {
        List<String> greenSpacesNames = new ArrayList<>();
        for (GreenSpaces greenSpace : repository.getGreenSpaces()) {
            greenSpacesNames.add(greenSpace.getName());
        }
        return greenSpacesNames;
    }

    public GreenSpaces getGreenSpaceByName(String name) {
        for (GreenSpaces greenSpace : repository.getGreenSpaces()) {
            if (greenSpace.getName().equals(name)) {
                return greenSpace;
            }
        }
        return null;
    }
    public GreenSpaces getGreenSpaceByEmail(String email) {
        for (GreenSpaces greenSpace : repository.getGreenSpaces()) {
            if (greenSpace.getEmail().equals(email)) {
                return greenSpace;
            }
        }
        return null;
    }
    public List<String> getGreenSpacesNamesAndEmailsByEmail(String email) {
        List<GreenSpaces> greenSpacesList = repository.getGreenSpaces();
        return greenSpacesList.stream()
                .filter(gs -> gs.getEmail().equals(email))
                .map(gs -> gs.getName() + " | " + gs.getEmail())
                .collect(Collectors.toList());
    }

    public List<GreenSpaces> getSortedGreenSpacesByEmail(String email) {
        List<GreenSpaces> greenSpacesList = repository.getGreenSpaces();
        List<GreenSpaces> filteredList = greenSpacesList.stream()
                .filter(gs -> gs.getEmail().equals(email))
                .collect(Collectors.toList());
        sortingStrategy.sort(filteredList);
        return filteredList;
    }

//    private SortingStrategy loadSortingStrategy() {
//        Properties props = new Properties();
//        try (FileInputStream fis = new FileInputStream("src/main/resources/config.properties")) {
//            props.load(fis);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        String strategy = props.getProperty("algorithm");
//
//        switch (strategy) {
//            case "QuickSortStrategy":
//                return new QuickSortStrategy();
//            case "BubbleSortStrategy":
//            default:
//                return new BubbleSortStrategy();
//        }
//    }
    private SortingStrategy loadSortingStrategy() {
        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream("src/main/resources/config.properties")) {
            props.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
            // Return a default sorting strategy in case of an error
            return new BubbleSortStrategy(); // Or any other default strategy you prefer
        }

        String strategy = props.getProperty("algorithm");

        switch (strategy) {
            case "QuickSortStrategy":
                return new QuickSortStrategy();
            case "BubbleSortStrategy":
            default:
                return new BubbleSortStrategy();
        }
    }


    public List<GreenSpaces> getSortedGreenSpaces() {
        List<GreenSpaces> greenSpacesList = repository.getGreenSpaces();
        sortingStrategy.sort(greenSpacesList);
        return greenSpacesList;
    }

    public List<String> getSortedGreenSpacesNamesAndEmails() {
        /*return getSortedGreenSpaces().stream()
                .map(gs -> gs.getName() + " | " + gs.getEmail())
                .collect(Collectors.toList());
                .collect(Collectors.toList());/*
         */
        List<String> newList = new ArrayList<>();
        for (GreenSpaces gp : getSortedGreenSpaces()){
            newList.add(gp.getName() + " | " + gp.getEmail());
        }
        Collections.sort(newList);
        return newList;
    }
    public boolean removeGreenSpace(String name) {
        // Get the green space to remove by its name
        GreenSpaces greenSpaceToRemove = getGreenSpaceByName(name);

        // If the green space exists, remove it from the repository
        if (greenSpaceToRemove != null) {
            // Attempt to remove the green space from the repository
            boolean removed = repository.removeGreenSpaceByName(name);
            if (removed) {
                return true; // Return true if removal is successful
            }
        }
        return false; // Return false if removal failed or if green space does not exist
    }


}

