package com.app.investments.fund.investingapp.investition_style.executor;

import com.app.investments.fund.base.exception.ResourceNotFoundException;
import com.app.investments.fund.base.executor.BaseExecutor;
import com.app.investments.fund.investingapp.investition_style.dto.InvestitionStyleDto;
import com.app.investments.fund.investingapp.investition_style.model.InvestitionStyle;
import com.app.investments.fund.investingapp.investition_style.repository.InvestitionStyleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Krzysztof Lakomy
 */
@Component
public class InvestitionStyleExecutor extends BaseExecutor<InvestitionStyle, InvestitionStyleDto> {

	@Autowired
	private InvestitionStyleRepository investitionStyleRepository;

	@Override
	protected InvestitionStyleDto mapEntityToDto(InvestitionStyle entity) {

		InvestitionStyleDto investitionStyleDto = new InvestitionStyleDto();
		investitionStyleDto.setInvestitionStyleId(entity.getInvestitionStyleId());
		if (entity.getStyleName() != null) {
			investitionStyleDto.setStyleName(entity.getStyleName().getStyleName());
		}
			investitionStyleDto.setInvestingFractionPL(entity.getInvestingFractionPL());
			investitionStyleDto.setInvestingFractionFR(entity.getInvestingFractionFR());
			investitionStyleDto.setInvestingFractionCash(entity.getInvestingFractionCash());

			return investitionStyleDto;
	}

	public List<InvestitionStyleDto> getAllInvestitionStyles(){
		return investitionStyleRepository.findAll().stream()
				.map(style -> mapEntityToDto(style)).collect(Collectors.toList());
	}

	public InvestitionStyle findInvestitionStyleById(String investitionStyle) {
		InvestitionStyle returnInvestitionStyle = new InvestitionStyle();
		if (investitionStyle != null && !investitionStyle.isEmpty()) {
			Long investitionStyleId = Long.valueOf(investitionStyle);
			returnInvestitionStyle = Optional.ofNullable(investitionStyleRepository.findByInvestitionStyleId(investitionStyleId))
							.orElseThrow(() -> new ResourceNotFoundException("No Investition Style Found !"));
		}
		return returnInvestitionStyle;
	}
}
