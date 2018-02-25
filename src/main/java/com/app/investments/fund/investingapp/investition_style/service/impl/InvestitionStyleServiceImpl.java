package com.app.investments.fund.investingapp.investition_style.service.impl;

import com.app.investments.fund.investingapp.investition_style.dto.InvestitionStyleDto;
import com.app.investments.fund.investingapp.investition_style.executor.InvestitionStyleExecutor;
import com.app.investments.fund.investingapp.investition_style.model.InvestitionStyle;
import com.app.investments.fund.investingapp.investition_style.service.InvestitionStyleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author Krzysztof Lakomy
 */
@Service
public class InvestitionStyleServiceImpl implements InvestitionStyleService{

	@Autowired
	private InvestitionStyleExecutor investitionStyleExecutor;

	@Override
	public List<InvestitionStyleDto> getAllInvestitionStyles() {
		return investitionStyleExecutor.getAllInvestitionStyles();
	}

	@Override
	public InvestitionStyle findInvestitionStyleById(@RequestParam String investitionStyleId) {
		return investitionStyleExecutor.findInvestitionStyleById(investitionStyleId);
	}
}
