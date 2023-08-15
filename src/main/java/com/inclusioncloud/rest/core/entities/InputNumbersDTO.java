package com.inclusioncloud.rest.core.entities;

public class InputNumbersDTO {
	
	private Double numberX;
	private Double numberY;
	private Double numberN;
	
	public InputNumbersDTO() {
		
	}

	public InputNumbersDTO(Double numberX, Double numberY, Double numberN) {
		super();
		this.numberX = numberX;
		this.numberY = numberY;
		this.numberN = numberN;
	}

	public Double getNumberX() {
		return numberX;
	}

	public void setNumberX(Double numberX) {
		this.numberX = numberX;
	}

	public Double getNumberY() {
		return numberY;
	}

	public void setNumberY(Double numberY) {
		this.numberY = numberY;
	}

	public Double getNumberN() {
		return numberN;
	}

	public void setNumberN(Double numberN) {
		this.numberN = numberN;
	}

	@Override
	public String toString() {
		return "InputNumbersDTO [numberX=" + numberX + ", numberY=" + numberY + ", numberN=" + numberN + "]";
	}
	
	

}
