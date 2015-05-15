package fr.esiea.glpoo.model.dao.csv;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import au.com.bytecode.opencsv.CSVWriter;
import fr.esiea.glpoo.model.domain.Partie;
import fr.esiea.glpoo.model.domain.Score;

public class OpenCsvScoreDao extends AbstractCsvDao implements CsvScoreDao{

	private static final Logger LOGGER = Logger.getLogger(OpenCsvPieceDao.class);
	
	private List<Score> scores;
	
	/**
	 * Return : List des objets "Partie" du fichier csv parties
	 */
	@Override
	public List<Score> findAllScores() {
		LOGGER.debug("findAllScores");

		if (scores == null) {
			throw new IllegalStateException("La liste n'a pas encore ete initialisee...");
		}

		return scores;
	}
	
	/**
	 * Charge la liste des parties
	 */
	private void reloadScores() {
		LOGGER.debug("reloadScores");

		if (file == null) {
			throw new IllegalStateException("Le fichier est nul...");
		}

		try {
			final List<String[]> lignes = getLignesFromFile();
			scores = new ArrayList<Score>(lignes.size());
			for (String[] ligne : lignes) {
				final Score score = transformLigneToScore(ligne);
				scores.add(score);
			}
		} catch (Exception e) {
			LOGGER.error("Une erreur s'est produite...", e);
		}
		
	}

	private Score transformLigneToScore(String[] ligne) {
		Score score = new Score(Integer.parseInt(ligne[0]),ligne[1], ligne[2], Integer.parseInt(ligne[3]));
		return score;
	}
	
	@Override
	public File getFile() {
		return file;
	}

	@Override
	public void save(Score score, File file) {
		LOGGER.debug("Sauvegarde du score");
		try {
			CSVWriter writer = new CSVWriter(new FileWriter(file), ' ', '\0');
			
			for(Score s : scores){
				String[] result = {""+s.getScore_id(), s.getNickname(), s.getLevel(), ""+s.getTime()}; 
				writer.writeNext(result);
			}
			String[] result = {""+score.getScore_id(), score.getNickname(), score.getLevel(), ""+score.getTime()};
			writer.writeNext(result);
			writer.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

	@Override
	public void init(File file) {
		this.file = file;
		
		reloadScores();
	}
	
	
}
