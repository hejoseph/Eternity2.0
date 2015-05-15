package fr.esiea.glpoo.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;











import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import org.apache.log4j.Logger;

import fr.esiea.glpoo.model.domain.Score;
import fr.esiea.glpoo.model.service.ScoreService;

//import fr.esiea.glpoo.view.SubMenuNewGame.LevelGame;

public class MainMenu extends JFrame {

	private JPanel mainMenu;
	private JPanel subMenuNewGame;
	private JPanel subMenuHighScores;

	private static final Logger LOGGER = Logger.getLogger(MainMenu.class);

	public MainMenu() {
		super("Eternity 2");
		setResizable(false);
//		setBackground(Color.black);
		// setPreferredSize(new Dimension(500, 300));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		mainMenu = initMainMenu();

		subMenuNewGame = initSubMenuNewGame();

		subMenuHighScores = initSubMenuHighScores();
		
		setContentPane(mainMenu);
		// setContentPane(subMenuNewGame);
		// getContentPane().add(mainMenu);
		pack();
	}

	private JPanel initSubMenuHighScores() {
		JPanel p = new JPanel();
		p.setPreferredSize(new Dimension(500, 300));
		// setLocationRelativeTo(null);

		JPanel container = new JPanel();
		container.setLayout(null);
		ScoreModele sm = new ScoreModele();
		JTable table = new JTable(sm);
		JScrollPane js = new JScrollPane(table);
		container.add(js);
		js.setBounds(0, 0, js.getPreferredSize().width, js.getPreferredSize().height);
//		for (int i = 0; i < 3; i++) {
//			JButton levelButton = new JButton(new LevelGame(i));
//			levelButton.setBounds(350, 10+30*i, levelButton.getPreferredSize().width, levelButton.getPreferredSize().height);
//			container.add(levelButton);
//		}
//		container.setBounds(30, 10, container.getPreferredSize().width,
//				container.getPreferredSize().height);
		JButton backButton = new JButton(new BackAction());
//		backButton.setBounds(90, 150, backButton.getPreferredSize().width,
//				backButton.getPreferredSize().height);
		p = new JPanel();
		p.setLayout(new BorderLayout());
//		p.setPreferredSize(new Dimension(500, 500));
		p.add(container, BorderLayout.CENTER);
		p.add(backButton, BorderLayout.SOUTH);
//		p.setLayout(null);
		return p;
	}

	private JPanel initMainMenu() {
		// setPreferredSize(new Dimension(500, 300));

		BufferedImage bf = null;
		
		
		
//		JButton newButton = new JButton(new NewAction());
//
//		JButton loadButton = new JButton(new LoadAction());
//
//		JButton scoreButton = new JButton(new ScoreAction());

		JPanel container = new JPanel();
		container.setBackground(Color.black);
		container.setPreferredSize(new Dimension(100, 100));
//		container.add(newButton, BorderLayout.NORTH);
//		container.add(loadButton, BorderLayout.CENTER);
//		container.add(scoreButton, BorderLayout.SOUTH);

		try {
			bf = ImageIO.read(new File("src/main/resources/new_game.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JPanel new_game = new ImageLoader(bf);
		new_game.setBackground(Color.BLACK);
		container.add(new_game);
		
		new_game.addMouseListener(new NewGameAction());
		
		try {
			bf = ImageIO.read(new File("src/main/resources/load_game.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JPanel load_game = new ImageLoader(bf);
		load_game.setBackground(Color.BLACK);
		container.add(load_game);
		
		load_game.addMouseListener(new LoadGameAction());
		
		try {
			bf = ImageIO.read(new File("src/main/resources/high_scores.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JPanel high_scores = new ImageLoader(bf);
		high_scores.setBackground(Color.BLACK);
		container.add(high_scores);
				
		high_scores.addMouseListener(new HighScoresAction());
		
		try {
			bf = ImageIO.read(new File("src/main/resources/eternity2.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JPanel p = new ImageLoader(bf);
		p.setLayout(new BorderLayout());
		p.setPreferredSize(new Dimension(200, 200));
		p.add(container, BorderLayout.SOUTH);

		return p;
		// setContentPane(mainMenu);
	}

	class NewAction extends AbstractAction {

		private NewAction() {
			super("New Game");
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			LOGGER.debug("after new clicked");
			// SubMenuNewGame smng = new SubMenuNewGame();
			// smng.setVisible(true);
			changePanel(subMenuNewGame);
		}

	}

	class LoadAction extends AbstractAction {

		private LoadAction() {
			super("Load Game");
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			LOGGER.debug("Click sur le bouton pivoter");
		}

	}

	class ScoreAction extends AbstractAction {

		private ScoreAction() {
			super("High Score");
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			LOGGER.debug("Click sur le bouton pivoter");
		}

	}

	private void changePanel(JPanel panel) {
		// getContentPane().removeAll();
		// getContentPane().add(panel, BorderLayout.CENTER);
		setContentPane(panel);
		// getContentPane().doLayout();
		// update(getGraphics());

		this.invalidate();
		this.validate();
		// this.repaint();
	}

	private JPanel initSubMenuNewGame() {
		JPanel p = new JPanel();
		p.setPreferredSize(new Dimension(500, 300));
		// setLocationRelativeTo(null);

		JPanel container = new JPanel();
		container.setLayout(null);
		
		for (int i = 0; i < 3; i++) {
			JButton levelButton = new JButton(new LevelGame(i));
			levelButton.setBounds(350, 10+30*i, levelButton.getPreferredSize().width, levelButton.getPreferredSize().height);
			container.add(levelButton);
		}
//		container.setBounds(30, 10, container.getPreferredSize().width,
//				container.getPreferredSize().height);
		JButton backButton = new JButton(new BackAction());
//		backButton.setBounds(90, 150, backButton.getPreferredSize().width,
//				backButton.getPreferredSize().height);
		p = new JPanel();
		p.setLayout(new BorderLayout());
//		p.setPreferredSize(new Dimension(500, 500));
		p.add(container, BorderLayout.CENTER);
		p.add(backButton, BorderLayout.SOUTH);
//		p.setLayout(null);
		return p;
	}

	class LevelGame extends AbstractAction {

		private int level;

		public LevelGame(int level) {
			super("Level " + (level + 1));
			this.level = level;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			JFrame view = new PuzzleJFrame(level + 4, true);
			view.setVisible(true);
		}

	}
	
	class BackAction extends AbstractAction {


		public BackAction() {
			super("Retour");
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			changePanel(mainMenu);
		}

	}
	
	class NewGameAction implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			changePanel(subMenuNewGame);
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	class LoadGameAction implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			LOGGER.info("Chargement des pieces");

			// final LoadGameActionHandler handler = new
			// LoadGameActionHandler(model);
			LoadGameJDialog popup = new LoadGameJDialog();
			popup.setPopup(popup);
			popup.setPreferredSize(new Dimension(500, 500));
			popup.setVisible(true);
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	class HighScoresAction implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			LOGGER.info("highscore clicked");
			changePanel(subMenuHighScores);
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	class ScoreModele extends AbstractTableModel{

		private ScoreService scoreService = ScoreService.getInstance();
		private String[] entetes = {"Pseudo","Size Game", "Temps"};
		private List<Score> scores;
		
		public ScoreModele(){
			scores = scoreService.findAllScores("src/main/resources/scores.csv");
		}
		
		public List<Score> getScores() {
			return scores;
		}
		
		@Override
		public int getRowCount() {
			return scores.size();
		}

		@Override
		public int getColumnCount() {
			return entetes.length;
		}
		
		@Override
		public String getColumnName(int columnIndex) {
			return entetes[columnIndex];
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			switch (columnIndex) {

			case 0:
				// Nom
				return scores.get(rowIndex).getNickname();

			case 1:
				// Prenom
				return scores.get(rowIndex).getLevel()+"x"+scores.get(rowIndex).getLevel();
				
			case 2:
				// Annee
				return TimerGame.convertSeconds(scores.get(rowIndex).getTime());

	
			default:
				throw new IllegalArgumentException();
			}
		}
		
	}
	
}
