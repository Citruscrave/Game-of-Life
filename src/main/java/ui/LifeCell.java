package ui;

import javax.swing.JButton;

public class LifeCell extends JButton{
	public int row;
	public int column;
	public static final int SIZE = 25;
	
	public LifeCell(int theRow, int theColumn){
		row=theRow;
		column = theColumn;
		setSize(SIZE,SIZE);
	}
}