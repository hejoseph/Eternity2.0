package fr.esiea.glpoo.model.dao.csv;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;


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

}
