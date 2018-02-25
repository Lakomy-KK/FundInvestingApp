package com.app.investments.fund.investingapp.investition_style.dto;

import com.app.investments.fund.base.dto.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Krzysztof Lakomy
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvestitionMoneyDto extends BaseDto {
	private int plInvestingMoney;
	private int frInvestingMoney;
	private int cashInvestingMoney;
	private int notInvestedMoney;
}
