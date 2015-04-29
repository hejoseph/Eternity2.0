package fr.esiea.glpoo.model.service;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import fr.esiea.glpoo.model.dao.csv.CsvFaceDao;
import fr.esiea.glpoo.model.dao.csv.OpenCsvFaceDao;
import fr.esiea.glpoo.model.domain.Face;
import fr.esiea.glpoo.model.domain.Piece;

public class FaceService {
	private static final Logger LOGGER = Logger.getLogger(FaceService.class);
	
	private CsvFaceDao csvFaceDao;
	
	private static FaceService instance;
	private String fileName;
	
	private LoadingCache<String, List<Face>> facesCache;
	
	/**
	 * Constructeur prive.
	 */
	private FaceService() {
		LOGGER.debug("Constructeur");
		csvFaceDao = new OpenCsvFaceDao();
		
		final CacheLoader<String, List<Face>> cacheLoader = new CacheLoader<String, List<Face>>() {
			public List<Face> load(final String key) {
				// Ici la key ne sert a rien
				LOGGER.debug("Mise en cache des donnees.");
				final File file = new File(fileName);
				csvFaceDao.init(file);
				return csvFaceDao.findAllFaces();
			}
		};

		facesCache = CacheBuilder.newBuilder() //
				.expireAfterWrite(10, TimeUnit.MINUTES) // Duree : 10 min.
				.build(cacheLoader);

		
	}




	/**
	 * Singleton classique, synchro, avec creation sur demande.
	 * 
	 * @return
	 */
	public static synchronized FaceService getInstance() {
		if (instance == null) {
			instance = new FaceService();
		}

		return instance;
	}
	
	
	public List<Face> findAllFaces(final String fileName) {
		this.fileName = fileName;

		try {
			return facesCache.get("faces");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Face getFaceById(int id){
		LOGGER.debug("get Face");
		return csvFaceDao.getFaceById(id);
	}
	
}
