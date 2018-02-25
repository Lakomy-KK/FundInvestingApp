package com.app.investments.fund.investingapp.investition_style.service;

import com.app.investments.fund.investingapp.investition_style.dto.InvestitionStyleDto;
import com.app.investments.fund.investingapp.investition_style.model.InvestitionStyle;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author Krzysztof Lakomy
 */
public interface InvestitionStyleService {
	List<InvestitionStyleDto> getAllInvestitionStyles();
	InvestitionStyle findInvestitionStyleById(@RequestParam String investitionStyleId);
}
