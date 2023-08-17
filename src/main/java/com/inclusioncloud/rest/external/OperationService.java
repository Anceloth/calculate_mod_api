package com.inclusioncloud.rest.external;

import org.springframework.data.repository.CrudRepository;

import com.inclusioncloud.rest.core.entities.ModOperation;

public interface OperationService extends CrudRepository<ModOperation, Long>{
	 

}
