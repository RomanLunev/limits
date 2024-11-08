package org.example.repository;

import org.example.entity.UserLimit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
public interface LimitRepository extends JpaRepository<UserLimit, Long> {

  Optional<UserLimit> findByUserId(Long userId);

  @Modifying
  @Query("update UserLimit l set l.amount = :amount")
  int resetAllAmount(@Param("amount") BigDecimal amount);
}
