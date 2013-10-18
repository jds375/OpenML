package utils;

import Jama.Matrix;

/** Various utility functions for the Jama matrix toolkit.
 * <p>
 * Copyright (c) 2008 Eric Eaton
 * <p>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p>
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see http://www.gnu.org/licenses/.
 * 
 * @author Eric Eaton (EricEaton@umbc.edu) <br>
 *         University of Maryland Baltimore County
 * 
 * @version 0.1
 *
 */
public class JamaUtils {

	/** Gets the specified column of a matrix.
	 * @param m the matrix.
	 * @param col the column to get.
	 * @return the specified column of m.
	 */
	public static Matrix getcol(Matrix m, int col) {
		return m.getMatrix(0,m.getRowDimension()-1,col,col);
	}
	
	
	/** Gets the specified columns of a matrix.
	 * @param m the matrix
	 * @param columns the columns to get
	 * @return the matrix of the specified columns of m.
	 */
	public static Matrix getcolumns(Matrix m, int[] columns) {
		Matrix colMatrix = new Matrix(m.getRowDimension(),columns.length);
		for (int i=0; i<columns.length; i++) {
			setcol(colMatrix, i, getcol(m, columns[i]));
		}
		return colMatrix;
	}
	
	
	/** Gets the specified row of a matrix.
	 * @param m the matrix.
	 * @param row the row to get.
	 * @return the specified row of m.
	 */
	public static Matrix getrow(Matrix m, int row) {
		return m.getMatrix(row,row,0,m.getColumnDimension()-1);
	}
	
	
	/** Gets the specified rows of a matrix.
	 * @param m the matrix
	 * @param rows the rows to get
	 * @return the matrix of the specified rows of m.
	 */
	public static Matrix getrows(Matrix m, int[] rows) {
		Matrix rowMatrix = new Matrix(rows.length, m.getColumnDimension());
		for (int i=0; i<rows.length; i++) {
			setrow(rowMatrix, i, getrow(m, rows[i]));
		}
		return rowMatrix;
	}
	
	/** Sets the specified row of a matrix.  Modifies the passed matrix.
	 * @param m the matrix.
	 * @param row the row to modify.
	 * @param values the new values of the row.
	 */
	public static void setrow(Matrix m, int row, Matrix values) {
		if (!isRowVector(values))
			throw new IllegalArgumentException("values must be a row vector.");
		m.setMatrix(row,row,0,m.getColumnDimension()-1,values);
	}
	
	
	/** Sets the specified column of a matrix.  Modifies the passed matrix.
	 * @param m the matrix.
	 * @param col the column to modify.
	 * @param values the new values of the column.
	 */
	public static void setcol(Matrix m, int col, Matrix values) {
		if (!isColumnVector(values))
			throw new IllegalArgumentException("values must be a column vector.");
		m.setMatrix(0,m.getRowDimension()-1,col,col,values);
	}
	
	
	/** Sets the specified column of a matrix.  Modifies the passed matrix.
	 * @param m the matrix.
	 * @param col the column to modify.
	 * @param values the new values of the column.
	 */
	public static void setcol(Matrix m, int col, double[] values) {
		if (values.length != m.getRowDimension())
			throw new IllegalArgumentException("values must have the same number of rows as the matrix.");
		for (int i=0; i<values.length; i++) {
			m.set(i, col, values[i]);
		}
	}
	
	
	/** Appends additional rows to the first matrix.
	 * @param m the first matrix.
	 * @param n the matrix to append containing additional rows.
	 * @return a matrix with all the rows of m then all the rows of n.
	 */
	public static Matrix rowAppend(Matrix m, Matrix n) {
		int mNumRows = m.getRowDimension();
		int mNumCols = m.getColumnDimension();
		int nNumRows = n.getRowDimension();
		int nNumCols = n.getColumnDimension();
		
		if (mNumCols != nNumCols)
			throw new IllegalArgumentException("Number of columns must be identical to row-append.");
		
		Matrix x = new Matrix(mNumRows+nNumRows,mNumCols);
		x.setMatrix(0,mNumRows-1,0,mNumCols-1,m);
		x.setMatrix(mNumRows,mNumRows+nNumRows-1,0,mNumCols-1,n);
		
		return x;
	}
	
	
	/** Appends additional columns to the first matrix.
	 * @param m the first matrix.
	 * @param n the matrix to append containing additional columns.
	 * @return a matrix with all the columns of m then all the columns of n.
	 */
	public static Matrix columnAppend(Matrix m, Matrix n) {
		int mNumRows = m.getRowDimension();
		int mNumCols = m.getColumnDimension();
		int nNumRows = n.getRowDimension();
		int nNumCols = n.getColumnDimension();
		
		if (mNumRows != nNumRows)
			throw new IllegalArgumentException("Number of rows must be identical to column-append.");
		
		Matrix x = new Matrix(mNumRows,mNumCols+nNumCols);
		x.setMatrix(0,mNumRows-1,0,mNumCols-1,m);
		x.setMatrix(0,mNumRows-1,mNumCols,mNumCols+nNumCols-1,n);
		
		return x;
	}
	
	
	/** Deletes a row from a matrix.  Does not change the passed matrix.
	 * @param m the matrix.
	 * @param row the row to delete.
	 * @return m with the specified row deleted.
	 */
	public static Matrix deleteRow(Matrix m, int row) {
		int numRows = m.getRowDimension();
		int numCols = m.getColumnDimension();
		Matrix m2 = new Matrix(numRows-1,numCols);
		for (int mi=0,m2i=0; mi < numRows; mi++) {
			if (mi == row)
				continue;  // skips incrementing m2i
			for (int j=0; j<numCols; j++) {
				m2.set(m2i,j,m.get(mi,j));
			}
			m2i++;
		}
		return m2;
	}
	
	
	/** Deletes a column from a matrix.  Does not change the passed matrix.
	 * @param m the matrix.
	 * @param col the column to delete.
	 * @return m with the specified column deleted.
	 */
	public static Matrix deleteCol(Matrix m, int col) {
		int numRows = m.getRowDimension();
		int numCols = m.getColumnDimension();
		Matrix m2 = new Matrix(numRows,numCols-1);
		for (int mj=0,m2j=0; mj < numCols; mj++) {
			if (mj == col)
				continue;  // skips incrementing m2j
			for (int i=0; i<numRows; i++) {
				m2.set(i,m2j,m.get(i,mj));
			}
			m2j++;
		}
		return m2;
	}
	
	
	/** Gets the sum of the specified row of the matrix.
	 * @param m the matrix.
	 * @param row the row.
	 * @return the sum of m[row,*]
	 */
	public static double rowsum(Matrix m, int row) {
		// error check the column index
		if (row < 0 || row >= m.getRowDimension()) {
			throw new IllegalArgumentException("row exceeds the row indices [0,"+(m.getRowDimension()-1)+"] for m.");
		}
		
		double rowsum = 0;
		
		// loop through the rows for this column and compute the sum
		int numCols = m.getColumnDimension();
		for (int j=0; j<numCols; j++) {
			rowsum += m.get(row,j);
		}
		
		return rowsum;
	}
	
	
	/** Gets the sum of the specified column of the matrix.
	 * @param m the matrix.
	 * @param col the column.
	 * @return the sum of m[*,col]
	 */
	public static double colsum(Matrix m, int col) {
		// error check the column index
		if (col < 0 || col >= m.getColumnDimension()) {
			throw new IllegalArgumentException("col exceeds the column indices [0,"+(m.getColumnDimension()-1)+"] for m.");
		}
		
		double colsum = 0;
		
		// loop through the rows for this column and compute the sum
		int numRows = m.getRowDimension();
		for (int i=0; i<numRows; i++) {
			colsum += m.get(i,col);
		}
		
		return colsum;
	}
	
	
	/** Computes the sum of each row of a matrix.
	 * @param m the matrix.
	 * @return a column vector of the sum of each row of m.
	 */
	public static Matrix rowsum(Matrix m) {
		int numRows = m.getRowDimension();
		int numCols = m.getColumnDimension();
		Matrix sum = new Matrix(numRows,1);
		// loop through the rows and compute the sum
		for (int i=0; i<numRows; i++) {
			for (int j=0; j<numCols; j++) {
				sum.set(i,0,sum.get(i,0)+m.get(i,j));
			}
		}
		return sum;
	}
	
	
	/** Computes the sum of each column of a matrix.
	 * @param m the matrix.
	 * @return a row vector of the sum of each column of m.
	 */
	public static Matrix colsum(Matrix m) {
		int numRows = m.getRowDimension();
		int numCols = m.getColumnDimension();
		Matrix sum = new Matrix(1,numCols);
		// loop through the rows and compute the sum
		for (int i=0; i<numRows; i++) {
			for (int j=0; j<numCols; j++) {
				sum.set(0,j,sum.get(0,j)+m.get(i,j));
			}
		}
		return sum;
	}
	
	/** Computes the sum the elements of a matrix.
	 * @param m the matrix.
	 * @return the sum of the elements of the matrix
	 */
	public static double sum(Matrix m) {
		int numRows = m.getRowDimension();
		int numCols = m.getColumnDimension();
		double sum = 0;
		// loop through the rows and compute the sum
		for (int i=0; i<numRows; i++) {
			for (int j=0; j<numCols; j++) {
				sum += m.get(i,j);
			}
		}
		return sum;
	}
	
	
	/** Determines if a given matrix is a row vector, that is, it has only one row.
	 * @param m the matrix.
	 * @return whether the given matrix is a row vector (whether it has only one row).
	 */
	public static boolean isRowVector(Matrix m) {
		return m.getRowDimension()==1;
	}
	
	
	/** Determines if a given matrix is a column vector, that is, it has only one column.
	 * @param m the matrix.
	 * @return whether the given matrix is a column vector (whether it has only one column).
	 */
	public static boolean isColumnVector(Matrix m) {
		return m.getColumnDimension()==1;
	}
	
	
	/** Transforms the given matrix into a column vector, that is, a matrix with one column.
	 * The matrix must be a vector (row or column) to begin with.
	 * @param m
	 * @return <code>m.transpose()</code> if m is a row vector,
	 *         <code>m</code> if m is a column vector.
	 * @throws IllegalArgumentException if m is not a row vector or a column vector.
	 */
	public static Matrix makeColumnVector(Matrix m) {	
		if (isColumnVector(m))
			return m;
		else if (isRowVector(m))
			return m.transpose();
		else
			throw new IllegalArgumentException("m is not a vector.");
	}
	
	
	/** Transforms the given matrix into a row vector, that is, a matrix with one row.
	 * The matrix must be a vector (row or column) to begin with.
	 * @param m
	 * @return <code>m.transpose()</code> if m is a column vector,
	 *         <code>m</code> if m is a row vector.
	 * @throws IllegalArgumentException if m is not a row vector or a column vector.
	 */
	public static Matrix makeRowVector(Matrix m) {
		if (isRowVector(m))
			return m;
		else if (isColumnVector(m))
			return m.transpose();
		else
			throw new IllegalArgumentException("m is not a vector.");
	}
	      
	
	/** Computes the dot product of two vectors.  Both must be either row or column vectors.
	 * @param m1
	 * @param m2
	 * @return the dot product of the two vectors.
	 */        
	public static double dotproduct(Matrix m1, Matrix m2) {
		
		Matrix m1colVector = makeColumnVector(m1);
		Matrix m2colVector = makeColumnVector(m2);
		
		int n = m1colVector.getRowDimension();
		if (n != m2colVector.getRowDimension()) {
			throw new IllegalArgumentException("m1 and m2 must have the same number of elements.");
		}
		
		double scalarProduct = 0;
		for (int row=0; row<n; row++) {
			scalarProduct += m1colVector.get(row,0) * m2colVector.get(row,0);
		}
		
		return scalarProduct;
		
	}
	
}
