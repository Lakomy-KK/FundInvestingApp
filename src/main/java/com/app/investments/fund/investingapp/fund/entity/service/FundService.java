package com.app.investments.fund.investingapp.fund.entity.service;

import com.app.investments.fund.investingapp.fund.entity.dto.FundDto;
import com.app.investments.fund.investingapp.fund.entity.model.Fund;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author Krzysztof Lakomy
 */
public interface FundService {
	List<FundDto> getAllFunds();
	FundDto findByFundId(@RequestParam Long fundId);
	Fund findByFundName(@RequestParam String fundName);
}
