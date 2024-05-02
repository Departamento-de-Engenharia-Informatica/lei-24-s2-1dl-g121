package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.repository.AuthenticationRepository;

import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.SkillRepository;

import java.util.Optional;

public class RegisterSkillsController {

    private SkillRepository skillRepository;
    private AuthenticationRepository authenticationRepository;


    //Repository instances are obtained from the Repositories class
    public RegisterSkillsController() {
        getSkillRepository();
        getAuthenticationRepository();
    }

    //Allows receiving the repositories as parameters for testing purposes
    public RegisterSkillsController(SkillRepository skillRepository,
                                    AuthenticationRepository authenticationRepository) {
        this.skillRepository = skillRepository;
        this.authenticationRepository = authenticationRepository;
    }

    private SkillRepository getSkillRepository() {
        if (skillRepository == null) {
            Repositories repositories = Repositories.getInstance();

            // Get the SkillRepository
            skillRepository = repositories.getSkillRepository();
        }
        return skillRepository;
    }

    private AuthenticationRepository getAuthenticationRepository() {
        if (authenticationRepository == null) {
            Repositories repositories = Repositories.getInstance();

            // Get the AuthenticationRepository
            authenticationRepository = repositories.getAuthenticationRepository();
        }
        return authenticationRepository;
    }

    public Optional<Skill> registerSkill(String skillName) {
        Skill newSkill = new Skill(skillName);
        if (!skillRepository.getSkills().contains(newSkill)) {
            try{
                skillRepository.getSkills().add(newSkill);
            } catch (UnsupportedOperationException e){
                System.out.println("Error:" + e.getMessage());
            }
            return Optional.of(newSkill);
        }
        return Optional.empty();
    }

}
