package fr.esiea.glpoo.model.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.table.AbstractTableModel;

import org.apache.log4j.Logger;

import fr.esiea.glpoo.model.service.FaceService;
import fr.esiea.glpoo.model.service.PieceService;
import fr.esiea.glpoo.view.LabelDemo;

/*Model*/
public class Puzzle extends AbstractTableModel {

	private static final Logger LOGGER = Logger.getLogger(Puzzle.class);

	public final static String RESOURCES_PATH = "src/test/resources/";
	public final static String PIECES_FILE_NAME = "pieces-01.csv";
	public final static String FACES_FILE_NAME = "faces-01.csv";

	private FaceService faceService = FaceService.getInstance();
	private PieceService pieceService = PieceService.getInstance();

	// private LabelDemo lb;
	private Piece[][] piecegame;
	private ImageIcon test;
	private Object[][] tab2d = {
			{ "etoile.png", "test2.png", "test3.png", "test4.png" },
			{ "test5.png", "test6.png", "test7.png", "test8.png" },
			{ "test9.png", "test10.png",/* vide */"", "test12.png" },
			{ "test13.png", "test14.png", "test15.png", "test16.png" } };

	public Puzzle() {
		super();
		List<Piece> pieces = pieceService.findAllPieces(RESOURCES_PATH
				+ PIECES_FILE_NAME);
		List<Face> faces = faceService.findAllFaces(RESOURCES_PATH
				+ FACES_FILE_NAME);
		int game_size = 16;
		int limited = pieces.size();
		int test=0;
		LOGGER.debug("taille de la base de donnee de piece :" + pieces.size());
		Piece[][] puzzle = new Piece[4][4];
		Random generator = new Random();
		for (int i = 0; i < puzzle.length ; i++) {
			for (int j = 0 ; j < puzzle[0].length ; j++ ) {
				int random_id = generator.nextInt(pieces.size()) + 1;
				LOGGER.debug("range of random num" + limited);
				Piece p1 = pieces.remove(generator.nextInt(limited));
				limited-=1;
				List<Face> tmpfaces = new ArrayList<Face>();

				List<Integer> faces_id = p1.get_faces_id();
				Face face;
				for (int id : faces_id) {
					face = faceService.getFaceById(id);
					tmpfaces.add(face);
				}
				test+=1;
				p1.setFaces(tmpfaces);
				System.out.println("hey"+test);
				puzzle[i][j] = p1;
			}
		}
		
		piecegame = puzzle;
	}

	public Class<?> getColumnClass() {
		return ImageIcon.class;
	}

	@Override
	public int getRowCount() {
		return piecegame.length;
	}

	@Override
	public int getColumnCount() {
		return piecegame[0].length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return piecegame[rowIndex][columnIndex];
	}

}
