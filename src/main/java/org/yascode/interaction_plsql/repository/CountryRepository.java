package org.yascode.interaction_plsql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.yascode.interaction_plsql.entity.Country;

public interface CountryRepository extends JpaRepository<Country, String> {
}
