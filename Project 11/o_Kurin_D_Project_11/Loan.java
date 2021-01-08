/*
 * Class: CMSC214
 * Instructor: Dr. Mark Estep
 * Description: The client in (b) sends the annual interest rate, number of years,
 * and loan amount to the server and receives the monthly payment and total payment from the server in (a).
 * This file is for Loan class to hold the methods that calculate the monthly payment and total payment.
 * Due: 11/22/2018
 * I pledge that I have completed the programming assignment independently.
   I have not copied the code from a student or any source.
   I have not given my code to any student.
   Print your Name here: Derya O. Kurin
*/





package o_Kurin_D_Project_11;


public class Loan {
	private double annualRate;
	private int noOfYears;
	private double loanAmount;

	public Loan(double annualInterestRate, int numberOfYears,
		double loanAmount) {
		this.annualRate = annualInterestRate;
		this.noOfYears = numberOfYears;
		this.loanAmount = loanAmount;
	}

	//Getter and setters
	public double getAnnualInterestRate() {

		return annualRate;
	}

	public void setAnnualInterestRate(double annualInterestRate) {
		this.annualRate = annualInterestRate;
	}

	public int getNumberOfYears() {
		return noOfYears;
	}

	public void setNumberOfYears(int numberOfYears) {
		this.noOfYears = numberOfYears;
	}

	public double getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(double loanAmount) {
		this.loanAmount = loanAmount;
	}

	//Method to compute the monthly payment.
	public double calculateMonthlyPayment() {
		double monthlyInterestRate = annualRate / 1200;
		double monthlyPayment = loanAmount * monthlyInterestRate / (1 -
		(Math.pow(1 / (1 + monthlyInterestRate), noOfYears * 12)));
		return monthlyPayment;
	}

	//Method to get the total payment.
	public double calculateTotalPayment() {
		double totalPayment = calculateMonthlyPayment() * noOfYears * 12;
		return totalPayment;
	}

}
