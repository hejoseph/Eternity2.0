package fr.esiea.glpoo.model.dao;

import java.io.File;
import java.util.List;

import fr.esiea.glpoo.model.domain.Face;

/**
 * 
 * @author Joseph HE, Stefan ANGOSO, Antoine GUIDIS
 *
 */

public interface FaceDao {

	/**
	 * Renvoie la liste complete des Faces.
	 * 
	 * REGLE RF001 : Renvoie la liste complete des faces.
	 * 
	 * REGLE RF002 : Renvoie une liste vide s'il n'y a aucune face.
	 * 
	 * @return
	 */
	public List<Face> findAllFaces();
	
	Face getFaceById(int i);
	
}
