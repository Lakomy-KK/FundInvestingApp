package com.app.investments.fund.investingapp.fund.type.service.impl;

import com.app.investments.fund.investingapp.fund.type.executor.FundTypeExecutor;
import com.app.investments.fund.investingapp.fund.type.model.FundType;
import com.app.investments.fund.investingapp.fund.type.service.FundTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Krzysztof Lakomy
 */
@Service
public class FundTypeServiceImpl implements FundTypeService {

	@Autowired
	private FundTypeExecutor fundTypeExecutor;

	@Override
	public FundType findFundTypeByFundTypeCode(Character fundTypeCode) {
		return fundTypeExecutor.getFundTypeByCode(fundTypeCode);
	}
}
