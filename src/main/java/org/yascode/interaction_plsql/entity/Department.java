package org.yascode.interaction_plsql.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "DEPARTMENTS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Department {

    @Id
    @Column(name = "DEPARTMENT_ID")
    private Long departmentId;

    @Column(name = "DEPARTMENT_NAME")
    @Lob
    private String departmentName;

    @ManyToOne
    @JoinColumn(name = "MANAGER_ID")
    private Employee manager;

    @OneToOne
    @JoinColumn(name = "LOCATION_ID")
    private Location location;
}
