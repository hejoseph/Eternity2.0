package fr.esiea.glpoo.model.service;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import fr.esiea.glpoo.model.dao.csv.CsvPieceDao;
import fr.esiea.glpoo.model.dao.csv.OpenCsvPieceDao;
import fr.esiea.glpoo.model.domain.Face;
import fr.esiea.glpoo.model.domain.Piece;

public class PieceService {
	private static final Logger LOGGER = Logger.getLogger(PieceService.class);
	
	private CsvPieceDao csvPieceDao;
	
	private static PieceService instance;
	private String fileName;
	
	private LoadingCache<String, List<Piece>> piecesCache;
	
	/**
	 * Constructeur prive.
	 */
	private PieceService() {
		LOGGER.debug("Constructeur");
		csvPieceDao = new OpenCsvPieceDao();
		
		final CacheLoader<String, List<Piece>> cacheLoader = new CacheLoader<String, List<Piece>>() {
			public List<Piece> load(final String key) {
				// Ici la key ne sert a rien
				LOGGER.debug("Mise en cache des donnees.");
				final File file = new File(fileName);
				csvPieceDao.init(file);
				return csvPieceDao.findAllPieces();
			}
		};

		piecesCache = CacheBuilder.newBuilder() //
				.expireAfterWrite(10, TimeUnit.MINUTES) // Duree : 10 min.
				.build(cacheLoader);

		
	}




	/**
	 * Singleton classique, synchro, avec creation sur demande.
	 * 
	 * @return
	 */
	public static synchronized PieceService getInstance() {
		if (instance == null) {
			instance = new PieceService();
		}

		return instance;
	}
	
	
	public List<Piece> findAllPieces(final String fileName) {
		this.fileName = fileName;

		try {
			return piecesCache.get("pieces");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
