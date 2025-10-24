package org.yascode.interaction_plsql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.yascode.interaction_plsql.entity.Location;

public interface LocationRepository extends JpaRepository<Location, Long> {
}
