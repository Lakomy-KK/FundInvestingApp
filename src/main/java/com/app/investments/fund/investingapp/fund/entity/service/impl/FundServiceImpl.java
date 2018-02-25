package com.app.investments.fund.investingapp.fund.entity.service.impl;

import com.app.investments.fund.investingapp.fund.entity.dto.FundDto;
import com.app.investments.fund.investingapp.fund.entity.executor.FundExecutor;
import com.app.investments.fund.investingapp.fund.entity.model.Fund;
import com.app.investments.fund.investingapp.fund.entity.service.FundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Krzysztof Lakomy
 */
@Service
public class FundServiceImpl implements FundService {

	@Autowired
	private FundExecutor fundExecutor;

	@Override
	public List<FundDto> getAllFunds() {
		return fundExecutor.getAllFunds();
	}

	@Override
	public FundDto findByFundId(Long fundId) {
		return fundExecutor.findFundDtoByFundId(fundId);
	}

	@Override
	public Fund findByFundName(String fundName) {
		return fundExecutor.getFundByFundName(fundName);
	}
}
