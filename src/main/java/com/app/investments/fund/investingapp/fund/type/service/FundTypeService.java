package com.app.investments.fund.investingapp.fund.type.service;

import com.app.investments.fund.investingapp.fund.type.model.FundType;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Krzysztof Lakomy
 */
public interface FundTypeService {
	FundType findFundTypeByFundTypeCode(@RequestParam Character fundTypeCode);
}
