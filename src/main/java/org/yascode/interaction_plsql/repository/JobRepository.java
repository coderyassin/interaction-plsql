package org.yascode.interaction_plsql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.yascode.interaction_plsql.entity.Job;

import java.math.BigDecimal;

public interface JobRepository extends JpaRepository<Job, String> {

    @Procedure(procedureName = "JOB_PKG.add_job")
    void addJob(@Param("v_JOB_ID") String jobId,
                @Param("v_JOB_TITLE") String jobTitle,
                @Param("v_MIN_SALARY") BigDecimal minSalary,
                @Param("v_MAX_SALARY") BigDecimal maxSalary);
}
