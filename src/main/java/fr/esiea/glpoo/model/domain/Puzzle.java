package fr.esiea.glpoo.model.domain;

import javax.swing.ImageIcon;
import javax.swing.table.AbstractTableModel;


/*Model*/
public class Puzzle extends AbstractTableModel{
	
	private Piece[][] pieces;
	private ImageIcon test;
	private String[][] tab2d = {
			{"1 ","2 ","3 "},
			{"4 ","5 ","6 "},
			{"7 ","8 ","9 "}};
	
	public Puzzle(){
		super();
		test = new ImageIcon("black.png");
	}
	
	public Class<?> getColumnClass(){
		return ImageIcon.class;
	}

	@Override
	public int getRowCount() {
		return tab2d.length;
	}

	@Override
	public int getColumnCount() {
		return tab2d[0].length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return tab2d[rowIndex][columnIndex];
	}
	
}
