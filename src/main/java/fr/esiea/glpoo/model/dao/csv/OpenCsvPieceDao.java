package fr.esiea.glpoo.model.dao.csv;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import fr.esiea.glpoo.model.domain.Face;
import fr.esiea.glpoo.model.domain.FaceType;
import fr.esiea.glpoo.model.domain.Piece;

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
			
//			final String[] ligneEntete = lignes.remove(0);
//			transformEntetes(ligneEntete);
			for(String[] s : lignes){
				System.out.println(s[0]+ " " + s[1] + " " + s[2]);
			}
			pieces = new ArrayList<Piece>(lignes.size());
//			chienMapByNom = new HashMap<String, Chien>(lignes.size());
			for (String[] ligne : lignes) {
				final Piece piece = transformLigneToPiece(ligne);
				pieces.add(piece);

//				chienMapByNom.put(chien.getNom(), chien);
			}
		} catch (Exception e) {
			LOGGER.error("Une erreur s'est produite...", e);
		}
		
	}
	
	private Piece transformLigneToPiece(final String[] values) throws Exception {
		LOGGER.debug("transformLigneToChien");

		final Piece piece = new Piece(Integer.parseInt(values[1]),Integer.parseInt(values[2]),Integer.parseInt(values[3]),Integer.parseInt(values[4]),Integer.parseInt(values[5]));

		return piece;
	}

}
