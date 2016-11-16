package org.wso2.training;


import java.io.Serializable;
import java.math.BigDecimal;

public class Option implements Serializable{
	
	public Option(String row, String col) {
		this.row = row;
		this.col = col;
	}
	public String getRow() {
		return row;
	}
	public void setRow(String row) {
		this.row = row;
	}
	public String getCol() {
		return col;
	}
	public void setCol(String col) {
		this.col = col;
	}
	public int getCategory() {
		return category;
	}
	public void setCategory(int category) {
		this.category = category;
	}
	String row;
	String col;
	int category=-1;

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Option-> row:"+row+ " col:"+col+" category:"+ category;
	}
	
}