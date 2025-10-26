package org.yascode.interaction_plsql.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.yascode.interaction_plsql.entity.Job;

@RequestMapping("/api/jobs")
public interface JobApi {

    @GetMapping("/{id}")
    Job getJobById(@PathVariable String id);
}
