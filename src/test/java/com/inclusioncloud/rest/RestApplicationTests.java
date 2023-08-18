package com.inclusioncloud.rest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.inclusioncloud.rest.core.entities.InputNumbersDTO;
import com.inclusioncloud.rest.core.entities.ModOperation;
import com.inclusioncloud.rest.core.usecases.CalculateUseCase;
import com.inclusioncloud.rest.external.OperationService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class RestApplicationTests {
	
	
	@Autowired
	private CalculateUseCase calculate;
	
	@Autowired
	private OperationService operationService;
	
	@Autowired
    private MockMvc mockMvc;
	
	
	/**
	 * This Unit test to calculate 1 input
	 */
	@Test
	void calculateTest() {
		
		InputNumbersDTO input = new InputNumbersDTO(7.0, 5.0, 12345.0);
		
		Double result;
		try {
			result = calculate.ejecutar(input);
			assertNotNull(result);
			assertEquals(12339.0, result);
		} catch (Exception e) {
			e.printStackTrace();
		}				
	}
	
	/**
	 * This Test calculates a list of inputs
	 */
	@Test
	void CalculateListOfValues() {
		List<InputNumbersDTO> numberList = new ArrayList<>();
		numberList.add(new InputNumbersDTO(7.0, 5.0, 12345.0));
		numberList.add(new InputNumbersDTO(5.0, 0.0, 4.0));
		numberList.add(new InputNumbersDTO(10.0, 5.0, 15.0));
		numberList.add(new InputNumbersDTO(17.0, 8.0, 54321.0));
		numberList.add(new InputNumbersDTO(499999993.0, 9.0, 1000000000.0));
		numberList.add(new InputNumbersDTO(10.0, 5.0, 187.0));
		numberList.add(new InputNumbersDTO(2.0, 0.0, 999999999.0));
		
		Double[] responses = {12339.0, 0.0, 15.0, 54306.0, 999999995.0, 185.0, 999999998.0};
		
		for (int i = 0; i < numberList.size(); i++) {
			InputNumbersDTO input = numberList.get(i);
			Double result;
			try {
				result = calculate.ejecutar(input);
				assertNotNull(result);
				assertEquals(responses[i], result);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	
	/**
	 * E2E Test
	 * Test Controller for getOperations
	 */
	@Test
	void getOperationsList() {
		List<ModOperation> operations = new ArrayList<>();
		operations.add(new ModOperation(10.0,5.0, 187.0, 185.0));
		operations.add(new ModOperation(2.0,0.0, 999999999.0, 999999998.0));
		operationService.saveAll(operations);
		
		Map<String, Object> credentials = new HashMap<>();
		credentials.put("name", "jonathan");
		credentials.put("password", "jonathan");
		credentials.put("roles", "manager");
		
		try {
				mockMvc.perform(get("/api/operations/")
						.with(SecurityMockMvcRequestPostProcessors.httpBasic("jonathan", "jonathan") )
				      .contentType(MediaType.APPLICATION_JSON))
				
				      .andExpect(status().isOk())
				      .andExpect(content()
				      .contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
		} catch (Exception e) {			
			e.printStackTrace();
		}	
	}
	
	/**
	 * E2E Test must get unauthorized status due there are not credentials to getOerations
	 */
	@Test
	void getOperationsWithoutCredentials() {
		
		try {
				mockMvc.perform(get("/api/operations/")
				      .contentType(MediaType.APPLICATION_JSON))
				      .andExpect(status().isUnauthorized());
		} catch (Exception e) {			
			e.printStackTrace();
		}	
	}
	

}
