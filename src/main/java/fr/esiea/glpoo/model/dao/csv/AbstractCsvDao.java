package fr.esiea.glpoo.model.dao.csv;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import au.com.bytecode.opencsv.CSVReader;

/**
 * 
 * @author Joseph HE, Stefan ANGOSO, Antoine GUIDIS
 *
 */

/*Class qui traite les fichiers csv*/
public abstract class AbstractCsvDao {
	private static final Logger LOGGER = Logger.getLogger(OpenCsvFaceDao.class);
	
	private final static char SEPARATOR = ' ';
	
	protected File file;
	
	protected List<String[]> getLignesFromFile() {
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
	
}
