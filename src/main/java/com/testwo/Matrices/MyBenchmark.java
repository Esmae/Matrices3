package com.testwo.Matrices;
/*
 * benchmarks the multiplication of 2 square matrices
 */

import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;

@State(Scope.Thread)
public class MyBenchmark {

	@State(Scope.Thread)
	public static class myState {

		@Param({ "2" })
		public static int noRows;// the number of rows to be in matrix1
		// as using square matrices this is also equal to the number of
		// rows/columns in matrix 1/2

		Matrix matrix1;
		Matrix matrix2;

		@Setup(Level.Trial)
		public void doSetup() {
			matrix1 = new Matrix(noRows, noRows);
			matrix2 = new Matrix(noRows, noRows);
		}
	}

	// Times the matrix multiplication for IKJ (i.e. the quicker one)
	@Benchmark
	@BenchmarkMode(Mode.Throughput) // Throughput is the default
	@Warmup(iterations = 5, time = 100, timeUnit = TimeUnit.MILLISECONDS)
	@Measurement(iterations = 20, time = 100, timeUnit = TimeUnit.MILLISECONDS)
	public Matrix testMultIKJ(myState theState) throws MatrixMultiplicationInvalid {
		return theState.matrix1.tryMatMultIKJ(theState.matrix2);
	}

	// Times the matrix multiplication for IJK (i.e. the slower one)
	@Benchmark
	@BenchmarkMode(Mode.Throughput) // Throughput is the default
	@Warmup(iterations = 5, time = 100, timeUnit = TimeUnit.MILLISECONDS)
	@Measurement(iterations = 20, time = 100, timeUnit = TimeUnit.MILLISECONDS)
	public Matrix testMultIJK(myState theState) throws MatrixMultiplicationInvalid {
		return theState.matrix1.tryMatMultIJK(theState.matrix2);
	}

}
