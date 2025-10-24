package org.yascode.interaction_plsql;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.yascode.interaction_plsql.dao.IEmployeeDao;
import org.yascode.interaction_plsql.entity.Employee;
import org.yascode.interaction_plsql.repository.CountryRepository;
import org.yascode.interaction_plsql.repository.EmployeeRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class InteractionPlsqlApplication implements CommandLineRunner {

	private final IEmployeeDao employeeDao;
	private final EmployeeRepository employeeRepository;
	private final CountryRepository countryRepository;

    public InteractionPlsqlApplication(IEmployeeDao employeeDao,
									   EmployeeRepository employeeRepository,
                                       CountryRepository countryRepository) {
        this.employeeDao = employeeDao;
        this.employeeRepository = employeeRepository;
        this.countryRepository = countryRepository;
    }

    public static void main(String[] args) {
		SpringApplication.run(InteractionPlsqlApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//employeeDao.addEmployee(397L, "Omar","Mellouki", "omar.mellouki@gmail.com" ,"+212660127845" , "2019-05-23", BigDecimal.valueOf(3500L), null, "SH_CLERK", 50L, 124L);
		//employeeDao.deleteEmployee(397L);
		//Optional<Employee> employee = employeeDao.getEmployee(197L);
		//List<Employee> employees = employeeDao.employeesByDepartment(50L);
		var a = 150;
	}
}
