<<<<<<< HEAD:infrastructure/src/main/java/com/nucleo42/infrastruture/entity/ProjectMemberId.java
package com.nucleo42.infrastruture.entity;
=======
package com.nucleo42.entity;
>>>>>>> 9d084426bfa1de7675a8c4e8c3dc73412a31ea9a:infrastructure/src/main/java/com/nucleo42/entity/ProjectMemberId.java

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class ProjectMemberId implements Serializable {
    private UUID projectId;
    private UUID userId;
    private Long roleId;
}