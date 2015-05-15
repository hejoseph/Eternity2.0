package fr.esiea.glpoo.model.dao.csv;

import java.io.File;

import fr.esiea.glpoo.model.dao.ScoreDao;
import fr.esiea.glpoo.model.domain.Piece;
import fr.esiea.glpoo.model.domain.Score;

public interface CsvScoreDao extends ScoreDao{

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

	void save(Score score, File file);
	
}
