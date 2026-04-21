package team.project_2026.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="name", unique=true)
    private String name;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UseCase> useCases;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CRC> crcs;


    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUseCases(List<UseCase> useCases){this.useCases = useCases;}

    public User getUserId() {
        return user;
    }

    public void setUserId(User user) {
        this.user = user;
    }
}
