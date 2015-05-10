package fr.esiea.glpoo.view;

//import TableTransferHandler;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
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
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.Timer;
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

	private JDialog finishedGameJDialog;
	private JDialog savingGameJDialog;
	
	/*for the time*/
	private TimerGame taskPerformer;
	private Timer timer;
	
	private JButton buttonRotate;
	private JButton buttonMove;

	/* Data Loaded */
	private Puzzle model;

	/* Playground */
	private Puzzle model_game;

	/* temporary model */
	private Puzzle newModelSelected, oldModelSelected = null;

	private int newSelectedRow, newSelectedColumn, oldSelectedRow,
			oldSelectedColumn, rowBoardGame, columnBoardGame, rowBoard,
			colBoard = -2;

	/* for loading data before playing */
	private JTable table;

	/* playground */
	private JTable table_game;

	public PuzzleJFrame() {
		super();

		LOGGER.debug("constructor ...");
		setTitle("Eternity 2");
		setPreferredSize(new Dimension(1300, 600));
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		JPanel container = new JPanel();
		model = new Puzzle(4, true);
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
		scrollPane.setBounds(50, 50, 403, 403);
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
				// Piece p = ((Piece)model.getValueAt(rowBoard, colBoard));
				// String list1="[ ";
				// for(Face f : p.getFaces()){
				// list1+=f.getFace_id() + " ";
				// }
				// list1+="]";
				// LOGGER.debug(p.getOrientation().getCode()+"   "+p.get_faces_id()+"   Affichage"+list1+
				// "  Nord:"+p.getNorth_face_id()+
				// "   Est:"+p.getEast_face_id()+"   Sud:"+p.getSouth_face_id()+"   West:"+p.getWest_face_id());

				oldSelectedRow = newSelectedRow;
				oldSelectedColumn = newSelectedColumn;
				newSelectedRow = rowBoard;
				newSelectedColumn = colBoard;
				oldModelSelected = newModelSelected;
				newModelSelected = model;
				LOGGER.debug(newModelSelected.equals(model));
				if (newModelSelected.getValueAt(newSelectedRow,
						newSelectedColumn) != null) {
					buttonRotate.setEnabled(true);
					LOGGER.debug("or not !");
				} else {
					buttonRotate.setEnabled(false);
				}
				if (oldModelSelected != null && newModelSelected != null) {
					if (oldModelSelected.getValueAt(oldSelectedRow,
							oldSelectedColumn) != null
							|| newModelSelected.getValueAt(newSelectedRow,
									newSelectedColumn) != null) {
						buttonMove.setEnabled(true);
					}
				}

			}
		});
		table.setDragEnabled(true);
		table.setTransferHandler(new PieceTransferHandler());
		

		final JPanel boutons = new JPanel();
		boutons.setLayout(new BoxLayout(boutons, BoxLayout.Y_AXIS));

		buttonRotate = new JButton(new RotateImage());
		buttonRotate.setEnabled(false);
		boutons.add(buttonRotate);

		buttonMove = new JButton(new MoveImage());
		buttonMove.setEnabled(false);
		boutons.add(buttonMove);

		JButton buttonValidate = new JButton(new ValidatePuzzle(taskPerformer));
		buttonValidate.setEnabled(true);
		boutons.add(buttonValidate);
		boutons.setBounds(500, 200, boutons.getPreferredSize().width,
				boutons.getPreferredSize().height);

		JLabel lblTime = new JLabel("START   !");
		lblTime.setBounds(480, 150, lblTime.getPreferredSize().width,
				lblTime.getPreferredSize().height);
		container.add(lblTime);

		int delay = 1000; // milliseconds
		taskPerformer = new TimerGame(lblTime); 
		timer = new Timer(delay, taskPerformer);
		timer.start();
		model_game = new Puzzle(4, false);
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
		scrollPane2.setBounds(800, 50, 403, 403);

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
				System.out.println(((Piece) newModelSelected.getValueAt(
						rowBoardGame, columnBoardGame)));
				if (newModelSelected.getValueAt(newSelectedRow,
						newSelectedColumn) != null) {
					System.out.println("haha");
					buttonRotate.setEnabled(true);
				} else {
					buttonRotate.setEnabled(false);
				}
				if (oldModelSelected != null && newModelSelected != null) {
					if (oldModelSelected.getValueAt(oldSelectedRow,
							oldSelectedColumn) != null
							|| newModelSelected.getValueAt(newSelectedRow,
									newSelectedColumn) != null) {
						buttonMove.setEnabled(true);
					}
				}
				System.out.println("old : " + oldSelectedRow + " "
						+ oldSelectedColumn);
				System.out.println("new : " + newSelectedRow + " "
						+ newSelectedColumn);
			}
		});

		table_game.setDragEnabled(true);
		table_game.setTransferHandler(new PieceTransferHandler());
		
		JLabel jltext = new JLabel("Jouer vos pieces dans ce plateau :)");
		jltext.setBounds(800, 10, jltext.getPreferredSize().width, jltext.getPreferredSize().height);
		container.add(jltext);
		container.add(boutons);
		container.add(scrollPane);
		container.add(scrollPane2);
		container.setLayout(null);
		// container.setBounds(0, 0, 500, 500);
		// container.setBackground(Color.RED);
		setContentPane(container);
		// getContentPane().add(scrollPane, BorderLayout.CENTER);

		// java.net.URL imgURL =
		// getClass().getClassLoader().getResource("black.png");
		// System.out.println(imgURL);

		addMenu();
	
		pack();
	}

	private void addMenu() {
		final JMenuBar menuBar = new JMenuBar();

		// Menu Fichier
		final JMenu menuFichier = new JMenu("Fichier");
		menuBar.add(menuFichier);

		// final JMenuItem menuOuvrir = new JMenuItem(new
		// OuvrirAction("Ouvrir"));
		// menuFichier.add(menuOuvrir);
		// final JMenuItem menuSauver = new JMenuItem(new
		// SauverAction("Sauver"));
		// menuFichier.add(menuSauver);
		// menuFichier.addSeparator();
		final JMenuItem newMenu = new JMenuItem(new NewGame("New Game"));
		menuFichier.add(newMenu);
		menuFichier.addSeparator();
		final JMenuItem saveMenu = new JMenuItem(new SaveAction("Save Game"));
		menuFichier.add(saveMenu);
		menuFichier.addSeparator();
		final JMenuItem quitMenu = new JMenuItem(new QuitAction("Quit"));
		menuFichier.add(quitMenu);

		setJMenuBar(menuBar);

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
			newModelSelected.rotateImage(newSelectedRow, newSelectedColumn);
			model_game.validate();
			if (model_game.getFinishedRound()) {
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
			Piece tmpPieceModel = (Piece) oldModelSelected.getValueAt(
					oldSelectedRow, oldSelectedColumn);
			Piece tmpPieceBoardGame = (Piece) newModelSelected.getValueAt(
					newSelectedRow, newSelectedColumn);
			oldModelSelected.setValueAt(tmpPieceBoardGame, oldSelectedRow,
					oldSelectedColumn);
			newModelSelected.setValueAt(tmpPieceModel, newSelectedRow,
					newSelectedColumn);
			buttonMove.setEnabled(false);

			oldModelSelected = newModelSelected = null;
			// model.validate();
			// if(model.getFinishedRound()){
			// buttonRotate.setEnabled(false);
			// buttonMove.setEnabled(false);
			// }
		}
	}

	class ValidatePuzzle extends AbstractAction {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
//		ActionListener taskPerformer;
		
		private ValidatePuzzle(ActionListener taskPerformer) {
			super("Valider");
//			this.taskPerformer = 
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			model.validate();
			
			finishedGameJDialog = new JDialog();
			finishedGameJDialog.setTitle("Ratio H/F");
			
			if (model.getFinishedRound()) {
				JLabel msg = new JLabel("Votre score : "+TimerGame.convertSeconds(taskPerformer.getS()));
				finishedGameJDialog.setLayout(new BorderLayout());
				finishedGameJDialog.add(msg,BorderLayout.CENTER);
				finishedGameJDialog.setPreferredSize(new Dimension(200,200));
				finishedGameJDialog.pack();
				finishedGameJDialog.setLocationRelativeTo(null);
				finishedGameJDialog.setVisible(true);
				timer.stop();
				// buttonRotate.setEnabled(false);
				// buttonMove.setEnabled(false);
//				System.exit(0);
			}
		}
	}

	private class NewGame extends AbstractAction {
		public NewGame(String texte) {
			super(texte);
		}

		public void actionPerformed(ActionEvent e) {
			LOGGER.info("Au revoir");
			setVisible(false);
			dispose();
			javax.swing.SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					JFrame view = new PuzzleJFrame();
					view.setVisible(true);
				}
			});
		}
	}

	private class QuitAction extends AbstractAction {
		public QuitAction(String text) {
			super(text);
		}

		public void actionPerformed(ActionEvent e) {
			LOGGER.info("Au revoir");
			System.exit(0);
		}
	}

	private class SaveAction extends AbstractAction {
		public SaveAction(String text) {
			super(text);
		}

		public void actionPerformed(ActionEvent e) {
			LOGGER.info("Sauverr");

			savingGameJDialog = new JDialog();
			savingGameJDialog.setTitle("Saving message");
			JLabel msg = new JLabel();
			Boolean isSaved = model.save();
			if(isSaved){
				msg.setText("Votre partie est sauvegardee");
				
			} else {
				msg.setText("Vous devez remplir toutes les cases du jeu avant de sauvegarder");
			}
			savingGameJDialog.setLayout(new BorderLayout());
			savingGameJDialog.add(msg,BorderLayout.CENTER);
			savingGameJDialog.setPreferredSize(new Dimension(400,75));
			savingGameJDialog.pack();
			savingGameJDialog.setLocationRelativeTo(null);
			savingGameJDialog.setVisible(true);
		}
	}
	
}
