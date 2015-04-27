import java.awt.EventQueue;

import org.apache.log4j.Logger;

import fr.esiea.glpoo.view.PuzzleJFrame;


public class Launcher {

	private static final Logger LOGGER = Logger.getLogger(Launcher.class);
	
	public static void main(String[] args) {

				PuzzleJFrame view = new PuzzleJFrame();
//				Puzzle model = new Puzzle();
//				PuzzleController controller = new PuzzleController(view, model);
//				controller.initPuzzle();
				view.setVisible(true);
				
	}
	
}
