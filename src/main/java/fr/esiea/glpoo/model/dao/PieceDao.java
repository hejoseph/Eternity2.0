package fr.esiea.glpoo.model.dao;

import java.util.List;

import fr.esiea.glpoo.model.domain.Piece;


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

	Piece getPieceById(int id);
	
}
