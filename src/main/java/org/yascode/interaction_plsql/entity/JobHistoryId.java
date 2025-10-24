package org.yascode.interaction_plsql.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class JobHistoryId implements Serializable {

    @Column(name = "EMPLOYEE_ID")
    private Long employeeId;

    @Column(name = "START_DATE")
    @Lob
    private String startDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JobHistoryId that = (JobHistoryId) o;
        return Objects.equals(employeeId, that.employeeId) &&
                Objects.equals(startDate, that.startDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeId, startDate);
    }
}
