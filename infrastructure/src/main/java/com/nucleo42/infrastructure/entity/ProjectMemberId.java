package com.nucleo42.infrastructure.entity;

import java.io.Serializable;
import java.util.UUID;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class ProjectMemberId implements Serializable {
    private UUID projectId;
    private UUID userId;
    private Long roleId;
}
