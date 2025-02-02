package com.nucleo42.entity;

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

    public Project(String name, String description, int vacancies, String goal, List<Technology> technologies, List<Member> members) {
        this.name = name;
        this.description = description;
        this.vacancies = vacancies;
        this.goal = goal;
        this.technologies = technologies;
        this.members = members;
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

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return vacancies == project.vacancies && Objects.equals(id, project.id) && Objects.equals(name, project.name) && Objects.equals(description, project.description) && Objects.equals(goal, project.goal) && Objects.equals(technologies, project.technologies) && Objects.equals(members, project.members);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, vacancies, goal, technologies, members);
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", vacancies=" + vacancies +
                ", goal='" + goal + '\'' +
                ", technologies=" + technologies +
                ", members=" + members +
                '}';
    }
}
