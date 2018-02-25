package com.app.investments.fund.investingapp.fund.type.dto;

import com.app.investments.fund.base.dto.BaseDto;
import com.app.investments.fund.investingapp.fund.entity.dto.FundDto;
import com.app.investments.fund.investingapp.single_investition.dto.SingleInvestitionDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Krzysztof Lakomy
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FundTypeDto  extends BaseDto{
	private Long fundTypeId;
	private String typeName;
	private Character typeCode;
	private List<FundDto> fundList;
	private List<SingleInvestitionDto> singleInvestitionDtoList;

}
