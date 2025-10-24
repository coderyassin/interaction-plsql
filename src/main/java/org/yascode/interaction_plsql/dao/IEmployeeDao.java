package org.yascode.interaction_plsql.dao;

import org.yascode.interaction_plsql.entity.Employee;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface IEmployeeDao {

    void addEmployee(Long employeeId, String firstName, String lastName, String email, String phoneNumber, String hireDate, BigDecimal salary, BigDecimal commissionPct, String jobId, Long departmentId, Long managerId);

    void deleteEmployee(Long employeeId);

    Optional<Employee> getEmployee(Long employeeId);

    List<Employee> employeesByDepartment(Long departmentId);
}

