package com.testwo.Matrices;

import java.util.Random;
/* represents a matrix, can do matrix multiplication and print out the matrix
 * Initialised with random doubles, but can be zeroed
 */

public class Matrix {

	private int numRows;
	private int numCols;

	private double[] theMatrixArray; // stored in row major

	// creating the random number generator
	// declared here so can have a seed so reproducible, without making all
	// matrices contain the same numbers
	private static Random randGenerator = new Random(1113);

	// constructor that initialises the array with random doubles
	public Matrix(int rowNum, int colNum) {
		numCols = colNum;
		numRows = rowNum;

		// initialising array with random numbers
		theMatrixArray = new double[numCols * numRows];
		// putting numbers in the array
		for (int i = 0; i < theMatrixArray.length; i++) {
			theMatrixArray[i] = randGenerator.nextDouble();
		}
	}

	// prints out the matrix in row column form
	public void printMatrix() {
		for (int i = 0; i < numRows; i++) {
			System.out.println();
			// every row starts on a new line
			for (int j = 0; j < numCols; j++) {
				System.out.printf("%.2f  ", theMatrixArray[numCols * i + j]);

			}
		}

	}

	// attempts to do this matrix times otherMatrix, if can't do due to columns
	// and
	// rows being incompatible returns an
	// MatrixMultplicationInvlaid error
	// The slower method of multiplication
	public Matrix tryMatMultIJK(Matrix otherMat) throws MatrixMultiplicationInvalid {
		Matrix multMatrix;// the matrix which will hold the result in
		if (this.numCols == otherMat.numRows) {// Can do matrix multiplication
			// creating the new matrix
			multMatrix = new Matrix(this.numRows, otherMat.numCols);
			matMultIJK(otherMat, multMatrix);
			return multMatrix;

		} else {
			throw new MatrixMultiplicationInvalid("These matrices have incompatible rows/columns to be multiplied");
		}

	}

	public void matMultIJK(Matrix otherMat, Matrix multMatrix) {
		// i: thisMatRow
		for (int i = 0; i < this.numRows; i++) {
			// computes one row of the new matrix
			multOneRowIJK(otherMat, i, multMatrix);
		}
		return;
	}

	// computes one row of the matrix (the end result matrix after
	// multiplication)
	public void multOneRowIJK(Matrix otherMat, int thisRow, Matrix multMat) {
		double sum;// matrix multiplication requires multiplication of elements
					// and summing them together, this holds that sum
		double thisValue;// stores the value currently looking at in this matrix
		double otherValue;// stores values currently looking at in other matrix
		for (int j = 0; j < otherMat.numCols; j++) {
			sum = 0;// reset to zero for each new, end matrix value to be
					// calculated
			for (int k = 0; k < this.numCols; k++) {
				// getting the values to multiply
				thisValue = this.theMatrixArray[this.numCols * thisRow + k];
				otherValue = otherMat.theMatrixArray[otherMat.numCols * k + j];
				// incrementing sum
				sum = sum + thisValue * otherValue;
			}
			// updating the matrix with the new values
			multMat.theMatrixArray[multMat.numCols * thisRow + j] = sum;
		}
		return;
	}

	// changes all of the elements of the matrix to be zero
	public void zeroMatrix() {
		for (int i = 0; i < theMatrixArray.length; i++) {
			theMatrixArray[i] = 0.0;
		}
	}

	// does looping i,k,j rather than i,j,k
	// Matrix multiplication is quicker this way
	public Matrix tryMatMultIKJ(Matrix otherMat) throws MatrixMultiplicationInvalid {
		if (this.numCols == otherMat.numRows) {// Can do matrix multiplication
			return matMultIKJ(otherMat);

		} else {
			throw new MatrixMultiplicationInvalid("These matrices have incompatible rows/columns to be multiplied");
		}
	}

	public Matrix matMultIKJ(Matrix otherMat) {
		// creating the new matrix
		// the matrix which will hold the result in
		Matrix multMatrix = new Matrix(this.numRows, otherMat.numCols);
		// zero the matrix
		multMatrix.zeroMatrix();

		// i: thisMatRow
		for (int i = 0; i < this.numRows; i++) {
			// computes one row of the new matrix
			multOneRowIKJ(otherMat, i, multMatrix);
		}
		return multMatrix;
	}

	public void multOneRowIKJ(Matrix otherMat, int thisRow, Matrix multMatrix) {
		double thisValue;// stores the value currently looking at in this matrix
		double otherValue;// stores values currently looking at in other matrix

		for (int k = 0; k < this.numCols; k++) {
			// need to do this step less times than in ijk method as no longer
			// in the inner loop
			thisValue = this.theMatrixArray[this.numCols * thisRow + k];
			for (int j = 0; j < otherMat.numCols; j++) {
				// getting the values to multiply
				otherValue = otherMat.theMatrixArray[otherMat.numCols * k + j];
				multMatrix.theMatrixArray[multMatrix.numCols * thisRow + j] += thisValue * otherValue;
			}
		}
	}

}
