package com.app.investments.fund.investingapp.investition.dto;

import com.app.investments.fund.base.dto.BaseDto;
import com.app.investments.fund.investingapp.single_investition.dto.SingleInvestitionDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Krzysztof Lakomy
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvestitionDto extends BaseDto {
	private Long investitionId;
	private String investitionDate;
	private String moneyNotInvested;
	private List<SingleInvestitionDto> singleInvestitionDtoList;
}
