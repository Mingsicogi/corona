package com.mins.corona.app.repository;

import com.mins.corona.app.entity.Infectee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InfecteeRepository extends JpaRepository<Infectee, Long> {

//    Page<Infectee> findAllByPaging(Pageable pageable);  // page=0&size=20&sort=name,desc&sort=address.city

}
