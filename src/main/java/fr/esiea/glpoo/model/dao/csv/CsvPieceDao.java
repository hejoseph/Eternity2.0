package fr.esiea.glpoo.model.dao.csv;

import java.io.File;

import fr.esiea.glpoo.model.dao.PieceDao;
import fr.esiea.glpoo.model.domain.Piece;

/**
 * 
 * DAO pour la gestion des pieces.
 * 
 * @author Joseph HE, Stefan ANGOSO, Antoine GUIDIS
 *
 */

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

	void save(Piece[][] puzzle, File file);
	
	
	
}
