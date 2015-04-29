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

import fr.esiea.glpoo.model.domain.Puzzle;

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
			column.setPreferredWidth(100); // sport column is bigger
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
		container = new JPanel();
	}

	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		LOGGER.debug("returning label");
		LOGGER.debug("row = " + row);
		LOGGER.debug("column = " + column);
		LOGGER.debug("value = " + value);

		// java.net.URL imgURL =
		// getClass().getClassLoader().getResource(value.toString());
		// ImageIcon icon = new ImageIcon(imgURL);

		// java.net.URL imgURL2 =
		// getClass().getClassLoader().getResource("red.png");
		// icon2 = new ImageIcon(imgURL2);
		// lbl.setText((String) value);
		// JLabel lb1 = new JLabel();
		// JLabel lb2 = new JLabel();
		// lbl.setIcon(icon);
		// lb2.setIcon(icon2);
		// lbl.setLayout(new BoxLayout(lbl, BoxLayout.X_AXIS));
		// lbl.add(lb1);
		// lbl.add(lb2);
		// lbl.setOpaque(true);
		LOGGER.debug("loading image");
		BufferedImage bf = null;
		try {
			bf = ImageIO.read(new File("src/main/resources/etoile.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JPanel j = new ImageLoader(bf);

		try {
			bf = ImageIO.read(new File("src/main/resources/torsade.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JPanel k = new ImageLoader(bf);

		container.setLayout(null);
		j.setOpaque(false);
		k.setOpaque(false);
		container.add(j);
		container.add(k);
		Dimension size = j.getPreferredSize();
		j.setBounds(5, 5, size.width, size.height);
		size = k.getPreferredSize();
		k.setBounds(5, 5, size.width, size.height);

		return container;
	}
}
