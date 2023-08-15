package com.inclusioncloud.rest.core.usecases;

import org.springframework.stereotype.Component;

import com.inclusioncloud.rest.core.entities.InputNumbersDTO;

@Component
public class CalculateUseCase implements UseCase<InputNumbersDTO, Double>{
	

	@Override
	public Double ejecutar(InputNumbersDTO input) throws Exception {
		for(Double i=input.getNumberN(); i>0 ; i--) {
			if(i%input.getNumberX()==input.getNumberY()) {
				return i;
			}
		}
		return 0.0;
	}

}
