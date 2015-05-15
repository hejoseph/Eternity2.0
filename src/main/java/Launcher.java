import java.awt.EventQueue;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JTable;

import org.apache.log4j.Logger;

import fr.esiea.glpoo.view.MainMenu;
import fr.esiea.glpoo.view.PuzzleJFrame;

/**
 * Classe qui va permettre de lancer le jeu, ou le controlleur
 * 
 * @author Joseph HE, Stefan ANGOSO, Antoine GUIDIS
 *
 */

public class Launcher {

	private static final Logger LOGGER = Logger.getLogger(Launcher.class);

	public static void main(String[] args) {

		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame view = new PuzzleJFrame(5,true);
				view.setVisible(true);
				
//				MainMenu mm = new MainMenu();
//				mm.setVisible(true);
			}
		});

		// JFrame view = new PuzzleJFrame();

		// Puzzle model = new Puzzle();
		// PuzzleController controller = new PuzzleController(view, model);
		// controller.initPuzzle();
		// view.setVisible(true);

	}

}
