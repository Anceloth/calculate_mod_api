package com.inclusioncloud.rest.core.usecases;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inclusioncloud.rest.core.entities.ModOperation;
import com.inclusioncloud.rest.external.OperationService;

/**
 * This describes the use case that have the business logic
 * @author jonathan
 */
@Component
public class GetAllOperationsUseCase implements UseCase<String, List<ModOperation>>{
	
	@Autowired
	private OperationService operationService;
	
	/**
	 * It returns a List of all operations registered 
	 * @return List<ModOperation>
	 */
	@Override
	public List<ModOperation> ejecutar(String input) throws Exception {
		
		return (List<ModOperation>) this.operationService.findAll();
	}

}
