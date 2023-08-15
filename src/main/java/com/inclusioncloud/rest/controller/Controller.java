package com.inclusioncloud.rest.controller;

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
import com.inclusioncloud.rest.core.entities.ResponseDTO;
import com.inclusioncloud.rest.core.usecases.CalculateUseCase;

@RestController
@RequestMapping("/api")
public class Controller {
	
	private Logger LOG = LoggerFactory.getLogger(Controller.class);
    
    @Autowired
    private CalculateUseCase calculateUseCase;

    @GetMapping("/")
    public ResponseEntity<ResponseDTO> calculate(
    		@RequestParam(value = "numX") Double numX,
    		@RequestParam(value = "numY") Double numY,
    		@RequestParam(value = "numN") Double numN 
    		) {
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
			return ResponseEntity.notFound().build();
		}
    }

    @PostMapping("/post/") 
    public ResponseEntity<ResponseDTO> calculate(@Validated @RequestBody InputNumbersDTO inputBody) {
    	ResponseDTO response = new ResponseDTO();
    	try {
    		Optional<ResponseDTO> output;
        	LOG.debug("Calculated by Post: {}", inputBody);
        	response.setResponse(calculateUseCase.ejecutar(inputBody));
        	output = Optional.of(response);
			return ResponseEntity.ok().body(output.get());
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().build();
		}
    }
    
}
