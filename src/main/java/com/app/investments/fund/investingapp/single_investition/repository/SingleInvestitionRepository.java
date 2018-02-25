package com.app.investments.fund.investingapp.single_investition.repository;

import com.app.investments.fund.investingapp.single_investition.model.SingleInvestition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Krzysztof Lakomy
 */
@Repository
public interface SingleInvestitionRepository extends JpaRepository<SingleInvestition, Long> {
}
