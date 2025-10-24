package org.yascode.interaction_plsql.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "EMPLOYEES")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee {

    @Id
    @Column(name = "EMPLOYEE_ID")
    private Long employeeId;

    @Column(name = "FIRST_NAME")
    @Lob
    private String firstName;

    @Column(name = "LAST_NAME")
    @Lob
    private String lastName;

    @Column(name = "EMAIL")
    @Lob
    private String email;

    @Column(name = "PHONE_NUMBER")
    @Lob
    private String phoneNumber;

    @Column(name = "HIRE_DATE")
    @Lob
    private String hireDate;

    @Column(name = "SALARY")
    private BigDecimal salary;

    @Column(name = "COMMISSION_PCT")
    private BigDecimal commissionPct;

    @ManyToOne
    @JoinColumn(name = "JOB_ID")
    private Job job;

    @ManyToOne
    @JoinColumn(name = "DEPARTMENT_ID")
    private Department department;

    @ManyToOne
    @JoinColumn(name = "MANAGER_ID")
    private Employee manager;
}
