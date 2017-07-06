package com.testwo.Matrices;

public class MatrixMultiplicationInvalid extends Exception {

	/**
	 * The error thrown if two matrices can't mathematically be multiplied
	 */
	private static final long serialVersionUID = -5939914518937400855L;

	public MatrixMultiplicationInvalid(String message) {
		super(message);
	}
}
