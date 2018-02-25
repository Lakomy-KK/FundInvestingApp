package com.app.investments.fund.investingapp.single_investition.dto;

import com.app.investments.fund.base.dto.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Krzysztof Lakomy
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SingleInvestitionDto extends BaseDto {
	private Long investitionId;
	private String fundType;
	private String fundName;
	private String moneyAmount;
	private String percentage;
}
