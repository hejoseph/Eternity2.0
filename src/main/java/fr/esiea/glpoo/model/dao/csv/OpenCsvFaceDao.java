package fr.esiea.glpoo.model.dao.csv;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import fr.esiea.glpoo.model.domain.Face;
import fr.esiea.glpoo.model.domain.FaceType;
import au.com.bytecode.opencsv.CSVReader;

public class OpenCsvFaceDao extends AbstractCsvFaceDao{
	
	private static final Logger LOGGER = Logger.getLogger(OpenCsvFaceDao.class);
	@Override
	protected void reloadFaces() {
		LOGGER.debug("reloadFaces");

		if (file == null) {
			throw new IllegalStateException("Le fichier est nul...");
		}

		try {
			final List<String[]> lignes = getLignesFromFile();
			
//			final String[] ligneEntete = lignes.remove(0);
//			transformEntetes(ligneEntete);
			for(String[] s : lignes){
				String msg ="";
				for(String value : s){
					msg += value + " ";
				}
				LOGGER.debug(msg);
			}
			faces = new ArrayList<Face>(lignes.size());
			faceMapById = new HashMap<Integer, Face>(lignes.size());
			for (String[] ligne : lignes) {
				String msg ="";
				for(String value : ligne){
					msg += value + " ";
				}
				LOGGER.debug(msg);
				final Face face = transformLigneToFace(ligne);
				LOGGER.debug("color = "+face.getBg_color());
				faces.add(face);
				
				faceMapById.put(face.getFace_id(), face);
			}
		} catch (Exception e) {
			LOGGER.error("Une erreur s'est produite...", e);
		}

	}
	
	protected void genListImgNames() {
		LOGGER.debug("inside gen image names");
//		img_names = new HashMap<Integer,String>(faces.size());
		for(Face face : faces){
			String img_name ="";
			img_name += face.getBg_color();
			if(face.getForm() != null){
				img_name += "_"+face.getForm();
			}
			if(face.getForm_color() != null){
				img_name += "_"+face.getForm_color();
			}
			img_name += ".png";
			face.setImg_name(img_name);
		}
	}
	
	private Face transformLigneToFace(final String[] values) throws Exception {
		LOGGER.debug("transformLigneToFace");

		final Face face = new Face();
		
		final String tempFaceType = values[0];
		final FaceType ft = FaceType.valueOfByCode(tempFaceType);
		face.setFace_type(ft);
		
		face.setFace_id(Integer.parseInt(values[1]));
		LOGGER.debug("WARNING before Set: "+values[2]);
		face.setBg_color(values[2]);
		LOGGER.debug("WARNING After Set: "+face.getBg_color());
		if(values.length == 3){
			return face;
		}
		
		face.setForm(values[3]);
		face.setForm_color(values[4]);

		return face;
	}
}
