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
	private JButton buttonMove;
	
	/*Data Loaded*/
	private Puzzle model;
	
	/*Playground*/
	private Puzzle model_game;
	
	/*temporary model*/
	private Puzzle newModelSelected, oldModelSelected = null;
	
	private int newSelectedRow, newSelectedColumn, oldSelectedRow, oldSelectedColumn,rowBoardGame, columnBoardGame, rowBoard, colBoard = -2;

	/*for loading data before playing*/
	private JTable table;

	/*playground*/
	private JTable table_game;
	
	public PuzzleJFrame() {
		super();

		LOGGER.debug("constructor ...");
		setTitle("Eternity 2");
		setPreferredSize(new Dimension(1300, 700));
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		JPanel container = new JPanel();
		model = new Puzzle(4,true);
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
				LOGGER.debug("in click");
				model.setClicked(true);
				rowBoard = table.rowAtPoint(evt.getPoint());
				colBoard = table.columnAtPoint(evt.getPoint());
				
				oldSelectedRow = newSelectedRow;
				oldSelectedColumn = newSelectedColumn;
				newSelectedRow = rowBoard;
				newSelectedColumn = colBoard;
				oldModelSelected = newModelSelected;
				newModelSelected = model;
				if(newModelSelected.getValueAt(newSelectedRow, newSelectedColumn)!=null){
					buttonRotate.setEnabled(true);
				} else {
					buttonRotate.setEnabled(false);
				}
				if(oldModelSelected != null && newModelSelected != null){
					buttonMove.setEnabled(true);
				}
				
			}
		});

		final JPanel boutons = new JPanel();
		boutons.setLayout(new BoxLayout(boutons, BoxLayout.Y_AXIS));
		
		buttonRotate = new JButton(new RotateImage());
		buttonRotate.setEnabled(false);
		boutons.add(buttonRotate);
		
		buttonMove = new JButton(new MoveImage());
		buttonMove.setEnabled(false);
		boutons.add(buttonMove);
		
		JButton buttonValidate = new JButton(new ValidatePuzzle());
		buttonValidate.setEnabled(true);
		boutons.add(buttonValidate);
		boutons.setBounds(500, 200, boutons.getPreferredSize().width, boutons.getPreferredSize().height);

		
		
		model_game = new Puzzle(4,false);
		table_game = new JTable(model_game);
		table_game.setTableHeader(null);
		table_game.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table_game.setRowHeight(100);
		column = null;
		for (int i = 0; i < 4; i++) {
			column = table_game.getColumnModel().getColumn(i);
			column.setPreferredWidth(100);
		}
		JScrollPane scrollPane2 = new JScrollPane(table_game);
		scrollPane2.setBounds(800, 200, 403, 403);
		
		for (int i = 0; i < 4; i++) {
			table_game.getColumnModel().getColumn(i)
					.setCellRenderer(new ImageRenderer());
		}
		
		table_game.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				model_game.setClicked(true);
				rowBoardGame = table_game.rowAtPoint(evt.getPoint());
				columnBoardGame = table_game.columnAtPoint(evt.getPoint());
				oldSelectedRow = newSelectedRow;
				oldSelectedColumn = newSelectedColumn;
				newSelectedRow = rowBoardGame;
				newSelectedColumn = columnBoardGame;
				oldModelSelected = newModelSelected;
				newModelSelected = model_game;
				System.out.println(((Piece) newModelSelected.getValueAt(rowBoardGame, columnBoardGame)));
				if(newModelSelected.getValueAt(newSelectedRow, newSelectedColumn)!=null){
					System.out.println("haha");
					buttonRotate.setEnabled(true);
				}else{
					buttonRotate.setEnabled(false);
				}
				if(oldModelSelected != null && newModelSelected != null){
					buttonMove.setEnabled(true);
				}
				System.out.println("old : " + oldSelectedRow + " " +oldSelectedColumn );
				System.out.println("new : " + newSelectedRow + " " +newSelectedColumn );
			}
		});
		
		container.add(boutons);
		container.add(scrollPane);
		container.add(scrollPane2);
		container.setLayout(null);
		// container.setBounds(0, 0, 500, 500);
//		container.setBackground(Color.RED);
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
			newModelSelected.rotateImage(newSelectedRow,newSelectedColumn);
			model.validate();
			if(model.getFinishedRound()){
				buttonRotate.setEnabled(false);
				buttonMove.setEnabled(false);
			}
		}
	}
	
	class MoveImage extends AbstractAction {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		private MoveImage() {
			super("Deplacer");
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			Piece tmpPieceModel = (Piece) oldModelSelected.getValueAt(oldSelectedRow, oldSelectedColumn);
			Piece tmpPieceBoardGame = (Piece) newModelSelected.getValueAt(newSelectedRow, newSelectedColumn);
			oldModelSelected.setValueAt(tmpPieceBoardGame, oldSelectedRow, oldSelectedColumn);
			newModelSelected.setValueAt(tmpPieceModel, newSelectedRow, newSelectedColumn);
			
			model.validate();
			if(model.getFinishedRound()){
				buttonRotate.setEnabled(false);
				buttonMove.setEnabled(false);
			}
		}
	}
	
	class ValidatePuzzle extends AbstractAction {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		private ValidatePuzzle() {
			super("Valider");
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			model.validate();
			if(model.getFinishedRound()){
				buttonRotate.setEnabled(false);
				buttonMove.setEnabled(false);
			}
		}
	}

}
