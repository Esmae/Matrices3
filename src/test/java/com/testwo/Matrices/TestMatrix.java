/**
 * JUnit testing, aiming for high coverage for testing the Matrix class 
 * This testing only works with the seed 1113
 * Tests to within 0.0001 (delta)
 */
package com.testwo.Matrices;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestMatrix {
	
	private static Matrix actualMat1;
	private static Matrix actualMat2;
	
	/**
	 * Code executed before the first test method
	 *	calculating the actual matrices
	 * Matrices must be initialised here so they are only created once and therefore are the matrices expected(hopefully)
	 */
	@BeforeClass
	public static void setUpClass(){
		//Code executed before the first test method
		// calculating the actual matrices
		// Matrices must be initialised here so they are only created once and therefore are the matrices expected(hopefully)
		actualMat1 = new Matrix(3,3,true);
		actualMat2 = new Matrix(3,3,true);
	}

	
	private double[] expectMat1 = { 0.634130, 0.518029, 0.128930, 0.195159, 0.795229, 0.736806, 0.760579, 0.365630,//the expected value for the first matrix to 6dp
			0.400583 };
	private double[] expectMat2 = { 0.436682, 0.395167, 0.458079, 0.545870, 0.411616, 0.881489, 0.303486, 0.494078,//the expected value for the second matrix to 6dp
			0.285301 };
	private double[] expectMatMult4dp = { 0.59882, 0.52752, 0.78390, 0.74292, 0.76849, 1.00060, 0.65329, 0.64897, 0.78499 };//the expected matrix for the multiplication to 4dp

	/**
	 * Test method for
	 * {@link com.testwo.Matrices.Matrix#tryMatMultIJK(com.testwo.Matrices.Matrix)}.
	 * 
	 * @throws MatrixMultiplicationInvalid
	 */
	@Test
	public void testTryMatMultIJK() {
		//calculating the matrix from Matrix class
		Matrix actualMultMatIJK = actualMat1.tryMatMultIJK(actualMat2);

		// seeing if the matrices calculated for this particular case (the
		// specify 3x3 matrices) work
		Assert.assertArrayEquals(expectMat1, actualMat1.theMatrixArray, 0.0001);
		Assert.assertArrayEquals(expectMat2, actualMat2.theMatrixArray, 0.0001);
		Assert.assertArrayEquals(expectMatMult4dp, actualMultMatIJK.theMatrixArray, 0.0001);

	}

	/**
	 * Test method for {@link com.testwo.Matrices.Matrix#zeroMatrix()}.
	 */

	@Test
	public void testZeroMatrix() {
		actualMat1.zeroMatrix();
		//should change all the elements in the Matrix to zero
		for (int i = 0; i < actualMat1.theMatrixArray.length; i++) {
			Assert.assertEquals(0.0, actualMat1.theMatrixArray[i], 0.0001);
		}
	}

	/**
	 * Test method for
	 * {@link com.testwo.Matrices.Matrix#tryMatMultIKJ(com.testwo.Matrices.Matrix)}.
	 * 
	 * @throws MatrixMultiplicationInvalid
	 */
	@Test
	public void testTryMatMultIKJ(){
		// calculating the actual matrices from Matrix class
		Matrix actualMultMatIKJ = actualMat1.tryMatMultIKJ(actualMat2);

		// seeing if the matrices calculated for this particular case (the
		// specify 3x3 matrices) work
		Assert.assertArrayEquals(expectMat1, actualMat1.theMatrixArray, 0.0001);
		Assert.assertArrayEquals(expectMat2, actualMat2.theMatrixArray, 0.0001);
		Assert.assertArrayEquals(expectMatMult4dp, actualMultMatIKJ.theMatrixArray, 0.0001);
	}

}
