package org.yascode.interaction_plsql.repository.procedure.internal;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import org.yascode.interaction_plsql.entity.Job;
import org.yascode.interaction_plsql.repository.exception.JobNotFoundException;
import org.yascode.interaction_plsql.repository.procedure.JobProcedureRepository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import static org.yascode.interaction_plsql.common.constants.OracleErrorCodes.JOB_NOT_FOUND;

@Repository
public class JobProcedureRepositoryImpl implements JobProcedureRepository {

    private final DataSource dataSource;
    private final JdbcTemplate jdbcTemplate;
    private static final String PKG_JOB = "JOB_PKG";

    public JobProcedureRepositoryImpl(DataSource dataSource,
                                      JdbcTemplate jdbcTemplate) {
        this.dataSource = dataSource;
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

    @Override
    public Job getJobById(String jobId) {
        try {
            SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(dataSource)
                    .withCatalogName(PKG_JOB)
                    .withFunctionName("GET_JOB_CURSOR_BY_ID")
                    .declareParameters(
                            new SqlParameter("v_JOB_ID", Types.VARCHAR),
                            new SqlOutParameter("return", Types.REF_CURSOR, jobRowMapper)
                    );
            Map<String, Object> result = simpleJdbcCall.execute(jobId);

            @SuppressWarnings("unchecked")
            Job job = ((java.util.List<Job>) result.get("return")).stream().findFirst().orElse(null);

            return job;

        } catch (DataAccessException e) {
            if (Objects.nonNull(e.getMessage()) && e.getMessage().contains(JOB_NOT_FOUND)) {
                throw new JobNotFoundException(jobId);
            }
            throw e;
        }
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

    private Job mapJob(ResultSet rs) throws SQLException {
        Job job = new Job();
        job.setJobId(rs.getString("job_id"));
        job.setJobTitle(rs.getString("job_title"));
        job.setMinSalary(rs.getBigDecimal("min_salary"));
        job.setMaxSalary(rs.getBigDecimal("max_salary"));
        return job;
    }

   private RowMapper<Job> jobRowMapper = (ResultSet rs, int rowNum) -> {
        Job job = new Job();
        job.setJobId(rs.getString("JOB_ID"));
        job.setJobTitle(rs.getString("JOB_TITLE"));
        job.setMinSalary(rs.getBigDecimal("MIN_SALARY"));
        job.setMaxSalary(rs.getBigDecimal("MAX_SALARY"));
        return job;
    };
}
