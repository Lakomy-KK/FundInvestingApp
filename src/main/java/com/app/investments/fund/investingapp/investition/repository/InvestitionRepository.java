package com.app.investments.fund.investingapp.investition.repository;

import com.app.investments.fund.investingapp.investition.model.Investition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * @author Krzysztof Lakomy
 */
@Repository
public interface InvestitionRepository extends JpaRepository<Investition, Long> {

	List<Investition> findByInvestitionDate(LocalDate investitionDate);
}
