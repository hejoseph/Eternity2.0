package fr.esiea.glpoo.model.dao.csv;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import fr.esiea.glpoo.model.domain.Face;
import fr.esiea.glpoo.model.domain.FaceType;
import au.com.bytecode.opencsv.CSVReader;

public class OpenCsvFaceDao extends AbstractCsvFaceDao{
	
	private static final Logger LOGGER = Logger.getLogger(OpenCsvFaceDao.class);

	private final static char SEPARATOR = ' ';

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
	
	private List<String[]> getLignesFromFile() {
		LOGGER.debug("getLignesFromFile");

		if (file == null) {
			throw new IllegalStateException("Le fichier est nul...");
		}

		final List<String[]> lignes = new ArrayList<String[]>();

		try {
			final FileReader fr = new FileReader(file);
			final CSVReader csvReader = new CSVReader(fr, SEPARATOR);

			String[] nextLine = null;
			while ((nextLine = csvReader.readNext()) != null) {
				int size = nextLine.length;

				// ligne vide
				if (size == 0) {
					continue;
				}
				String debut = nextLine[0].trim();
				if (debut.isEmpty() && size == 1) {
					continue;
				}

				// ligne de commentaire
				if (debut.startsWith("#")) {
					continue;
				}
				lignes.add(nextLine);
			}

		} catch (Exception e) {
			LOGGER.error("aie aie aie", e);
		}

		return lignes;
	}
	
	private Face transformLigneToFace(final String[] values) throws Exception {
		LOGGER.debug("transformLigneToChien");

		final Face face = new Face();
		
		final String tempFaceType = values[0];
		final FaceType ft = FaceType.valueOfByCode(tempFaceType);
		face.setFace_type(ft);
		
		face.setId_face(Integer.parseInt(values[1]));
		face.setCouleur_fond(values[2]);
		if(values.length == 3){
			return face;
		}
		
		face.setForme(values[3]);
		face.setCouleur_forme(values[4]);

		return face;
	}
}
