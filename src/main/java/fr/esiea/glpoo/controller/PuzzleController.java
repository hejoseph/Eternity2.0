package fr.esiea.glpoo.controller;
import static java.awt.BorderLayout.CENTER;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;

import fr.esiea.glpoo.model.domain.Puzzle;
import fr.esiea.glpoo.view.PuzzleJFrame;

/**
 * Classe qui va faire interagir les donnees (model) , et l'interface graphique (view).
 * 
 * @author Joseph HE, Stefan ANGOSO, Antoine GUIDIS
 *
 */

public class PuzzleController {
	private PuzzleJFrame view;
	private Puzzle model;
	public PuzzleController(PuzzleJFrame view, Puzzle model) {
		super();
		this.view = view;
		this.model = model;
	}
	
	public void initPuzzle(){
		JTable tableau = new JTable(this.model);

		this.view.setTableau(tableau);
		this.view.getContentPane().add(this.view.getTableau(), BorderLayout.SOUTH);
	}
}

