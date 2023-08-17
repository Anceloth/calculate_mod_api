package com.inclusioncloud.rest.core.usecases;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inclusioncloud.rest.core.entities.InputNumbersDTO;
import com.inclusioncloud.rest.core.entities.ModOperation;

/**
 * This describes the use case that have the business logic,
 * 
 * @author jonathan
 *
 */
@Component
public class CalculateUseCase implements UseCase<InputNumbersDTO, Double> {

	private Logger LOG = LoggerFactory.getLogger(CalculateUseCase.class);

	@Autowired
	SaveRecordUseCase saveRecord;

	private ModOperation operationToSave;

	/**
	 * @param InputNumbersDTO
	 * @return the maximum possible integer from 0 to N that has the remainder Y
	 *         modulo X. and call another UseCase in a thread called
	 *         `CalculateUseCase` in order to save successful operations in DB
	 */
	@Override
	public Double ejecutar(InputNumbersDTO input) throws Exception {
		Double result = 0.0;
		Thread thread = new Thread(saveRecord);
		for (Double i = input.getNumberN(); i > 0; i--) {
			if (i % input.getNumberX() == input.getNumberY()) {
				result = i;
				i = 0.0;
			}
		}
		operationToSave = new ModOperation(input.getNumberX(), input.getNumberY(), input.getNumberN(), result);
		saveRecord.ejecutar(operationToSave);
		thread.run();

		return result;
	}

}
