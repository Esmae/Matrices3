package com.testwo.Matrices;

/*
 * The class on which to perform a reference benchmark so different runs, run on different VMs can be compared. 
 */
import java.util.Random;

public class Reference {
	private static Random randGenerator = new Random(1113);
	private double[] myArray;

	//Initialise a 10,000 array with random doubles
	public Reference() {
		myArray = new double[10000];
		for (int i = 0; i < 10000; i++) {
			myArray[i] = randGenerator.nextDouble();
		}
	}

	//sum up all the numbers in the array
	public double addNum() {
		double sum = 0;
		for (int i = 0; i < myArray.length; i++) {
			sum += myArray[i];
		}
		return sum;
	}

}
