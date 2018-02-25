package com.app.investments.fund.investingapp.single_investition.executor;

import com.app.investments.fund.base.executor.BaseExecutor;
import com.app.investments.fund.investingapp.fund.entity.executor.FundExecutor;
import com.app.investments.fund.investingapp.fund.type.executor.FundTypeExecutor;
import com.app.investments.fund.investingapp.single_investition.dto.SingleInvestitionDto;
import com.app.investments.fund.investingapp.single_investition.model.SingleInvestition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Krzysztof Lakomy
 */
@Component
public class SingleInvestitionExecutor extends BaseExecutor<SingleInvestition, SingleInvestitionDto> {

	@Autowired
	private FundTypeExecutor fundTypeExecutor;

	@Autowired
	private FundExecutor fundExecutor;

	@Override
	protected SingleInvestitionDto mapEntityToDto(SingleInvestition entity) {
	    SingleInvestitionDto investitionDto = new SingleInvestitionDto();
	    investitionDto.setInvestitionId(entity.getInvestitionId());
	    investitionDto.setFundType((entity.getFundType()) != null ? entity.getFundType().getTypeName() : null);
	    investitionDto.setFundName((entity.getFund()) != null ? entity.getFund().getFundName() : null);
	    if(entity.getMoneyInvestment() != null) {
			investitionDto.setMoneyAmount(String.valueOf(entity.getMoneyInvestment()));
		}
		if(entity.getInvestmentPercentage() != null) {
			investitionDto.setPercentage(String.valueOf(entity.getInvestmentPercentage()));
		}
	    return investitionDto;

	}

	@Override
	protected SingleInvestition mapDtoToEntity(SingleInvestitionDto dto){
	    SingleInvestition singleInvestition = new SingleInvestition();
	    singleInvestition.setInvestitionId(dto.getInvestitionId());
	    singleInvestition.setFundType(fundTypeExecutor.getFundTypeByName(dto.getFundType()));
	    singleInvestition.setFund(fundExecutor.getFundByFundName(dto.getFundName()));
	    if(dto.getMoneyAmount()!= null && !dto.getMoneyAmount().isEmpty()) {
			singleInvestition.setMoneyInvestment(Double.valueOf(dto.getMoneyAmount()));
		}
		if(dto.getPercentage()!= null && !dto.getPercentage().isEmpty()) {
			singleInvestition.setInvestmentPercentage(Double.valueOf(dto.getPercentage()));
		}
	    return singleInvestition;

	}

	public List<SingleInvestitionDto> mapToDtoList(List<SingleInvestition> singleInvestitionList){
		List<SingleInvestitionDto> singleSingleInvestitionDtoList = new ArrayList<>();
		if(!singleInvestitionList.isEmpty()){
			singleSingleInvestitionDtoList = singleInvestitionList.stream()
					.map(investition -> mapEntityToDto(investition))
					.collect(Collectors.toList());Collectors.toList();
		}
		return singleSingleInvestitionDtoList;
	}

	public List<SingleInvestition> mapToEntityList(List<SingleInvestitionDto> singleInvestitionDtoList){
		List<SingleInvestition> singleSingleInvestitionList = new ArrayList<>();
		if(!singleInvestitionDtoList.isEmpty()){
			singleSingleInvestitionList = singleInvestitionDtoList.stream()
					.map(investitionDto -> mapDtoToEntity(investitionDto))
					.collect(Collectors.toList());Collectors.toList();
		}
		return singleSingleInvestitionList;
	}

}
