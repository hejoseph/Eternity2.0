package fr.esiea.glpoo.model.service;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import fr.esiea.glpoo.model.dao.csv.CsvFaceDao;
import fr.esiea.glpoo.model.dao.csv.CsvPartieDao;
import fr.esiea.glpoo.model.dao.csv.OpenCsvFaceDao;
import fr.esiea.glpoo.model.dao.csv.OpenCsvPartieDao;
import fr.esiea.glpoo.model.domain.Face;
import fr.esiea.glpoo.model.domain.Partie;

public class PartieService {
private static final Logger LOGGER = Logger.getLogger(PartieService.class);
	
	private CsvPartieDao csvPartieDao;
	
	/**
	 * Instance de la classe, pour le singleton.
	 */
	private static PartieService instance;
	private String fileName;
	
	/**
	 * Cache
	 */
	private LoadingCache<String, List<Partie>> partiesCache;
	
	/**
	 * Constructeur prive.
	 */
	private PartieService() {
		LOGGER.debug("Constructeur");
		csvPartieDao = new OpenCsvPartieDao();
		
		final CacheLoader<String, List<Partie>> cacheLoader = new CacheLoader<String, List<Partie>>() {
			public List<Partie> load(final String key) {
				// Ici la key ne sert a rien
				LOGGER.debug("Mise en cache des donnees.");
				final File file = new File(fileName);
				csvPartieDao.init(file);
				return csvPartieDao.findAllParties();
			}
		};

		partiesCache = CacheBuilder.newBuilder() //
				.expireAfterWrite(10, TimeUnit.MINUTES) // Duree : 10 min.
				.build(cacheLoader);

		
	}




	/**
	 * Singleton classique, synchro, avec creation sur demande.
	 * 
	 * @return
	 */
	public static synchronized PartieService getInstance() {
		if (instance == null) {
			instance = new PartieService();
		}

		return instance;
	}
	
	/**
	 * 
	 * @param fileName
	 * @return
	 */
	public List<Partie> findAllParties(final String fileName) {
		this.fileName = fileName;

		try {
			return partiesCache.get("parties");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Partie loadPartieFromFile(String filename){
		return csvPartieDao.loadPiecesFromPartie(new File(filename));
	}
	
//	public Partie getPartieById(int id){
//		LOGGER.debug("get Face");
//		return csvPartieDao.getPartieById(id);
//	}
}
