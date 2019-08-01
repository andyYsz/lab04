package com.fsd.salary;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * SalaryHandler
 */
public class SalaryHandler {

	public static void main(String args[]) {	
		SalaryForm salaryForm = new SalaryForm();
		List<SalaryForm> salaryList = new ArrayList<SalaryForm>();
		Scanner input = new Scanner(System.in);			
		String incrementTerm;
		String deductionTerm;		

		System.out.println("Starting Salary : ");
		salaryForm.setSalary(input.nextDouble());
		
		System.out.println("Increment to be received in percent : ");
		salaryForm.setIncrementPercent(input.nextDouble());

		System.out.println("How frequently is increment received (quarterly, half-yearly, annually) : ");
		incrementTerm = input.next();
		salaryForm.setIncrementTerm(getTermCalc(incrementTerm));

		System.out.println("Deductions on Income in Percent : ");
		salaryForm.setDeductionPercent(input.nextDouble());
		
		System.out.println("How frequently are deductions done (quarterly, half-yearly, annually) : ");
		deductionTerm = input.next();
		salaryForm.setDeductionTerm(getTermCalc(deductionTerm));
		
		System.out.println("Prediction for (years) : ");
		salaryForm.setYear(input.nextInt());
		
		System.out.println("Increment Report");
		System.out.println("---------------------------------------------------------------------------------------------------------------------");
		System.out.println("Year                "+ "Starting Salary         "+"Number of Increments    "+"Increment %           "+"Increment Amount        ");
		System.out.println("---------------------------------------------------------------------------------------------------------------------");

		getIncrementReport(salaryForm, salaryList);

		System.out.println("\n");
		System.out.println("Deduction Report");
		System.out.println("---------------------------------------------------------------------------------------------------------------------");
		System.out.println("Year                "+ "Starting Salary         "+"Number of Deductions    "+"Deduction %             "+"Deduction Amount       ");
		System.out.println("---------------------------------------------------------------------------------------------------------------------");

		getDeductionReport(salaryForm, salaryList);

		System.out.println("\n");
		System.out.println("Prediction");
		System.out.println("---------------------------------------------------------------------------------------------------------------------");
		System.out.println("Year                "+ "Starting Salary         "+"Increment Amount    "+"Deduction Amount        "+"Salary Growth         ");
		System.out.println("---------------------------------------------------------------------------------------------------------------------");

		getPredictionReport(salaryList);
	}

	private static void getIncrementReport(SalaryForm salaryData, List<SalaryForm> salaryList) {		
		IntStream.rangeClosed(1, salaryData.getYear()).forEach(i -> {
			SalaryForm salaryForm = new SalaryForm();
			salaryForm.setSalary(salaryData.getSalary());
			
			incrementCalc(salaryForm, salaryData.getIncrementTerm(), salaryData.getIncrementPercent());
			deductionCalc(salaryForm, salaryData.getDeductionTerm(), salaryData.getDeductionPercent());
			
			System.out.println(i +"                   "+salaryForm.getSalary()+"                     "+salaryData.getIncrementTerm()+"                   "+salaryData.getIncrementPercent()+"                   "+salaryForm.getAmountReceived());
			salaryList.add(salaryForm);			
		
			salaryData.setSalary(getSalaryGrowth(salaryForm));
		});
	}

	private static void getDeductionReport(SalaryForm salaryData, List<SalaryForm> salaryList) {
		AtomicInteger atomicCount = new AtomicInteger(0);
		salaryList.forEach(salaryDet -> {
			atomicCount.incrementAndGet();
			System.out.println(atomicCount.get() +"                   "+salaryDet.getSalary()+"                   "+salaryData.getDeductionTerm()+"                   "+salaryData.getDeductionPercent()+"                   "+salaryDet.getDeductionAmount());
		});		
	}

	private static void getPredictionReport(List<SalaryForm> salaryList) {
		AtomicInteger atomic = new AtomicInteger(0);	
		salaryList.forEach(salaryDet -> {
			atomic.incrementAndGet();			
			Double salaryGrowth = getSalaryGrowth(salaryDet);	
			salaryGrowth = salaryGrowth - salaryDet.getSalary();
			System.out.println(atomic.get() +"                   "+salaryDet.getSalary()+"                   "+salaryDet.getAmountReceived()+"                   "+salaryDet.getDeductionAmount()+"           "+ Math.round(salaryGrowth));
		});
	}

	private static Double getSalaryGrowth (SalaryForm salaryForm) {
		Double salaryhike = salaryForm.getSalary() + salaryForm.getAmountReceived(); 
		Double salaryGrowth = salaryhike - salaryForm.getDeductionAmount();
		return (double) Math.round(salaryGrowth);
	}

	private static void incrementCalc(SalaryForm salaryForm, int noOfIncrement, double incrementPercent) {
		Double amount = (salaryForm.getSalary()*incrementPercent)/100;
		salaryForm.setAmountReceived(Math.round(amount)*noOfIncrement);	
	}

	private static void deductionCalc(SalaryForm salaryForm, int noOfDeduction, double decrementPercent) {
		Double amount = (salaryForm.getSalary()*decrementPercent)/100;
		salaryForm.setDeductionAmount(Math.round(amount)*noOfDeduction);		
	}

	private static int getTermCalc(String incrementPeriod) {
		int incrementCount = 0;
		if(incrementPeriod.equalsIgnoreCase("quarterly")) {
			incrementCount = 4;
		} else if(incrementPeriod.equalsIgnoreCase("half-yearly")) {
			incrementCount = 3;
		} else if(incrementPeriod.equalsIgnoreCase("annually")) {
			incrementCount = 1;
		}
		return incrementCount;
	}		
}
