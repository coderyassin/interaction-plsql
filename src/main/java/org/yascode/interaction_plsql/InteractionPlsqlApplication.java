package org.yascode.interaction_plsql;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.yascode.interaction_plsql.dao.IEmployeeDao;
import org.yascode.interaction_plsql.entity.Employee;
import org.yascode.interaction_plsql.repository.CountryRepository;
import org.yascode.interaction_plsql.repository.EmployeeInfoRepository;
import org.yascode.interaction_plsql.repository.EmployeeRepository;
import org.yascode.interaction_plsql.repository.JobRepository;
import org.yascode.interaction_plsql.repository.procedure.JobProcedureRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class InteractionPlsqlApplication implements CommandLineRunner {

	private final IEmployeeDao employeeDao;
	private final EmployeeRepository employeeRepository;
	private final CountryRepository countryRepository;
	private final JobRepository jobRepository;
	private final JobProcedureRepository jobProcedureRepository;
	private final EmployeeInfoRepository employeeInfoRepository;

    public InteractionPlsqlApplication(IEmployeeDao employeeDao,
                                       EmployeeRepository employeeRepository,
                                       CountryRepository countryRepository,
                                       JobRepository jobRepository,
                                       JobProcedureRepository jobProcedureRepository,
									   EmployeeInfoRepository employeeInfoRepository) {
        this.employeeDao = employeeDao;
        this.employeeRepository = employeeRepository;
        this.countryRepository = countryRepository;
        this.jobRepository = jobRepository;
        this.jobProcedureRepository = jobProcedureRepository;
        this.employeeInfoRepository = employeeInfoRepository;
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
		//jobRepository.addJob("DR_AM", "Director of Administration", BigDecimal.valueOf(30000L), BigDecimal.valueOf(50000L));}
		//jobProcedureRepository.getJob("DR_AM");
		//employeeInfoRepository.findAll();
	}
}
