package com.crud.accenture;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@ComponentScan
@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Long>{

}


