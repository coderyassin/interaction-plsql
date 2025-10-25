package org.yascode.interaction_plsql.entity.view;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.relational.core.mapping.Column;
import lombok.*;
import org.hibernate.annotations.Immutable;

@Immutable
@Table("EMPLOYEES_INFO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeInfo {

    @Id
    @Column("EMPLOYEE_ID")
    private Long employeeId;

    @Column("FIRST_NAME")
    private String firstName;

    @Column("LAST_NAME")
    private String lastName;

    @Column("JOB_TITLE")
    private String jobTitle;

    @Column("DEPARTMENT_NAME")
    private String departmentName;

    @Column("STREET_ADDRESS")
    private String streetAddress;

    @Column("COUNTRY_NAME")
    private String countryName;

    @Column("REGION_NAME")
    private String regionName;
}
