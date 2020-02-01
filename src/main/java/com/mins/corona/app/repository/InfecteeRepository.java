package com.mins.corona.app.repository;

import com.mins.corona.app.entity.Infectee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InfecteeRepository extends JpaRepository<Infectee, Long> {
}
