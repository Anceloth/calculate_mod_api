package com.inclusioncloud.rest.core.usecases;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inclusioncloud.rest.core.entities.ModOperation;
import com.inclusioncloud.rest.external.OperationService;

/**
 * This describes the use case that have the business logic, can be exceuted in a thread
 * @author jonathan
 *
 */
@Component
public class SaveRecordUseCase extends Thread implements UseCase<ModOperation, Integer>{
	
	private Logger LOG = LoggerFactory.getLogger(SaveRecordUseCase.class);
	
	@Autowired
	private OperationService operationRepository;
	
	/**
	 * It saves successful operations in DB 
	 * @param ModOperation
	 * @return 1 if success, 0 if not
	 */
	@Override
	public Integer ejecutar(ModOperation input) throws Exception {
		ModOperation operationSaved = this.operationRepository.save(input);
		LOG.debug("Operation Saved: {}", operationSaved);
		return operationSaved != null ? 1 : 0;
	}
	
}
