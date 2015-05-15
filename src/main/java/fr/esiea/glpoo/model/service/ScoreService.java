package fr.esiea.glpoo.model.service;

import java.io.File;
import java.util.List;

import org.apache.log4j.Logger;

import com.google.common.cache.LoadingCache;

import fr.esiea.glpoo.model.dao.csv.CsvPartieDao;
import fr.esiea.glpoo.model.dao.csv.CsvScoreDao;
import fr.esiea.glpoo.model.dao.csv.OpenCsvPartieDao;
import fr.esiea.glpoo.model.dao.csv.OpenCsvScoreDao;
import fr.esiea.glpoo.model.domain.Partie;
import fr.esiea.glpoo.model.domain.Score;

public class ScoreService {
private static final Logger LOGGER = Logger.getLogger(PartieService.class);
	
	private CsvScoreDao csvScoreDao;
	
	/**
	 * Instance de la classe, pour le singleton.
	 */
	private static ScoreService instance;
	private String fileName;
	
	/**
	 * Cache
	 */
//	private LoadingCache<String, List<Partie>> partiesCache;
//	
	/**
	 * Constructeur prive.
	 */
	private ScoreService() {
		LOGGER.debug("Constructeur");
		csvScoreDao = new OpenCsvScoreDao();
	}




	/**
	 * Singleton classique, synchro, avec creation sur demande.
	 * 
	 * @return
	 */
	public static synchronized ScoreService getInstance() {
		if (instance == null) {
			instance = new ScoreService();
		}

		return instance;
	}
	
	/**
	 * 
	 * @param fileName
	 * @return
	 */
	public List<Score> findAllScores(final String fileName) {
		this.fileName = fileName;

		try {
			final File file = new File(fileName);
			csvScoreDao.init(file);
			return csvScoreDao.findAllScores();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
//	public Partie loadPartieFromFile(String filename){
//		return csvPartieDao.loadPiecesFromPartie(new File(filename));
//	}
	
//	public Partie getPartieById(int id){
//		LOGGER.debug("get Face");
//		return csvPartieDao.getPartieById(id);
//	}
	
	public void save(Score score, String filename){
		final String target = "src/main/resources/scores.csv";
		try{
		final File file = new File(filename);
		csvScoreDao.init(file);
		} catch (Exception e){
			e.printStackTrace();
		}
		final File file2 = new File(target);
		csvScoreDao.save(score, file2);
	}
}
