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

public class OpenCsvPieceDao extends AbstractCsvPieceDao{
	
	private static final Logger LOGGER = Logger.getLogger(OpenCsvPieceDao.class);

	@Override
	protected void reloadPieces() {
		LOGGER.debug("reloadPieces");

		if (file == null) {
			throw new IllegalStateException("Le fichier est nul...");
		}

		try {
//			reloadFaces();
			
			final List<String[]> lignes = getLignesFromFile();
			
			for(String[] s : lignes){
				System.out.println(s[0]+ " " + s[1] + " " + s[2]);
			}
			pieces = new ArrayList<Piece>(lignes.size());
			pieceMapById = new HashMap<Integer, Piece>(lignes.size());
			for (String[] ligne : lignes) {
				final Piece piece = transformLigneToPiece(ligne);
				pieces.add(piece);
				LOGGER.debug("before put hashmap, id piece = "+piece.getPiece_id());
				pieceMapById.put(piece.getPiece_id(), piece);
			}
		} catch (Exception e) {
			LOGGER.error("Une erreur s'est produite...", e);
		}
		
	}
	
	private Piece transformLigneToPiece(final String[] values) throws Exception {
		LOGGER.debug("transformLigneToPiece");
		
		final Piece piece = new Piece(Integer.parseInt(values[1]),Integer.parseInt(values[2]),Integer.parseInt(values[3]),Integer.parseInt(values[4]),Integer.parseInt(values[5]));
//		List<Face> faces = new ArrayList<Face>();
//		for(int i=2;i<=5;i++){
//			int face_id = Integer.parseInt(values[i]);
//			LOGGER.debug("face id ="+face_id);
//			final Face face = transformIdtoFace(face_id);
//			face.setImg_name(dao.getImg_names().get(face_id));
//			LOGGER.debug("Image name ="+face.getImg_name());
//			faces.add(face);
//		}
		
//		piece.setFaces(faces);
		
		return piece;
	}
	
//	private void reloadFaces(){
//		dao = new OpenCsvFaceDao();
//		final File file = new File("src/test/resources/faces-01.csv");
//		dao.init(file);
//	}

//	private Face transformIdtoFace(int id) {
//		return dao.getFaceById(id);
//	}

}
