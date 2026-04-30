package team.project_2026.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class UseCase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @Column(name="name")
    private String name;

    @Column(name="actors")
    private String actors;

    @Column(name="preconditions")
    private String preconditions;

    @Column(name="mainFlow")
    private String mainFlow;

    @Column(name="postconditions")
    private String postconditions;

    @ManyToMany(mappedBy = "useCases")
    private List<CRC> crcCards;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public Project getProject() {return project;}
    public void setProject(Project project) {this.project = project;}

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getActors() {
        return actors;
    }
    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getPreconditions() {
        return preconditions;
    }
    public void setPreconditions(String preconditions) {
        this.preconditions = preconditions;
    }


    public String getMainFlow() {
        return mainFlow;
    }
    public void setMainFlow(String mainFlow) {
        this.mainFlow = mainFlow;
    }

    public String getPostconditions() {
        return postconditions;
    }
    public void setPostconditions(String postconditions) {
        this.postconditions = postconditions;
    }

    public List<CRC> getCrcCards() {return crcCards;}
    public void setCrcCards(List<CRC> crcCards) {this.crcCards = crcCards;}

    public void addCrc(CRC crc) {
        if (!this.crcCards.contains(crc)) {
            this.crcCards.add(crc);
            crc.getUseCases().add(this);
        }
    }
}