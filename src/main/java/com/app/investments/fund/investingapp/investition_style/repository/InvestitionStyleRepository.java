package com.app.investments.fund.investingapp.investition_style.repository;

import com.app.investments.fund.investingapp.investition_style.model.InvestitionStyle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Krzysztof Lakomy
 */
@Repository
public interface InvestitionStyleRepository extends JpaRepository<InvestitionStyle, Long>{
	InvestitionStyle findByInvestitionStyleId(Long investitionStyleId);
}
