package com.testwo.Matrices;

import java.util.Arrays;
import java.util.Random;

/**
 * represents a matrix, can do matrix multiplication (2 different
 * implementations) and print out the matrix Initialised with random doubles,
 * but can be zeroed
 * 
 *
 */

public class Matrix {

	private int numRows;
	private int numCols;

	double[] theMatrixArray; // stored in row major

	/**
	 * creating the random number generator declared here so can have a seed so
	 * reproducible, without making all matrices contain the same numbers
	 */
	private static Random randGenerator = new Random(1113);

	/**
	 * constructor that initialises the array with random doubles
	 * 
	 * @param rowNum:
	 *            the number of rows in the matrix
	 * @param colNum:
	 *            the number of columns in the matrix
	 * @param value:
	 *            true if want to fill with random number, false if want to fill
	 *            with zeros
	 */
	public Matrix(int rowNum, int colNum, boolean value) {
		numCols = colNum;
		numRows = rowNum;

		// initialising array with random numbers, doubles between 0.0 and 1.0
		theMatrixArray = new double[numCols * numRows];

		if (value) {
			for (int i = 0; i < theMatrixArray.length; i++) {
				theMatrixArray[i] = randGenerator.nextDouble();
			}
		}
		// putting numbers in the array

	}

	/**
	 * returns the matrix as a string
	 */
	public String toString() {
		StringBuilder strMatrix = new StringBuilder();
		for (int i = 0; i < numRows; i++) {
			strMatrix.append("\n");
			// every row starts on a new line
			for (int j = 0; j < numCols; j++) {
				strMatrix.append(theMatrixArray[numCols * i + j] + "\t");
			}
		}
		return strMatrix.toString();
	}

	/**
	 * attempts to do this matrix times otherMatrix, if can't do due to columns
	 * and rows being incompatible returns an Illegal Argument Exception. The
	 * slower method of multiplication result[i][j]=this[i][k]other[k][j] For
	 * this methods, i is the outer loop, then j, and k is the inner loop
	 * 
	 * @param otherMat:
	 * @return
	 */
	public Matrix tryMatMultIJK(Matrix otherMat) {
		Matrix multMatrix;// the matrix which will hold the result in
		if (this.numCols == otherMat.numRows) {// Can do matrix multiplication
			// creating the new matrix
			multMatrix = new Matrix(this.numRows, otherMat.numCols, false);
			matMultIJK(otherMat, multMatrix);
			return multMatrix;

		} else {
			throw new IllegalArgumentException("These matrices have incompatible rows/columns to be multiplied");
		}

	}

	/**
	 * performs the matrix multiplication
	 * 
	 * @param otherMat
	 * @param multMatrix
	 */
	private void matMultIJK(Matrix otherMat, Matrix multMatrix) {
		// i: thisMatRow
		for (int i = 0; i < this.numRows; i++) {
			// computes one row of the new matrix
			multOneRowIJK(otherMat, i, multMatrix);
		}
		return;
	}

	/**
	 * Computes one row of the result matrix(multMat)
	 * 
	 * @param otherMat
	 * @param thisRow
	 * @param multMat
	 */
	private void multOneRowIJK(Matrix otherMat, int thisRow, Matrix multMat) {
		double sum;// matrix multiplication requires multiplication of elements
					// and summing them together, this holds that sum
		double thisValue;// stores the value currently looking at in this matrix
		double otherValue;// stores values currently looking at in other matrix
		int value1 = this.numCols * thisRow;
		int value2 = multMat.numCols * thisRow;
		for (int j = 0; j < otherMat.numCols; j++) {
			sum = 0;// reset to zero for each new, end matrix value to be
					// calculated
			for (int k = 0; k < this.numCols; k++) {
				// getting the values to multiply
				thisValue = this.theMatrixArray[value1 + k];
				otherValue = otherMat.theMatrixArray[otherMat.numCols * k + j];
				// incrementing sum
				sum = sum + thisValue * otherValue;
			}
			// updating the matrix with the new values
			multMat.theMatrixArray[value2 + j] = sum;
		}
		return;
	}

	/**
	 * changes all of the elements of the matrix to be zero
	 */
	public void zeroMatrix() {
		Arrays.fill(theMatrixArray, 0.0);
	}

	/**
	 * attempts to do this matrix times otherMatrix, if can't do due to columns
	 * and rows being incompatible returns an Illegal Argument Exception. The
	 * faster method of multiplication result[i][j]=this[i][k]other[k][j] For
	 * this methods, i is the outer loop, then k, and j is the inner loop
	 * 
	 * @param otherMat:
	 * @return
	 */
	public Matrix tryMatMultIKJ(Matrix otherMat) throws MatrixMultiplicationInvalid {
		if (this.numCols == otherMat.numRows) {// Can do matrix multiplication
			return matMultIKJ(otherMat);

		} else {
			throw new MatrixMultiplicationInvalid("These matrices have incompatible rows/columns to be multiplied");
		}
	}

	/**
	 * computes the matrix multiplication and returns the result
	 * 
	 * @param otherMat
	 * @return
	 */
	private Matrix matMultIKJ(Matrix otherMat) {
		// creating the new matrix
		// the matrix which will hold the result in
		Matrix multMatrix = new Matrix(this.numRows, otherMat.numCols, false);
		// zero the matrix
		multMatrix.zeroMatrix();

		// i: thisMatRow
		for (int i = 0; i < this.numRows; i++) {
			// computes one row of the new matrix
			multOneRowIKJ(otherMat, i, multMatrix);
		}
		return multMatrix;
	}

	/**
	 * Computes one row of the result matrix(multMat)
	 * 
	 * @param otherMat
	 * @param thisRow
	 * @param multMat
	 */
	private void multOneRowIKJ(Matrix otherMat, int thisRow, Matrix multMatrix) {
		double thisValue;// stores the value currently looking at in this matrix
		double otherValue;// stores values currently looking at in other matrix
		int value1 = this.numCols * thisRow;
		int value2 = multMatrix.numCols * thisRow;

		for (int k = 0; k < this.numCols; k++) {
			// need to do this step less times than in ijk method as no longer
			// in the inner loop
			thisValue = this.theMatrixArray[value1 + k];
			for (int j = 0; j < otherMat.numCols; j++) {
				// getting the values to multiply
				otherValue = otherMat.theMatrixArray[otherMat.numCols * k + j];
				multMatrix.theMatrixArray[value2 + j] += thisValue * otherValue;
			}
		}
	}

}
