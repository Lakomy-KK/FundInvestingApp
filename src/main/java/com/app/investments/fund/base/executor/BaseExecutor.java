package com.app.investments.fund.base.executor;

import com.app.investments.fund.base.dto.BaseDto;
import com.app.investments.fund.base.model.BaseEntity;

/**
 * @author Krzysztof Lakomy
 */
public abstract class BaseExecutor<E extends BaseEntity, D extends BaseDto> {

	 protected D mapEntityToDto(E entity){
	 	return null;
	 }
	protected E mapDtoToEntity(D dto){
		return null;
	}
}
