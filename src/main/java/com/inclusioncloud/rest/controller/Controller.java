package com.inclusioncloud.rest.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.inclusioncloud.rest.core.entities.InputNumbersDTO;
import com.inclusioncloud.rest.core.entities.ModOperation;
import com.inclusioncloud.rest.core.entities.ResponseDTO;
import com.inclusioncloud.rest.core.usecases.CalculateUseCase;
import com.inclusioncloud.rest.core.usecases.GetAllOperationsUseCase;

/**
 * Controller which exposes the endpoints calculate/GET & calculate/POST
 * 
 * @author jonathan
 *
 */
@RestController
@RequestMapping("/api")
public class Controller {

	private Logger LOG = LoggerFactory.getLogger(Controller.class);

	@Autowired
	private CalculateUseCase calculateUseCase;
	
	@Autowired
	private GetAllOperationsUseCase getAlloperations;

	@GetMapping("/")
	public ResponseEntity<ResponseDTO> calculate(
			@RequestParam(value = "numX", required = true) Double numX,
			@RequestParam(value = "numY", required = true) Double numY,
			@RequestParam(value = "numN", required = true) Double numN) {
		Optional<ResponseDTO> output;
		try {
			LOG.debug("Getting Params X: ", numX, numY, numN);

			InputNumbersDTO input = new InputNumbersDTO(numX, numY, numN);
			ResponseDTO response = new ResponseDTO();
			response.setResponse(calculateUseCase.ejecutar(input));
			output = Optional.of(response);
			return ResponseEntity.ok().body(output.get());

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().build();
		}
	}

	@PostMapping("/post/")
	public ResponseEntity<ResponseDTO> calculate(@Validated @RequestBody(required = true) InputNumbersDTO inputBody) {
		ResponseDTO response = new ResponseDTO();
		try {
			Optional<ResponseDTO> output;
			LOG.debug("Calculating by Post: {}", inputBody);
			if (inputBody.getNumberX() != null && inputBody.getNumberY() != null && inputBody.getNumberN() != null) {
				response.setResponse(calculateUseCase.ejecutar(inputBody));
				output = Optional.of(response);
				return ResponseEntity.ok().body(output.get());
			}
			else {
				return ResponseEntity.badRequest().build();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().build();
		}
	}
	
	 @GetMapping("/operations/")
	 public ResponseEntity<List<ModOperation>> getOperations() {
		 List<ModOperation> operations = new ArrayList<>();
		 try {
			Optional<List<ModOperation>> output;
			operations = getAlloperations.ejecutar(null);
			output = Optional.of(operations);
			return ResponseEntity.ok().body(output.get());
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().build();
		}
	 }

}
