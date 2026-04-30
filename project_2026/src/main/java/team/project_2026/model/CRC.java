package team.project_2026.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
public class CRC {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @Column(name="className")
    private String className;

    @Column(name="responsibilities")
    private String responsibilities;

    @Column(name="collaborations")
    private String collaborations;

    @ManyToMany
    @JoinTable(
            name = "usecase_crc",
            joinColumns = @JoinColumn(name = "crc_id"),
            inverseJoinColumns = @JoinColumn(name = "usecase_id")
    )
    private List<UseCase> useCases;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public Project getProject() {
        return project;
    }
    public void setProject(Project project) {
        this.project = project;
    }

    public String getClassName() {
        return className;
    }
    public void setClassName(String className) {
        this.className = className;
    }

    public String getResponsibilities() {
        return responsibilities;
    }
    public void setResponsibilities(String responsibilities) {
        this.responsibilities = responsibilities;
    }

    public String getCollaborations() {
        return collaborations;
    }
    public void setCollaborations(String collaborations) {
        this.collaborations = collaborations;
    }

    public List<UseCase> getUseCases() {return useCases;}
    public void setUseCases(List<UseCase> useCases) {this.useCases = useCases;}

    public void addUseCase(UseCase useCase) {
        if (!this.useCases.contains(useCase)) {
            this.useCases.add(useCase);
            useCase.getCrcCards().add(this);
        }
    }

}

