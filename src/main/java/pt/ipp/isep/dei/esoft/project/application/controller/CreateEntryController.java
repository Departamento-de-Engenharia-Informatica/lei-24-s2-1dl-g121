package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.repository.Agenda;
import pt.ipp.isep.dei.esoft.project.repository.ToDoList;

public class CreateEntryController {
    private Agenda agenda;
    private ToDoList toDoList;

    public CreateEntryController(Agenda agenda, ToDoList toDoList) {
        this.agenda = agenda;
        this.toDoList = toDoList;
    }


}
