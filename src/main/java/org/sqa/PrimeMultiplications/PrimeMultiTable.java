package org.sqa.PrimeMultiplications;

import java.util.Scanner;

/**
 * Prime multiplication table
 *
 */
public class PrimeMultiTable {
	
	public static int N;	// number of requested prime numbers
	static int[] primeNumbersArray;
	public static int filledNumberInArray = 0;
	static int[][] multiplTable;
	
	// reads user input and returns it as a String
	public String getUserInput() {
		Scanner input = new Scanner(System.in);
		System.out.println("Enter quantity prime numbers: ");
		return input.nextLine();
	}
	
	// checks if a number passed to it is prime
	public boolean isPrimeNumber(int number) {
		
		if (number <= 1)
			return false;	// 1 is not a prime number

		if (number ==2)
			return true;	// 2 is only even prime number
		
		if (number % 2 == 0)
			return false;	// excluding all even numbers
		
		for (int i = 2; i < number; i++) {
			if (number % i == 0)
				return false;
		}
		return true;
	}
	
	// loops through all numbers to find the prime ones
	public void findPrimeNumbers() {
		int p = 0;
		while (filledNumberInArray < N) {
			if (isPrimeNumber(p)) {
				addPrimeNumberToArray(p);
			}
			p += 1;
		}
		
	}
	
	public void addPrimeNumberToArray(int primeNumber) {
		primeNumbersArray[filledNumberInArray] = primeNumber;
		filledNumberInArray += 1;
	}
	
	public void printOutMultiplicationTable() {
		String formatOut = "";
		formatOut = "%" + formatOutputPrimeMultiplicationTable(N) + "d";
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.printf(formatOut, multiplTable[i][j]);
			}
			System.out.println("\n");
		}
	}
	
	// calculates how much spacing is required between numbers based on how much the largest number takes
	public int formatOutputPrimeMultiplicationTable(int n) {
		int numDigits = 1;
		
		if (n < 6)
			numDigits = 3; 
		else if (n >= 6  && n < 13)
			numDigits = 4;
		else if (n >= 13 && n < 26)
			numDigits = 5;
		else if (n >= 26 && n < 67)
			numDigits = 6;
		else if (n >= 68)
			numDigits = 7;
		
		return numDigits;
	}
	
    public static void main(String[] args) {
    	PrimeMultiTable pmt = new PrimeMultiTable();
    	Boolean isCorrectInput = true;
    	
    	// checking if user input is an integer
    	try{
    		N = Integer.parseInt(pmt.getUserInput());
    	}catch(Exception e){
    		isCorrectInput = false;
    	}
    	
    	// checking if user input is within 1 and 100
    	if(isCorrectInput){
    		if(N<1||N>100){
    			isCorrectInput = false;
    		}
    	}
    	
    	// based on previous verifications, printing out error message if user input is wrong
    	if(!isCorrectInput){
    		System.out.println("Invalid input! The input should be an integer between 1 and 100.");
    		System.exit(1);
    	}
    	
    	primeNumbersArray = new int[N];
    	pmt.findPrimeNumbers();
    	
    	// filling out 2-dimensional array of multiplied values
    	multiplTable = new int[N][N];
		multiplTable[0][0] = multiplTable[0][0] = 1;
    	for(int i = 1; i < N; i++) {
    		multiplTable[0][i] = multiplTable[i][0] = primeNumbersArray[i-1];
    	}
    	for (int i = 1; i < N; i++) {
    	  	for (int j = 1; j < N; j++) {
    	  		multiplTable[i][j] = multiplTable[0][i] * multiplTable[j][0];
    	  	}
    	}
    	pmt.printOutMultiplicationTable();
    }
}
