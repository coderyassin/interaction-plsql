package org.yascode.interaction_plsql.repository;

import org.springframework.data.repository.CrudRepository;
import org.yascode.interaction_plsql.entity.view.EmployeeInfo;

public interface EmployeeInfoRepository extends CrudRepository<EmployeeInfo, Long> {
}
