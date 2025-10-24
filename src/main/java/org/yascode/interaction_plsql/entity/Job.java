package org.yascode.interaction_plsql.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "JOBS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Job {

    @Id
    @Column(name = "JOB_ID")
    @Lob
    private String jobId;

    @Column(name = "JOB_TITLE")
    @Lob
    private String jobTitle;

    @Column(name = "MIN_SALARY")
    private BigDecimal minSalary;

    @Column(name = "MAX_SALARY")
    private BigDecimal maxSalary;
}
