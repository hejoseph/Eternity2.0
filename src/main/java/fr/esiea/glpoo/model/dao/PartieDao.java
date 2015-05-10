package fr.esiea.glpoo.model.dao;

import java.io.File;
import java.util.List;

import fr.esiea.glpoo.model.domain.Partie;
import fr.esiea.glpoo.model.domain.Piece;

public interface PartieDao {

	List<Partie> findAllParties();
	
}
