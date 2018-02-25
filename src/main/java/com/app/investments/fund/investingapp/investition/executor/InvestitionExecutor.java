package com.app.investments.fund.investingapp.investition.executor;
import com.app.investments.fund.base.date_utils.DateUtils;
import com.app.investments.fund.base.exception.ResourceNotFoundException;
import com.app.investments.fund.investingapp.investition.repository.InvestitionRepository;
import com.app.investments.fund.investingapp.investition_style.executor.InvestitionStyleExecutor;
import com.app.investments.fund.investingapp.single_investition.model.SingleInvestition;
import java.time.LocalDate;
import com.app.investments.fund.investingapp.single_investition.dto.SingleInvestitionDto;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.app.investments.fund.base.executor.BaseExecutor;
import com.app.investments.fund.investingapp.investition.dto.InvestitionDto;
import com.app.investments.fund.investingapp.investition.model.Investition;
import com.app.investments.fund.investingapp.single_investition.executor.SingleInvestitionExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Krzysztof Lakomy
 */
@Component
public class InvestitionExecutor extends BaseExecutor<Investition, InvestitionDto> {

	@Autowired
	private InvestitionRepository investitionRepository;

	@Autowired
	private SingleInvestitionExecutor singleInvestitionExecutor;

	@Override
	protected InvestitionDto mapEntityToDto(Investition entity) {
	    InvestitionDto investitionDto = new InvestitionDto();
	    investitionDto.setInvestitionId(entity.getInvestitionId());
	    investitionDto.setInvestitionDate(DateUtils.convertLocalDate2String(entity.getInvestitionDate()));
	    investitionDto.setMoneyNotInvested(String.valueOf(entity.getMoneyNotInvested()));

	    if(!entity.getSingleInvestitions().isEmpty()) {
			investitionDto.setSingleInvestitionDtoList(singleInvestitionExecutor.mapToDtoList(entity.getSingleInvestitions()));
		}
	    return investitionDto;
	}

	@Override
	protected Investition mapDtoToEntity(InvestitionDto dto){
	    Investition investition = new Investition();
	    investition.setInvestitionId(dto.getInvestitionId());
	    if(dto.getInvestitionDate() != null && !dto.getInvestitionDate().isEmpty()) {
			investition.setInvestitionDate(DateUtils.convertString2LocalDate(dto.getInvestitionDate()));
		}
	    investition.setSingleInvestitions(singleInvestitionExecutor.mapToEntityList(dto.getSingleInvestitionDtoList()));
	    investition.setMoneyNotInvested(Double.valueOf(dto.getMoneyNotInvested()));
	    return investition;

	}

	public InvestitionDto getInvestitionByDate(LocalDate investitionDate){
		Investition investition;
		List<Investition> investitions = investitionRepository.findByInvestitionDate(investitionDate);
		if(investitions.isEmpty()){
					throw new ResourceNotFoundException("No Fund Found !");
		}else{
			 investition = investitions.get(investitions.size()-1);
		}
		return mapEntityToDto(investition);
	}

	public InvestitionDto saveInvestition(Investition investition){
		 investitionRepository.save(investition);
		 return getInvestitionByDate(investition.getInvestitionDate());
	}

}
