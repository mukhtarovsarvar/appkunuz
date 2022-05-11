package com.company.repository;

import com.company.entity.RegionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RegionRepository extends JpaRepository<RegionEntity,Integer> {


    Optional<RegionEntity> findByKey(String key);

    void deleteByKey(String key);
}
