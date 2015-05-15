package fr.esiea.glpoo.view;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTable;

import org.apache.log4j.Logger;

import fr.esiea.glpoo.model.domain.Puzzle;


public class RotateKeyListener implements KeyListener {

	private static final Logger LOGGER = Logger.getLogger(RotateKeyListener.class);
	
	private JTable plateau;
	private Puzzle model;
	
	private int rowSelected, colSelected;
	
	public RotateKeyListener(JTable plateau){
		this.plateau = plateau;
		this.model = (Puzzle) plateau.getModel();
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
		
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println("hello"+e.getKeyCode());
		rowSelected = plateau.getSelectedRow();
		colSelected = plateau.getSelectedColumn();
		if(e.getKeyCode() != KeyEvent.VK_R){
			return;
		}
		if(model.getValueAt(rowSelected, colSelected)!= null){
			model.rotateImage(rowSelected, colSelected);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
