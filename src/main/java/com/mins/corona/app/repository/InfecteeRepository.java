package com.mins.corona.app.repository;

import com.mins.corona.app.entity.Infectee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InfecteeRepository extends JpaRepository<Infectee, Long> {

}
