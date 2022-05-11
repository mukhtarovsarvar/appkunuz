package com.company.repository;

import com.company.entity.ComentaryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComentRepository extends JpaRepository<ComentaryEntity,Integer> {

}
