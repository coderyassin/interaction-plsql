package org.yascode.interaction_plsql;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.yascode.interaction_plsql.dao.IEmployeeDao;
import org.yascode.interaction_plsql.entity.Job;
import org.yascode.interaction_plsql.event.publisher.AddJobPublisher;
import org.yascode.interaction_plsql.event.publisher.EventPublisher;
import org.yascode.interaction_plsql.repository.CountryRepository;
import org.yascode.interaction_plsql.repository.EmployeeInfoRepository;
import org.yascode.interaction_plsql.repository.EmployeeRepository;
import org.yascode.interaction_plsql.repository.JobRepository;
import org.yascode.interaction_plsql.repository.procedure.JobProcedureRepository;

import java.math.BigDecimal;

@SpringBootApplication
@EnableAsync
public class InteractionPlsqlApplication implements CommandLineRunner {

	private final IEmployeeDao employeeDao;
	private final EmployeeRepository employeeRepository;
	private final CountryRepository countryRepository;
	private final JobRepository jobRepository;
	private final JobProcedureRepository jobProcedureRepository;
	private final EmployeeInfoRepository employeeInfoRepository;
	private final EventPublisher eventPublisher;
	private final AddJobPublisher addJobPublisher;

    public InteractionPlsqlApplication(IEmployeeDao employeeDao,
                                       EmployeeRepository employeeRepository,
                                       CountryRepository countryRepository,
                                       JobRepository jobRepository,
                                       JobProcedureRepository jobProcedureRepository,
                                       EmployeeInfoRepository employeeInfoRepository,
                                       EventPublisher eventPublisher,
									   AddJobPublisher addJobPublisher) {
        this.employeeDao = employeeDao;
        this.employeeRepository = employeeRepository;
        this.countryRepository = countryRepository;
        this.jobRepository = jobRepository;
        this.jobProcedureRepository = jobProcedureRepository;
        this.employeeInfoRepository = employeeInfoRepository;
        this.eventPublisher = eventPublisher;
        this.addJobPublisher = addJobPublisher;
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
		//eventPublisher.publishEvent("Yassin Mellouki is the best");

		Job job = Job.builder()
				.jobId("DR_CY")
				.jobTitle("Director Of Cyber Security")
				.minSalary(BigDecimal.valueOf(50000L))
				.maxSalary(BigDecimal.valueOf(100000L))
				.build();

		addJobPublisher.publishEvent(job);

	}
}
