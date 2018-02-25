package com.app.investments.fund.investingapp.fund.type.executor;

import com.app.investments.fund.base.exception.ResourceNotFoundException;
import com.app.investments.fund.base.executor.BaseExecutor;
import com.app.investments.fund.investingapp.fund.entity.executor.FundExecutor;
import com.app.investments.fund.investingapp.fund.type.dto.FundTypeDto;
import com.app.investments.fund.investingapp.fund.type.model.FundType;
import com.app.investments.fund.investingapp.fund.type.repository.FundTypeRepository;
import com.app.investments.fund.investingapp.single_investition.executor.SingleInvestitionExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author Krzysztof Lakomy
 */
@Component
public class FundTypeExecutor extends BaseExecutor<FundType, FundTypeDto> {

	@Autowired
	private FundTypeRepository fundTypeRepository;

	@Autowired
	private FundExecutor fundExecutor;

	@Autowired
	private SingleInvestitionExecutor singleInvestitionExecutor;


	@Override
	protected FundTypeDto mapEntityToDto(FundType entity) {
	    FundTypeDto fundTypeDto= new FundTypeDto();
	    fundTypeDto.setFundTypeId(entity.getFundTypeId());
	    fundTypeDto.setTypeName(entity.getTypeName());
	    fundTypeDto.setTypeCode(entity.getTypeCode());
	    if(!entity.getFundList().isEmpty()) {
			fundTypeDto.setFundList(fundExecutor.mapToDtoList(entity.getFundList()));
		}
		if(!entity.getSingleInvestitionList().isEmpty()) {
			fundTypeDto.setSingleInvestitionDtoList(singleInvestitionExecutor.mapToDtoList(entity.getSingleInvestitionList()));
		}
		return fundTypeDto;
	}

	public FundType getFundTypeByName(String name){
		Optional<FundType> fundtype =
				Optional.ofNullable(fundTypeRepository.findByTypeName(name))
						.orElseThrow(() -> new ResourceNotFoundException("No Fund Type Found !"));
		return fundtype.get();
	}

	public FundType getFundTypeByCode(Character typeCode){
		Optional<FundType> fundtype =
				Optional.ofNullable(fundTypeRepository.findByTypeCode(typeCode))
						.orElseThrow(() -> new ResourceNotFoundException("No Fund Type Found !"));
		return fundtype.get();
	}
}
