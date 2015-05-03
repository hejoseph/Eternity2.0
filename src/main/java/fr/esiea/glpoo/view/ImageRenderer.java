package fr.esiea.glpoo.view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import org.apache.log4j.Logger;

import fr.esiea.glpoo.model.domain.Face;
import fr.esiea.glpoo.model.domain.Piece;

/**
 * Cette classe permet de redefinir le comportement d'une cellule du JTable
 * 
 * @author Joseph HE, Stefan ANGOSO, Antoine GUIDIS.
 *
 */

public class ImageRenderer extends DefaultTableCellRenderer {
	JLabel lbl = new JLabel();
	JPanel container;
	private static final Logger LOGGER = Logger.getLogger(ImageRenderer.class);

	// ImageIcon icon, icon2;

	public ImageRenderer() {
	}

	
	/**
	 * Cette methode est appelee pour chaque cellule du JTable
	 * Retourne un composant qui est une piece et qui sera affichee dans la cellule du tableau
	 * @param value : represente une piece qui est recuperee a partir du model "Puzzle"
	 * @return un conteneur de piece
	 */
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		container = new JPanel();
//		LOGGER.debug("returning label");
//		LOGGER.debug("row = " + row);
//		LOGGER.debug("column = " + column);
//		LOGGER.debug("value = " + value);
//		
//		LOGGER.debug("loading image");
		BufferedImage bf = null;
		
		Piece p = (Piece)value;
		List<Face> faces = p.getFaces();
		Double[] angle = {Math.PI/2,Math.PI/1,-Math.PI/2,Math.PI/0.5};
		for(int i = 0; i< faces.size();i++){
//			LOGGER.debug("nombre de face pour la piece : "+faces.size());
			try {
				String img_name = faces.get(i).getImg_name();
//				LOGGER.debug("nom de l'image a lire: "+img_name);
				bf = ImageIO.read(new File("src/main/resources/"+img_name));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			JPanel j = new ImageLoader(bf,angle[i]);
			j.setOpaque(false);
			container.add(j);
			Dimension size = j.getPreferredSize();
			j.setBounds(0, 0, size.width, size.height);
		}
		
//		try {
//			bf = ImageIO.read(new File("src/main/resources/noir.png"));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		JPanel j = new ImageLoader(bf,Math.PI/2);
//
//		try {
//			bf = ImageIO.read(new File("src/main/resources/bleu_zigzag_blanc.png"));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		JPanel k = new ImageLoader(bf,-Math.PI/2);
//
		container.setLayout(null);
//		j.setOpaque(false);
//		k.setOpaque(false);
//		container.add(j);
//		container.add(k);
//		Dimension size = j.getPreferredSize();
//		j.setBounds(0, 0, size.width, size.height);
//		size = k.getPreferredSize();
//		k.setBounds(0, 0, size.width, size.height);
		
//		String m = "";
//		Piece p = (Piece) value;
//		for(Face f : p.getFaces()){
//			m+=f.getImg_name()+ " ";
//		}
//		
//		lbl.setText(m);
//		container.add(lbl);
		return container;
	}
}