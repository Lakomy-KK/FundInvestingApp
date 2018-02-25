package com.app.investments.fund.investingapp.fund.type.repository;

import com.app.investments.fund.investingapp.fund.type.model.FundType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Krzysztof Lakomy
 */
@Repository
public interface FundTypeRepository extends JpaRepository<FundType, Long>{

	Optional<FundType> findByTypeName(String name);
	Optional<FundType> findByTypeCode(Character code);
}
