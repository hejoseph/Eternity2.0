package fr.esiea.glpoo.model.dao.csv;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;







import au.com.bytecode.opencsv.CSVWriter;
import fr.esiea.glpoo.model.dao.FaceDao;
import fr.esiea.glpoo.model.domain.Face;
import fr.esiea.glpoo.model.domain.FaceType;
import fr.esiea.glpoo.model.domain.Piece;

/**
 * 
 * Classe Dao qui va recuperer les donnes du fichier csv Piece
 * 
 * @author Joseph HE, Stefan ANGOSO, Antoine GUIDIS
 *
 */

public class OpenCsvPieceDao extends AbstractCsvPieceDao{
	
	private static final Logger LOGGER = Logger.getLogger(OpenCsvPieceDao.class);

	@Override
	protected void reloadPieces() {
		LOGGER.debug("reloadPieces");

		if (file == null) {
			throw new IllegalStateException("Le fichier est nul...");
		}

		try {
			final List<String[]> lignes = getLignesFromFile();
			pieces = new ArrayList<Piece>(lignes.size());
			pieceMapById = new HashMap<Integer, Piece>(lignes.size());
			for (String[] ligne : lignes) {
				final Piece piece = transformLigneToPiece(ligne);
				pieces.add(piece);
				pieceMapById.put(piece.getPiece_id(), piece);
			}
		} catch (Exception e) {
			LOGGER.error("Une erreur s'est produite...", e);
		}
		
	}
	
	private Piece transformLigneToPiece(final String[] values) throws Exception {
		LOGGER.debug("transformLigneToPiece");
		
		final Piece piece = new Piece(Integer.parseInt(values[1]),Integer.parseInt(values[2]),Integer.parseInt(values[3]),Integer.parseInt(values[4]),Integer.parseInt(values[5]));
		
		return piece;
	}

	@Override
	public void save(Piece[][] puzzle, File file) {
		LOGGER.debug("Sauvegarde des " + puzzle.length*puzzle[0].length + " pieces dans le fichier " + file.getName());
		try {
			CSVWriter writer = new CSVWriter(new FileWriter(file), ' ', '\0');
			writer.writeNext(new String[]{"#", "Pieces:" , "nom_fichier"});
			writer.writeNext(new String[]{"#", "Faces:" , "nom_fichier"});
			writer.writeNext(new String[]{"#", "P", "id_piece:" , "position_X" , "position_Y", "orientation(Nord/Est/Sud/Ouest)"});
			writer.writeNext(new String[]{"Pieces:", "pieces-01.csv" , "nom_fichier"});
			writer.writeNext(new String[]{"Faces:", "faces-01.csv" , "nom_fichier"});
			for(int i = 0 ; i < puzzle.length ; i++){
				for(int j = 0 ; j < puzzle[0].length; j++){
					String[] result = {"P",""+puzzle[i][j].getPiece_id(),""+i,""+j,puzzle[i][j].getOrientation().getCode()};
					writer.writeNext(result);
				}
			}
			writer.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

}
