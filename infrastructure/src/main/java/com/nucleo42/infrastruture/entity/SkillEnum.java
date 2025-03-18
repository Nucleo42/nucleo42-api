package com.nucleo42.entity;

import com.nucleo42.exception.SkillDoesNotExistException;

public enum SkillEnum {
    COMUNICACAO(1, "Comunicação"),
    TRABALHO_EQUIPE(2, "Trabalho em equipe"),
    ANGULAR(3, "Angular"),
    JAVA(4, "Java"),
    RESOLUCAO_PROBLEMA(5, "Resolução de problemas"),
    JAVASCRIPT(6, "Javascript"),
    TYPESCRIPT(7, "Typescript");

    private long id;
    private String skill;

    SkillEnum(long id, String skill) {
        this.id = id;
        this.skill = skill;
    }

    public long getId() {
        return id;
    }

    public String getSkill() {
        return skill;
    }

    public static String fromId(long id) {
        for (SkillEnum s : SkillEnum.values()) {
            if (s.getId() == id) {
                return s.getSkill();
            }
        }
        throw new SkillDoesNotExistException(id);
    }

}
