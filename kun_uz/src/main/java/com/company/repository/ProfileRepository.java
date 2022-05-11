package com.company.repository;

import com.company.entity.ProfileEntity;
import com.company.enums.ProfileStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Optional;

public interface ProfileRepository extends JpaRepository<ProfileEntity,Integer> {

    Optional<ProfileEntity> findByEmail(String email);

    void deleteByEmail(String email);

    @Transactional
    @Modifying
    @Query(value = "update ProfileEntity set status = :status where  id = :id")
    void changeStatus(@Param("id") Integer userId, @Param("status") ProfileStatus active);
}
