package com.linker.ferdinand.model;

/**
 * 行列信息
 * 
 * @author Ferdinand
 *
 */
public class RowCol {
	private int row;
	private int col;

	public RowCol(int row, int col) {
		super();
		this.row = row;
		this.col = col;
	}

	public RowCol() {
		super();
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}
}
