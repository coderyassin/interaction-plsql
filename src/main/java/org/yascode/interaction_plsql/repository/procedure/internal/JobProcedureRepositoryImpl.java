package org.yascode.interaction_plsql.repository.procedure.internal;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import org.yascode.interaction_plsql.entity.Job;
import org.yascode.interaction_plsql.repository.procedure.JobProcedureRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class JobProcedureRepositoryImpl implements JobProcedureRepository {

    private final JdbcTemplate jdbcTemplate;
    private static final String PKG_JOB = "JOB_PKG";

    public JobProcedureRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Job> getJob(String jobId) {
        List<Job> results = executeFunctionWithCursor(
                "get_job",
                Map.of("v_JOB_ID", jobId)
        );
        return results.isEmpty() ? Optional.empty() : Optional.of(results.get(0));
    }

    private List<Job> executeFunctionWithCursor(String functionName, Map<String, Object> params) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_JOB)
                .withFunctionName(functionName)
                .returningResultSet("result", this::mapJob);

        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        params.forEach(paramSource::addValue);

        Map<String, Object> result = jdbcCall.execute(paramSource);
        return (List<Job>) result.get("result");
    }

    private Job mapJob(ResultSet rs, int rowNum) throws SQLException {
        Job job = new Job();
        job.setJobId(rs.getString("job_id"));
        job.setJobTitle(rs.getString("job_title"));
        job.setMinSalary(rs.getBigDecimal("min_salary"));
        job.setMaxSalary(rs.getBigDecimal("max_salary"));
        return job;
    }
}
