package fr.esiea.glpoo.model.dao;

import java.util.List;

import fr.esiea.glpoo.model.domain.Piece;

/**
 * DAO pour la gestion des pieces.
 * 
 * @author Joseph HE, Stefan ANGOSO, Antoine GUIDIS
 *
 */

public interface PieceDao {
	
	/**
	 * Renvoie la liste complete des pieces.
	 * 
	 * REGLE RF001 : Renvoie la liste complete des pieces.
	 * 
	 * REGLE RF002 : Renvoie une liste vide s'il n'y a aucune piece.
	 * 
	 * @return
	 */
	List<Piece> findAllPieces();

	
	/**
	 * Recuperation d'une piece a partir de son identifiant
	 * @param id
	 * @return une piece
	 */
	Piece getPieceById(int id);
	
}
