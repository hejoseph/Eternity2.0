package fr.esiea.glpoo.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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
 * Classe qui definit l'interface graphique, qui represente donc la vue
 * 
 * @author Joseph HE, Stefan ANGOSO, Antoine GUIDIS
 *
 */

/* View */
public class PuzzleJFrame extends JFrame {

	ImageIcon img;
	private static final Logger LOGGER = Logger.getLogger(PuzzleJFrame.class);

	private JButton buttonRotate;
	
	private Puzzle model;

	private int selectedRow, selectedColumn =-1;

	// private Puzzle model;
	private JTable table;

	public PuzzleJFrame() {
		super();

		LOGGER.debug("constructor ...");
		setTitle("Eternity 2");
		setPreferredSize(new Dimension(1300, 700));
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		JPanel container = new JPanel();
		model = new Puzzle();
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
		scrollPane.setBounds(50, 200, 403, 403);
		LOGGER.debug("before image renderer");
		// java.net.URL imgURL =
		// getClass().getClassLoader().getResource("piece.png");
		// System.out.println(imgURL);
		// img = new ImageIcon(imgURL);
		for (int i = 0; i < 4; i++) {
			table.getColumnModel().getColumn(i)
					.setCellRenderer(new ImageRenderer());
		}

		table.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				selectedRow = table.rowAtPoint(evt.getPoint());
				selectedColumn = table.columnAtPoint(evt.getPoint());
				buttonRotate.setEnabled(true);
			}
		});

		final JPanel boutons = new JPanel();
		buttonRotate = new JButton(new RotateImage());
		buttonRotate.setEnabled(false);
		boutons.add(buttonRotate);
		boutons.setBounds(0, 0, boutons.getPreferredSize().width, 40);

		container.add(boutons);
		container.add(scrollPane);
		container.setLayout(null);
		// container.setBounds(0, 0, 500, 500);
		container.setBackground(Color.RED);
		setContentPane(container);
		// getContentPane().add(scrollPane, BorderLayout.CENTER);

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

	class RotateImage extends AbstractAction {

		private RotateImage() {
			super("Pivoter");
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			LOGGER.debug("Click sur le bouton pivoter");
			model.rotateImage(selectedRow,selectedColumn);
		}
	}

}
