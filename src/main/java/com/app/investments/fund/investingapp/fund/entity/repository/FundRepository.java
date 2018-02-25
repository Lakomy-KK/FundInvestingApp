package com.app.investments.fund.investingapp.fund.entity.repository;

import com.app.investments.fund.investingapp.fund.entity.model.Fund;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Krzysztof Lakomy
 */
@Repository
public interface FundRepository extends JpaRepository<Fund, Long> {
	Optional<Fund> findByFundName(String name);
}
