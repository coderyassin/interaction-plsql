package org.yascode.interaction_plsql.dao.internal;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.dialect.OracleTypes;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.yascode.interaction_plsql.dao.IEmployeeDao;
import org.yascode.interaction_plsql.entity.Employee;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@Slf4j
public class EmployeeDao implements IEmployeeDao {
    private static final String PKG_EMPLOYEE = "EMPLOYEE_PKG";
    private final JdbcTemplate jdbcTemplate;

    public EmployeeDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addEmployee(Long employeeId, String firstName, String lastName, String email, String phoneNumber, String hireDate, BigDecimal salary, BigDecimal commissionPct, String jobId, Long departmentId, Long managerId) {
        jdbcTemplate.execute((Connection connection) -> {
            CallableStatement cs = connection.prepareCall(
                    "{call " + PKG_EMPLOYEE + ".add_employee(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}"

            );
            cs.setLong(1, employeeId);
            cs.setString(2, firstName);
            cs.setString(3, lastName);
            cs.setString(4, email);
            cs.setString(5, phoneNumber);
            cs.setString(6, hireDate);
            cs.setString(7, jobId);
            cs.setBigDecimal(8, salary);
            cs.setBigDecimal(9, commissionPct);
            cs.setLong(10, managerId);
            cs.setLong(11, departmentId);
            cs.execute();
            return null;
        });
    }

    @Override
    public void deleteEmployee(Long employeeId) {
        jdbcTemplate.execute((Connection connection) -> {
            CallableStatement cs = connection.prepareCall(
                    "{call " + PKG_EMPLOYEE + ".delete_employee(?)}"
            );
            cs.setLong(1, employeeId);
            cs.execute();
            return null;
        });
    }

    @Override
    public Optional<Employee> getEmployee(Long employeeId) {
        return jdbcTemplate.execute((Connection connection) -> {
            CallableStatement cs = connection.prepareCall(
                    "{? = call " + PKG_EMPLOYEE + ".get_employee(?)}"
            );

            cs.registerOutParameter(1, OracleTypes.CURSOR);
            cs.setLong(2, employeeId);

            cs.execute();

            ResultSet rs = (ResultSet) cs.getObject(1);
            if (rs.next()) {
                return Optional.ofNullable(mapResultSetToEmployee(rs));
            }
            return Optional.empty();
        });
    }

    @Override
    public List<Employee> employeesByDepartment(Long departmentId) {
        return jdbcTemplate.execute((Connection connection) -> {
            CallableStatement cs = null;
            ResultSet rs = null;
            List<Employee> employees = new ArrayList<>();

            try {
                cs = connection.prepareCall("{? = call " + PKG_EMPLOYEE + ".employees_by_department(?)}");

                cs.registerOutParameter(1, OracleTypes.CURSOR);

                cs.setLong(2, departmentId);

                cs.execute();

                rs = (ResultSet) cs.getObject(1);

                while (rs.next()) {
                    employees.add(mapResultSetToEmployee(rs));
                }

                log.info("Found {} employees for the {} department.",
                        employees.size(), departmentId);

                return employees;

            } catch (SQLException e) {
                log.error("SQL error while retrieving employees from the department: {}",
                        departmentId, e);

                return List.of();
            } finally {
                closeResources(rs, cs);
            }
        });
    }

    private Employee mapResultSetToEmployee(ResultSet rs) throws SQLException {
        Employee employee = new Employee();

        employee.setEmployeeId(rs.getLong("EMPLOYEE_ID"));
        employee.setFirstName(rs.getString("FIRST_NAME"));
        employee.setLastName(rs.getString("LAST_NAME"));
        employee.setEmail(rs.getString("EMAIL"));
        employee.setPhoneNumber(rs.getString("PHONE_NUMBER"));
        employee.setHireDate(rs.getString("HIRE_DATE"));
        employee.setSalary(rs.getBigDecimal("SALARY"));
        employee.setCommissionPct(rs.getBigDecimal("COMMISSION_PCT"));

        return employee;
    }
    private void closeResources(ResultSet rs, CallableStatement cs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                log.error("Error while closing the ResultSet", e);
            }
        }
        if (cs != null) {
            try {
                cs.close();
            } catch (SQLException e) {
                log.error("Error while closing the CallableStatement", e);
            }
        }
    }
}
