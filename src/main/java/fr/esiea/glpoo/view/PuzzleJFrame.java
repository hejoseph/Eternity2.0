package fr.esiea.glpoo.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

import org.apache.log4j.Logger;

import fr.esiea.glpoo.model.domain.Face;
import fr.esiea.glpoo.model.domain.Piece;
import fr.esiea.glpoo.model.domain.Puzzle;

/**
 * 
 * @author Joseph HE, Stefan ANGOSO, Antoine GUIDIS
 *
 */

/*View*/
public class PuzzleJFrame extends JFrame {

	ImageIcon img;
	private static final Logger LOGGER = Logger.getLogger(PuzzleJFrame.class);

	// private Puzzle model;
	private JTable table;

	public PuzzleJFrame() {
		super();

		LOGGER.debug("constructor ...");
		setTitle("Eternity 2");
		setPreferredSize(new Dimension(1300, 700));
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		Puzzle model = new Puzzle();
		table = new JTable(model);
		table.setTableHeader(null);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setRowHeight(100);
		TableColumn column = null;
		for (int i = 0; i < 4; i++) {
			column = table.getColumnModel().getColumn(i);
			column.setPreferredWidth(100); 
		}
		JScrollPane scrollPane = new JScrollPane(table);

		LOGGER.debug("before image renderer");
		// java.net.URL imgURL =
		// getClass().getClassLoader().getResource("piece.png");
		// System.out.println(imgURL);
		// img = new ImageIcon(imgURL);
		for (int i = 0; i < 4; i++) {
			table.getColumnModel().getColumn(i)
					.setCellRenderer(new ImageRenderer());
		}

		// tableau.addMouseListener(new java.awt.event.MouseAdapter() {
		// @Override
		// public void mouseClicked(java.awt.event.MouseEvent evt) {
		// int row = tableau.rowAtPoint(evt.getPoint());
		// int col = tableau.columnAtPoint(evt.getPoint());
		// LOGGER.debug("inside event click");
		//
		// java.net.URL imgURL =
		// getClass().getClassLoader().getResource("black.png");
		// System.out.println(imgURL);
		// img = new ImageIcon(imgURL);
		// tableau.getColumnModel().getColumn(0).setCellRenderer(new
		// ImageRenderer(img));
		// // fireTableRowsInserted(0, 0);
		// }
		// });
		getContentPane().add(scrollPane, BorderLayout.CENTER);

		// java.net.URL imgURL =
		// getClass().getClassLoader().getResource("black.png");
		// System.out.println(imgURL);

		pack();
	}

	public JTable getTableau() {
		return table;
	}

	public void setTableau(JTable table) {
		this.table = table;
	}

}

class ImageRenderer extends DefaultTableCellRenderer {
	JLabel lbl = new JLabel();
	JPanel container;
	private static final Logger LOGGER = Logger.getLogger(ImageRenderer.class);

	// ImageIcon icon, icon2;

	public ImageRenderer() {
	}

	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		container = new JPanel();
		LOGGER.debug("returning label");
		LOGGER.debug("row = " + row);
		LOGGER.debug("column = " + column);
		LOGGER.debug("value = " + value);
		
		LOGGER.debug("loading image");
		BufferedImage bf = null;
		Piece p = (Piece)value;
		List<Face> faces = p.getFaces();
		Double[] angle = {Math.PI/2,Math.PI/1,-Math.PI/2,Math.PI/0.5};
		for(int i = 0; i< faces.size();i++){
			LOGGER.debug("nombre de face pour la piece : "+faces.size());
			try {
				String img_name = faces.get(i).getImg_name();
				LOGGER.debug("nom de l'image a lire: "+img_name);
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
