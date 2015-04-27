package fr.esiea.glpoo.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import org.apache.log4j.Logger;

import fr.esiea.glpoo.model.domain.Puzzle;

/*View*/
public class PuzzleJFrame extends JFrame{
	
	ImageIcon img; 
	private static final Logger LOGGER = Logger.getLogger(PuzzleJFrame.class);
	
//	private Puzzle model;
	private JTable tableau;
	
	public PuzzleJFrame(){
		super();
		
		LOGGER.debug("constructor ...");
		setTitle("Eternity 2");
		setPreferredSize(new Dimension(500, 400));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		Puzzle model = new Puzzle();
		tableau = new JTable(model);
		LOGGER.debug("before image renderer");
		java.net.URL imgURL = getClass().getClassLoader().getResource("piece.png");
        System.out.println(imgURL);
        img = new ImageIcon(imgURL);
        tableau.getColumnModel().getColumn(0).setCellRenderer(new ImageRenderer(this.img));
        tableau.setRowHeight(150);
        tableau.addMouseListener(new java.awt.event.MouseAdapter() {
		    @Override
		    public void mouseClicked(java.awt.event.MouseEvent evt) {
		        int row = tableau.rowAtPoint(evt.getPoint());
		        int col = tableau.columnAtPoint(evt.getPoint());
		        LOGGER.debug("inside event click");
		        
		        java.net.URL imgURL = getClass().getClassLoader().getResource("black.png");
		        System.out.println(imgURL);
		        img = new ImageIcon(imgURL);
		        tableau.getColumnModel().getColumn(0).setCellRenderer(new ImageRenderer(img));
//		        fireTableRowsInserted(0, 0);
		    }
		});
		getContentPane().add(tableau, BorderLayout.NORTH);
		
//		java.net.URL imgURL = getClass().getClassLoader().getResource("black.png");
//        System.out.println(imgURL);
		
		
		pack();
	}

	public JTable getTableau() {
		return tableau;
	}

	public void setTableau(JTable tableau) {
		this.tableau = tableau;
	}
	
	

}

class ImageRenderer extends DefaultTableCellRenderer {
	  JLabel lbl = new JLabel();
	  private static final Logger LOGGER = Logger.getLogger(ImageRenderer.class);
	  ImageIcon icon, icon2;
	  
	  public ImageRenderer(ImageIcon img){
		  this.icon = img;
	  }

	  public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
	      boolean hasFocus, int row, int column) {
		  LOGGER.debug("returning label");
		  LOGGER.debug("row = "+row);
		  LOGGER.debug("column = "+column);
		  
//		  java.net.URL imgURL2 = getClass().getClassLoader().getResource("red.png");
//		  icon2 = new ImageIcon(imgURL2);
//	    lbl.setText((String) value);
//		  JLabel lb1 = new JLabel();
//		  JLabel lb2 = new JLabel();
		  lbl.setIcon(icon);
//		  lb2.setIcon(icon2);
//		  lbl.setLayout(new BoxLayout(lbl, BoxLayout.X_AXIS));
//	    lbl.add(lb1);
//	    lbl.add(lb2);
	    lbl.setOpaque(true);
	    return lbl;
	  }
	}




