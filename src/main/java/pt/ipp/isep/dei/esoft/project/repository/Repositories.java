package pt.ipp.isep.dei.esoft.project.repository;

import java.io.Serializable;

public class Repositories implements Serializable {

    private static Repositories instance;
    private OrganizationRepository organizationRepository;
    private AuthenticationRepository authenticationRepository;
    private SkillRepository skillRepository;
    private CollaboratorRepository collaboratorRepository;
    private JobRepository jobRepository;
    private TeamRepository createTeamRepository;
    private GreenSpacesRepository greenSpacesRepository;
    private ToDoList toDoList;
    private Agenda agenda;

    private Repositories() {
        organizationRepository = new OrganizationRepository();
        authenticationRepository = new AuthenticationRepository();
        skillRepository = new SkillRepository();
        collaboratorRepository = new CollaboratorRepository();
        jobRepository = new JobRepository();
        createTeamRepository = new TeamRepository();
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
    public void setOrganizationRepository(OrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }

    public AuthenticationRepository getAuthenticationRepository() {
        return authenticationRepository;
    }
    public void setAuthenticationRepository(AuthenticationRepository authenticationRepository) {
        this.authenticationRepository = authenticationRepository;
    }

    public SkillRepository getSkillRepository() { return skillRepository; }
    public void setSkillRepository(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    public CollaboratorRepository getCollaboratorRepository() {
        return collaboratorRepository;
    }
    public void setCollaboratorRepository(CollaboratorRepository collaboratorRepository) {
        this.collaboratorRepository = collaboratorRepository;
    }

    public JobRepository getJobRepository() {return jobRepository;}
    public void setJobRepository(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    public TeamRepository getTeamRepository() {return createTeamRepository;}
    public void setTeamRepository(TeamRepository teamRepository) {
        this.createTeamRepository = teamRepository;
    }

    public GreenSpacesRepository getGreenSpacesRepository() {return greenSpacesRepository;}
    public void setGreenSpacesRepository (GreenSpacesRepository greenSpacesRepository) {
        this.greenSpacesRepository = greenSpacesRepository;
    }

    public ToDoList getToDoList() {return toDoList;}
    public void setToDoList(ToDoList toDoList) {
        this.toDoList = toDoList;
    }

    public Agenda getAgenda() {return agenda;}
    public void setAgenda (Agenda agenda) {
        this.agenda = agenda;
    }
}