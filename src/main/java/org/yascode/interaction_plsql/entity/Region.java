package org.yascode.interaction_plsql.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "REGIONS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Region {

    @Id
    @Column(name = "REGION_ID")
    private Long regionId;

    @Column(name = "REGION_NAME")
    @Lob
    private String regionName;
}
