package org.yascode.interaction_plsql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.yascode.interaction_plsql.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
