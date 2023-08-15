package com.inclusioncloud.rest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.inclusioncloud.rest.core.entities.InputNumbersDTO;
import com.inclusioncloud.rest.core.usecases.CalculateUseCase;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class RestApplicationTests {
	
	
	@Autowired
	private CalculateUseCase calculate;
	
	
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
	

}
