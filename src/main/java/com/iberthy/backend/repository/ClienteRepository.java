package com.iberthy.backend.repository;

import com.iberthy.backend.domain.entity.ClienteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteModel, Long> {

    @Query("select c from ClienteModel c where c.id = :id and c.enabled = true")
    ClienteModel findByIdActive(Long id);

}
