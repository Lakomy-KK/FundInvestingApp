package com.app.investments.fund.investingapp.investition.form;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Krzysztof Lakomy
 */
@Data
@NoArgsConstructor
public class InvestitionCalculationTable {
	List<InvestitionFundForm> investitionFundFormList;
}
