package fr.esiea.glpoo.model.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.table.AbstractTableModel;

import org.apache.log4j.Logger;

import fr.esiea.glpoo.model.domain.Face;
import fr.esiea.glpoo.model.domain.Piece;
import fr.esiea.glpoo.model.service.FaceService;
import fr.esiea.glpoo.model.service.PieceService;

/**
 * Classe qui represente le model, c'est-a-dire le coeur des donnees du jeu
 * 
 * @author Joseph HE, Stefan ANGOSO, Antoine GUIDIS
 *
 */

/* Model */
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

	Boolean generated;
	Boolean clicked = false;
	Boolean finishedRound;

	public Boolean getClicked() {
		return this.clicked;
	}

	public void setClicked(Boolean clicked) {
		this.clicked = clicked;
	}

	public Puzzle(int size, Boolean generate) {
		super();

		generated = generate;
		piecegame = new Piece[size][size];
		if (!generate) {
			initPuzzle();
			return;
		}

		List<Piece> pieces = pieceService.findAllPieces(RESOURCES_PATH
				+ PIECES_FILE_NAME);
		List<Face> faces = faceService.findAllFaces(RESOURCES_PATH
				+ FACES_FILE_NAME);
		
		for (Piece p : pieces) {
			List<Face> lf = new ArrayList<Face>();
			for (Integer face_id : p.get_faces_id()) {
				Face f = faceService.getFaceById(face_id);
				lf.add(f);
			}
			p.setFaces(lf);
		}
		int test = 0;
		LOGGER.debug("taille de la base de donnee de piece :" + pieces.size());

		// piecegame = generateRandomPiece(puzzle, pieces);
		while (generated) {
			piecegame = generateRandomSolvablePuzzle(piecegame, pieces);
		}
		System.out.println("taille fin : " + pieces.size());
	}

	private void initPuzzle() {
		for (int i = 0; i < piecegame.length; i++) {
			for (int j = 0; j < piecegame[0].length; j++) {
				piecegame[i][j] = null;
			}
		}
	}

	private Piece[][] generateRandomSolvablePuzzle(Piece[][] puzzle,
			List<Piece> pieces) {
		int limited = pieces.size();
		Object p = ((ArrayList<Piece>) pieces).clone();
		int a = 0;
		for (int i = 0; i < puzzle.length; i++) {
			for (int j = 0; j < puzzle[0].length; j++) {
				puzzle[i][j] = generatePiece(i, j, puzzle, (List<Piece>) p);
				a += 1;
				System.out.println("object return" + a);
			}
		}
		System.out.println("generated");
		return puzzle;
	}

	private Piece generatePiece(int row, int column, Piece[][] puzzle,
			List<Piece> pieces) {
		generated = false;
		int game_size = puzzle.length;
		Piece result = null;
		Piece p1 = null;
		Random generator = new Random();
		int start = 0;
		do {
			if (start == pieces.size()) {
				System.out.println("oh");
				generated = true;
				return null;
			}
			int random_id = generator.nextInt(pieces.size());
			p1 = pieces.get(random_id);
			if (row == 0) {
				if (column == 0) {
					if (p1.nbBord() == 2) {
						p1 = needRotation(row, column, p1, game_size);
						result = pieces.remove(random_id);
					}
				} else if (column == game_size - 1) {
					if (p1.nbBord() == 2) {
						p1 = needRotation(row, column, p1, game_size);
						result = (p1.getFaceIdAt(3) == puzzle[row][column - 1]
								.getFaceIdAt(1)) ? pieces.remove(random_id)
								: null;
					}
				} else {
					if (p1.nbBord() == 1) {
						p1 = needRotation(row, column, p1, game_size);
						result = (p1.getFaceIdAt(3) == puzzle[row][column - 1]
								.getFaceIdAt(1)) ? pieces.remove(random_id)
								: null;
					}
				}
			} else if (row == game_size - 1) {
				if (column == 0) {
					if (p1.nbBord() == 2) {
						p1 = needRotation(row, column, p1, game_size);
						// result = pieces.remove(random_id);
						result = (p1.getFaceIdAt(0) == puzzle[row - 1][column]
								.getFaceIdAt(2)) ? pieces.remove(random_id)
								: null;
					}
				} else if (column == game_size - 1) {
					while (start < pieces.size() && result == null) {
						p1 = pieces.get(start);
						if (p1.nbBord() == 2) {
							p1 = needRotation(row, column, p1, game_size);
							Piece pieceNorth = puzzle[row - 1][column];
							Piece pieceWest = puzzle[row][column - 1];
							LOGGER.debug("Piece West face Est : "
									+ pieceWest.getEast_face_id());
							LOGGER.debug("Piece Nord, face Sud : "
									+ pieceNorth.getSouth_face_id());
							LOGGER.debug("(0;0) : "
									+ puzzle[0][0].getFacesPattern());
							LOGGER.debug("(0;3) : "
									+ puzzle[0][3].getFacesPattern());
							LOGGER.debug("(3;0) : "
									+ puzzle[3][0].getFacesPattern());
							LOGGER.debug("Nord : " + p1.getNorth_face_id()
									+ ",   Ouest :" + p1.getWest_face_id());
							// result = pieces.remove(random_id);
							result = (p1.getFaceIdAt(0) == pieceNorth
									.getFaceIdAt(2) && p1.getFaceIdAt(3) == pieceWest
									.getFaceIdAt(1)) ? p1 : null;
						}
						System.out.println(pieces.size() + " " + start);
						start += 1;
					}
				} else {
					if (p1.nbBord() == 1) {
						p1 = needRotation(row, column, p1, game_size);
						// result = pieces.remove(random_id);
						result = (p1.getFaceIdAt(0) == puzzle[row - 1][column]
								.getFaceIdAt(2) && p1.getFaceIdAt(3) == puzzle[row][column - 1]
								.getFaceIdAt(1)) ? pieces.remove(random_id)
								: null;
					}
				}
			} else {
				if (column == 0) {
					if (p1.nbBord() == 1) {
						p1 = needRotation(row, column, p1, game_size);
						// result = pieces.remove(random_id);
						result = (p1.getFaceIdAt(0) == puzzle[row - 1][column]
								.getFaceIdAt(2)) ? pieces.remove(random_id)
								: null;
					}
				} else if (column == game_size - 1) {
					if (p1.nbBord() == 1) {
						p1 = needRotation(row, column, p1, game_size);
						// result = pieces.remove(random_id);
						result = (p1.getFaceIdAt(0) == puzzle[row - 1][column]
								.getFaceIdAt(2) && p1.getFaceIdAt(3) == puzzle[row][column - 1]
								.getFaceIdAt(1)) ? pieces.remove(random_id)
								: null;
					}
				} else {
					// System.out.println("size : "+pieces.size());
					// System.out.println("at : "+random_id);
					if (p1.nbBord() == 0) {
						// p1 = needRotation(row, column, p1, game_size);
						// result = pieces.remove(random_id);
						int nbRotation = 3;
						while (nbRotation >= 0) {
							result = (p1.getFaceIdAt(0) == puzzle[row - 1][column]
									.getFaceIdAt(2) && p1.getFaceIdAt(3) == puzzle[row][column - 1]
									.getFaceIdAt(1)) ? pieces.remove(random_id)
									: null;
							if (result != null) {
								break;
							}
							p1.rotate();
							nbRotation -= 1;
						}
					}
					// System.out.println("before getting");
				}
			}
		} while (result == null);

		return result;
	}

	private Piece researchPieceById(List<Piece> pieces, int id) {
		for (Piece p : pieces) {
			if (p.getPiece_id() == id) {
				return p;
			}
		}
		System.out.println("fin du programme");
		System.exit(0);
		return null;
	}

	private Piece needRotation(int row, int column, Piece p, int game_size) {

		if (p.nbBord() > 0) {
			if (row == 0) {
				while (!p.toString().matches("B...")) {
					p.rotate();
				}
			} else if (row == game_size - 1) {
				while (!p.toString().matches("..B.")) {
					p.rotate();
				}
			}

			if (column == 0) {
				while (!p.toString().matches("...B")) {
					p.rotate();
				}
			} else if (column == game_size - 1) {
				while (!p.toString().matches(".B..")) {
					p.rotate();
				}
			}
		}
		return p;
	}

	private Piece[][] generateRandomPiece(Piece[][] puzzle, List<Piece> pieces) {
		Random generator = new Random();
		int limited = pieces.size();
		for (int i = 0; i < puzzle.length; i++) {
			for (int j = 0; j < puzzle[0].length; j++) {
				int random_id = generator.nextInt(pieces.size()) + 1;
				LOGGER.debug("range of random num" + limited);
				Piece p1 = pieces.remove(generator.nextInt(limited));
				limited -= 1;
				List<Face> tmpfaces = new ArrayList<Face>();

				List<Integer> faces_id = p1.get_faces_id();
				Face face;
				for (int id : faces_id) {
					face = faceService.getFaceById(id);
					tmpfaces.add(face);
				}
				p1.setFaces(tmpfaces);
				System.out.println("hey" + test);
				puzzle[i][j] = p1;
			}
		}

		return puzzle;
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

	@Override
	public void setValueAt(Object o, int rowIndex, int columnIndex) {
		piecegame[rowIndex][columnIndex] = (Piece) o;
		fireTableRowsInserted(rowIndex, rowIndex);
	}

	public void rotateAllImages() {
		for (Piece[] pieces : piecegame) {
			for (Piece piece : pieces) {
				piece.rotate();
			}
		}
		fireTableRowsInserted(0, 3);
	}

	public void rotateImage(int selectedRow, int selectedColumn) {
		piecegame[selectedRow][selectedColumn].rotate();
		fireTableRowsInserted(selectedRow, selectedRow);
	}

	public boolean isLoaded(){
		for (Piece[] pieces : this.piecegame) {
			for (Piece piece : pieces) {
				if (piece == null) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	public void validate() {
		
		if(!isLoaded()){
			return;
		}
		
		for (int i = 0; i < piecegame.length; i++) {
			for (int j = 0; j < piecegame[0].length; j++) {
				int nbBord = piecegame[i][j].nbBord();
				if (i == 0) {
					if (j == 0) {
						if(nbBord != 2){
							return;
						}
					} else if (j == piecegame[0].length - 1) {
						if(nbBord != 2){
							return;
						}
						if(piecegame[i][j].getFaceIdAt(3)!=piecegame[i][j-1].getFaceIdAt(1)){
							return;
						}
					} else {
						if(nbBord != 1){
							return;
						}
						if(piecegame[i][j].getFaceIdAt(3)!=piecegame[i][j-1].getFaceIdAt(1)){
							return;
						}
					}
				} else if (i == piecegame.length - 1) {
					if (j == 0) {
						if(nbBord != 2){
							return;
						}
						if(piecegame[i][j].getFaceIdAt(0)!=piecegame[i-1][j].getFaceIdAt(2)){
							return;
						}
					} else if (j == piecegame[0].length - 1) {
						if(nbBord != 2){
							return;
						}
						if(piecegame[i][j].getFaceIdAt(0)!=piecegame[i-1][j].getFaceIdAt(2)){
							return;
						}
						if(piecegame[i][j].getFaceIdAt(3)!=piecegame[i][j-1].getFaceIdAt(1)){
							return;
						}
					} else {
						if(nbBord != 1){
							return;
						}
						if(piecegame[i][j].getFaceIdAt(0)!=piecegame[i-1][j].getFaceIdAt(2)){
							return;
						}
						if(piecegame[i][j].getFaceIdAt(3)!=piecegame[i][j-1].getFaceIdAt(1)){
							return;
						}
					}
				} else {
					if (j == 0) {
						if(nbBord != 1){
							return;
						}
						if(piecegame[i][j].getFaceIdAt(0)!=piecegame[i-1][j].getFaceIdAt(2)){
							return;
						}
					} else if (j == piecegame[0].length - 1) {
						if(nbBord != 1){
							return;
						}
						if(piecegame[i][j].getFaceIdAt(0)!=piecegame[i-1][j].getFaceIdAt(2)){
							return;
						}
						if(piecegame[i][j].getFaceIdAt(3)!=piecegame[i][j-1].getFaceIdAt(1)){
							return;
						}
					} else {
						if(nbBord != 0){
							return;
						}
						if(piecegame[i][j].getFaceIdAt(0)!=piecegame[i-1][j].getFaceIdAt(2)){
							return;
						}
						if(piecegame[i][j].getFaceIdAt(3)!=piecegame[i][j-1].getFaceIdAt(1)){
							return;
						}
					}
				}
			}
		}
		
		this.finishedRound = true;

	}

	public Boolean getFinishedRound() {
		return this.finishedRound;
	}

	public void setFinishedRound(Boolean finishedRound) {
		this.finishedRound = finishedRound;
	}
	
	public boolean save(){
		if(this.isLoaded()){
			pieceService.save(piecegame);
			return true;
		}
		return false;
	}

}
