package org.yascode.interaction_plsql.repository.exception;

public class JobNotFoundException extends RuntimeException {

    private final String jobId;

    public JobNotFoundException(String jobId) {
        super("Job not found with ID: " + jobId);
        this.jobId = jobId;
    }

    public String getJobId() {
        return jobId;
    }
}
