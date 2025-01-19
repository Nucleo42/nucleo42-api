package com.discord.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Project {
    private String name;
    private String description;
    private int vacancies;
    private String goal;
    private List<Technology> technologies;
    private List<Member> members;
}
