package org.yascode.interaction_plsql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.yascode.interaction_plsql.entity.Region;

public interface RegionRepository extends JpaRepository<Region, Long> {
}
