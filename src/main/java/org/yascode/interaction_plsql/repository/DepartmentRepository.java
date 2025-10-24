package org.yascode.interaction_plsql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.yascode.interaction_plsql.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
