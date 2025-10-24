package org.yascode.interaction_plsql.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "JOB_HISTORY")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobHistory {

    @EmbeddedId
    private JobHistoryId id;

    @Column(name = "END_DATE")
    @Lob
    private String endDate;

    @ManyToOne
    @MapsId("employeeId")
    @JoinColumn(name = "EMPLOYEE_ID")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "JOB_ID")
    private Job job;

    @ManyToOne
    @JoinColumn(name = "DEPARTMENT_ID")
    private Department department;
}
