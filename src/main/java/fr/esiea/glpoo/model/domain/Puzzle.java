package fr.esiea.glpoo.model.domain;

import javax.swing.ImageIcon;
import javax.swing.table.AbstractTableModel;

import fr.esiea.glpoo.view.LabelDemo;


/*Model*/
public class Puzzle extends AbstractTableModel{
	
//	private LabelDemo lb;
	private Piece[][] pieces;
	private ImageIcon test;
	private Object[][] tab2d = {
			{"etoile.png","test2.png","test3.png","test4.png"},
			{"test5.png","test6.png","test7.png","test8.png"},
			{"test9.png","test10.png",/*vide*/"","test12.png"},
			{"test13.png","test14.png","test15.png","test16.png"}};
	
	public Puzzle(){
		super();
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
