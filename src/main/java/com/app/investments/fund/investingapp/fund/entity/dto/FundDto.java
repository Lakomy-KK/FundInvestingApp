package com.app.investments.fund.investingapp.fund.entity.dto;

import com.app.investments.fund.base.dto.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Krzysztof Lakomy
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FundDto extends BaseDto {
	private Long fundId;
	private String fundName;
	private String fundType;
	private Character fundTypeCode;
}
