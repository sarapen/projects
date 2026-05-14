package team.project_2026.model;

import java.util.*;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="user_name", unique=true)
    private String username;

    @Column(name="email")
    private String email;

    @Column(name="password")
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Project> projects = new ArrayList<>();


    @ManyToMany(mappedBy = "collaborators")
    private Set<Project> collabProjects = new HashSet<>();


    public String getUsername() {
        return username;
    }

    public String getPassword() {return password; }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public Set<Project> getCollabProjects() {
        return collabProjects;
    }

    public void setCollabProjects(Set<Project> collabProjects) {
        this.collabProjects = collabProjects;
    }

    public void addCollabProjects(Project collabProjects) {
        projects.add(collabProjects);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        return id == user.id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }

}
