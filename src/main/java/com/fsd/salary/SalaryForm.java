package com.fsd.salary;

/**
 * SalaryForm
 */
public class SalaryForm {

	private double salary;
	private int incrementTerm;
	private int deductionTerm;
	private double amountReceived;
	private double deductionAmount;
	private int year;
	private double incrementPercent;
	private double deductionPercent;
	
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public int getIncrementTerm() {
		return incrementTerm;
	}
	public void setIncrementTerm(int incrementTerm) {
		this.incrementTerm = incrementTerm;
	}
	public double getAmountReceived() {
		return amountReceived;
	}
	public void setAmountReceived(double amountReceived) {
		this.amountReceived = amountReceived;
	}
	public double getDeductionAmount() {
		return deductionAmount;
	}
	public void setDeductionAmount(double deductionAmount) {
		this.deductionAmount = deductionAmount;
	}
	public int getDeductionTerm() {
		return deductionTerm;
	}
	public void setDeductionTerm(int deductionTerm) {
		this.deductionTerm = deductionTerm;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public double getIncrementPercent() {
		return incrementPercent;
	}
	public void setIncrementPercent(double incrementPercent) {
		this.incrementPercent = incrementPercent;
	}
	public double getDeductionPercent() {
		return deductionPercent;
	}
	public void setDeductionPercent(double deductionPercent) {
		this.deductionPercent = deductionPercent;
	}	
}
