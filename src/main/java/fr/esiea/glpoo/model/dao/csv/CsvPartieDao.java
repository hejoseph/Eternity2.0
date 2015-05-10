package fr.esiea.glpoo.model.dao.csv;

import java.io.File;

import fr.esiea.glpoo.model.dao.PartieDao;
import fr.esiea.glpoo.model.domain.Partie;

public interface CsvPartieDao extends PartieDao{
	
	/**
	 * Initialisation du DAO.
	 * 
	 * @param file
	 * @return 
	 */
	void init(File file);

	/**
	 * Gets the CSV file used.
	 * 
	 * @return
	 */
	File getFile();
	
	Partie loadPiecesFromPartie(File file);
	
}
