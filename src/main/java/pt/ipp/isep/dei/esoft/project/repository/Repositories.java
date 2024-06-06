package pt.ipp.isep.dei.esoft.project.repository;

import java.io.Serializable;

public class Repositories implements Serializable {

    private static Repositories instance;
    private final OrganizationRepository organizationRepository;
    private final AuthenticationRepository authenticationRepository;
    private final SkillRepository skillRepository;
    private final CollaboratorRepository collaboratorRepository;
    private final JobRepository jobRepository;
    private final CreateTeamRepository createTeamRepository;
    private GreenSpacesRepository greenSpacesRepository;
    private ToDoList toDoList;
    private Agenda agenda;

    private Repositories() {
        organizationRepository = new OrganizationRepository();
        authenticationRepository = new AuthenticationRepository();
        skillRepository = new SkillRepository();
        collaboratorRepository = new CollaboratorRepository();
        jobRepository = new JobRepository();
        createTeamRepository = new CreateTeamRepository();
        greenSpacesRepository = new GreenSpacesRepository();
        toDoList = new ToDoList();
        agenda = new Agenda();
    }

    public static Repositories getInstance() {
        if (instance == null) {
            synchronized (Repositories.class) {
                instance = new Repositories();
            }
        }
        return instance;
    }

    public OrganizationRepository getOrganizationRepository() {
        return organizationRepository;
    }

    public AuthenticationRepository getAuthenticationRepository() {
        return authenticationRepository;
    }

    public SkillRepository getSkillRepository() { return skillRepository; }

    public CollaboratorRepository getCollaboratorRepository() {
        return collaboratorRepository;
    }

    public JobRepository getJobRepository() {return jobRepository;}

    public CreateTeamRepository getCreateTeamRepository() {return createTeamRepository;}

    public GreenSpacesRepository getGreenSpacesRepository() {return greenSpacesRepository;}

    public ToDoList getToDoList() {return toDoList;}

    public Agenda getAgenda() {return agenda;}

    public void setToDoList(ToDoList toDoList) {
        this.toDoList = toDoList;
    }

    public void setAgenda (Agenda agenda) {
        this.agenda = agenda;
    }

    public void setGreenSpacesRepository (GreenSpacesRepository greenSpacesRepository) {
        this.greenSpacesRepository = greenSpacesRepository;
    }
}