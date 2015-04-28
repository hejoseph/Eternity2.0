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
				System.out.println(s[0]+ " " + s[1] + " " + s[2]);
			}
			faces = new ArrayList<Face>(lignes.size());
//			chienMapByNom = new HashMap<String, Chien>(lignes.size());
			for (String[] ligne : lignes) {
				final Face face = transformLigneToFace(ligne);
				
				faces.add(face);
				
//				chienMapByNom.put(chien.getNom(), chien);
			}
		} catch (Exception e) {
			LOGGER.error("Une erreur s'est produite...", e);
		}

	}
	
	protected void genListImgNames() {
		LOGGER.debug("inside gen image names");
		img_names = new HashMap<Integer,String>(faces.size());
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
			img_names.put(face.getFace_id(),img_name);
		}
	}
	
	private Face transformLigneToFace(final String[] values) throws Exception {
		LOGGER.debug("transformLigneToFace");

		final Face face = new Face();
		
		final String tempFaceType = values[0];
		final FaceType ft = FaceType.valueOfByCode(tempFaceType);
		face.setFace_type(ft);
		
		face.setFace_id(Integer.parseInt(values[1]));
		face.setBg_color(values[2]);
		if(values.length == 3){
			return face;
		}
		
		face.setForm(values[3]);
		face.setBg_color(values[4]);

		return face;
	}
}
