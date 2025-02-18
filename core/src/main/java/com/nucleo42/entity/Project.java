package com.nucleo42.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Project {
    private UUID id;
    private String name;
    private String description;
    private int vacancies;
    private String goal;
    private List<Technology> technologies;
    private List<Member> members;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Project(UUID id, String name, String description, int vacancies, String goal, List<Technology> technologies, List<Member> members, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.vacancies = vacancies;
        this.goal = goal;
        this.technologies = technologies;
        this.members = members;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Project(String name, String description, int vacancies, String goal, List<Technology> technologies, List<Member> members) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.description = description;
        this.vacancies = vacancies;
        this.goal = goal;
        this.technologies = technologies;
        this.members = members;
        this.createdAt = LocalDateTime.now();
    }

    public Project() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getVacancies() {
        return vacancies;
    }

    public void setVacancies(int vacancies) {
        this.vacancies = vacancies;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public List<Technology> getTechnologies() {
        return technologies;
    }

    public void setTechnologies(List<Technology> technologies) {
        this.technologies = technologies;
    }

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return vacancies == project.vacancies && Objects.equals(id, project.id) && Objects.equals(name, project.name) && Objects.equals(description, project.description) && Objects.equals(goal, project.goal) && Objects.equals(technologies, project.technologies) && Objects.equals(members, project.members) && Objects.equals(createdAt, project.createdAt) && Objects.equals(updatedAt, project.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, vacancies, goal, technologies, members, createdAt, updatedAt);
    }

    @Override
    public String toString() {
        return "Project {" +
                "\nid=" + id +
                ", \nname='" + name + '\'' +
                ", \ndescription='" + description + '\'' +
                ", \nvacancies=" + vacancies +
                ", \ngoal='" + goal + '\'' +
                ", \ntechnologies=" + technologies +
                ", \nmembers=" + members +
                ", \ncreatedAt=" + createdAt +
                ", \nupdatedAt=" + updatedAt +
                "\n}";
    }
}
