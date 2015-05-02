package fr.esiea.glpoo.model.dao.csv;

import java.io.File;
import java.util.List;
import java.util.Map;

import fr.esiea.glpoo.model.dao.FaceDao;
import fr.esiea.glpoo.model.domain.Face;

/**
 * 
 * @author Joseph HE, Stefan ANGOSO, Antoine GUIDIS
 *
 */

public interface CsvFaceDao extends FaceDao{
	
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
	
//	public Map<Integer,String> getImg_names();

	

}
