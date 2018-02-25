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
public class InvestitionStyleDto extends BaseDto {
	private Long investitionStyleId;
	private String styleName;
	private Double investingFractionPL;
	private Double investingFractionFR;
	private Double investingFractionCash;
}
