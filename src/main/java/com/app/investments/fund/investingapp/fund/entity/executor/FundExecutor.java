package com.app.investments.fund.investingapp.fund.entity.executor;

import com.app.investments.fund.base.exception.ResourceNotFoundException;
import com.app.investments.fund.base.executor.BaseExecutor;
import com.app.investments.fund.investingapp.fund.entity.dto.FundDto;
import com.app.investments.fund.investingapp.fund.entity.model.Fund;
import com.app.investments.fund.investingapp.fund.entity.repository.FundRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Krzysztof Lakomy
 */
@Component
public class FundExecutor extends BaseExecutor<Fund, FundDto>{

	@Autowired
	private FundRepository fundRepository;

	@Override
	protected FundDto mapEntityToDto(Fund entity) {
		FundDto dto = new FundDto();
		dto.setFundId(entity.getFundId());
		dto.setFundName(entity.getFundName());
		dto.setFundType((entity.getFundType()) != null ? entity.getFundType().getTypeName() : null);
		dto.setFundTypeCode((entity.getFundType()) != null ? entity.getFundType().getTypeCode() : null);
		return dto;
	}
	public List<FundDto> mapToDtoList(List<Fund> fundList){
		return fundList.stream().map(fund -> mapEntityToDto(fund))
				.collect(Collectors.toList());
	}

	public List<FundDto> getAllFunds(){
		return fundRepository.findAll().stream()
				.map(fund -> mapEntityToDto(fund))
				.collect(Collectors.toList());
	}

	public Fund getFundByFundName(String name){
		Optional<Fund> fund =
				Optional.ofNullable(fundRepository.findByFundName(name))
						.orElseThrow(() -> new ResourceNotFoundException("No Fund Found !"));
		return fund.get();
	}

	public FundDto findFundDtoByFundId(Long fundId) {
		return mapEntityToDto(fundRepository.findOne(fundId));
	}
}
