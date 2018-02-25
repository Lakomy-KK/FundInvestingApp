package com.app.investments.fund.investingapp.investition.service;

import com.app.investments.fund.investingapp.investition.dto.InvestitionDto;
import com.app.investments.fund.investingapp.investition.form.InvestitionFundForm;
import com.app.investments.fund.investingapp.investition.model.Investition;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author Krzysztof Lakomy
 */
@Service
public interface InvestitionService {

	InvestitionDto calculateInvestitionsByStyle(@RequestBody InvestitionFundForm investitionFundForm);
	InvestitionDto saveInvestition(@RequestBody Investition investition);

}
