package fr.esiea.glpoo.view;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JTable;

import org.apache.log4j.Logger;

import fr.esiea.glpoo.model.domain.Puzzle;

/*View*/
public class PuzzleJFrame extends JFrame{
	
	private static final Logger LOGGER = Logger.getLogger(PuzzleJFrame.class);
	
//	private Puzzle model;
	private JTable tableau;
	
	public PuzzleJFrame(){
		super();
		setTitle("Eternity 2");
		setPreferredSize(new Dimension(500, 400));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		pack();
	}

	public JTable getTableau() {
		return tableau;
	}

	public void setTableau(JTable tableau) {
		this.tableau = tableau;
	}
	
	

}
