package fr.esiea.glpoo.model.dao;

import java.util.List;

import fr.esiea.glpoo.model.domain.Score;

public interface ScoreDao {

	List<Score> findAllScores();
	
}
