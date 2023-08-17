package com.inclusioncloud.rest.core.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * This class describes each successful operation 
 * @author jonathan 
 */
@Entity
@Table(name = "operation")
public class ModOperation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "number_x", nullable = false)
	private Double numberX;
	
	@Column(name = "number_y", nullable = false)
	private Double numberY;
	
	@Column(name = "number_n", nullable = false)
	private Double numberN;
	
	@Column(nullable = false)
	private Double result;
	
	public ModOperation() {
	}
	
	public ModOperation(Double numberX, Double numberY, Double numberN, Double result) {
		super();
		this.numberX = numberX;
		this.numberY = numberY;
		this.numberN = numberN;
		this.result = result;
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

	public Double getResult() {
		return result;
	}

	public void setResult(Double result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "ModOperation [id=" + id + ", numberX=" + numberX + ", numberY=" + numberY + ", numberN=" + numberN
				+ ", result=" + result + "]";
	}
	
}
