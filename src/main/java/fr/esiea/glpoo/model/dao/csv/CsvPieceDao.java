package fr.esiea.glpoo.model.dao.csv;

import java.io.File;

import fr.esiea.glpoo.model.dao.PieceDao;

public interface CsvPieceDao extends PieceDao{

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
	
}
